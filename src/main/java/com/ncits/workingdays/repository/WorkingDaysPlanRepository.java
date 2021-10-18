package com.ncits.workingdays.repository;

import com.ncits.workingdays.domain.WorkingDays;
import com.ncits.workingdays.domain.WorkingDaysPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingDaysPlanRepository extends JpaRepository<WorkingDaysPlan, Long> {
}
