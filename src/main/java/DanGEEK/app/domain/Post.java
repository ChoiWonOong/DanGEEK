package DanGEEK.app.domain;

import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.dto.post.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "post_type")
    private PostType type;
    @Setter
    @Column(name = "image_url")
    private String imageUrl;
    //mate invite field
    @Column(name = "invite_number")
    private Long inviteNumber;
    //group buy field
    private String link;
    private String mallName;
    private String item;
    private String price;
    //complain field
    @Column(name = "dormitory_name")
    private String dormitoryName;
    @Column(name = "dorm_room_number")
    private String dormRoomNumber;
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public Post(PostCreateRequestDto dto, Member member, ChatRoom chatRoom) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.type = PostType.getPostType(dto.getPost_type());
        this.link = dto.getLink();
        this.mallName = dto.getMallName();
        this.item = dto.getItem();
        this.price = dto.getPrice();
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public Post(PostCreateRequestDto dto, Member member) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.type = PostType.getPostType(dto.getPost_type());
        this.member = member;
        this.dormitoryName = dto.getDormitoryName();
        this.dormRoomNumber = dto.getRoomNumber();
    }
    public Post(String title, String contents, PostType type, Member member, ChatRoom chatRoom) {
        this.title = title;
        this.contents = contents;
        this.type = type;
        this.member = member;
        this.chatRoom = chatRoom;
    }
    public PostResponseDto toResponseDto() {
        PostResponseDto dto;
        if (type == PostType.GROUP_BUY) {
            dto = new GroupBuyResponseDto(id, title, contents, member.getNickname(), type.getType(), link, mallName, item, price, chatRoom.toResponseDto());
        } else if (type == PostType.INVITE) {
            dto = new MateInviteResponseDto(id, title, contents, member.getNickname(), type.getType(), member.getIntroduction().toIntroductionDto(), chatRoom.toResponseDto());
        } else{
            dto = new ComplainResponseDto(id, title, contents, member.getNickname(), type.getType(), dormitoryName, dormRoomNumber);
        }
        log.info("dto : {}", dto);
        return dto;
    }
}
