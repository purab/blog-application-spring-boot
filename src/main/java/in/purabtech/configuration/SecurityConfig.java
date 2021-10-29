package in.purabtech.configuration;

import in.purabtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    /**
     * HTTPSecurity configurer
     * - roles ADMIN allow to access /admin/**
     * - roles USER allow to access /user/** and /newPost/**
     * - anybody can visit /, /home, /registration, /error, /blog/**, /post/**, /h2-console/**
     * - every other page needs authentication
     * - custom 403 access denied handler
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/home", "/registration", "/error", "/blog/**", "/post/**", "/h2-console/**").permitAll()
                    .antMatchers("/newPost/**", "/commentPost/**", "/createComment/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .logout()
                .permitAll()
                // Fix for H2 console
                .and().headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     * Configure and return BCrypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
