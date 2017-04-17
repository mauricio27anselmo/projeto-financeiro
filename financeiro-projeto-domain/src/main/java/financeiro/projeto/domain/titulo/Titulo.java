package financeiro.projeto.domain.titulo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import financeiro.projeto.domain.pessoa.Pessoa;

public class Titulo implements Serializable{
	private static final long serialVersionUID = 1145971039170619700L;
	
	private long numeroTitulo;
	private Date dataCriacaoTitulo;
	private Date dataVencimentoTitulo;
	private Pessoa dadosPessoa;
	private BigDecimal valorTitulo;
	private BigDecimal valorJurosTitulo;
	private BigDecimal valorJurosCalculadoTitulo;
	private BigDecimal valorDescontoTitulo;
	private Date dataPagamentoTitulo;
	private BigDecimal valorPagoTitulo;
	private String tipoContaTitulo;
	
	public Titulo(){
	}
	
	public long getNumeroTitulo() {
		return numeroTitulo;
	}
	
	public void setNumeroTitulo(long numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}
	
	public Date getDataCriacaoTitulo() {
		return dataCriacaoTitulo;
	}
	
	public void setDataCriacaoTitulo(Date dataCriacaoTitulo) {
		this.dataCriacaoTitulo = dataCriacaoTitulo;
	}
	
	public Date getDataVencimentoTitulo() {
		return dataVencimentoTitulo;
	}
	
	public void setDataVencimentoTitulo(Date dataVencimentoTitulo) {
		this.dataVencimentoTitulo = dataVencimentoTitulo;
	}
	
	public Pessoa getDadosPessoa() {
		return dadosPessoa;
	}
	
	public void setDadosPessoa(Pessoa dadosPessoa) {
		this.dadosPessoa = dadosPessoa;
	}
	
	public BigDecimal getValorTitulo() {
		return valorTitulo;
	}
	
	public void setValorTitulo(BigDecimal valorTitulo) {
		this.valorTitulo = valorTitulo;
	}
	
	public BigDecimal getValorJurosTitulo() {
		return valorJurosTitulo;
	}
	
	public void setValorJurosTitulo(BigDecimal valorJurosTitulo) {
		this.valorJurosTitulo = valorJurosTitulo;
	}
	
	public BigDecimal getValorJurosCalculadoTitulo() {
		return valorJurosCalculadoTitulo;
	}
	
	public void setValorJurosCalculadoTitulo(BigDecimal valorJurosCalculadoTitulo) {
		this.valorJurosCalculadoTitulo = valorJurosCalculadoTitulo;
	}
	
	public BigDecimal getValorDescontoTitulo() {
		return valorDescontoTitulo;
	}
	
	public void setValorDescontoTitulo(BigDecimal valorDescontoTitulo) {
		this.valorDescontoTitulo = valorDescontoTitulo;
	}
	
	public Date getDataPagamentoTitulo() {
		return dataPagamentoTitulo;
	}
	
	public void setDataPagamentoTitulo(Date dataPagamentoTitulo) {
		this.dataPagamentoTitulo = dataPagamentoTitulo;
	}
	
	public BigDecimal getValorPagoTitulo() {
		return valorPagoTitulo;
	}
	
	public void setValorPagoTitulo(BigDecimal valorPagoTitulo) {
		this.valorPagoTitulo = valorPagoTitulo;
	}
	
	public String getTipoContaTitulo() {
		return tipoContaTitulo;
	}
	
	public void setTipoContaTitulo(String tipoContaTitulo) {
		this.tipoContaTitulo = tipoContaTitulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dadosPessoa == null) ? 0 : dadosPessoa.hashCode());
		result = prime
				* result
				+ ((dataCriacaoTitulo == null) ? 0 : dataCriacaoTitulo
						.hashCode());
		result = prime
				* result
				+ ((dataPagamentoTitulo == null) ? 0 : dataPagamentoTitulo
						.hashCode());
		result = prime
				* result
				+ ((dataVencimentoTitulo == null) ? 0 : dataVencimentoTitulo
						.hashCode());
		result = prime * result + (int) (numeroTitulo ^ (numeroTitulo >>> 32));
		result = prime * result
				+ ((tipoContaTitulo == null) ? 0 : tipoContaTitulo.hashCode());
		result = prime
				* result
				+ ((valorDescontoTitulo == null) ? 0 : valorDescontoTitulo
						.hashCode());
		result = prime
				* result
				+ ((valorJurosCalculadoTitulo == null) ? 0
						: valorJurosCalculadoTitulo.hashCode());
		result = prime
				* result
				+ ((valorJurosTitulo == null) ? 0 : valorJurosTitulo.hashCode());
		result = prime * result
				+ ((valorPagoTitulo == null) ? 0 : valorPagoTitulo.hashCode());
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
		Titulo other = (Titulo) obj;
		if (dadosPessoa == null) {
			if (other.dadosPessoa != null)
				return false;
		} else if (!dadosPessoa.equals(other.dadosPessoa))
			return false;
		if (dataCriacaoTitulo == null) {
			if (other.dataCriacaoTitulo != null)
				return false;
		} else if (!dataCriacaoTitulo.equals(other.dataCriacaoTitulo))
			return false;
		if (dataPagamentoTitulo == null) {
			if (other.dataPagamentoTitulo != null)
				return false;
		} else if (!dataPagamentoTitulo.equals(other.dataPagamentoTitulo))
			return false;
		if (dataVencimentoTitulo == null) {
			if (other.dataVencimentoTitulo != null)
				return false;
		} else if (!dataVencimentoTitulo.equals(other.dataVencimentoTitulo))
			return false;
		if (numeroTitulo != other.numeroTitulo)
			return false;
		if (tipoContaTitulo == null) {
			if (other.tipoContaTitulo != null)
				return false;
		} else if (!tipoContaTitulo.equals(other.tipoContaTitulo))
			return false;
		if (valorDescontoTitulo == null) {
			if (other.valorDescontoTitulo != null)
				return false;
		} else if (!valorDescontoTitulo.equals(other.valorDescontoTitulo))
			return false;
		if (valorJurosCalculadoTitulo == null) {
			if (other.valorJurosCalculadoTitulo != null)
				return false;
		} else if (!valorJurosCalculadoTitulo
				.equals(other.valorJurosCalculadoTitulo))
			return false;
		if (valorJurosTitulo == null) {
			if (other.valorJurosTitulo != null)
				return false;
		} else if (!valorJurosTitulo.equals(other.valorJurosTitulo))
			return false;
		if (valorPagoTitulo == null) {
			if (other.valorPagoTitulo != null)
				return false;
		} else if (!valorPagoTitulo.equals(other.valorPagoTitulo))
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
		return "Titulo [dataCriacaoTitulo=" + dataCriacaoTitulo + ", dataVencimentoTitulo=" + dataVencimentoTitulo
				+ ", dadosPessoa=" + dadosPessoa + ", numeroTitulo=" + numeroTitulo + ", valorTitulo=" + valorTitulo
				+ ", valorJurosTitulo=" + valorJurosTitulo + ", valorJurosCalculadoTitulo=" + valorJurosCalculadoTitulo
				+ ", valorDescontoTitulo=" + valorDescontoTitulo + ", dataPagamentoTitulo=" + dataPagamentoTitulo
				+ ", valorPagoTitulo=" + valorPagoTitulo + ", tipoContaTitulo=" + tipoContaTitulo + "]";
	}
}
