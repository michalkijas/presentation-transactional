package com.example.demo.readonly;

import com.example.demo.SandboxTransactionalApplication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * https://blog.pchudzik.com/201911/read-from-replica/
 * https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
 */
//@Configuration
public class TransactionRoutingConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.master")
    public HikariConfig masterConfiguration() {
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.slave")
    public HikariConfig slaveConfiguration() {
        return new HikariConfig();
    }

    @Bean
    public DataSource routingDataSource() {
        return new MasterReplicaRoutingDataSource(
                new HikariDataSource(masterConfiguration()),
                new HikariDataSource(slaveConfiguration())
        );
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(routingDataSource())
//                .packages("com.example.demo")
                .packages(SandboxTransactionalApplication.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("jpaTxManager") PlatformTransactionManager wrapped) {
        return new ReplicaAwareTransactionManager(wrapped);
    }

    @Bean(name = "jpaTxManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
