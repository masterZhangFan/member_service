package cn.gaozheng.sales.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsConfig {
    /**
     * http端口
     */
    @Value("${server.port.http}")
    private int httpPort;
    /**
     * 服务端口
     */
    @Value("${server.port}")
    private int serverPort;

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityCollection collection = new SecurityCollection();
                // 添加下面一行代码，可以实现http和https同时访问，但是http无法重定向到https
                // 如果注释该行代码，http可以重定向到https，但是所有请求都会变成get请求
                 collection.addMethod("post");
                collection.addPattern("/*");
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        factory.addAdditionalTomcatConnectors(httpConnector());
        return factory;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(serverPort);
        return connector;
    }
}