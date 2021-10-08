package com.ncits.workingdays.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 부서 정보 - DepartmentDto
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(setterPrefix = "with", toBuilder = true)
public class DepartmentDto implements Serializable {

    private Long id;

    /**
     * 부서 코드
     */
    private String departmentCode;

    /**
     * 부서명
     */
    private String departmentName;

    /**
     * 부서 관리자
     */
    private EmployeeDto departmentAdmin;

    /**
     * 상위 부서
     */
    private DepartmentDto upperDepartment;
}
