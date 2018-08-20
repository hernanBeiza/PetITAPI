package cl.petit.api;

import cl.petit.api.config.ArchivoConfig;
import cl.petit.api.config.JwtFilter;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "cl.petit.api")
@EnableJpaRepositories(basePackages = "cl.petit.api.persistence.daos")
@EntityScan(basePackages = "cl.petit.api.models.entities")
@EnableJpaAuditing

@EnableConfigurationProperties({
        ArchivoConfig.class
})
public class PetItapiApplication {

    @Bean
    public TomcatServletWebServerFactory tomcatEmbeddedServletContainerFactory() {
        return new TomcatServletWebServerFactory(){
            @Override
            protected void customizeConnector(Connector connector) {
                super.customizeConnector(connector);
                connector.setParseBodyMethods("POST,PUT,DELETE");
            }
        };
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(PetItapiApplication.class, args);
    }
}
