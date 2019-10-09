package uestc.wyb.aa.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uestc.wyb.aa.pojo.Bill;
import uestc.wyb.aa.pojo.Team;
import uestc.wyb.aa.pojo.TeamUser;
import uestc.wyb.aa.pojo.User;
import uestc.wyb.aa.service.*;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class TeamController {
    @Autowired
    public TeamService teamService;
    @Autowired
    public TeamMemberService teamMemberService;
    @Autowired
    public UserService userService;
    @Autowired
    public BillService billService;
    @Autowired
    public BillMemberService billMemberService;

    @RequestMapping("/teamList")
    public String teamListCon(HttpSession session,Model m){
        long leaderID = (long) session.getAttribute("userID");
        String name = (String) session.getAttribute("name");
        List<Team> teamList = teamService.findByLeaderID(leaderID);
        m.addAttribute("tl",teamList);
        m.addAttribute("name",name);
        m.addAttribute("userID",leaderID);
        return "teamList";
    }

    @RequestMapping("/newTeamSubmit")
    public String addTeam(@Param("teamName")String teamName, HttpSession session) {
        long leaderID = (long) session.getAttribute("userID");
        Team team = new Team();
        team.setTeamName(teamName);
        team.setLeaderID(leaderID);
        teamService.save(team);
        {//先把队长添加到队伍中
            long teamID = teamService.getIDByLeaderID_TeamName(leaderID,teamName);
            TeamUser teamUser1 = new TeamUser();
            teamUser1.setTeamID(teamID);
            teamUser1.setMemberID(leaderID);
            teamMemberService.save(teamUser1);
        }

        return "redirect:teamList";
    }

    @RequestMapping("/deleteTeam")
    public String deleteTeam(long teamID){
        billMemberService.deleteByTeamID(teamID);
        billService.deleteByTeamID(teamID);
        teamService.deleteTeam(teamID);
        teamMemberService.deleteByTeamID(teamID);
        return "redirect:teamList";
    }

    @RequestMapping("/editTeam")
    public String editTeam(long teamID,Model m,HttpSession session) {
        session.setAttribute("nowTeamID",teamID);
        Team team = teamService.getTeamByID(teamID);
        long leaderID = team.getLeaderID();
        User leader = userService.findByID(leaderID);
        List<User> teamMembersWithoutLeader = teamMemberService.findTeamMemberWithoutLeader(teamID,leaderID);
        List<User> userReadyList = teamMemberService.findUserReadyByTeamID(teamID);
        List<Bill> billsInTeam = billService.findBillsByTeamID(teamID);
        m.addAttribute("billsInTeam",billsInTeam);
        m.addAttribute("leader",leader);
        m.addAttribute("team",team);
        m.addAttribute("teamMembersWithoutLeader",teamMembersWithoutLeader);
        m.addAttribute("userReadyList",userReadyList);
        return "editTeam";
    }

    @RequestMapping("/deleteTeamMember")
    public String deleteTeamMember(long memberID,HttpSession session){
        teamMemberService.deleteByMemberID(memberID);
        Long teamID = (Long) session.getAttribute("nowTeamID");
        return "redirect:editTeam?teamID="+teamID;
    }

    @RequestMapping("/updateTeam")
    public String updateTeam(Team t) {
        teamService.updateTeam(t);
        return "redirect:teamList";
    }

    @RequestMapping("/addToTeam")
    public String addToTeam(long id, HttpSession session) {
        Long teamID = (Long) session.getAttribute("nowTeamID");
        TeamUser teamUser = new TeamUser();
        teamUser.setMemberID(id);
        teamUser.setTeamID(teamID);
        teamMemberService.save(teamUser);
        return "redirect:editTeam?teamID="+teamID;
    }
}
