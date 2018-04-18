package com.db117.adminstaging.config.security;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author 大兵
 * @date 2018-04-15 18:07
 **/
// 注解开启Spring Security的功能
@EnableWebSecurity
//开启security注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserService customUserService;
    @Autowired
    private DruidDataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //定义哪些url需要保护，哪些url不需要保护
                .authorizeRequests()
                //定义不需要认证就可以访问
                .antMatchers( "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //定义当需要用户登录时候，转到的登录页面
                .loginPage("/login")
                .successForwardUrl("/index")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .and()
                .rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(1209600);

        http.authorizeRequests().antMatchers("/index").hasRole("USER");
        http.csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth    //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("role");

        //定义密码编码格式
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置TokenRepository
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 配置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

}