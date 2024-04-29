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
import java.util.ResourceBundle;


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

    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList(resourceBundle.getString("web.link")));

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
        httpSecurity.authorizeRequests().antMatchers("/payee/").permitAll();

        httpSecurity.authorizeRequests().antMatchers("/pictures/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/css/**").permitAll();
        httpSecurity.formLogin().loginPage("/payee/").
                usernameParameter("username").
                failureHandler(officialsFailureHandler).
                successHandler(officialsSuccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.logout().permitAll();

        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("payee.wsdl")).permitAll();
        httpSecurity.authorizeRequests().antMatchers(resourceBundle.getString("payee.api")).permitAll();
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
