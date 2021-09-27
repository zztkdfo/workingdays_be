package com.ncits.workingdays.domain.Vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeamVo implements Serializable {

    private Long id;

    private String teamCode;

    private String teamName;

    private String teamMail;

    private String teamLeader;

    private List<MemberVo> memberList = new ArrayList<>();

}
