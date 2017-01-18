/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private FunctionaryDetailsService functionaryDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
            .csrf().disable()
            .authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/font-awesome/**")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .requestCache()
//                .and()


                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/font-awesome/**").permitAll()
                .antMatchers("/menu_orcamento", "/comite/**").hasAnyAuthority("GERENTE, ADMINISTRADOR")
                .antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/acessonegado")
                .and()
//                .exceptionHandling().accessDeniedPage("/acesso").and()
                .requestCache()
                .and()

            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
            .logout()   
                .permitAll()
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login")
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .and();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(functionaryDetailsService);
    }
    
}