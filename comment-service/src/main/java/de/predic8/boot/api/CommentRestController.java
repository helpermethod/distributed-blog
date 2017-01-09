package de.predic8.boot.api;

import de.predic8.boot.domain.Comment;
import de.predic8.boot.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CommentRepository commentRepository;

    public CommentRestController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Comment> findByPostId(Long postId) {
        logger.info("GET /comment");
        return commentRepository.findByPostId(postId);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Comment comment, UriComponentsBuilder uriComponentsBuilder) {
        Comment c = commentRepository.save(comment);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uriComponentsBuilder.path("/comments/{id}").buildAndExpand(c.getId()).toUriString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
