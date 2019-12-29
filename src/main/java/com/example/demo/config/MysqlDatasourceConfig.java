package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionFactoryRef = "mysqlDataSourceSqlSessionFactoy")
public class MysqlDatasourceConfig {

	@Bean("mysqlDatasource")
	@ConfigurationProperties(prefix = "druid.mysql")
	public DataSource mysqlDataSource() {

		return DruidDataSourceBuilder.create().build();
	}

	@Bean("dataSourceTransactionManage")
	public DataSourceTransactionManager dataSourceTranscation() {

		return new DataSourceTransactionManager(mysqlDataSource());

	}

	@Bean("mysqlDataSourceSqlSessionFactoy")
	public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(mysqlDataSource());

		return bean.getObject();

	}

}
