package DanGEEK.app.service;

import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Chat.ChatRoom;
import DanGEEK.app.domain.Chat.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberIntroduction;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.*;
import DanGEEK.app.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final ChatRoomMemberService chatRoomMemberService;
    private final ChatService chatService;
    public Post createInvitePost(PostCreateRequestDto postDto) {
        // 채팅방 생성
        ChatRoom chatRoom = chatRoomService.createChatRoom(memberService.getMe().getNickname(), postDto.getMaxUser());
        // 게시글 생성
        Post post = new Post(postDto.getTitle(), postDto.getContents(), PostType.getPostType(postDto.getPost_type()), memberService.getMe(), chatRoom);
        // 게시글 저장
        post = postRepository.save(post);
        // 채팅방 입장
        ChatRoomMember chatRoomMember = chatRoomMemberService.createChatRoomMember(chatRoom.getRoomId());
        //log.info("chatRoomMember : {} {}", chatRoomMember.getMemberId(), chatRoomMember.getRoomId());
        return post;
    }
    public Post createGroupBuyPost(PostCreateRequestDto postDto, String url) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(postDto.getTitle(), 4);
        log.info("chatRoom : {} {}", chatRoom.getRoomId(), chatRoom.getMaxUser());
        Post post = new Post(postDto, memberService.getMe(), chatRoom);
        post.setImageUrl(url);
        post = postRepository.save(post);
        // enterRoom
        ChatRoomMember chatRoomMember = chatRoomMemberService.createChatRoomMember(chatRoom.getRoomId());
        log.info("chatRoomMember : {} {}", chatRoomMember.getMemberId(), chatRoomMember.getRoomId());
        return post;
    }
    public Post createComplainPost(PostCreateRequestDto postDto){
        log.info("postDto : {}", postDto.getDormitoryName(), postDto.getRoomNumber());
        Post post = new Post(postDto, memberService.getMe());
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
    @Transactional
    public PostDeleteDto delete(Long post_id){
        try{
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        Long roomId;
        if(post.getType().equals(PostType.INVITE)){
            roomId = post.getChatRoom().getRoomId();
        }
        else if(post.getType().equals(PostType.GROUP_BUY)){
            roomId = post.getChatRoom().getRoomId();
        }
        else{
            throw new RestApiException(ErrorCode.NOT_EXIST_ERROR);
        }

        log.info("delete post : {}", post_id);
        chatRoomService.deleteChatRoomMember(roomId);
        postRepository.delete(post);
        return new PostDeleteDto(post_id, roomId);
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
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