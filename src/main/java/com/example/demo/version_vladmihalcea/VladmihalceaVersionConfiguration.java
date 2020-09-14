package com.example.demo.version_vladmihalcea;

import com.example.demo.SandboxTransactionalApplication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import java.util.Map;

/**
 * Require: hibernate.connection.provider_disables_autocommit=true
 * https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
 */
//@Configuration
class VladmihalceaVersionConfiguration {

//    @Bean
//    public DataSource readWriteDataSource() {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setURL(primaryUrl);
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
//        return connectionPoolDataSource(dataSource);
//    }
//
//    @Bean
//    public DataSource readOnlyDataSource() {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setURL(replicaUrl);
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
//        return connectionPoolDataSource(dataSource);
//    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.master")
    HikariConfig readWriteDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setAutoCommit(false);
        return hikariConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.slave")
    HikariConfig readOnlyDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setAutoCommit(false);
        return hikariConfig;
    }

    @Bean
    public TransactionRoutingDataSource actualDataSource() {
        TransactionRoutingDataSource routingDataSource = new TransactionRoutingDataSource();
        routingDataSource.setTargetDataSources(Map.of(
                TransactionRoutingDataSource.DataSourceType.READ_WRITE, new HikariDataSource(readWriteDataSource()),
                TransactionRoutingDataSource.DataSourceType.READ_ONLY, new HikariDataSource(readOnlyDataSource())
        ));
        return routingDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(actualDataSource())
                .packages(SandboxTransactionalApplication.class)
                .build();
    }

//    @Override
//    protected Properties additionalProperties() {
//        Properties properties = super.additionalProperties();
//        properties.setProperty(
//                "hibernate.connection.provider_disables_autocommit", Boolean.TRUE.toString()
//        );
//        return properties;
//    }

//    @Override
//    protected String[] packagesToScan() {
//        return new String[]{"com.vladmihalcea.book.hpjp.hibernate.transaction.forum"};
//    }

//    @Override
//    protected String databaseType() {
//        return Database.POSTGRESQL.name().toLowerCase();
//    }


//    private HikariDataSource connectionPoolDataSource(DataSource dataSource) {
//        return new HikariDataSource(hikariConfig(dataSource));
//    }
//
//    private HikariConfig hikariConfig(DataSource dataSource) {
//        HikariConfig hikariConfig = new HikariConfig();
//        int cpuCores = Runtime.getRuntime().availableProcessors();
//        hikariConfig.setMaximumPoolSize(cpuCores * 4);
//        hikariConfig.setDataSource(dataSource);
//        hikariConfig.setAutoCommit(false);
//        return hikariConfig;
//    }

}
