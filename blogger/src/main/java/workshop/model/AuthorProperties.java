package workshop.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("blogger.author")
public record AuthorProperties(String firstName, String lastName, String email) {
}
