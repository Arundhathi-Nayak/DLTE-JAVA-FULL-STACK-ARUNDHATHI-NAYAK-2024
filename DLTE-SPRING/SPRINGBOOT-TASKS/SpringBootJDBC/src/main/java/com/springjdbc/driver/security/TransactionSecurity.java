package com.springjdbc.driver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TransactionSecurity {
    @Autowired
    private TransactionOfficialService transactionOfficialService;

    AuthenticationManager manager;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       httpSecurity.csrf().disable();
       httpSecurity.httpBasic();
       httpSecurity.formLogin();
       httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
       httpSecurity.authorizeRequests().anyRequest().authenticated();
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(transactionOfficialService);
        manager=builder.build();
        httpSecurity
                .authenticationManager(manager);
        return httpSecurity.build();
    }
}
