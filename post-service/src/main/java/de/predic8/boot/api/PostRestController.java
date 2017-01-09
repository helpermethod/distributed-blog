package de.predic8.boot.api;

import de.predic8.boot.dao.Comment;
import de.predic8.boot.dao.FullPost;
import de.predic8.boot.resource.PostRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    private final RestTemplate restTemplate;
    private final PostRepository postRepository;

    public PostRestController(RestTemplate restTemplate, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<FullPost> index() {
        return postRepository.findAll()
                .stream()
                .map(p -> {
                    List<Comment> comments = restTemplate.exchange(
                            "http://comment-service/comments?postId={postId}",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Comment>>() {
                            },
                            p.getId()
                    ).getBody();

                    return new FullPost(p.getTitle(), p.getContent(), comments);
                })
                .collect(Collectors.toList());
    }
}
