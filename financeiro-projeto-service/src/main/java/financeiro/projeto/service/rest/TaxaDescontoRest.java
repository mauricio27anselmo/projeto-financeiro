package financeiro.projeto.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import financeiro.projeto.domain.taxas.TaxaDesconto;
import financeiro.projeto.domain.taxas.TaxaDescontoBs;

@RestController(value = "/descontos")
public class TaxaDescontoRest {
	@Autowired
	private TaxaDescontoBs taxaDescontoBs;
	
	@RequestMapping(method = RequestMethod.PUT, value="/baixa/descontos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TaxaDesconto excluirPessoa(@RequestBody TaxaDesconto taxas){
		return taxaDescontoBs.calcularValorDesconto(taxas);
	}
}

