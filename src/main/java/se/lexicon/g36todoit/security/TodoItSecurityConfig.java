package se.lexicon.g36todoit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoItSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserAuthenticationSuccessHandler appUserAuthenticationSuccessHandler;

    /*
        Authenticated - Logged in.
        Authorized - Authorities (rights to write, rights to read etc...)

        When we are authenticated: we get a session with a state. State is called UserDetails

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
                    .successHandler(appUserAuthenticationSuccessHandler)
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
