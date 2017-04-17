package financeiro.projeto.domain.utilitarios;

import java.io.Serializable;

public class Resposta implements Serializable{
	private static final long serialVersionUID = 4644599884651932064L;
	
	private boolean sucesso;
	private String resposta;
	
	public Resposta (boolean sucesso, String resposta){
		this.sucesso = sucesso;
		this.resposta = resposta;
	}
	
	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resposta == null) ? 0 : resposta.hashCode());
		result = prime * result + (sucesso ? 1231 : 1237);
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
		Resposta other = (Resposta) obj;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		if (sucesso != other.sucesso)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resposta [sucesso=" + sucesso + ", resposta=" + resposta + "]";
	}
	
	
}
