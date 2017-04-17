package financeiro.projeto.domain.pessoa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financeiro.projeto.domain.utilitarios.FiltroRelatorio;
import financeiro.projeto.domain.utilitarios.Resposta;

@Service
public class PessoaBs {
	private Logger logger = LoggerFactory.getLogger(PessoaBs.class);
	
	@Autowired
	private PessoaDao dao;
	
	public List<Pessoa> listarPessoas(){
		return dao.listarPessoas();
	}
	
	public Resposta incluirPessoa(Pessoa pessoa){
		try{
			dao.incluirPessoa(pessoa);
			return new Resposta(true, "Pessoa cadastrada com sucesso!");
		}catch(Exception ex){
			logger.error("Erro ao incluir Pessoa! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao incluir Pessoa!");
		}
		
	}
	
	public Resposta excluirPessoa(String cpfCnpjPessoa){
		try{
			int resposta = dao.excluirPessoa(cpfCnpjPessoa);
			if(resposta != 0){
				return new Resposta(true, "Pessoa excluida com sucesso!");
			}
			return new Resposta(false, "Erro ao excluir pessoa!");
		}catch(Exception ex){
			logger.error("Erro ao excluir Pessoa! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao excluir Pessoa!");
		}
	}
	
	public Resposta atualizarPessoa(Pessoa pessoa){
		try{
			int resposta = dao.atualizarPessoa(pessoa);
			if(resposta != 0){
				return new Resposta(true, "Pessoa atualizada com sucesso!");
			}
			return new Resposta(false, "Erro ao atualizar Pessoa!");
		}catch(Exception ex){
			logger.error("Erro ao atualizar Pessoa! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao atualizar Pessoa!");
		}	
	}
	
	public List<Pessoa> obterRelatorioPessoas(FiltroRelatorio filtro){
		if(filtro != null && filtro.getListagem() != null){
			switch(filtro.getListagem()){
				case "Todas":
					return dao.obterRelatorioPessoas();
				case "Possuir títulos cadastrados":
					return dao.obterRelatorioPessoasComTitulo();
				case "Não possuir títulos cadastrados":
					return dao.obterRelatorioPessoasSemTitulo();
			}
		}
		return null;
	}
	
	public Resposta gerarRelatorioPessoas(FiltroRelatorio filtro){
		if(filtro != null && filtro.getListagem() != null){
			PessoaRelatorio relatorio = new PessoaRelatorio();
			List<Pessoa> pessoas;
			switch(filtro.getListagem()){
				case "Todas":
					pessoas = dao.obterRelatorioPessoas();
					return relatorio.gerarRelatorio(pessoas, "(Todas)", "relatorio-pessoas-cadastradas.pdf");
				case "Possuir títulos cadastrados":
					pessoas = dao.obterRelatorioPessoasComTitulo();
					return relatorio.gerarRelatorio(pessoas, " com títulos cadastrados", "relatorio-pessoas-com-titulo.pdf");
				case "Não possuir títulos cadastrados":
					pessoas = dao.obterRelatorioPessoasSemTitulo();
					return relatorio.gerarRelatorio(pessoas, " sem títulos cadastrados", "relatorio-pessoas-sem-titulo.pdf");
			}
		}
		return new Resposta(false, "Erro ao gerar relatório!");
	}
}
