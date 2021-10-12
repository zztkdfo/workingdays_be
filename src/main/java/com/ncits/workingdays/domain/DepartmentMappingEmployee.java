package com.ncits.workingdays.domain;

import com.ncits.workingdays.config.BooleanToStringConverter;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 사용자 부서 매핑 정보 - DepartmentMappingEmployee
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Audited
@Entity
@Builder
@Table(name = "TB_DEPARTMENT_MAPPING_EMPLOYEE")
public class DepartmentMappingEmployee extends AuditableEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_MAPPING_EMPLOYEE_ID", columnDefinition = "bigint", nullable = false)
    private Long id;


    /**
     * Department
     */
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Department department;

    /**
     * Department ID
     */
    @Column(name = "DEPARTMENT_ID", insertable = false, updatable = false)
    private Long departmentId;

    /**
     * Employee
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Employee employee;

    /**
     * Employee ID
     */
    @Column(name = "EMPLOYEE_ID", insertable = false, updatable = false)
    private Long employeeId;


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
     * 기본부서 여부(F인 경우 겸직으로 판단)
     */
    @Convert(converter = BooleanToStringConverter.class)
    @Column(name = "IS_PRIMARY", nullable = false, length = 1)
    private Boolean isPrimary;

}
