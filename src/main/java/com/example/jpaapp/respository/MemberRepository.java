package com.example.jpaapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.jpaapp.domain.Member;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member AS m JOIN FETCH m.team")
    List<Member> findMembers();

}
