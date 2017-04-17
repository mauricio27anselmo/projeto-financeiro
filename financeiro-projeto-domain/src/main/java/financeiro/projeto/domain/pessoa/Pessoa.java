package financeiro.projeto.domain.pessoa;

import java.io.Serializable;
import java.sql.Date;

public class Pessoa implements Serializable{
	private static final long serialVersionUID = -2908443981152012043L;
	
	private String cpfCnpjPessoa;
	private String nomePessoa;
	private Date dataNascimentoPessoa;
	private String cepPessoa;
	private String logradouroPessoa;
	private int numeroPessoa;
	private String bairroPessoa;
	private String cidadePessoa;
	private String estadoPessoa;
	
	public Pessoa(){
	}
	
	public String getCpfCnpjPessoa() {
		return cpfCnpjPessoa;
	}

	public void setCpfCnpjPessoa(String cpfCnpjPessoa) {
		this.cpfCnpjPessoa = cpfCnpjPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}
	
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	
	public Date getDataNascimentoPessoa() {
		return dataNascimentoPessoa;
	}
	
	public void setDataNascimentoPessoa(Date dataNascimentoPessoa) {
		this.dataNascimentoPessoa = dataNascimentoPessoa;
	}
	
	public String getCepPessoa() {
		return cepPessoa;
	}
	
	public void setCepPessoa(String cepPessoa) {
		this.cepPessoa = cepPessoa;
	}
	
	public String getLogradouroPessoa() {
		return logradouroPessoa;
	}
	
	public void setLogradouroPessoa(String logradouroPessoa) {
		this.logradouroPessoa = logradouroPessoa;
	}
	
	public int getNumeroPessoa() {
		return numeroPessoa;
	}
	
	public void setNumeroPessoa(int numeroPessoa) {
		this.numeroPessoa = numeroPessoa;
	}
	
	public String getBairroPessoa() {
		return bairroPessoa;
	}
	
	public void setBairroPessoa(String bairroPessoa) {
		this.bairroPessoa = bairroPessoa;
	}
	
	public String getCidadePessoa() {
		return cidadePessoa;
	}
	
	public void setCidadePessoa(String cidadePessoa) {
		this.cidadePessoa = cidadePessoa;
	}
	
	public String getEstadoPessoa() {
		return estadoPessoa;
	}
	
	public void setEstadoPessoa(String estadoPessoa) {
		this.estadoPessoa = estadoPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bairroPessoa == null) ? 0 : bairroPessoa.hashCode());
		result = prime * result
				+ ((cepPessoa == null) ? 0 : cepPessoa.hashCode());
		result = prime * result
				+ ((cidadePessoa == null) ? 0 : cidadePessoa.hashCode());
		result = prime * result
				+ ((cpfCnpjPessoa == null) ? 0 : cpfCnpjPessoa.hashCode());
		result = prime
				* result
				+ ((dataNascimentoPessoa == null) ? 0 : dataNascimentoPessoa
						.hashCode());
		result = prime * result
				+ ((estadoPessoa == null) ? 0 : estadoPessoa.hashCode());
		result = prime
				* result
				+ ((logradouroPessoa == null) ? 0 : logradouroPessoa.hashCode());
		result = prime * result
				+ ((nomePessoa == null) ? 0 : nomePessoa.hashCode());
		result = prime * result + numeroPessoa;
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
		Pessoa other = (Pessoa) obj;
		if (bairroPessoa == null) {
			if (other.bairroPessoa != null)
				return false;
		} else if (!bairroPessoa.equals(other.bairroPessoa))
			return false;
		if (cepPessoa == null) {
			if (other.cepPessoa != null)
				return false;
		} else if (!cepPessoa.equals(other.cepPessoa))
			return false;
		if (cidadePessoa == null) {
			if (other.cidadePessoa != null)
				return false;
		} else if (!cidadePessoa.equals(other.cidadePessoa))
			return false;
		if (cpfCnpjPessoa == null) {
			if (other.cpfCnpjPessoa != null)
				return false;
		} else if (!cpfCnpjPessoa.equals(other.cpfCnpjPessoa))
			return false;
		if (dataNascimentoPessoa == null) {
			if (other.dataNascimentoPessoa != null)
				return false;
		} else if (!dataNascimentoPessoa.equals(other.dataNascimentoPessoa))
			return false;
		if (estadoPessoa == null) {
			if (other.estadoPessoa != null)
				return false;
		} else if (!estadoPessoa.equals(other.estadoPessoa))
			return false;
		if (logradouroPessoa == null) {
			if (other.logradouroPessoa != null)
				return false;
		} else if (!logradouroPessoa.equals(other.logradouroPessoa))
			return false;
		if (nomePessoa == null) {
			if (other.nomePessoa != null)
				return false;
		} else if (!nomePessoa.equals(other.nomePessoa))
			return false;
		if (numeroPessoa != other.numeroPessoa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [cpfCnpjPessoa=" + cpfCnpjPessoa + ", nomePessoa="
				+ nomePessoa + ", dataNascimentoPessoa=" + dataNascimentoPessoa
				+ ", cepPessoa=" + cepPessoa + ", logradouroPessoa="
				+ logradouroPessoa + ", numeroPessoa=" + numeroPessoa
				+ ", bairroPessoa=" + bairroPessoa + ", cidadePessoa="
				+ cidadePessoa + ", estadoPessoa=" + estadoPessoa + "]";
	}
}
