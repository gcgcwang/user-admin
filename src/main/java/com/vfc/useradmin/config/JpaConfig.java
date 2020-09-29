package com.vfc.useradmin.config;

import com.vfc.useradmin.core.SpringContextHolder;
import com.vfc.useradmin.core.exception.LocalExceptionResolver;
import com.vfc.useradmin.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JpaConfig {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Bean
    public JpaService jpaService(){
        JpaService jpaService = new DefaultJpaService();
        ((DefaultJpaService) jpaService).setSqlGenerator(sqlGenerator());
        ((DefaultJpaService) jpaService).setNamedJdbcTemplate(namedParameterJdbcTemplate);
        return jpaService;
    }

    @Bean
    public SqlGenerator sqlGenerator(){
        SqlGenerator branchSqlGenerator = new BranchSqlGenerator();
        ((BranchSqlGenerator) branchSqlGenerator).setEntryMapper(entryMapper());
        return branchSqlGenerator;
    }

    @Bean
    public EntryMapper entryMapper(){
        EntryMapper jpaEntryMapper = new JpaEntryMapper();
        return jpaEntryMapper;
    }

    @Bean
    public SpringContextHolder  SpringContextHolder(){
        SpringContextHolder springContextHolder = new SpringContextHolder();
        return springContextHolder;
    }

    @Bean
    public LocalExceptionResolver LocalExceptionResolver(){
       return  new LocalExceptionResolver();
    }
}
