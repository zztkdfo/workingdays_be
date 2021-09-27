package com.ncits.workingdays.service;

import com.ncits.workingdays.domain.Team;
import com.ncits.workingdays.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;


    public List<Team> findTeamList(){
        return teamRepository.findAll();
    }

    public Team getTeam(Long id){

        if(teamRepository.findById(id).isPresent()){
            return teamRepository.findById(id).get();
        }

        return null;
    }
}
