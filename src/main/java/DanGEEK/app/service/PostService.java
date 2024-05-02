package DanGEEK.app.service;

import DanGEEK.app.domain.Post;
import DanGEEK.app.dto.PostDto;
import DanGEEK.app.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostDto save(PostDto postDto){
        Post post = new Post(postDto.getTitle(),postDto.getContents(), postDto.getPost_type());
        postRepository.save(post);
        return postDto;
    }
}