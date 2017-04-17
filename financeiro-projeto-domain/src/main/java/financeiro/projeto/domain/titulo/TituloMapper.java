package financeiro.projeto.domain.titulo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import financeiro.projeto.domain.pessoa.Pessoa;

public class TituloMapper implements RowMapper<Titulo>{
	
	@Override
	public Titulo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Titulo titulo = new Titulo();
			Pessoa pessoa = new Pessoa();
			
			titulo.setNumeroTitulo(rs.getLong("tit_numero"));
			titulo.setTipoContaTitulo(rs.getString("tit_tipo_conta"));
			titulo.setValorDescontoTitulo(rs.getBigDecimal("tit_valor_desconto"));
			titulo.setValorJurosCalculadoTitulo(rs.getBigDecimal("tit_valor_juros_calculado"));
			titulo.setValorJurosTitulo(rs.getBigDecimal("tit_valor_juros"));
			titulo.setValorPagoTitulo(rs.getBigDecimal("tit_valor_pago"));
			titulo.setValorTitulo(rs.getBigDecimal("tit_valor"));
			titulo.setDataCriacaoTitulo(rs.getDate("tit_data_criacao"));
			titulo.setDataPagamentoTitulo(rs.getDate("tit_data_pagamento"));
			titulo.setDataVencimentoTitulo(rs.getDate("tit_data_vencimento"));
			
			pessoa.setCpfCnpjPessoa(rs.getString("pes_cpf_cnpj"));
			pessoa.setBairroPessoa(rs.getString("pes_bairro"));
			pessoa.setCepPessoa(rs.getString("pes_cep"));
			pessoa.setCidadePessoa(rs.getString("pes_cidade"));
			pessoa.setEstadoPessoa(rs.getString("pes_estado"));
			pessoa.setLogradouroPessoa(rs.getString("pes_logradouro"));
			pessoa.setNomePessoa(rs.getString("pes_nome"));
			pessoa.setNumeroPessoa(rs.getInt("pes_numero"));
			pessoa.setDataNascimentoPessoa(rs.getDate("pes_data_nascimento"));
			
			titulo.setDadosPessoa(pessoa);
			return titulo;
	}

}
