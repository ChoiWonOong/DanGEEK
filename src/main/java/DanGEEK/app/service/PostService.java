package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.MemberIntroduction;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.*;
import DanGEEK.app.repository.MemberIntroductionRepository;
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
    private final MemberIntroductionRepository memberIntroductionRepository;

    public PostResponseDto save(PostCreateRequestDto postDto){
        Post post = new Post(postDto.getTitle(),postDto.getContents(), PostType.getPostType(postDto.getPost_type()), memberRepository.findById(SecurityUtil.getCurrentMemberId()).get());
        postRepository.save(post);
        return post.toResponseDto();
    }
    public PostResponseDto update(PostCreateRequestDto postDto){
        Post post = postRepository.findById(postDto.getPostId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        post.setTitle(postDto.getTitle());
        post.setContents(postDto.getContents());
        postRepository.save(post);
        return post.toResponseDto();
    }
    public PostResponseDto delete(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        PostResponseDto postDto = post.toResponseDto();
        postRepository.delete(post);
        return postDto;
    }
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        PostResponseDto postResponseDto = post.toResponseDto();
        if(post.getType().equals(PostType.INVITE)){
            MateInviteResponseDto mateInviteResponseDto = (MateInviteResponseDto) postResponseDto;
            MemberIntroduction memberIntroduction = memberIntroductionRepository.findByMember(memberRepository.findById(SecurityUtil.getCurrentMemberId()).get())
                    .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
            mateInviteResponseDto.setMemberIntroductionCreateDto(memberIntroduction.toIntroductionDto());
        }
        else if(post.getType().equals(PostType.GROUP_BUY)){
            GroupBuyResponseDto groupBuyResponseDto = (GroupBuyResponseDto) postResponseDto;
            groupBuyResponseDto.setGroupBuyInfo(post.getLink(),post.getMallName(),post.getItem(),post.getPrice());
        }
        else if(post.getType().equals(PostType.COMPLAIN)){
            ComplainResponseDto complaintResponseDto = (ComplainResponseDto) postResponseDto;
            complaintResponseDto.setRoomNumber(post.getRoomNumber());
        }
        else{
            throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        }
        return postResponseDto;
    }
    public List<PostResponseDto> getPosts(PostType postType){
        List<Post> postList = postRepository.findByType(postType);
        List<PostResponseDto> result = new LinkedList<>();
        for(Post post : postList){
            result.add(post.toResponseDto());
        }
        return result;
    }
}