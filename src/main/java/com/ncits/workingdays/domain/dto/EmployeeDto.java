package com.ncits.workingdays.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 사용자 정보 - EmployeeDto
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(setterPrefix = "with", toBuilder = true)
public class EmployeeDto implements Serializable {

    private Long id;

    /**
     * 계정
     */
    private String employeeAccount;

    /**
     * 사번
     */
    private String employeeNumber;

    /**
     * 성명
     */
    private String employeeName;

    /**
     * 직급(팀원, 팀장, 실장 ... )
     */
    private String position;

    /**
     * 이메일
     */
    private String employeeMail;

}
