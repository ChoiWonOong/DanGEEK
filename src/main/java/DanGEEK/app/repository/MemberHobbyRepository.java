package DanGEEK.app.repository;

import DanGEEK.app.domain.Hobby;
import DanGEEK.app.domain.Member.Member;
import DanGEEK.app.domain.Member.MemberHobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberHobbyRepository extends JpaRepository<MemberHobby, Member>{
    List<MemberHobby> findByMemberAndHobby(Member member, Hobby hobby);
    List<MemberHobby> findByMember(Member member);
}
