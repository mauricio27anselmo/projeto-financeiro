package financeiro.projeto.domain.titulo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import financeiro.projeto.domain.pessoa.PessoaDao;
import financeiro.projeto.domain.utilitarios.FiltroRelatorio;
import financeiro.projeto.domain.utilitarios.Resposta;
import financeiro.projeto.domain.utilitarios.TipoConta;

@Service
public class TituloBs {
	Logger logger = LoggerFactory.getLogger(TituloBs.class);
	
	@Autowired
	private TituloDao tituloDao;
	
	@Autowired
	private PessoaDao pessoaDao;
	
	public Resposta cadastrarContaPagar(Titulo titulo){
		try{
			pessoaDao.incluirPessoa(titulo.getDadosPessoa());
			titulo.setDataCriacaoTitulo(Date.valueOf(LocalDate.now()));
			titulo.setTipoContaTitulo(TipoConta.CONTA_PAGAR.toString());
			tituloDao.incluirTitulo(titulo);
			return new Resposta(true, "Conta a pagar cadastrada com sucesso!");
		}catch(Exception ex){
			logger.error("Erro ao cadastrar conta a pagar! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao cadastrar conta a pagar!");
		}
		 
	}
	
	public List<Titulo> consultarContasPagar(){
		return tituloDao.consultarContasPagar();
	}
	
	public Resposta excluirContaPagar(long numeroTitulo){
		try{
			int resposta = tituloDao.excluirContaPagar(numeroTitulo);
			if(resposta != 0){
				return new Resposta(true, "Conta a pagar excluida com sucesso!");
			}
			return new Resposta(false, "Erro ao excluir conta a pagar! Não foi encontrada conta a pagar ou titulo está pago");
		}catch(Exception ex){
			logger.error("Erro ao excluir conta a pagar! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao excluir conta a pagar!");
		}
	}
	
	public Resposta cadastrarContaReceber(Titulo titulo){
		try{
			pessoaDao.incluirPessoa(titulo.getDadosPessoa());
			titulo.setDataCriacaoTitulo(Date.valueOf(LocalDate.now()));
			titulo.setTipoContaTitulo(TipoConta.CONTA_RECEBER.toString());
			tituloDao.incluirTitulo(titulo);
			return new Resposta(true, "Conta a receber cadastrada com sucesso!");
		}catch(Exception ex){
			logger.error("Erro ao cadastrar conta a receber! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao cadastrar conta a receber!");
		}
		 
	}
	
	public List<Titulo> consultarContasReceber(){
		return tituloDao.consultarContasReceber();
	}
	
	public Resposta excluirContaReceber(long numeroTitulo){
		try{
			int resposta = tituloDao.excluirContaReceber(numeroTitulo);
			if(resposta != 0){
				return new Resposta(true, "Conta a receber excluida com sucesso!");
			}
			return new Resposta(false, "Erro ao excluir conta a receber! Não foi encontrada conta a receber ou titulo está pago");
		}catch(Exception ex){
			logger.error("Erro ao excluir conta a receber! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao excluir conta a receber!");
		}
	}
	
	public List<Titulo> consultarContasPagarPendente(){
		return tituloDao.consultarContasPagarPendente();
	}
	
	public List<Titulo> consultarContasReceberPendente(){
		return tituloDao.consultarContasReceberPendente();
	}
	
	public Resposta gravarBaixa(Titulo titulo){
		try{
			int resposta = tituloDao.gravarBaixa(titulo);
			if(resposta != 0){
				return new Resposta(true, "Baixa gravada com sucesso!");
			}
			return new Resposta(false, "Erro ao gravar baixa! O valor pago não foi informado");
		}catch(Exception ex){
			logger.error("Erro ao gravar baixa! Causa: {}", ex.getMessage());
			return new Resposta(false, "Erro ao gravar baixa!");
		}
	}
	
