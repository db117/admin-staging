//package com.db117.adminstaging.config.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.SecurityMetadataSource;
//import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
//import org.springframework.security.access.intercept.InterceptorStatusToken;
//import org.springframework.security.web.FilterInvocation;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
// * 供Spring Security使用，用于权限校验。
// *
// * @author 大兵
// * @date 2018-04-19 14:51
// **/
//@Slf4j
//public class MySecurityFilter extends AbstractSecurityInterceptor
//        implements Filter {
//    //    @Autowired
//    private CustomAccessDecisionManager customAccessDecisionManager;
//    //    @Autowired
//    private CustomSecurityMetadataSource customSecurityMetadataSource;
//
//    public void invoke(FilterInvocation fi) throws IOException, ServletException {
//        //fi里面有一个被拦截的url
//        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
//        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
//        InterceptorStatusToken token = super.beforeInvocation(fi);
//        try {
//            //执行下一个拦截器
//            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//        } finally {
//            super.afterInvocation(token, null);
//        }
//
//    }
//
//    @PostConstruct
//    public void init() {
////        super.setAuthenticationManager(authenticationManager);
//        super.setAccessDecisionManager(customAccessDecisionManager);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("自定义过滤器开始....");
//    }
//
//    /**
//     * 登陆后，每次访问资源都通过这个拦截器拦截
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        FilterInvocation fi = new FilterInvocation(request, response, chain);
//        invoke(fi);
//    }
//
//    @Override
//    public void destroy() {
//        log.info("自定义过滤器结束....");
//    }
//
//
//    @Override
//    public Class<?> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }
//
//    @Override
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return customSecurityMetadataSource;
//    }
//}
