package com.gokhan.veteriner.config;

import com.jolbox.bonecp.BoneCPDataSource;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

//import org.springframework.context.annotation.Profile;


/**
 * Created by sevikm on 4/3/2017.
 */

@Configuration
@MapperScan("com.gokhan.veteriner.persistence")
//@Profile("production") //for now, we will use below configuration for tests as well. Later we might use different configuration for tests.
@Slf4j
public class PersistenceConfiguration {


  @Value("${datasource.bonecp.url}")
  private String jdbcUrl;

  @Value("${datasource.bonecp.username}")
  private String jdbcUsername;

  @Value("${datasource.bonecp.password}")
  private String jdbcPassword;

  @Value("${datasource.bonecp.driverClass}")
  private String driverClass;

  @Value("${datasource.bonecp.idleMaxAgeInMinutes}")
  private Integer idleMaxAgeInMinutes;

  @Value("${datasource.bonecp.idleConnectionTestPeriodInMinutes}")
  private Integer idleConnectionTestPeriodInMinutes;

  @Value("${datasource.bonecp.maxConnectionsPerPartition}")
  private Integer maxConnectionsPerPartition;

  @Value("${datasource.bonecp.minConnectionsPerPartition}")
  private Integer minConnectionsPerPartition;

  @Value("${datasource.bonecp.partitionCount}")
  private Integer partitionCount;

  @Value("${datasource.bonecp.acquireIncrement}")
  private Integer acquireIncrement;

  @Value("${datasource.bonecp.statementsCacheSize}")
  private Integer statementsCacheSize;

  @Bean(destroyMethod = "close")
  @Primary
  @ConfigurationProperties(prefix = "datasource.primary")
  public DataSource dataSource() {
  BoneCPDataSource dataSource = new BoneCPDataSource();
  dataSource.setDriverClass(driverClass);
  dataSource.setJdbcUrl(jdbcUrl);
  dataSource.setUsername(jdbcUsername);
  dataSource.setPassword(jdbcPassword);
  dataSource.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
  dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
  dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
  dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
  dataSource.setPartitionCount(partitionCount);
  dataSource.setAcquireIncrement(acquireIncrement);
  dataSource.setStatementsCacheSize(statementsCacheSize);

    System.out.println("datasource instantiated with the following properties:");
    System.out.println(this.toString());

  return dataSource;
  }


  @Bean
  public DataSourceTransactionManager transactionManager() throws IOException {
  return new DataSourceTransactionManager(dataSource());
  }


  @Bean
  @Primary
  public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
  SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
  sessionFactory.setDataSource(dataSource());
//  sessionFactory.setTypeAliasesPackage("com.basf.edn.domain");
  sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
  return sessionFactory;
  }


//  @Bean(name="batchSqlSessionTemplate")
//  public SqlSessionTemplate batchSqlSessionTemplate() throws Exception {
//  return new SqlSessionTemplate(sqlSessionFactory().getObject(), ExecutorType.BATCH);
//  }


  @Override
  public String toString() {
  return "PersistenceConfiguration{" +
   "jdbcUrl='" + jdbcUrl + '\'' +
   ", jdbcUsername='" + jdbcUsername + '\'' +
   ", jdbcPassword='" + "*****" + '\'' +
   ", driverClass='" + driverClass + '\'' +
   ", idleMaxAgeInMinutes=" + idleMaxAgeInMinutes +
   ", idleConnectionTestPeriodInMinutes=" + idleConnectionTestPeriodInMinutes +
   ", maxConnectionsPerPartition=" + maxConnectionsPerPartition +
   ", minConnectionsPerPartition=" + minConnectionsPerPartition +
   ", partitionCount=" + partitionCount +
   ", acquireIncrement=" + acquireIncrement +
   ", statementsCacheSize=" + statementsCacheSize +
   '}';
  }

}
