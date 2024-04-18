package com.payment.webservice.security;

import com.paymentdao.payment.security.MyBankOfficialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


//{
//
//        "customerName": "Anupama",
//        "customerAddress": "shanthala",
//        "customerStatus": "Active",
//        "customerContact": 7867543245,
//        "username": "anu",
//        "password": "anu",
//        "attempts":1
//
//        }
@Configuration
public class CustomerSecureConfig {
    @Autowired
    MyBankOfficialsService service;

    AuthenticationManager authenticationManager;

    @Autowired
    OfficialsFailureHandler officialsFailureHandler;
    @Autowired
    OfficialsSuccessHandler officialsSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://127.0.0.1:5500"));

        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.formLogin().
                usernameParameter("username").
                failureHandler(officialsFailureHandler).
                successHandler(officialsSuccessHandler);
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        httpSecurity.cors();

        httpSecurity.authorizeRequests().anyRequest().authenticated();


        AuthenticationManagerBuilder builder=httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        authenticationManager=builder.build();
        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }
}
