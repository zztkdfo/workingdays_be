package com.ncits.workingdays.domain.dto;

import com.ncits.workingdays.domain.AuditableEntity;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 근무일 계획 정보 - WorkingDaysPlanDto
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(setterPrefix = "with", toBuilder = true)
public class WorkingDaysPlanDto extends AuditableEntity<Long> implements Serializable {

    private Long id;

    /**
     * 석식 시간
     */
    private String breakTime;

    /**
     * 석식 시간
     */
    private String dinner;

    /**
     * 사원
     */
    private EmployeeDto employee;

    /**
     * 근무 종료 시간
     */
    private String endTime;

    /**
     * 비고
     */
    private String etc;

    /**
     * 점심 시간
     */
    private String lunch;

    /**
     * 근무 시작 시간
     */
    private String startTime;

    /**
     * 근무일
     */
    private Timestamp workingDate;

    /**
     * 근무시간(하루)
     */
    private Integer workingTIme;

    /**
     * 근무 유형
     */
    private String workingType;

    /**
     * 근무 유형 시간
     */
    private String workingTypeTime;

}
