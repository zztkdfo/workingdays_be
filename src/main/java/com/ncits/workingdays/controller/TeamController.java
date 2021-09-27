package com.ncits.workingdays.controller;

import com.ncits.workingdays.domain.Team;
import com.ncits.workingdays.domain.Vo.TeamVo;
import com.ncits.workingdays.service.TeamService;
import com.ncits.workingdays.utils.XJsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TeamService teamService;

    @ApiOperation("Team 리스트")
    @RequestMapping(value = "/findTeamList", method = RequestMethod.POST)
    @ResponseBody
    public List<Team> findMemberList() {
        List<Team> list = teamService.findTeamList();
        return list;
    }

    @ApiOperation("Team 상세 정보")
    @RequestMapping(value = "/getTeam", method = RequestMethod.POST)
    @ResponseBody
    public Team getMember(@RequestBody Long id) {
        return teamService.getTeam(id);
    }

    @ApiOperation("Team 상세 정보 vo")
    @RequestMapping(value = "/getTeamVo", method = RequestMethod.POST)
    @ResponseBody
    public TeamVo getMemberV0(@RequestBody Long id) throws IOException {
        Team team = teamService.getTeam(id);
        return XJsonUtils.convertToVo(team, TeamVo.class);
    }


}
