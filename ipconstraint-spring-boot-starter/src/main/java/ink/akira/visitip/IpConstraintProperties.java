package ink.akira.visitip;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ip-constraint")
public class IpConstraintProperties {
    private boolean enable = true;
    private String allowedRegexIp = "127\\.0\\.0\\.1|0:0:0:0:0:0:0:1";
    private String protectedAntPath;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

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
