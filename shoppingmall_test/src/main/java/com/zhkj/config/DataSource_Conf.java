package com.zhkj.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * 用于配置数据库连接
 */
@ComponentScan("com.zhkj")
@Configuration
@MapperScan("com.zhkj")
public class DataSource_Conf {
  @Autowired
  Configure configure;
    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUsername(configure.getUsername());
        druidDataSource.setPassword(configure.getPassword());
        druidDataSource.setUrl(configure.getUrl());
        druidDataSource.setDriverClassName(configure.getDriver());
        return druidDataSource;
    }
    @Autowired
    private DataSource dataSource;
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("mapper/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactoryBean.setTypeAliasesPackage("com.zhkj.mapper");
        return sqlSessionFactoryBean;
    }

}
