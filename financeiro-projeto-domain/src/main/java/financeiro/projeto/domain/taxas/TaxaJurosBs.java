package financeiro.projeto.domain.taxas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaxaJurosBs {
	private Logger logger = LoggerFactory.getLogger(TaxaJurosBs.class);
	
	public TaxaJuros calcularTaxasJuros(TaxaJuros taxas){
		Date dataPagamento = taxas.getDataPagamento();
		Date dataVencimento = taxas.getDataVencimento();
		long diasAtraso = Math.abs(calcularDiasAtraso(dataPagamento, dataVencimento));
		if(diasAtraso > 0){
			BigDecimal valorTitulo = taxas.getValorTitulo();
			int porcentagem = taxas.getPorcentagemJuros();
			taxas.setValorJurosCalculado(calcularValorJuros(porcentagem, valorTitulo, diasAtraso));
			taxas.setValorJurosSugerido(calcularValorJurosSugerido(porcentagem, valorTitulo, diasAtraso));
		}
		return taxas;
	}
	
	private long calcularDiasAtraso(Date dataPagamento, Date dataVencimento){
		LocalDate vencimento = dataVencimento.toLocalDate();
		LocalDate pagamento = dataPagamento.toLocalDate();
		return pagamento.until(vencimento, ChronoUnit.DAYS);
	}
	
	private BigDecimal calcularValorJuros(int porcentagemJuros, BigDecimal valorTitulo, long diasAtraso){
		double valor = valorTitulo.doubleValue();
		double montante = valor * (Math.pow((1 + ((double)porcentagemJuros/100.0)),((double)diasAtraso/30.0)));
		double valorJuros = (montante - valor);
		return new BigDecimal(valorJuros).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	private BigDecimal calcularValorJurosSugerido(int porcentagemJuros, BigDecimal valorTitulo, long diasAtraso){
		double valor = valorTitulo.doubleValue();
		double valorJurosSugerido = ((((double)porcentagemJuros/100.0) * valor)/30.0) * diasAtraso;
		return new BigDecimal(valorJurosSugerido).setScale(2, RoundingMode.HALF_EVEN);
	}
}

