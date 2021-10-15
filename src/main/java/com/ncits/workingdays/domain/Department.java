package com.ncits.workingdays.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 부서 정보 - Department
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
@Entity
@Builder
@Table(name = "TB_DEPARTMENT")
public class Department extends AuditableEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", columnDefinition = "bigint", nullable = false)
    private Long id;

    /**
     * 부서 코드
     */
    @Column(name = "DEPARTMENT_CODE", nullable = false)
    private String departmentCode;

    /**
     * 부서명
     */
    @Column(name = "DEPARTMENT_NAME", nullable = false)
    private String departmentName;

    /**
     * 부서 관리자
     */
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ADMIN", referencedColumnName = "EMPLOYEE_ID", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), nullable = true)
    private Employee departmentAdmin;

    /**
     * 상위 부서
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UPPER_DEPARTMENT", referencedColumnName = "DEPARTMENT_ID", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Department upperDepartment;
}
