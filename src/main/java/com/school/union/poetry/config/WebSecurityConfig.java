package com.school.union.poetry.config;

import com.school.union.poetry.security.CustomUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfig
 *
 * @author houxin
 * @date 2019/4/30
 */
@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        //注册CustomUserService的Bean
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return new BCryptPasswordEncoder().encode(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                boolean matches = new BCryptPasswordEncoder().matches(charSequence, s);
                log.info("s = {}, c = {}", s, charSequence);
                if (!matches) {
                    log.info("密码错误");
                }
                return matches;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(new SessionRegistryImpl());
        http.authorizeRequests()
                //后台管理只允许管理员角色访问
                .antMatchers("/backstage/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/backstage/index").permitAll()
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll();
    }
}
