//package com.db117.adminstaging.config.security;
//
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.access.vote.AbstractAccessDecisionManager;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源 这个也称为授权器，<br>
// *
// * @author 大兵
// * @date 2018-04-19 10:45
// **/
////@Component
//public class CustomAccessDecisionManager extends AbstractAccessDecisionManager {
//
//    protected CustomAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
//        super(decisionVoters);
//    }
//
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
//            throws AccessDeniedException, InsufficientAuthenticationException {
//        // 检查用户是否够权限访问资源
//        // 参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
//        // 参数object是url
//        // 参数configAttributes所需的权限
//        if (configAttributes == null) {
//            return;
//        }
//        for (ConfigAttribute ca : configAttributes) {
//            String needRole = ((SecurityConfig) ca).getAttribute();
//            for (GrantedAuthority ga : authentication.getAuthorities()) {
//                if (needRole.equals(ga.getAuthority())) {
//                    return;
//                }
//            }
//        }
//        // 注意：执行这里，后台是会抛异常的，但是界面会跳转到所配的access-denied-page页面
//        throw new AccessDeniedException("没有权限,拒绝访问!");
//    }
//}
