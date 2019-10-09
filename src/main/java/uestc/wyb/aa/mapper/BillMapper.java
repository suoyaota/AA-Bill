package uestc.wyb.aa.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import uestc.wyb.aa.pojo.Bill;

import java.util.List;

@Component
public interface BillMapper {
    @Select("select * from bills where teamID = #{teamID}")
    List<Bill> findBillsByTeamID (long teamID);
    @Select("select * from bills where id = #{id}")
    Bill findBillByID(long id);
    @Insert(" insert into bills ( teamID, billName ) values (#{teamID},#{billName}) ")
    Integer save(Bill bill);
    @Delete("delete from bills where id = #{id}")
    void deleteByID(long id);
    @Delete("delete from bills where teamID = #{teamID}")
    void deleteByTeamID(long teamID);
    @Update("update bills set billName = #{billName} where id = #{id}")
    Integer updateBillName(Bill bill);
    @Update("update bills set totalMoney = #{totalMoney} where id = #{id}")
    Integer updateBillTotalMoney(Bill bill);
    @Select("select id from bills where billName = #{billName} and teamID = #{teamID}")
    Long getID(@Param("billName")String billName, @Param("teamID") long teamID);
    @Select("select teamID from bills where id = #{billID}")
    Long getTeamIDByBillID (long billID);
}
