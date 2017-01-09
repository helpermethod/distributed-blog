package de.predic8.boot.api;

import de.predic8.boot.dao.FullPost;
import de.predic8.boot.resource.PostRepository;
import de.predic8.boot.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostRestController {
    private final PostRepository postRepository;
    private final CommentService commentService;

    public PostRestController(PostRepository postRepository, CommentService commentService) {
        this.postRepository = postRepository;
        this.commentService = commentService;
    }

    @GetMapping
    public List<FullPost> index() {
        return postRepository.findAll()
                .stream()
                .map(p -> new FullPost(p.getTitle(), p.getContent(), commentService.findByPostId(p.getId())))
                .collect(Collectors.toList());
    }
}
