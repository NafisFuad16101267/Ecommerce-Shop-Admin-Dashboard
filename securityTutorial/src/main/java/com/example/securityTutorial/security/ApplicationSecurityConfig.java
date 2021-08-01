package com.example.securityTutorial.security;

import com.example.securityTutorial.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails annaSmithUser = User.builder()
                .username("Annasmith")
                .password(passwordEncoder.encode("password"))
                .roles("STUDENT")
                .build();

        UserDetails linda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(
                annaSmithUser
        );
    }
}
