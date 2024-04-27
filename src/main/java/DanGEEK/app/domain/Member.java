package DanGEEK.app.domain;

import DanGEEK.app.dto.MemberCreateResponseDto;
import DanGEEK.app.dto.MyPageDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @Column(name ="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private Boolean introductionWritten = false;

    @Column(nullable = false)
    private Boolean putOnRecommend = false;
    @OneToOne
    @JoinColumn(name="introduction_id")
    private MemberIntroduction introduction;

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
    public Member passwordReassign(String password){
        this.password = password;
        return this;
    }
    public void writeIntroduction(MemberIntroduction memberIntroduction){
        this.introductionWritten = true;
        this.introduction = memberIntroduction;
    }
    public void changePutOutToRecommend(){
        this.putOnRecommend= !this.putOnRecommend;
    }
    public MyPageDto MemberToMyPageDto(){
        return new MyPageDto(this.username, this.nickname, this.introductionWritten, this.putOnRecommend);
    }
    public MemberIntroduction getIntroduction(){
        return this.introduction;
    }
}
