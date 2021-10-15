package com.ncits.workingdays.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ncits.workingdays.domain.DepartmentMappingEmployee;
import com.ncits.workingdays.domain.Employee;
import com.ncits.workingdays.domain.dto.EmployeeDto;
import com.ncits.workingdays.service.DepartmentMappingEmployeeService;
import com.ncits.workingdays.service.DepartmentService;
import com.ncits.workingdays.service.EmployeeService;
import com.ncits.workingdays.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Employee"})
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentMappingEmployeeService departmentMappingEmployeeService;
    @Resource
    private DepartmentService departmentService;

    @GetMapping
    @ApiOperation(value = "Employee 목록")
    List<EmployeeDto> getEmployees() throws JsonProcessingException {
        return employeeService.getEmployees();
    }

    @GetMapping(value = "/{employeeId}")
    @ApiOperation(value = "Employee 조회")
    EmployeeDto getEmployee(@PathVariable Long employeeId) throws IOException {
        return employeeService.getEmployeeDto(employeeId);
    }

    @PostMapping
    @ApiOperation(value = "Employee 추가")
    EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) throws IOException {
        Employee employee = Employee.builder()
            .employeeAccount(employeeDto.getEmployeeAccount())
            .employeeMail(employeeDto.getEmployeeMail())
            .employeeName(employeeDto.getEmployeeName())
            .employeeNumber(employeeDto.getEmployeeNumber())
            .position(employeeDto.getPosition())
            .build();

        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    @ApiOperation(value = "Employee 수정")
    EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) throws IOException {
        if(XObjectUtils.isNotEmpty(employeeDto.getId())){
            Employee employee = employeeService.getEmployee(employeeDto.getId());
            if(XObjectUtils.isNotEmpty(employee)){
                Employee updateEmployee = Employee.builder()
                        .id(employee.getId())
                        .employeeNumber(employee.getEmployeeNumber())
                        .employeeAccount(employee.getEmployeeAccount())

                        // update 항목
                        .employeeMail(employeeDto.getEmployeeMail())
                        .employeeName(employee.getEmployeeName())
                        .position(employeeDto.getPosition())
                        .build();

                return employeeService.updateEmployee(updateEmployee);
            }
        }
        return null;
    }



}
