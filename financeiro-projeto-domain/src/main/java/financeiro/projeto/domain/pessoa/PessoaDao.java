package financeiro.projeto.domain.pessoa;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaDao {
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public PessoaDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Pessoa> listarPessoas(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM pessoa;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new PessoaMapper());
	}
	
	public int incluirPessoa(Pessoa pessoa){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("INSERT INTO pessoa (pes_cpf_cnpj, pes_nome, pes_data_nascimento, pes_cep,");
		sqlBuffer.append("pes_logradouro, pes_numero, pes_bairro, pes_cidade, pes_estado)");
		sqlBuffer.append(" SELECT ?,?,?,?,?,?,?,?,?");
		sqlBuffer.append(" WHERE NOT EXISTS(");
		sqlBuffer.append(" SELECT 1 FROM pessoa WHERE pes_cpf_cnpj = ?);");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql.toString(), new Object[] {pessoa.getCpfCnpjPessoa(), pessoa.getNomePessoa(),
			pessoa.getDataNascimentoPessoa(), pessoa.getCepPessoa(), pessoa.getLogradouroPessoa(),
			pessoa.getNumeroPessoa(), pessoa.getBairroPessoa(), pessoa.getCidadePessoa(), 
			pessoa.getEstadoPessoa(), pessoa.getCpfCnpjPessoa()});
	}
	
	public int excluirPessoa(String cpfCnpjPessoa){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("DELETE FROM pessoa WHERE pes_cpf_cnpj = ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql, new Object[]{cpfCnpjPessoa});
	}
	
	public int atualizarPessoa(Pessoa pessoa){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("UPDATE pessoa");
		sqlBuffer.append(" SET pes_nome = ?,");
		sqlBuffer.append(" pes_data_nascimento = ?,");
		sqlBuffer.append(" pes_cep = ?,");
		sqlBuffer.append(" pes_logradouro = ?,");
		sqlBuffer.append(" pes_numero = ?,");
		sqlBuffer.append(" pes_bairro = ?,");
		sqlBuffer.append(" pes_cidade = ?,");
		sqlBuffer.append(" pes_estado = ?");
		sqlBuffer.append(" WHERE pes_cpf_cnpj = ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql, new Object[]{
				pessoa.getNomePessoa(), pessoa.getDataNascimentoPessoa(), pessoa.getCepPessoa(),
				pessoa.getLogradouroPessoa(), pessoa.getNumeroPessoa(), pessoa.getBairroPessoa(),
				pessoa.getCidadePessoa(), pessoa.getEstadoPessoa(), pessoa.getCpfCnpjPessoa()});
	}
	
	public List<Pessoa> obterRelatorioPessoas(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM pessoa ORDER BY pes_nome");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new PessoaMapper());
	}
	
	public List<Pessoa> obterRelatorioPessoasComTitulo(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM pessoa INNER JOIN titulo");
		sqlBuffer.append(" ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\" ORDER BY pes_nome");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new PessoaMapper());
	}
	
	public List<Pessoa> obterRelatorioPessoasSemTitulo(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM pessoa LEFT JOIN titulo");
		sqlBuffer.append(" ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append(" WHERE tit_cpf_cnpj IS NULL ORDER BY pes_nome");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new PessoaMapper());
	}
}

