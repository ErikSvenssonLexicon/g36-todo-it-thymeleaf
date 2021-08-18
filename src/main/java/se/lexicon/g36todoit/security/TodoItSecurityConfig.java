package se.lexicon.g36todoit.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoItSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
        Authenticated - Logged in.
        Authorized - Authorities (rights to write, rights to read etc...)

     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/index", "/css/**", "/images/**", "/webjars/**", "/js/**", "/errors/**").permitAll()
                    .antMatchers("/people/create", "/people/create/process").anonymous()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/public/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/public/logout")
                    .logoutSuccessUrl("/index?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/errors/access-denied");
    }
}
