package uestc.wyb.aa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.wyb.aa.mapper.TeamMemberMapper;
import uestc.wyb.aa.mapper.UserMapper;
import uestc.wyb.aa.pojo.TeamUser;
import uestc.wyb.aa.pojo.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMemberService {
    @Autowired
    TeamMemberMapper teamMemberMapper;
    @Autowired
    UserMapper userMapper;

    public List<User> findTeamMemberWithoutLeader(long teamID , long leaderID) {
        List<TeamUser> teamUser = teamMemberMapper.findByTeamID(teamID);
        List<User> teamMembersWithoutLeader = new ArrayList<>();
        for (TeamUser tu : teamUser) {
            long userID = tu.getMemberID();
            if (userID != leaderID) {
                User user = userMapper.findByID(userID);
                teamMembersWithoutLeader.add(user);
            }
        }
        return teamMembersWithoutLeader;
    }

    public List<User> findTeamMemberByTeamID (long teamID){

        List<TeamUser> teamUser = teamMemberMapper.findByTeamID(teamID);
        List<User> teamMembers = new ArrayList<>();
        for (TeamUser tu:teamUser){
            long userID = tu.getMemberID();
            User user = userMapper.findByID(userID);
            teamMembers.add(user);
        }
        return teamMembers;
    }

    public List<User> findUserReadyByTeamID(long teamID){

        List<User> teamMember = findTeamMemberByTeamID(teamID);
        List<User> users1 = userMapper.findAll();
        List<User> users2 = new ArrayList<>();
        for(User u:users1){
            long uID = u.getId();
            boolean ans = true;
            for(User ut:teamMember){
                long utID = ut.getId();
                if(uID == utID){
                    ans = false;
                    break;
                }
            }
            if(ans){
                users2.add(u);
            }
        }
        return users2;
    }

    public void save (TeamUser teamUser) { teamMemberMapper.save(teamUser);}


    public void deleteByMemberID(long memberID){ teamMemberMapper.deleteByMemberID(memberID);}

    public void deleteByTeamID(long teamID){ teamMemberMapper.deleteByTeamID(teamID);}


}
