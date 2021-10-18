package com.ncits.workingdays.repository;

import com.ncits.workingdays.domain.Employee;
import com.ncits.workingdays.domain.WorkingDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingDaysRepository extends JpaRepository<WorkingDays, Long> {
}
