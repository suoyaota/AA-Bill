package uestc.wyb.aa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uestc.wyb.aa.pojo.Bill;
import uestc.wyb.aa.pojo.BillMember;
import uestc.wyb.aa.pojo.User;
import uestc.wyb.aa.service.BillMemberService;
import uestc.wyb.aa.service.BillService;
import uestc.wyb.aa.service.TeamService;
import uestc.wyb.aa.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BillController {
    @Autowired
    public BillService billService;
    @Autowired
    public BillMemberService billMemberService;
    @Autowired
    public TeamService teamService;
    @Autowired
    public UserService userService;

    @RequestMapping("/addBill")
    public String addBill(@RequestParam("billName") String billName, HttpSession session){
        long teamID = (long) session.getAttribute("nowTeamID");
        Bill bill = new Bill();
        bill.setBillName(billName);
        bill.setTeamID(teamID);
        billService.save(bill);
        {//把队长加入账单成员中
            long leaderID = (long) session.getAttribute("userID");
            String leaderName = (String) session.getAttribute("name");
            long billID = billService.getID(billName, teamID);
            BillMember billMember = new BillMember();
            billMember.setBillID(billID);
            billMember.setMemberID(leaderID);
            billMember.setMemberName(leaderName);
            billMemberService.save(billMember);
        }
        return "redirect:editTeam?teamID="+teamID;
    }

    @RequestMapping("/deleteBill")
    public String deleteBill(long billID , HttpSession session){
        billService.deleteByID(billID);
        billMemberService.deleteByBillID(billID);
        long teamID = (long) session.getAttribute("nowTeamID");
        return "redirect:editTeam?teamID="+teamID;
    }

    @RequestMapping("/editBill")
    public String editBill(long billID, Model m,HttpSession session){
        Long leaderID = (Long) session.getAttribute("userID");
        session.setAttribute("billID",billID);
        String leaderName = (String) session.getAttribute("name");
        Bill bill = billService.findBillByID(billID);
        m.addAttribute("bill",bill);
        List<BillMember> billMembers = billMemberService.findBillMembers(billID);
        List<User> teamMembersReady = billMemberService.findTeamMembersReady(billID);
        long billMemberNum = billMembers.size();
        boolean init = (boolean) session.getAttribute("init");
        if(init) {
            double ratio = 1;
            double aaRatio = ratio / billMemberNum;
            billMemberService.updateAARatio(aaRatio,billID);
            billMembers = billMemberService.findBillMembers(billID);
        }

        double ratioRemained = 1;
        for(BillMember bm:billMembers){
            ratioRemained-=bm.getRatio();
        }

        m.addAttribute("leaderID",leaderID);
        m.addAttribute("billMemberNum",billMemberNum);
        m.addAttribute("leaderName",leaderName);
        m.addAttribute("teamMembersReady",teamMembersReady);
        m.addAttribute("billMembers",billMembers);
        m.addAttribute("ratioRemained",ratioRemained);
        return "editBill";
    }

    @RequestMapping("/updateBillName")
    public String updateBillName (Bill bill, HttpSession session){
        billService.updateBillName(bill);
        long teamID = (long) session.getAttribute("nowTeamID");
        return "redirect:editTeam?teamID="+teamID;
    }


    @RequestMapping("/addToBill")
    public String addToBill (long id , HttpSession session){
        long billID = (long) session.getAttribute("billID");
        User userToAdd= userService.findByID(id);
        BillMember billMemberToAdd = new BillMember();
        billMemberToAdd.setMemberName(userToAdd.getName());
        billMemberToAdd.setMemberID(id);
        billMemberToAdd.setBillID(billID);
        billMemberService.save(billMemberToAdd);
        boolean init = true;
        session.setAttribute("init",init);
        return "redirect:editBill?billID="+billID;
    }


    @RequestMapping("/deleteBillMember")
    public String deleteBillMember(long memberID, HttpSession session){
        long billID = (long) session.getAttribute("billID");
        long leaderID = (long) session.getAttribute("userID");
        billMemberService.deleteByBillIDAndMemberID(billID,memberID);
        boolean init = true;
        session.setAttribute("init",init);
        return "redirect:editBill?billID="+billID;
    }

    @RequestMapping("/updateBillTotalMoney")
    public String updateBillTotalMoney(Bill bill,HttpSession session){
        long billID = (long) session.getAttribute("billID");
        billService.updateBillTotalMoney(bill);
        boolean init = true;
        session.setAttribute("init",init);
        return "redirect:editBill?billID="+billID;
    }

    @RequestMapping("/updateRatio")
    public String updateRatio(BillMember billMember,HttpSession session){
        long billID = (long) session.getAttribute("billID");
        billMemberService.updateRatio(billMember);
        boolean init = false;
        session.setAttribute("init",init);
        return "redirect:editBill?billID="+billID;
    }

    @RequestMapping("/backToTeam")
    public String backToTeam(HttpSession session){
        long teamID = (long) session.getAttribute("nowTeamID");
        return "redirect:editTeam?teamID="+teamID;
    }


}
