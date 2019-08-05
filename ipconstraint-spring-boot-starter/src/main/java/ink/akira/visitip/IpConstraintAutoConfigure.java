package ink.akira.visitip;

import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.IpClient;
import org.pac4j.http.credentials.authenticator.IpRegexpAuthenticator;
import org.pac4j.springframework.web.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableConfigurationProperties(IpConstraintProperties.class)
@Configuration
public class IpConstraintAutoConfigure implements WebMvcConfigurer {
    @Autowired
    private IpConstraintProperties ipConstraintProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        IpRegexpAuthenticator ipRegexpAuthenticator = new IpRegexpAuthenticator(ipConstraintProperties.getAllowedRegexIp());
        IpClient ipClient = new IpClient(ipRegexpAuthenticator);
        Config config = new Config(ipClient);
        SecurityInterceptor securityInterceptor = new SecurityInterceptor(config);
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns(ipConstraintProperties.getProtectedAntPath());
    }
}
