package de.predic8.boot.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.predic8.boot.dao.Comment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    private final RestTemplate restTemplate;

    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "findByPostIdFallback")
    public List<Comment> findByPostId(Long postId) {
        return restTemplate.exchange(
                "http://comment-service/comments?postId={postId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {},
                postId).getBody();
    }

    private List<Comment> findByPostIdFallback() {
        return Collections.emptyList();
    }
}