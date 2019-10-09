package uestc.wyb.aa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.wyb.aa.mapper.BillMapper;
import uestc.wyb.aa.pojo.Bill;

import java.util.List;

@Service
public class BillService {
    @Autowired BillMapper billMapper;

    public List<Bill> findBillsByTeamID(long teamID){ return billMapper.findBillsByTeamID(teamID);}
    public Bill findBillByID(long id){return billMapper.findBillByID(id);}
    public Integer save(Bill bill) {return billMapper.save(bill);}
    public void deleteByID(long id) { billMapper.deleteByID(id);}
    public void deleteByTeamID(long teamID) { billMapper.deleteByTeamID(teamID);}
    public Integer updateBillName (Bill bill){ return billMapper.updateBillName(bill);}
    public Long getID(String billName, long teamID) {return billMapper.getID(billName,teamID);}
    public Integer updateBillTotalMoney(Bill bill){return billMapper.updateBillTotalMoney(bill);}
}
