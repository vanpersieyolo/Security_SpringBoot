package com.example.demo.config;

import com.example.demo.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SuccessHandle successHandle;

    @Autowired
    private IAppUserService appUserService;
//    @Bean
//    public UserDetailsService userDetailsService(){
//        User.UserBuilder userBuilder =User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(userBuilder.username("bill").password("123").roles("USER").build());
//        manager.createUser(userBuilder.username("admin").password("123").roles("USER", "ADMIN").build());
//        manager.createUser(userBuilder.username("dba").password("123").roles("ADMIN", "DBA").build());
//        return manager;
//    }
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService((UserDetailsService) appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.userDetailsService((UserDetailsService) appUserService)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/home").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .and().formLogin().loginPage("/login")
                .and().exceptionHandling().accessDeniedPage("/Access_Denied")
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }
}
