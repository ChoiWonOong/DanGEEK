/*
package DanGEEK.app.service;


import DanGEEK.app.Exception.ErrorCode;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Chat.ChatRoomMember;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberIntroduction;
import DanGEEK.app.domain.Notification;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.Notification.MateNotificationSendDto;
import DanGEEK.app.dto.Notification.NotificationAnswerDto;
import DanGEEK.app.dto.Notification.NotificationSendDto;
import DanGEEK.app.repository.EmitterRepository;
import DanGEEK.app.repository.MemberIntroductionRepository;
import DanGEEK.app.repository.NotificationRepository;
import DanGEEK.app.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    // 기본 타임아웃 설정
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;
    private final PostService postService;
    private final NotificationRepository notificationRepository;
    private final MemberIntroductionRepository memberIntroductionRepository;
    private final ChatRoomMemberService chatRoomMemberService;
    private final MemberService memberService;

    public ChatRoomMember answerNotification(NotificationAnswerDto notificationAnswerDto){
        Notification notification = notificationRepository.findById(notificationAnswerDto.getNotificationId())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        ChatRoomMember chatRoomMember = chatRoomMemberService.createChatRoomMember(notification.getPost().getChatRoom().getRoomId());
        notificationRepository.delete(notification);
        return chatRoomMember;
    }
    public MateNotificationSendDto getNotification(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        MemberIntroduction memberIntroduction = memberIntroductionRepository.findByMember(notification.getSender())
                .orElseThrow(()->new RestApiException(ErrorCode.NOT_EXIST_ERROR));
        MateNotificationSendDto notificationSendDto = notification.toMateNotificationDto();
        notificationSendDto.setMemberIntroduction(memberIntroduction.toIntroductionDto());
        return notificationSendDto;
    }
    public List<NotificationSendDto> getNotificationList(){
        List<Notification> notificationList = notificationRepository.findAllByReceiver(memberService.getMe());
        List<NotificationSendDto> notificationSendDtoList = new ArrayList<>();
        for(Notification n : notificationList){
            if(n.getPost().getType().equals(PostType.INVITE)){
                notificationSendDtoList.add(n.toMateNotificationDto());
            }
            else if(n.getPost().getType().equals(PostType.GROUP_BUY)){
                notificationSendDtoList.add(n.toGroupBuyNotificationDto());
            }
        }
        return notificationSendDtoList;
    }
    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = createEmitter(userId);

        sendToClient(userId, "EventStream Created. [userId=" + userId + "]");
        return emitter;
    }

    */
/**
     * 서버의 이벤트를 클라이언트에게 보내는 메서드
     * 다른 서비스 로직에서 이 메서드를 사용해 데이터를 Object event에 넣고 전송하면 된다.
     * param userId - 메세지를 전송할 사용자의 아이디.
     * param event  - 전송할 이벤트 객체.
     *//*

    public Notification createNotification(Post post, Member sender){
        Notification notification = new Notification(post, post.getMember(), sender);
        return notificationRepository.save(notification);
    }
    public NotificationSendDto notify(NotificationSendDto notificationSendDto) {
        Member sender = memberService.findMemberByNickname(notificationSendDto.getSender());
        Post post = postService.findById(notificationSendDto.getPostId());
        Member receiver = memberService.findMemberByNickname(notificationSendDto.getReceiver());
        Notification notification = new Notification(post, sender, receiver);
        notificationRepository.save(notification);
        if(post.getType().equals(PostType.INVITE)){
            sendToClient(receiver.getId(), notification.toMateNotificationDto());
        }
        else if(post.getType().equals(PostType.GROUP_BUY)){
            sendToClient(receiver.getId(), notification.toGroupBuyNotificationDto());
        }
        sendToClient(receiver.getId(), "알림이 도착했습니다.");
        return notificationSendDto;
    }

    */
/**
     * 클라이언트에게 데이터를 전송
     *
     * @param id   - 데이터를 받을 사용자의 아이디.
     * @param data - 전송할 데이터.
     *//*

    private void sendToClient(Long id, Object data) { //data
        SseEmitter emitter = emitterRepository.get(id);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().id(String.valueOf(id)).name("sse").data(data));
            } catch (IOException exception) {
                emitterRepository.deleteById(id);
                emitter.completeWithError(exception);
            }
        }
    }

    */
/**
     * 사용자 아이디를 기반으로 이벤트 Emitter를 생성
     *
     * @param id - 사용자 아이디.
     * @return SseEmitter - 생성된 이벤트 Emitter.
     *//*

    private SseEmitter createEmitter(Long id) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(id, emitter);

        // Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        // Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;
    }
}*/
