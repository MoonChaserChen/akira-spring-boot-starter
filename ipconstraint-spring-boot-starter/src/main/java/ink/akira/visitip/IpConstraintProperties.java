package ink.akira.visitip;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "resource")
public class IpConstraintProperties {
    private String allowedRegexIp = "127\\.0\\.0\\.1|0:0:0:0:0:0:0:1";
    private String protectedAntPath;

    public String getAllowedRegexIp() {
        return allowedRegexIp;
    }

    public void setAllowedRegexIp(String allowedRegexIp) {
        this.allowedRegexIp = allowedRegexIp;
    }

    public String getProtectedAntPath() {
        return protectedAntPath;
    }

    public void setProtectedAntPath(String protectedAntPath) {
        this.protectedAntPath = protectedAntPath;
    }
}
