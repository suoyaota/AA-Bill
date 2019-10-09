package uestc.wyb.aa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.wyb.aa.mapper.TeamMapper;
import uestc.wyb.aa.pojo.Team;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    public TeamMapper teamMapper;


    public  List<Team> findByLeaderID(Long leaderID) { return teamMapper.findByLeaderID(leaderID);}
    public Integer save(Team team) {return teamMapper.save(team);}
    public void deleteTeam(long id) {teamMapper.delete(id);}
    public Team getTeamByID(long id) { return teamMapper.getTeamByID(id);}
    public Integer updateTeam(Team team) { return teamMapper.update(team);}
    public Long getIDByLeaderID_TeamName (long leaderID,String teamName) {
        return teamMapper.getIDByLeaderID_TeamName(leaderID,teamName);
    }


}
