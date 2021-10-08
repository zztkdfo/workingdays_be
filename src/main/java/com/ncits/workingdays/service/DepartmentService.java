package com.ncits.workingdays.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ncits.workingdays.domain.Department;
import com.ncits.workingdays.domain.Employee;
import com.ncits.workingdays.domain.dto.DepartmentDto;
import com.ncits.workingdays.domain.dto.EmployeeDto;
import com.ncits.workingdays.repository.DepartmentRepository;
import com.ncits.workingdays.repository.EmployeeRepository;
import com.ncits.workingdays.utils.XJsonUtils;
import com.ncits.workingdays.utils.XObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    /**
      * Department 목록
      * @return
      * @throws JsonProcessingException
      */
    public List<DepartmentDto> getDepartments() throws JsonProcessingException {
        return XJsonUtils.convertToDtoList(departmentRepository.findAll(), DepartmentDto.class);
    }

    /**
      * Department Dto 조회
      * @param departmentId
      * @return
      * @throws IOException
      */
    public DepartmentDto getDepartmentDto(Long departmentId) throws IOException {
        return XJsonUtils.convertToVo(this.getDepartment(departmentId), DepartmentDto.class);
    }

    /**
     * Department 조회
     * @param departmentId
     * @return
     */
    public Department getDepartment(Long departmentId) {
        if(XObjectUtils.isNotEmpty(departmentId)){
            Optional<Department> optDepartment = departmentRepository.findById(departmentId);
            return optDepartment.orElseThrow();
        }
        return null;
    }

    /**
     * Department 추가
     * @param department
     * @return
     * @throws IOException
     */
    public DepartmentDto saveDepartment(Department department) throws IOException {
        return XJsonUtils.convertToVo(departmentRepository.save(department), DepartmentDto.class);
    }

    /**
     * Department 수정
     * @param department
     * @return
     */
    public DepartmentDto updateDepartment(Department department) throws IOException {
        return XJsonUtils.convertToVo(departmentRepository.save(department), DepartmentDto.class);
    }

}
