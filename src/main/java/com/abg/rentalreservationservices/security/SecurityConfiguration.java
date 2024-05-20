package com.abg.rentalreservationservices.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource) {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                String userQuery = "select email, password, true from user where email = ?";

                // Load user details
                UserDetails user = this.getJdbcTemplate().queryForObject(userQuery, new Object[]{email},
                        (rs, rowNum) -> User.withUsername(rs.getString("email"))
                                .password(rs.getString("password"))
                                .authorities("USER")
                                .build());

                return user;
            }
        };
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
