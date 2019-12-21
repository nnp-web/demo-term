package com.example.demo.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages="com.example.demo.mapper", sqlSessionFactoryRef="mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {

	@Bean("mysqlDataSource")
	@ConfigurationProperties(prefix="druid.mysql")
	public DataSource getDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean("mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
	
	@Bean("mysqlSqlSessionFactory")
	public SqlSessionFactory createFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		return factoryBean.getObject();
	}
}
