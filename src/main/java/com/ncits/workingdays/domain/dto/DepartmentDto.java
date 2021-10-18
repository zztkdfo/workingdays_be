package com.ncits.workingdays.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 부서 관리자 ID
     */
    private Long departmentAdminId;

    /**
     * 상위 부서
     */
    private DepartmentDto upperDepartment;

    /**
     * 상위 부서 ID
     */
    private Long upperDepartmentId;
}
