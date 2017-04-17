package financeiro.projeto.web;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import financeiro.projeto.BaseComponentScan;


@Configuration
@ComponentScan(basePackageClasses = {BaseComponentScan.class})
@EnableWebMvc
public class AppConfig{
	
	@Bean(name = "dataSource")
	public DataSource dataSource(){
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:5432/financeiro");
		config.setUsername("postgres");
		config.setPassword("m@sterk3y");
		return new HikariDataSource(config);
	}
	
	@Bean(name = "liquibase")
	public SpringLiquibase liquibase(@Qualifier("dataSource")DataSource dataSource){
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource());
		liquibase.setChangeLog("classpath:changelog/changelog-master.xml");
		return liquibase;
	}
}
