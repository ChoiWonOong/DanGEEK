package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.PostDto;
import DanGEEK.app.repository.MemberRepository;
import DanGEEK.app.repository.PostRepository;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public PostDto save(PostDto postDto){
        Post post = new Post(postDto.getTitle(),postDto.getContents(), postDto.getPost_type(), memberRepository.findById(SecurityUtil.getCurrentMemberId()).get());
        postRepository.save(post);
        return postDto;
    }
    public PostDto getPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_FOUND)).toDto();
    }
    public List<PostDto> getPosts(PostType postType){
        List<Post> postList = postRepository.findByType(postType);
        List<PostDto> result = new LinkedList<>();
        for(Post post : postList){
            result.add(post.toDto());
        }
        return result;
    }
}