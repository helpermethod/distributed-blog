package de.predic8.boot.resource;

import de.predic8.boot.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostResource extends JpaRepository<Post, Long> {
}