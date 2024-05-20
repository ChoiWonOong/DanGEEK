package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.PostCreateRequestDto;
import DanGEEK.app.dto.post.PostResponseDto;
import DanGEEK.app.dto.post.PostUpdateRequestDto;
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
    public PostResponseDto save(PostCreateRequestDto postDto){
        Post post = new Post(postDto.getTitle(),postDto.getContents(), postDto.getPost_type(), memberRepository.findById(SecurityUtil.getCurrentMemberId()).get());
        postRepository.save(post);
        return post.toDto();
    }
    public PostResponseDto update(PostUpdateRequestDto postDto){
        Post post = postRepository.findById(postDto.getPost_id())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        post.setTitle(postDto.getTitle());
        post.setContents(postDto.getContents());
        postRepository.save(post);
        return post.toDto();
    }
    public PostResponseDto delete(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        PostResponseDto postDto = post.toDto();
        postRepository.delete(post);
        return postDto;
    }
    public PostResponseDto getPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_FOUND)).toDto();
    }
    public List<PostResponseDto> getPosts(PostType postType){
        List<Post> postList = postRepository.findByType(postType);
        List<PostResponseDto> result = new LinkedList<>();
        for(Post post : postList){
            result.add(post.toDto());
        }
        return result;
    }
}