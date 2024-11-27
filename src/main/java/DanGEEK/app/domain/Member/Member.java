package DanGEEK.app.domain.Member;

import DanGEEK.app.domain.*;
import DanGEEK.app.dto.member.MemberCreateResponseDto;
import DanGEEK.app.dto.MyPageDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member extends BaseEntity {
    @Getter
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;

    @Getter
    @Column(nullable = false)
    private String password;

    @Getter
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    @Getter
    private Boolean introductionWritten = false;
    @Column(nullable = false)
    @Getter
    private Boolean surveyDone = false;
    @Column(nullable = false)
    @Getter
    private Boolean putOnRecommend = false;
    @Getter
    @OneToOne
    @JoinColumn(name="introduction_id")
    private MemberIntroduction introduction;
    @Setter
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany
    @JoinColumn(name="posts")
    private List<Post> posts;
/*
    @OneToMany
    @JoinColumn(name = "notifications")
    private List<Notification> notifications;
*/

    @OneToOne
    @JoinColumn(name = "fcm_token")
    private FCMToken fcmToken;

    @OneToOne
    @Getter
    @JoinColumn(name = "member_analyze_info")
    private MemberAnalyzeInfo memberAnalyzeInfo;
    @Getter
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @Builder
    public Member(String username, String password, String nickname, Authority authority){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
    public static MemberCreateResponseDto memberToResponseDto(Member member) {
        return new MemberCreateResponseDto(member.username, member.nickname);
    }
    public static User memberToUser(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.authority.toString());
        return new User(
                String.valueOf(member.id),
                member.password,
                Collections.singleton(grantedAuthority)
        );
    }
    public void passwordReassign(String password){
        this.password = password;
    }
    public void  writeIntroduction(MemberIntroduction memberIntroduction){
        this.introductionWritten = true;
        this.introduction = memberIntroduction;
    }
    public void surveyDone(){
        this.surveyDone = true;
    }
    public void changePutOutToRecommend(Boolean putOnRecommend){
        this.putOnRecommend= putOnRecommend;
    }
    public MyPageDto MemberToMyPageDto(){
        return new MyPageDto(this.username, this.nickname, this.introductionWritten, this.surveyDone, this.putOnRecommend, this.imageUrl);
    }
}
