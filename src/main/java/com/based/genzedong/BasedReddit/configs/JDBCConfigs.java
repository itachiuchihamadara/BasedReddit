package com.based.genzedong.BasedReddit.configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import static com.based.genzedong.BasedReddit.Constants.POSTGRES_DRIVER_CLASS;

@Configuration
public class JDBCConfigs {

    @Value("${spring.data.jdbc.url}")
    private String url;

    @Value("${spring.data.jdbc.username}")
    private String username;

    @Value("${spring.data.jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource(){

        return DataSourceBuilder.create()
                .driverClassName(POSTGRES_DRIVER_CLASS)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource source){

        return new NamedParameterJdbcTemplate(source);
    }
}
