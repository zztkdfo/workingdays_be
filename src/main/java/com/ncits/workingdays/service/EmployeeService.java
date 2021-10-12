package com.ncits.workingdays.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ncits.workingdays.domain.Employee;
import com.ncits.workingdays.domain.dto.EmployeeDto;
import com.ncits.workingdays.repository.EmployeeRepository;
import com.ncits.workingdays.utils.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    /**
     * Employee 목록
     * @return
     * @throws JsonProcessingException
     */
    public List<EmployeeDto> getEmployees() throws JsonProcessingException {
        return XJsonUtils.convertToDtoList(employeeRepository.findAll(), EmployeeDto.class);
    }

    /***
     * Employee Dto 조회
     * @param employeeId
     * @return
     * @throws IOException
     */
    public EmployeeDto getEmployeeDto(Long employeeId) throws IOException {
        return XJsonUtils.convertToDto(this.getEmployee(employeeId), EmployeeDto.class);
    }

    /**
     * Employee 조회
     * @param employeeId
     * @return
     */
    public Employee getEmployee(Long employeeId) {
        if(XObjectUtils.isNotEmpty(employeeId)){
            Optional<Employee> optEmployee = employeeRepository.findById(employeeId);
            return optEmployee.orElseThrow();
        }
        return null;
    }

    /**
     * Employee 추가
     * @param employee
     * @return
     * @throws IOException
     */
    public EmployeeDto saveEmployee(Employee employee) throws IOException {
        return XJsonUtils.convertToDto(employeeRepository.save(employee), EmployeeDto.class);
    }

    /**
     * Employee 수정
     * @param employee
     * @return
     */
    public EmployeeDto updateEmployee(Employee employee) throws IOException {
        return XJsonUtils.convertToDto(employeeRepository.save(employee), EmployeeDto.class);
    }

}
