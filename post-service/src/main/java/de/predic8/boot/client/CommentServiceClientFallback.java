package de.predic8.boot.client;

import de.predic8.boot.dao.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Component
public class CommentServiceClientFallback implements CommentServiceClient{
    @Override
    public List<Comment> findByPostId(@RequestParam("postId") Long postId) {
        return Collections.emptyList();
    }
}
