//package com.db117.adminstaging.config.security;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.RememberMeServices;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
///**
// * @author 大兵
// * @date 2018-04-15 18:07
// **/
//// 注解开启Spring Security的功能
//@EnableWebSecurity
////开启security注解
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private CustomUserServiceImpl customUserServiceImpl;
//    @Autowired
//    private DruidDataSource dataSource;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                //定义哪些url需要保护，哪些url不需要保护
//                .authorizeRequests()
//                //定义不需要认证就可以访问
//                .antMatchers("/static/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //定义当需要用户登录时候，转到的登录页面
//                .loginPage("/login")
//                .successForwardUrl("/index")
//                .permitAll()
//                //当用户通过验证后进行一系列操作
//                .successHandler(loginSuccessHandler())
//                .and()
//                .logout()
//                .permitAll()
//                //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
//                .and()
//                .rememberMe()
//                .rememberMeServices(rememberMeServices())
//                .key("db117")
//                .tokenValiditySeconds(1209600);
//
//        http.csrf().disable()
//                //解决iframe不能访问问题
//                .headers().frameOptions().sameOrigin();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //定义密码编码格式
//        auth.userDetailsService(customUserServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    /**
//     * 配置TokenRepository 把token写入到数据库
//     */
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        // 配置数据源
//        jdbcTokenRepository.setDataSource(dataSource);
//        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
//        // jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }
//
//    /**
//     * 登录成功后跳转的页面 用户或者管理员登录日志
//     */
//    @Bean
//    public LoginSuccessHandler loginSuccessHandler() {
//        return new LoginSuccessHandler();
//    }
//
//    /**
//     * 返回 RememberMeServices 实例
//     */
//    @Bean
//    public RememberMeServices rememberMeServices() {
//        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
//        // 此处需要设置数据源，否则无法从数据库查询验证信息
//        rememberMeTokenRepository.setDataSource(dataSource);
//
//        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
//        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
//        PersistentTokenBasedRememberMeServices rememberMeServices =
//                new PersistentTokenBasedRememberMeServices("db117", customUserServiceImpl, rememberMeTokenRepository);
//
//        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
//        rememberMeServices.setParameter("remember-me");
//        return rememberMeServices;
//    }
//}