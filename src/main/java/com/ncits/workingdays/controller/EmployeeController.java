package com.ncits.workingdays.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ncits.workingdays.config.BooleanToStringConverter;
import com.ncits.workingdays.domain.DepartmentMappingEmployee;
import com.ncits.workingdays.domain.Employee;
import com.ncits.workingdays.domain.WorkingDays;
import com.ncits.workingdays.domain.WorkingDaysPlan;
import com.ncits.workingdays.domain.dto.EmployeeDto;
import com.ncits.workingdays.repository.WorkingDaysPlanRepository;
import com.ncits.workingdays.repository.WorkingDaysRepository;
import com.ncits.workingdays.service.DepartmentMappingEmployeeService;
import com.ncits.workingdays.service.DepartmentService;
import com.ncits.workingdays.service.EmployeeService;
import com.ncits.workingdays.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    @Resource
    private WorkingDaysRepository workingDaysRepository;
    @Resource
    private WorkingDaysPlanRepository workingDaysPlanRepository;

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

    @GetMapping(value = "/csv")
    @ApiOperation(value = "CSV Import Test")
    Boolean test() {
        List<List<String>> result = readToList();
        for(int i=0; i<result.size(); i++) {

            List<String> line = result.get(i);
            try {
                WorkingDaysPlan workingDays = WorkingDaysPlan.builder()
                    .breakTime(line.get(1) != null ? line.get(1) : "")
                    .dinner(line.get(2) != null ? line.get(2) : "")
                    .employee(employeeService.getEmployee(Long.valueOf(line.get(3))))
                    .endTime((line.get(4) != null ? line.get(4) : ""))
                    .etc((line.get(5) != null ? line.get(5) : ""))
                    .lunch((line.get(6) != null ? line.get(6) : ""))
                    .startTime((line.get(7) != null ? line.get(7) : ""))
                    .workingDate(java.sql.Timestamp.valueOf(line.get(8)))
                    .workingTime(line.get(9) != null ? Integer.valueOf(line.get(9)) : 0)
                    .workingType((line.get(10) != null ? line.get(10) : ""))
                    .workingTypeTime((line.get(11) != null ? line.get(11) : ""))
                    .build();

                System.out.println("id: " + line.get(0));

                workingDaysPlanRepository.save(workingDays);
            }catch (Exception e){

            }

        }

        return null;
    }


    public static List<List<String>> readToList() {

        String path = "/Users/isanglae/Desktop/study/WORKING_DAYS/WORKING_DAYS_PLAN.csv";
        List<List<String>> list = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8");
            String line = "";

            while((line=br.readLine()) != null) {
                String[] token = line.split(",");
                List<String> tempList = new ArrayList<String>(Arrays.asList(token));
                list.add(tempList);
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if(br != null) {br.close();}
            } catch (IOException e) {
            }
        }
        return list;
    }


}
