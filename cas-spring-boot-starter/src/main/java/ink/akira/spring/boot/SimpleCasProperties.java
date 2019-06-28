package ink.akira.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by akira on 2018/11/30.
 */
@ConfigurationProperties(prefix = "cas")
public class SimpleCasProperties {
    public static final String LOG_IN = "login";
    public static final String LOG_OUT = "logout";

    private Server server;
    private Client client;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static class Server {
        private Url url;

        public Url getUrl() {
            return url;
        }

        public void setUrl(Url url) {
            this.url = url;
        }

        public static class Url{
            private String prefix;

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }
    }
    public static class Client {
        private Url url;

        public Url getUrl() {
            return url;
        }

        public void setUrl(Url url) {
            this.url = url;
        }

        public static class Url{
            private String prefix;

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }
    }

    public String getServerUrlPrefix() {
        return this.server.getUrl().getPrefix();
    }

    public String getClientUrlPrefix() {
        return this.client.getUrl().getPrefix();
    }

    public String getServerLoginUrl(){
        return this.getServerUrlPrefix() + "/" + LOG_IN;
    }

    public String getServerLogoutUrl(){
        return this.getServerUrlPrefix() + "/" +  LOG_OUT;
    }
}
