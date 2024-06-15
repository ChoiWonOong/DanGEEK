package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.ChatRoom;
import DanGEEK.app.domain.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberIntroduction;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.*;
import DanGEEK.app.repository.*;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final ChatRoomMemberService chatRoomMemberService;

    public Post createInvitePost(PostCreateRequestDto postDto){
        ChatRoom chatRoom = chatRoomService.createChatRoom(memberService.getMe().getNickname(), postDto.getMaxUser());
        Post post = new Post(postDto.getTitle(),postDto.getContents(), PostType.getPostType(postDto.getPost_type()), memberService.getMe(), chatRoom);
        post = postRepository.save(post);
        log.info("post : {} {}", post.getMember().getIntroduction().getContents(), chatRoom.getRoomId());
        // enterRoom
        ChatRoomMember chatRoomMember = chatRoomMemberService.createChatRoomMember(chatRoom.getRoomId(), SecurityUtil.getCurrentMemberId());
        log.info("chatRoomMember : {} {}", chatRoomMember.getMemberId(), chatRoomMember.getRoomId());
        return post;
    }
    public Post createGroupBuyPost(PostCreateRequestDto postDto, String url){
        Post post = new Post(postDto.getTitle(),postDto.getContents(), PostType.GROUP_BUY, memberService.getMe(), postDto.getLink(),postDto.getMallName(),postDto.getItem(),postDto.getPrice());
        post.setImageUrl(url);
        post = postRepository.save(post);
        return post;
    }
    public Post createComplainPost(PostCreateRequestDto postDto){
        Post post = new Post(postDto.getTitle(),postDto.getContents(), PostType.COMPLAIN, memberService.getMe(), postDto.getDormitoryName(),postDto.getRoomNumber());
        post = postRepository.save(post);
        return post;
    }

    public Post update(PostCreateRequestDto postDto){
        Post post = postRepository.findById(postDto.getPostId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        post.setTitle(postDto.getTitle());
        post.setContents(postDto.getContents());
        postRepository.save(post);
        return post;
    }
    public Post delete(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        postRepository.delete(post);
        return post;
    }
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        PostResponseDto postResponseDto = post.toResponseDto();
        if(post.getType().equals(PostType.INVITE)){
            MateInviteResponseDto mateInviteResponseDto = (MateInviteResponseDto) postResponseDto;
            Member member = memberService.findMemberById(post.getMember().getId());
            MemberIntroduction memberIntroduction = member.getIntroduction();
            mateInviteResponseDto.setMemberIntroduction(memberIntroduction.toIntroductionDto());
        }
        else if(post.getType().equals(PostType.GROUP_BUY)){
            GroupBuyResponseDto groupBuyResponseDto = (GroupBuyResponseDto) postResponseDto;
            groupBuyResponseDto.setGroupBuyInfo(post.getLink(),post.getMallName(),post.getItem(),post.getPrice());
        }
        else if(post.getType().equals(PostType.COMPLAIN)){
            ComplainResponseDto complaintResponseDto = (ComplainResponseDto) postResponseDto;
            complaintResponseDto.setRoomNumber(post.getDormRoomNumber());
        }
        else{
            throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        }
        return postResponseDto;
    }
    public List<Post> getPosts(PostType postType){
        return postRepository.findByType(postType);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
    }
}