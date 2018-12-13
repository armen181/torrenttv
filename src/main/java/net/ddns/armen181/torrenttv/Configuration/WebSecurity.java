package net.ddns.armen181.torrenttv.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();                   //h2-console conflict
        http.headers().frameOptions().disable(); //h2-console conflict

        http.httpBasic().and()
                .authorizeRequests()
                 .antMatchers("/**").permitAll()
                .antMatchers("/rest/**").authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();

    }

}
