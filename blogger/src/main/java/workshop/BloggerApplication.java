package workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.SanitizingFunction;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.CommandLinePropertySource;
import workshop.model.AuthorProperties;

@SpringBootApplication
@EnableConfigurationProperties(AuthorProperties.class)
public class BloggerApplication {
    // new in spring boot v2.6.4
    public static void main(String[] args) {
        //SpringApplication.run(BloggerApplication.class, args);
        SpringApplication app = new SpringApplication(BloggerApplication.class);
        app.setApplicationStartup(new BufferingApplicationStartup(10000));
        app.run(args);
    }

    @Bean
    public CookieSameSiteSupplier cookieSameSiteSupplier() {
        return CookieSameSiteSupplier.ofStrict().whenHasName("myCookie");
    }

    @Bean
    public SanitizingFunction sanitizingFunction() {
        // program argument: --api.secret=password --api.version=1.0.0 --api.name=blogger-service
        return data -> data.getPropertySource().getName()
                .equals(CommandLinePropertySource.COMMAND_LINE_PROPERTY_SOURCE_NAME) ? data.withValue("this is top secret!") : data;
    }
}
