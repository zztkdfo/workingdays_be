package com.ncits.workingdays.controller;

import com.ncits.workingdays.domain.DepartmentMappingEmployee;
import com.ncits.workingdays.domain.dto.DepartmentMappingEmployeeDto;
import com.ncits.workingdays.service.DepartmentMappingEmployeeService;
import com.ncits.workingdays.service.DepartmentService;
import com.ncits.workingdays.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/v1/mappings")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"DepartmentMappingEmployee"})
public class DepartmentMappingEmployeeController {
    @Resource
    private DepartmentService departmentService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentMappingEmployeeService departmentMappingEmployeeService;

    @PostMapping
    @ApiOperation(value = "Mapping 추가")
    DepartmentMappingEmployeeDto saveMapping(@RequestBody DepartmentMappingEmployeeDto departmentMappingEmployeeDto) throws IOException {
        DepartmentMappingEmployee dme = DepartmentMappingEmployee.builder()
                .department(departmentService.getDepartment(departmentMappingEmployeeDto.getDepartmentId()))
                .employee(employeeService.getEmployee(departmentMappingEmployeeDto.getEmployeeId()))
                .isPrimary(departmentMappingEmployeeDto.getIsPrimary())
                .build();

        return departmentMappingEmployeeService.saveMapping(dme);
    }

    @PostMapping(value = "/dme")
    @ApiOperation(value = "Employee Mapping User")
    Boolean setDepartmentMappingEmployee(
            @RequestParam Long employeeId,
            @RequestParam Long departmentId,
            @RequestParam String position,
            @RequestParam Boolean isPrimary

            ) throws IOException {

    DepartmentMappingEmployee dme = DepartmentMappingEmployee.builder()
        .department(departmentService.getDepartment(departmentId))
        .employee(employeeService.getEmployee(employeeId))
        .position(position)
        .isPrimary(isPrimary)
        .build();

    departmentMappingEmployeeService.saveMapping(dme);



        return true;
    }

}
