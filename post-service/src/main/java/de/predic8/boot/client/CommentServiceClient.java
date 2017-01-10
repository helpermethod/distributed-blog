package de.predic8.boot.client;

import de.predic8.boot.dao.Comment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "comment-service", fallback = CommentServiceClientFallback.class)
public interface CommentServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/comments")
    List<Comment> findByPostId(@RequestParam("postId") Long postId);
}