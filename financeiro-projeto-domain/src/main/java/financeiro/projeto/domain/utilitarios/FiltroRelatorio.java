package financeiro.projeto.domain.utilitarios;

import java.io.Serializable;

public class FiltroRelatorio implements Serializable{
	private static final long serialVersionUID = 3332543715788084437L;
	
	private String listagem;
	private String ordenacao;
	private String endereco;
	
	public String getListagem() {
		return listagem;
	}
	
	public void setListagem(String listagem) {
		this.listagem = listagem;
	}
	
	public String getOrdenacao() {
		return ordenacao;
	}
	
	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((listagem == null) ? 0 : listagem.hashCode());
		result = prime * result
				+ ((ordenacao == null) ? 0 : ordenacao.hashCode());
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
		FiltroRelatorio other = (FiltroRelatorio) obj;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (listagem == null) {
			if (other.listagem != null)
				return false;
		} else if (!listagem.equals(other.listagem))
			return false;
		if (ordenacao == null) {
			if (other.ordenacao != null)
				return false;
		} else if (!ordenacao.equals(other.ordenacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FiltroRelatorio [listagem=" + listagem + ", ordenacao="
				+ ordenacao + ", endereco=" + endereco + "]";
	}	
}
