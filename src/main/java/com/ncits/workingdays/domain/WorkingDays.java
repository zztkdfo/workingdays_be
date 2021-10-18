package com.ncits.workingdays.domain;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 근무일 정보
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
@Entity
@Builder
@Table(name = "TB_WORKING_DAYS")
public class WorkingDays extends AuditableEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORKING_DAYS_ID", columnDefinition = "bigint", nullable = false)
    private Long id;

    /**
     * 석식 시간
     */
    @Column(name = "BREAK_TIME", nullable = true)
    private String breakTime;

    /**
     * 석식 시간
     */
    @Column(name = "DINNER", nullable = true)
    private String dinner;

    /**
     * 사원
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), nullable = true)
    private Employee employee;

    /**
     * 근무 종료 시간
     */
    @Column(name = "END_TIME", nullable = false)
    private String endTime;

    /**
     * 비고
     */
    @Column(name = "ETC", nullable = false)
    private String etc;

    /**
     * 점심 시간
     */
    @Column(name = "LUNCH", nullable = true)
    private String lunch;

    /**
     * 근무 시작 시간
     */
    @Column(name = "START_TIME", nullable = false)
    private String startTime;

    /**
     * 근무일
     */
    @Column(name="WORKING_DATE", nullable = false, columnDefinition = "datetime")
    private Timestamp workingDate;

    /**
     * 근무시간(하루)
     */
    @Column(name="WORKING_TIME", nullable = true)
    private Integer workingTIme;

    /**
     * 근무 유형
     */
    @Column(name = "WORKING_TYPE", nullable = false)
    private String workingType;

    /**
     * 근무 유형 시간
     */
    @Column(name = "WORKING_TYPE_TIME", nullable = false)
    private String workingTypeTime;

}
