package financeiro.projeto.domain.taxas;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class TaxaDescontoBs {
	
	public TaxaDesconto calcularValorDesconto(TaxaDesconto taxas){
		double valorTitulo = taxas.getValorTitulo().doubleValue();
		int porcentagem = taxas.getPorcentagemDesconto();
		double valorDesconto = valorTitulo - (((double)porcentagem/100.0)*valorTitulo);
		BigDecimal desconto = new BigDecimal(valorDesconto).setScale(2, RoundingMode.HALF_EVEN);
		taxas.setValorDesconto(desconto);
		return taxas;
	}
}
