package DanGEEK.app.repository;

import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByType(PostType postType);
}
