package com.ncits.workingdays.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/workingDays")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Working Days"})
public class WorkingDaysController {

}
