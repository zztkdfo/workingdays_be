package com.ncits.workingdays.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 사용자 정보 - Employee
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
@Entity
@Builder
@Table(name = "TB_EMPLOYEE")
public class Employee extends AuditableEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", columnDefinition = "bigint", nullable = false)
    private Long id;

    /**
     * 계정
     */
    @Column(name = "EMPLOYEE_ACCOUNT", nullable = false)
    private String employeeAccount;

    /**
     * 사번
     */
    @Column(name = "EMPLOYEE_NUMBER", nullable = false)
    private String employeeNumber;

    /**
     * 성명
     */
    @Column(name = "EMPLOYEE_NAME", nullable = false)
    private String employeeName;

    /**
     * 직급(팀원, 팀장, 실장 ... )
     */
    @Column(name = "POSITION", nullable = false)
    private String position;

    /**
     * 이메일
     */
    @Column(name = "EMPLOYEE_MAIL", nullable = false)
    private String employeeMail;

}
