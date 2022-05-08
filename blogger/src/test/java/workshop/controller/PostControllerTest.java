package workshop.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findAllPosts() {
        webTestClient.get()
                .uri("/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size", Matchers.is(3));
    }
}