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

import financeiro.projeto.domain.pessoa.Pessoa;
import financeiro.projeto.domain.pessoa.PessoaBs;
import financeiro.projeto.domain.utilitarios.FiltroRelatorio;
import financeiro.projeto.domain.utilitarios.Resposta;

@RestController(value = "/pessoa")
public class PessoaRest {
	@Autowired
	private PessoaBs pessoaBs;
	
	@RequestMapping(method = RequestMethod.GET, value="/pessoa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Pessoa> listarPessoas(){
		return pessoaBs.listarPessoas();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/pessoa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta incluirPessoa(@RequestBody Pessoa pessoa){
		return pessoaBs.incluirPessoa(pessoa);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/pessoa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta excluirPessoa(@RequestParam("pessoa") String cpfCnpjPessoa){
		return pessoaBs.excluirPessoa(cpfCnpjPessoa);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/pessoa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta atualizarPessoa(@RequestBody Pessoa pessoa){
		return pessoaBs.atualizarPessoa(pessoa);	
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/pessoa/relatorio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Pessoa> obterRelatorioPessoas(@RequestBody FiltroRelatorio filtro){
		return pessoaBs.obterRelatorioPessoas(filtro);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/pessoa/relatorio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Resposta gerarRelatorioPessoas(@RequestBody FiltroRelatorio filtro){
		return pessoaBs.gerarRelatorioPessoas(filtro);
	}
	
	
}
