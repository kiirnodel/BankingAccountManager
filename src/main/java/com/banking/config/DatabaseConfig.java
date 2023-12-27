package com.banking.config;

import com.banking.enuns.BusinessType;
import com.banking.exception.BusinessException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Data
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfig {

    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private String poolName;
    private Integer maxPoolSize;
    private Integer minPoolSize;
    private Long maxLifetime;
    private Long validationTimeout;

    @Bean
    public DataSource dataSource() throws BusinessException {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(driverClassName);
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
            hikariConfig.setPoolName(poolName);
            hikariConfig.setMinimumIdle(minPoolSize);
            hikariConfig.setMaximumPoolSize(maxPoolSize);
            hikariConfig.setMaxLifetime(maxLifetime);
            hikariConfig.setValidationTimeout(validationTimeout);
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            throw new BusinessException(BusinessType.LIMIT_EXCEEDED);
        }
    }
}
