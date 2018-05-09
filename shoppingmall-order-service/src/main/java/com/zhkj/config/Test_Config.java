package com.zhkj.config;
import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@ComponentScan("com.zhkj")
@Configuration
@MapperScan("com.zhkj")
public class Test_Config {
    @Autowired
    Configure configure;
    @Bean
    public DataSource dataSource() throws SQLException, IOException {
//        // 配置真实数据源
//      Map<String, DataSource> dataSourceMap = new HashMap<>();
//        // 配置第一个数据源
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/shoppingmall?characterEncoding=utf-8&useSSL=false");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("root");
//        dataSourceMap.put("shoppingmall", dataSource1);
//        // 配置Order表规则
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
//        orderTableRuleConfig.setLogicTable("t_order");
//        orderTableRuleConfig.setActualDataNodes("shoppingmall.orderfromshop${0..1}");
//        // 配置分表策略
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "orderfromshop${id % 2}"));
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//        // 获取数据源对象
//        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
        return  ShardingDataSourceFactory.createDataSource(new File(getClass().getResource("/sharding-jdbc/sharding-jdbc.yml").getFile()));

    }

    @Autowired
    DataSource dataSource;
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
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