	public List<Titulo> obterRelatorioContasReceber(FiltroRelatorio filtro){
		if(filtro != null && filtro.getListagem() != null && filtro.getOrdenacao() != null){
			String ordenacao = converterOrdenacao(filtro.getOrdenacao());
			if(ordenacao != null){
				switch(filtro.getListagem()){
					case "Todas":
						return tituloDao.obterRelatorioContasReceberTodas(ordenacao);
					case "Somente abertas":
						return tituloDao.obterRelatorioContasReceberPendentes(ordenacao);
					case "Somente pagas":
						return tituloDao.obterRelatorioContasReceberFinalizadas(ordenacao);
				}
			}
			return null;
		}
		return null;
	}
	
	public Resposta gerarRelatorioContasReceber(FiltroRelatorio filtro){
		if(filtro != null && filtro.getListagem() != null && filtro.getOrdenacao() != null){
			String ordenacao = converterOrdenacao(filtro.getOrdenacao());
			if(ordenacao != null){
				List<Titulo> titulos;
				TituloRelatorio relatorio = new TituloRelatorio();
				switch(filtro.getListagem()){
					case "Todas":
						titulos = tituloDao.obterRelatorioContasReceberTodas(ordenacao);
						return relatorio.gerarRelatorio(titulos, TipoConta.CONTA_RECEBER, "(Todas)", "relatorio-conta-receber-todas.pdf");
					case "Somente abertas":
						titulos = tituloDao.obterRelatorioContasReceberPendentes(ordenacao);
						return relatorio.gerarRelatorio(titulos, TipoConta.CONTA_RECEBER, "não pagas", "relatorio-conta-receber-pendentes.pdf");
					case "Somente pagas":
						titulos = tituloDao.obterRelatorioContasReceberFinalizadas(ordenacao);
						return relatorio.gerarRelatorio(titulos, TipoConta.CONTA_RECEBER, "pagas", "relatorio-conta-receber-pagas.pdf");
				}
			}
			return new Resposta(false, "Erro ao gerar relatório!");
		}
		return new Resposta(false, "Erro ao gerar relatório!");
	}
	
	public List<Titulo> obterRelatorioContasPagar(FiltroRelatorio filtro){
		if(filtro != null && filtro.getListagem() != null && filtro.getOrdenacao() != null){
			String ordenacao = converterOrdenacao(filtro.getOrdenacao());
			if(ordenacao != null){
				switch(filtro.getListagem()){
					case "Todas":
						return tituloDao.obterRelatorioContasPagarTodas(ordenacao);
					case "Somente abertas":
						return tituloDao.obterRelatorioContasPagarPendentes(ordenacao);
					case "Somente pagas":
						return tituloDao.obterRelatorioContasPagarFinalizadas(ordenacao);
				}
			}
			return null;
		}
		return null;
	}
	
	public Resposta gerarRelatorioContasPagar(FiltroRelatorio filtro){
		if(filtro != null && filtro.getListagem() != null && filtro.getOrdenacao() != null){
			String ordenacao = converterOrdenacao(filtro.getOrdenacao());
			if(ordenacao != null){
				List<Titulo> titulos;
				TituloRelatorio relatorio = new TituloRelatorio();
				switch(filtro.getListagem()){
					case "Todas":
						titulos = tituloDao.obterRelatorioContasPagarTodas(ordenacao);
						return relatorio.gerarRelatorio(titulos, TipoConta.CONTA_PAGAR, "(Todas)", "relatorio-conta-pagar-todas.pdf");
					case "Somente abertas":
						titulos = tituloDao.obterRelatorioContasPagarPendentes(ordenacao);
						return relatorio.gerarRelatorio(titulos, TipoConta.CONTA_PAGAR, "não pagas", "relatorio-conta-pagar-pendentes.pdf");
					case "Somente pagas":
						titulos = tituloDao.obterRelatorioContasPagarFinalizadas(ordenacao);
						return relatorio.gerarRelatorio(titulos, TipoConta.CONTA_PAGAR, "pagas", "relatorio-conta-pagar-pagas.pdf");
				}
			}
			return new Resposta(false, "Erro ao gerar relatório!");
		}
		return new Resposta(false, "Erro ao gerar relatório!");
	}
	
	private String converterOrdenacao(String ordenacao){
		switch(ordenacao){
			case "Data de criação":
				return "tit_data_criacao";
			case "Data de vencimento":
				return "tit_data_vencimento";
			case "Valor do título":
				return "tit_valor";
			default:
				return null;
		}
	}
}	
	

