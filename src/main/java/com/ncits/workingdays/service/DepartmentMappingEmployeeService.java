package com.ncits.workingdays.service;

import com.ncits.workingdays.domain.DepartmentMappingEmployee;
import com.ncits.workingdays.domain.dto.DepartmentMappingEmployeeDto;
import com.ncits.workingdays.repository.DepartmentMappingEmployeeRepository;
import com.ncits.workingdays.utils.XJsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class DepartmentMappingEmployeeService {

    @Resource
    DepartmentMappingEmployeeRepository departmentMappingEmployeeRepository;

    /**
     * Mapping 등록
     * @param departmentMappingEmployee
     * @return
     */
    public DepartmentMappingEmployeeDto saveMapping(DepartmentMappingEmployee departmentMappingEmployee) throws IOException {
        return XJsonUtils.convertToDto(departmentMappingEmployeeRepository.save(departmentMappingEmployee), DepartmentMappingEmployeeDto.class);

    }
}
