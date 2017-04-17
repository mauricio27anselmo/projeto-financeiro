package financeiro.projeto.domain.taxas;

import java.io.Serializable;
import java.math.BigDecimal;

public class TaxaDesconto implements Serializable{
	private static final long serialVersionUID = 2986081451615850546L;
	
	private int porcentagemDesconto;
	private BigDecimal valorTitulo;
	private BigDecimal valorDesconto;
	
	public int getPorcentagemDesconto() {
		return porcentagemDesconto;
	}
	
	public void setPorcentagemDesconto(int porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}
	
	public BigDecimal getValorTitulo() {
		return valorTitulo;
	}
	
	public void setValorTitulo(BigDecimal valorTitulo) {
		this.valorTitulo = valorTitulo;
	}
	
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + porcentagemDesconto;
		result = prime * result
				+ ((valorDesconto == null) ? 0 : valorDesconto.hashCode());
		result = prime * result
				+ ((valorTitulo == null) ? 0 : valorTitulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxaDesconto other = (TaxaDesconto) obj;
		if (porcentagemDesconto != other.porcentagemDesconto)
			return false;
		if (valorDesconto == null) {
			if (other.valorDesconto != null)
				return false;
		} else if (!valorDesconto.equals(other.valorDesconto))
			return false;
		if (valorTitulo == null) {
			if (other.valorTitulo != null)
				return false;
		} else if (!valorTitulo.equals(other.valorTitulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaxaDesconto [porcentagemDesconto=" + porcentagemDesconto
				+ ", valorTitulo=" + valorTitulo + ", valorDesconto="
				+ valorDesconto + "]";
	}
	
}
