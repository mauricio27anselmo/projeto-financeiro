package financeiro.projeto.domain.pessoa;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import financeiro.projeto.domain.utilitarios.Resposta;

public class PessoaRelatorio {
	private Logger logger = LoggerFactory.getLogger(PessoaRelatorio.class);
	
	public Resposta gerarRelatorio(List<Pessoa> pessoas, String tipoRelatorio, String nomeArquivo){
		try{
			if(pessoas != null && !pessoas.isEmpty()){
				String home = System.getProperty("user.home");
				final String ENDERECO = String.format("%s/Downloads/%s", home, nomeArquivo);
				Document document = new Document();
				PrintWriter writer = new PrintWriter(ENDERECO, "UTF-8");
				writer.close();
		        PdfWriter.getInstance(document, new FileOutputStream(ENDERECO));
		        HashMap<String, Phrase> propriedadesDocumento = obterPropriedadesRelatorio(tipoRelatorio);
		        document.open();
		        document.add(propriedadesDocumento.get("titulo"));
		        document.add(Chunk.NEWLINE);
		        document.add(Chunk.NEWLINE);
		        verificarCamposNulos(pessoas);
		        for(Pessoa pessoa : pessoas){
		            document.add(propriedadesDocumento.get("colunaNome"));
		            document.add(new Phrase(pessoa.getNomePessoa()));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaCpfCnpj"));
		            document.add(new Phrase(pessoa.getCpfCnpjPessoa()));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaDataNascimento"));
		            document.add(new Phrase(formatarDataNascimento(pessoa)));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaCidade"));
		            document.add(new Phrase(pessoa.getCidadePessoa()));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaEstado"));
		            document.add(new Phrase(pessoa.getEstadoPessoa()));
		            document.add(Chunk.NEWLINE);
		            document.add(Chunk.NEWLINE);
		        }
		        document.add(new Phrase(String.format("Total de pessoas listadas: %d", pessoas.size())));
		        document.close();
		        return new Resposta(true, "Relatório gerado com sucesso! Verifique a pasta Downloads para encontrar o arquivo!");
			}else{
				return new Resposta(false, "Não há registros suficientes para gerar relatório!");
			}
		}catch(Exception ex){
			logger.error("Erro ao gerar relatório: Causa:{}", ex.getMessage());
			return new Resposta(false, "Erro ao gerar relatório");
		}	
	}
	
	private HashMap<String, Phrase> obterPropriedadesRelatorio(String tipoRelatorio){
		HashMap<String, Phrase> propriedadesDocumento = new HashMap<String, Phrase>(); 
		Font fonteTitulo = new Font(FontFamily.UNDEFINED, 14, Font.BOLD);
        Font fonteColunas = new Font(FontFamily.UNDEFINED, 12, Font.BOLD);
        String textTitulo = String.format("Relatório de pessoas cadastradas %s", tipoRelatorio);
        propriedadesDocumento.put("titulo", new Phrase(new Chunk(textTitulo, fonteTitulo)));
        propriedadesDocumento.put("colunaNome", new Phrase(new Chunk("Nome: ", fonteColunas)));
        propriedadesDocumento.put("colunaCpfCnpj", new Phrase(new Chunk("CPF/CNPJ: ", fonteColunas)));
        propriedadesDocumento.put("colunaDataNascimento", new Phrase(new Chunk("Data de Nascimento: ", fonteColunas)));
        propriedadesDocumento.put("colunaCidade", new Phrase(new Chunk("Cidade: ", fonteColunas)));
        propriedadesDocumento.put("colunaEstado", new Phrase(new Chunk("Estado: ", fonteColunas)));
		return propriedadesDocumento;
	}
	
	private void verificarCamposNulos(List<Pessoa> pessoas){
		for(Pessoa pessoa: pessoas){
			String nome = pessoa.getNomePessoa() != null ? pessoa.getNomePessoa() : "Não informado!";
			pessoa.setNomePessoa(nome);
			String cpfCnpj = pessoa.getCpfCnpjPessoa() != null ? pessoa.getCpfCnpjPessoa() : "Não informado!";
			pessoa.setCpfCnpjPessoa(cpfCnpj);
			String cidade = pessoa.getCidadePessoa() != null ? pessoa.getCidadePessoa() : "Não informado!";
			pessoa.setCidadePessoa(cidade);
			String estado = pessoa.getEstadoPessoa() != null ? pessoa.getEstadoPessoa() : "Não informado!";
			pessoa.setEstadoPessoa(estado);
		}
	}
	
	private String formatarDataNascimento(Pessoa pessoa){
		return pessoa.getDataNascimentoPessoa() != null ? 
				new SimpleDateFormat("dd/MM/yyyy").format(pessoa.getDataNascimentoPessoa()): "Não informado!";
	}
}
