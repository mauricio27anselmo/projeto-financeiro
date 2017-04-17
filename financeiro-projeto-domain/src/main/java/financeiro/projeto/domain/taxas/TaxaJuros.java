package financeiro.projeto.domain.taxas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class TaxaJuros implements Serializable{
	private static final long serialVersionUID = 3309506232260719991L;
	
	private int porcentagemJuros;
	private BigDecimal valorTitulo;
	private BigDecimal valorJurosCalculado;
	private BigDecimal valorJurosSugerido;
	private Date dataVencimento;
	private Date dataPagamento;
	
	public int getPorcentagemJuros() {
		return porcentagemJuros;
	}
	
	public void setPorcentagemJuros(int porcentagemJuros) {
		this.porcentagemJuros = porcentagemJuros;
	}
	
	public BigDecimal getValorTitulo() {
		return valorTitulo;
	}
	
	public void setValorTitulo(BigDecimal valorTitulo) {
		this.valorTitulo = valorTitulo;
	}
	
	public BigDecimal getValorJurosCalculado() {
		return valorJurosCalculado;
	}
	
	public void setValorJurosCalculado(BigDecimal valorJurosCalculado) {
		this.valorJurosCalculado = valorJurosCalculado;
	}
	
	public BigDecimal getValorJurosSugerido() {
		return valorJurosSugerido;
	}
	
	public void setValorJurosSugerido(BigDecimal valorJurosSugerido) {
		this.valorJurosSugerido = valorJurosSugerido;
	}
	
	public Date getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + porcentagemJuros;
		result = prime
				* result
				+ ((valorJurosCalculado == null) ? 0 : valorJurosCalculado
						.hashCode());
		result = prime
				* result
				+ ((valorJurosSugerido == null) ? 0 : valorJurosSugerido
						.hashCode());
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
		TaxaJuros other = (TaxaJuros) obj;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (porcentagemJuros != other.porcentagemJuros)
			return false;
		if (valorJurosCalculado == null) {
			if (other.valorJurosCalculado != null)
				return false;
		} else if (!valorJurosCalculado.equals(other.valorJurosCalculado))
			return false;
		if (valorJurosSugerido == null) {
			if (other.valorJurosSugerido != null)
				return false;
		} else if (!valorJurosSugerido.equals(other.valorJurosSugerido))
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
		return "TaxaJuros [porcentagemJuros=" + porcentagemJuros
				+ ", valorTitulo=" + valorTitulo + ", valorJurosCalculado="
				+ valorJurosCalculado + ", valorJurosSugerido="
				+ valorJurosSugerido + ", dataVencimento=" + dataVencimento
				+ ", dataPagamento=" + dataPagamento + "]";
	}
}
