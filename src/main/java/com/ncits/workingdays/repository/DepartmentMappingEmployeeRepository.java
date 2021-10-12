package com.ncits.workingdays.repository;

import com.ncits.workingdays.domain.DepartmentMappingEmployee;
import com.ncits.workingdays.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMappingEmployeeRepository extends JpaRepository<DepartmentMappingEmployee, Long> {
}
