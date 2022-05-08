package workshop.controller;

import org.springframework.web.bind.annotation.*;
import workshop.model.Post;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    @GetMapping("/posts/{name}/{name}")
    public void findPostByAuthor(@PathVariable String name) {
        System.out.println("name: " + name);
    }

    @GetMapping("/posts")
    public List<String> findAllPosts() {
        return List.of("Post 1", "Post 2", "Post 3");
    }

    @PostMapping("/posts")
    public void create(@Valid @RequestBody Post post) {
        System.out.println(post);
    }
}
