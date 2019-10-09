package uestc.wyb.aa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.wyb.aa.mapper.BillMapper;
import uestc.wyb.aa.mapper.BillMemberMapper;
import uestc.wyb.aa.pojo.Bill;
import uestc.wyb.aa.pojo.BillMember;
import uestc.wyb.aa.pojo.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillMemberService {
    @Autowired
    BillMemberMapper billMemberMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    TeamMemberService teamMemberService;

    public void deleteByBillID(long billID){billMemberMapper.deleteByBillID(billID);}
    public void deleteByTeamID(long teamID) {
        List<Bill> billsToDelete = billMapper.findBillsByTeamID(teamID);
        for(Bill b:billsToDelete){
            long bID=b.getId();
            billMemberMapper.deleteByBillID(bID);
        }
    }
    public Integer save(BillMember billMember) {return billMemberMapper.save(billMember);}
    public List<BillMember> findBillMembers(long billID) {return billMemberMapper.findBillMembers(billID);}
    public List<User> findTeamMembersReady(long billID) {
        long teamID = billMapper.getTeamIDByBillID(billID);
        List<User> teamMembers = teamMemberService.findTeamMemberByTeamID(teamID);
        List<BillMember> billMembers = billMemberMapper.findBillMembers(billID);
        List<User> teamMembersReady = new ArrayList<>();
        for(User tm:teamMembers){
            boolean ans = true;
            for(BillMember bm:billMembers){
                if(tm.getId() == bm.getMemberID()){
                    ans = false;
                    break;
                }
            }
            if(ans) teamMembersReady.add(tm);
        }
        return teamMembersReady;
    }
    public void deleteByBillIDAndMemberID(long billID, long memberID) {
        billMemberMapper.deleteByBillIDAndMemberID(billID, memberID);
    }
    public Integer updateAARatio(double aaRatio,long billID){
        return billMemberMapper.updateAARatio(aaRatio,billID);
    }
    public Integer updateAAToPay(double aaToPay, long billID){
        return billMemberMapper.updateAAToPay(aaToPay,billID);
    }
    public Integer updateRatio(BillMember billMember){
        return billMemberMapper.updateRatio(billMember);
    }







}
