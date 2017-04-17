package financeiro.projeto.domain.titulo;

import java.io.FileOutputStream;
import java.io.PrintWriter;
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
import financeiro.projeto.domain.utilitarios.TipoConta;

public class TituloRelatorio {
	private Logger logger = LoggerFactory.getLogger(TituloRelatorio.class);
	
	public Resposta gerarRelatorio(List<Titulo> titulos, TipoConta tipoConta, String tipoRelatorio, String nomeArquivo){
		try{
			if(titulos != null && !titulos.isEmpty()){
				String home = System.getProperty("user.home");
				final String ENDERECO = String.format("%s/Downloads/%s", home, nomeArquivo);
				Document document = new Document();
				PrintWriter writer = new PrintWriter(ENDERECO, "UTF-8");
				writer.close();
		        PdfWriter.getInstance(document, new FileOutputStream(ENDERECO));
		        HashMap<String, Phrase> propriedadesDocumento = obterPropriedadesRelatorio(tipoConta, tipoRelatorio);
		        document.open();
		        document.add(propriedadesDocumento.get("titulo"));
		        document.add(Chunk.NEWLINE);
		        document.add(Chunk.NEWLINE);
		        for(Titulo titulo : titulos){
		            document.add(propriedadesDocumento.get("colunaNumero"));
		            document.add(new Phrase(Long.toString(titulo.getNumeroTitulo())));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaPessoa"));
		            document.add(new Phrase(titulo.getDadosPessoa().getNomePessoa()));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaDataCriacao"));
		            document.add(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(titulo.getDataCriacaoTitulo())));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaDataVencimento"));
		            document.add(new Phrase(new SimpleDateFormat("dd/MM/yyyy").format(titulo.getDataVencimentoTitulo())));
		            document.add(Chunk.NEWLINE);
		            document.add(propriedadesDocumento.get("colunaValor"));
		            document.add(new Phrase(titulo.getValorTitulo().toString()));
		            document.add(Chunk.NEWLINE);
		            document.add(Chunk.NEWLINE);
		        }
		        document.add(new Phrase(String.format("Total de titulos listados: %d", titulos.size())));
		        document.close();
		        return new Resposta(true, "Relatório gerado com sucesso! Verifique a pasta Downloads para encontrar o arquivo!");
			}else{
				return new Resposta(false, "Não há registros suficientes para gerar relatório!");
			}
		}catch(Exception ex){
			logger.error("Erro ao gerar relatório: Causa:{}", ex.getMessage());
			return new Resposta(false, "Erro ao gerar relatório!");
		}	
	}
	
	private String converterTipoConta(TipoConta tipoConta){
		switch(tipoConta){
			case CONTA_PAGAR:
				return "pagar";
			case CONTA_RECEBER:
				return "receber";
		}
		return null;
	}
	
	private HashMap<String, Phrase> obterPropriedadesRelatorio(TipoConta tipoConta, String tipoRelatorio){
		HashMap<String, Phrase> propriedadesDocumento = new HashMap<String, Phrase>(); 
		Font fonteTitulo = new Font(FontFamily.UNDEFINED, 14, Font.BOLD);
        Font fonteColunas = new Font(FontFamily.UNDEFINED, 12, Font.BOLD);
        String conta = converterTipoConta(tipoConta);
        String textTitulo = String.format("Relatório de contas a %s cadastradas %s", conta, tipoRelatorio);
        propriedadesDocumento.put("titulo", new Phrase(new Chunk(textTitulo, fonteTitulo)));
        propriedadesDocumento.put("colunaNumero", new Phrase(new Chunk("Número do título: ", fonteColunas)));
        propriedadesDocumento.put("colunaPessoa", new Phrase(new Chunk("Pessoa vinculada: ", fonteColunas)));
        propriedadesDocumento.put("colunaDataCriacao", new Phrase(new Chunk("Data de criacao: ", fonteColunas)));
        propriedadesDocumento.put("colunaDataVencimento", new Phrase(new Chunk("Data de vencimento: ", fonteColunas)));
        propriedadesDocumento.put("colunaValor", new Phrase(new Chunk("Valor(R$): ", fonteColunas)));
		return propriedadesDocumento;
	}
}
