package de.predic8.boot.api;

import de.predic8.boot.client.CommentServiceClient;
import de.predic8.boot.dao.FullPost;
import de.predic8.boot.resource.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    private final PostRepository postRepository;
    private final CommentServiceClient commentServiceClient;

    public PostRestController(PostRepository postRepository, CommentServiceClient commentServiceClient) {
        this.postRepository = postRepository;
        this.commentServiceClient = commentServiceClient;
    }

    @GetMapping
    public List<FullPost> index() {
        return postRepository.findAll()
                .stream()
                .map(p -> new FullPost(p.getTitle(), p.getContent(), commentServiceClient.findByPostId(p.getId())))
                .collect(Collectors.toList());
    }
}
