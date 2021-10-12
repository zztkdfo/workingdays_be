package com.ncits.workingdays.domain.dto;

import com.ncits.workingdays.config.BooleanToStringConverter;
import lombok.*;

import java.io.Serializable;

/**
 * 부서 사용자 매핑 - DepartmentMappingEmployeeDto
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(setterPrefix = "with", toBuilder = true)
public class DepartmentMappingEmployeeDto implements Serializable {

    private Long id;

    /**
     * 부서
     */
    private DepartmentDto department;
    private Long departmentId;

    /**
     * 부서명
     */
    private EmployeeDto employee;
    private Long employeeId;

    /**
     * 기본부서 야부(F인 경우 겸직으로 판단)
     */
    private Boolean isPrimary;

}
