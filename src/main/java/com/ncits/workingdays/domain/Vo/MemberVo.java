package com.ncits.workingdays.domain.Vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MemberVo implements Serializable {

    private Long id;

    private String memberCode;

    private String memberAccount;

    private String memberName;

    private TeamVo team;

    private Long teamId;

}
