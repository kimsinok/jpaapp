package com.example.jpaapp.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @Table(name = "t_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType; // MEMBER, ADMIN

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "withdraw_date")
    private LocalDateTime withdrawDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // 연관 관계 편의 메소드
    public void setTeam(Team team) {
        this.team = team;
        team.addMember(this);
    }

}
