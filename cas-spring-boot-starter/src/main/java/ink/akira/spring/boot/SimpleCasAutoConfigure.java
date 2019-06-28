package ink.akira.spring.boot;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by akira on 2018/11/30.
 */
@Configuration
@EnableConfigurationProperties(SimpleCasProperties.class)
public class SimpleCasAutoConfigure {
    @Autowired
    private SimpleCasProperties simpleCasProperties;

    @Bean
    public FilterRegistrationBean singleSignOutFilterBean(SingleSignOutFilter singleSignOutFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(singleSignOutFilter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerUrlPrefix", simpleCasProperties.getClientUrlPrefix());
        return registration;
    }

    @Bean
    public FilterRegistrationBean authenticationFilterBean(AuthenticationFilter authenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authenticationFilter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerLoginUrl", simpleCasProperties.getServerLoginUrl());
        registration.addInitParameter("serverName", simpleCasProperties.getClientUrlPrefix());
        return registration;
    }

    @Bean
    public FilterRegistrationBean ticketValidationFilterBean(Cas20ProxyReceivingTicketValidationFilter ticketValidationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ticketValidationFilter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("casServerUrlPrefix", simpleCasProperties.getServerUrlPrefix());
        registration.addInitParameter("serverName", simpleCasProperties.getClientUrlPrefix());
        return registration;
    }

    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilterBean(HttpServletRequestWrapperFilter httpServletRequestWrapperFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(httpServletRequestWrapperFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean assertionThreadLocalFilterBena(AssertionThreadLocalFilter assertionThreadLocalFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(assertionThreadLocalFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 单点退出，可选
     * @return
     */
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        return new SingleSignOutFilter();
    }

    /**
     * 用户认证，必需
     * @return
     */
    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    /**
     * Ticket校验，必需
     * @return
     */
    @Bean
    public Cas20ProxyReceivingTicketValidationFilter ticketValidationFilter() {
        return new Cas20ProxyReceivingTicketValidationFilter();
    }

    /**
     * 对HttpServletRequest请求包装， 可通过HttpServletRequest.getRemoteUser()方法获得登录用户的登录名，可选
     * @return
     */
    @Bean
    public HttpServletRequestWrapperFilter httpServletRequestWrapperFilter() {
        return new HttpServletRequestWrapperFilter();
    }

    /**
     * 该过滤器使得可以通过org.jasig.cas.client.util.AssertionHolder.getAssertion().getPrincipal().getName()来获取用户的登录名。
     * 这个类把Assertion信息放在ThreadLocal变量中，这样应用程序不在web层也能够获取到当前登录信息
     * 可选
     * @return
     */
    @Bean
    public AssertionThreadLocalFilter assertionThreadLocalFilter() {
        return new AssertionThreadLocalFilter();
    }
}
