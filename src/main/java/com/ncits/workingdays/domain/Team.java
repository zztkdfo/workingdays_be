package com.ncits.workingdays.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name ="TB_TEAM")

public class Team {

    @Id
    @Column(name = "TEAM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEAM_CODE", nullable = false)
    private String teamCode;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Column(name = "TEAM_MAIL")
    private String teamMail;

    @Column(name = "TEAM_LEADER")
    private String teamLeader;

    @OneToMany(mappedBy = "team")
    private List<Member> memberList = new ArrayList<>();

}
