package com.abg.rentalreservationservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select name,password, true from user where name = ?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username where username=?"
        );
        return jdbcUserDetailsManager;
    }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authorizeHttpRequests(configurer ->
                       configurer
                       .requestMatchers("/register/**").permitAll()
                       .anyRequest().authenticated()
       )
               .formLogin(form ->
                       form
                               .loginPage("/login")
                               .loginProcessingUrl("/authenticateTheUser")
                               .permitAll()
               )
               .logout(logout -> logout.permitAll()
               );
       httpSecurity.httpBasic(Customizer.withDefaults());
       httpSecurity.csrf(csrf -> csrf.disable());
       System.out.println("Security configuration applied successfully.");

       return httpSecurity.build();
   }

}
