package financeiro.projeto.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import financeiro.projeto.domain.taxas.TaxaJuros;
import financeiro.projeto.domain.taxas.TaxaJurosBs;

@RestController(value = "/taxas")
public class TaxaJurosRest {
	
	@Autowired
	private TaxaJurosBs taxaJurosBs;
	
	@RequestMapping(method = RequestMethod.PUT, value="/baixa/juros", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TaxaJuros excluirPessoa(@RequestBody TaxaJuros taxas){
		return taxaJurosBs.calcularTaxasJuros(taxas);
	}
}
