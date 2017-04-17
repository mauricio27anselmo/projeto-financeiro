package financeiro.projeto.domain.titulo;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import financeiro.projeto.domain.pessoa.Pessoa;
import financeiro.projeto.domain.pessoa.PessoaMapper;

@Repository
public class TituloDao {
	private Logger logger = LoggerFactory.getLogger(TituloDao.class);
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public TituloDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Titulo> listarTitulos(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\";");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new TituloMapper());
	}
	
	public int incluirTitulo(Titulo titulo){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("INSERT INTO titulo (tit_data_criacao, tit_data_vencimento, tit_valor,");
		sqlBuffer.append("tit_valor_juros, tit_valor_juros_calculado, tit_valor_desconto, ");
		sqlBuffer.append("tit_data_pagamento, tit_valor_pago, tit_tipo_conta, tit_cpf_cnpj)");
		sqlBuffer.append(" VALUES(?,?,?,?,?,?,?,?,?,?);");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql, new Object[] {titulo.getDataCriacaoTitulo(), titulo.getDataVencimentoTitulo(),
			titulo.getValorTitulo(), titulo.getValorJurosTitulo(), titulo.getValorJurosCalculadoTitulo(), titulo.getValorDescontoTitulo(),
			titulo.getDataPagamentoTitulo(), titulo.getValorPagoTitulo(), titulo.getTipoContaTitulo(), 
			titulo.getDadosPessoa().getCpfCnpjPessoa()});
	}
	
	public List<Titulo> consultarContasPagar(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\" AND  tit_tipo_conta = 'CONTA_PAGAR';");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new TituloMapper());
	}
	
	public int excluirContaPagar(long numeroTitulo){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("DELETE FROM titulo WHERE tit_numero = ? AND tit_tipo_conta = 'CONTA_PAGAR' AND tit_valor_pago IS NULL;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql, new Object[]{numeroTitulo}); 
	}
	
	public List<Titulo> consultarContasReceber(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\" AND  tit_tipo_conta = 'CONTA_RECEBER';");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new TituloMapper());
	}
	
	public int excluirContaReceber(long numeroTitulo){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("DELETE FROM titulo WHERE tit_numero = ? AND tit_tipo_conta = 'CONTA_RECEBER' AND tit_valor_pago IS NULL;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql, new Object[]{numeroTitulo}); 
	}
	
	public List<Titulo> consultarContasPagarPendente(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append( "AND  tit_tipo_conta = 'CONTA_PAGAR' AND tit_valor_pago IS NULL;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new TituloMapper());
	}
	
	public List<Titulo> consultarContasReceberPendente(){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append( "AND  tit_tipo_conta = 'CONTA_RECEBER' AND tit_valor_pago IS NULL;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new TituloMapper());
	}
	
	public int gravarBaixa(Titulo titulo){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("UPDATE titulo");
		sqlBuffer.append(" SET tit_valor_juros_calculado=?, ");
		sqlBuffer.append(" tit_valor_pago=?,");
		sqlBuffer.append(" tit_data_pagamento=?,");
		sqlBuffer.append(" tit_valor_desconto=?");
		sqlBuffer.append(" WHERE tit_numero = ? AND tit_valor_pago IS NULL;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.update(sql,
				new Object[] { titulo.getValorJurosCalculadoTitulo(),
						titulo.getValorTitulo(),
						titulo.getDataPagamentoTitulo(),
						titulo.getValorDescontoTitulo(),
						titulo.getNumeroTitulo()});
	}
	
	public List<Titulo> obterRelatorioContasReceberTodas(String ordenacao){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append(" AND  tit_tipo_conta = 'CONTA_RECEBER' ORDER BY ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new Object[]{ordenacao}, new TituloMapper());
	}
	
	public List<Titulo> obterRelatorioContasReceberPendentes(String ordenacao){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append(" AND  tit_tipo_conta = 'CONTA_RECEBER' AND tit_valor_pago IS NULL");
		sqlBuffer.append(" ORDER BY ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new Object[]{ordenacao}, new TituloMapper());
	}
	
	public List<Titulo> obterRelatorioContasReceberFinalizadas(String ordenacao){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append(" AND  tit_tipo_conta = 'CONTA_RECEBER' AND tit_valor_pago IS NOT NULL");
		sqlBuffer.append(" ORDER BY ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new Object[]{ordenacao}, new TituloMapper());
	}
	
	public List<Titulo> obterRelatorioContasPagarTodas(String ordenacao){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append(" AND  tit_tipo_conta = 'CONTA_PAGAR' ORDER BY ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new Object[]{ordenacao}, new TituloMapper());
	}
	
	public List<Titulo> obterRelatorioContasPagarPendentes(String ordenacao){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append( "AND  tit_tipo_conta = 'CONTA_PAGAR' AND tit_valor_pago IS NULL");
		sqlBuffer.append(" ORDER BY ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new Object[]{ordenacao}, new TituloMapper());
	}
	
	public List<Titulo> obterRelatorioContasPagarFinalizadas(String ordenacao){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT * FROM titulo INNER JOIN pessoa ON \"pes_cpf_cnpj\" = \"tit_cpf_cnpj\"");
		sqlBuffer.append(" AND  tit_tipo_conta = 'CONTA_PAGAR' AND tit_valor_pago IS NOT NULL");
		sqlBuffer.append(" ORDER BY ?;");
		String sql = sqlBuffer.toString();
		return jdbcTemplate.query(sql, new Object[]{ordenacao}, new TituloMapper());
	}
}
