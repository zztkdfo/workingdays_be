package com.ncits.workingdays.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ncits.workingdays.domain.Department;
import com.ncits.workingdays.domain.dto.DepartmentDto;
import com.ncits.workingdays.service.DepartmentService;
import com.ncits.workingdays.service.EmployeeService;
import com.ncits.workingdays.utils.XObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/departments")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Department"})
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "Department 목록")
    List<DepartmentDto> getDepartments() throws JsonProcessingException {
        return departmentService.getDepartments();
    }

    @GetMapping(value = "/{departmentId}")
    @ApiOperation(value = "Department 조회")
    DepartmentDto getDepartment(@PathVariable Long departmentId) throws IOException {
        return departmentService.getDepartmentDto(departmentId);
    }

    @PostMapping
    @ApiOperation(value = "Department 추가")
    DepartmentDto saveDepartment(@RequestBody DepartmentDto departmentDto) throws IOException {
        Department department = Department.builder()
            .departmentAdmin(employeeService.getEmployee(departmentDto.getDepartmentAdminId()))
            .departmentCode(departmentDto.getDepartmentCode())
            .departmentName(departmentDto.getDepartmentName())
            .upperDepartment(departmentService.getDepartment(departmentDto.getUpperDepartmentId()))
            .build();
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{departmentId}")
    @ApiOperation(value = "Department 수정")
    DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto) throws IOException {
        if(XObjectUtils.isNotEmpty(departmentDto.getId())){
            Department department = departmentService.getDepartment(departmentDto.getId());
            if(XObjectUtils.isNotEmpty(department)){
                Department updateDepartment = Department.builder()
                    .id(department.getId())
                    .departmentCode(department.getDepartmentCode())

                    // update 항목
                    .departmentName(departmentDto.getDepartmentName())
                    .departmentAdmin(employeeService.getEmployee(departmentDto.getDepartmentAdminId()))
                    .upperDepartment(departmentService.getDepartment(departmentDto.getUpperDepartmentId()))
                    .build();

                return departmentService.updateDepartment(updateDepartment);
            }
        }
        return null;
    }
}
