package financeiro.projeto.domain.pessoa;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PessoaMapper implements RowMapper<Pessoa>{

	@Override
	public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpfCnpjPessoa(rs.getString("pes_cpf_cnpj"));
		pessoa.setBairroPessoa(rs.getString("pes_bairro"));
		pessoa.setCepPessoa(rs.getString("pes_cep"));
		pessoa.setCidadePessoa(rs.getString("pes_cidade"));
		pessoa.setEstadoPessoa(rs.getString("pes_estado"));
		pessoa.setLogradouroPessoa(rs.getString("pes_logradouro"));
		pessoa.setNomePessoa(rs.getString("pes_nome"));
		pessoa.setNumeroPessoa(rs.getInt("pes_numero"));
		pessoa.setDataNascimentoPessoa(rs.getDate("pes_data_nascimento"));
		return pessoa;
	}

}
