package financeiro.projeto.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import financeiro.projeto.domain.titulo.Titulo;
import financeiro.projeto.domain.titulo.TituloBs;
import financeiro.projeto.domain.utilitarios.FiltroRelatorio;
import financeiro.projeto.domain.utilitarios.Resposta;

@RestController(value = "/titulo")

public class TituloRest {
	@Autowired
	private TituloBs tituloBs;
	
	@RequestMapping(method = RequestMethod.POST, value="/conta_pagar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta cadastrarContaPagar(@RequestBody Titulo titulo){
		return tituloBs.cadastrarContaPagar(titulo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/conta_pagar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Titulo> consultarContasPagar(){
		return tituloBs.consultarContasPagar();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/conta_pagar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta excluirContaPagar(@RequestParam("titulo")long numeroTitulo){
		return tituloBs.excluirContaPagar(numeroTitulo);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/conta_receber", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta cadastrarContaReceber(@RequestBody Titulo titulo){
		return tituloBs.cadastrarContaReceber(titulo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/conta_receber", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Titulo> consultarContasReceber(){
		return tituloBs.consultarContasReceber();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/conta_receber", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta excluirContaReceber(@RequestParam("titulo")long numeroTitulo){
		return tituloBs.excluirContaReceber(numeroTitulo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/conta_pagar_pendente", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Titulo> consultarContasPagarPendente(){
		return tituloBs.consultarContasPagarPendente();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/conta_receber_pendente", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Titulo> consultarContasReceberPendente(){
		return tituloBs.consultarContasReceberPendente();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/baixa/confirmacao", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta gravarBaixa(@RequestBody Titulo titulo){
		return tituloBs.gravarBaixa(titulo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/conta_pagar/relatorio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Titulo> consultarContasPagarRelatorio(@RequestBody FiltroRelatorio filtro){
		return tituloBs.obterRelatorioContasPagar(filtro);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/conta_pagar/relatorio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta gerarContasPagarRelatorio(@RequestBody FiltroRelatorio filtro){
		return tituloBs.gerarRelatorioContasPagar(filtro);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/conta_receber/relatorio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Titulo> consultarContasReceberRelatorio(@RequestBody FiltroRelatorio filtro){
		return tituloBs.obterRelatorioContasReceber(filtro);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/conta_receber/relatorio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta gerarContasReceberRelatorio(@RequestBody FiltroRelatorio filtro){
		return tituloBs.gerarRelatorioContasReceber(filtro);
	}
}
