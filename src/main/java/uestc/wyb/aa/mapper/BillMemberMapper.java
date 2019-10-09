package uestc.wyb.aa.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import uestc.wyb.aa.pojo.BillMember;

import java.util.List;

@Component
public interface BillMemberMapper {
    @Delete("delete from bill_member where billID = #{billID}")
    void deleteByBillID(long billID);
    @Insert("insert into bill_member (billID, memberID, memberName) values (#{billID},#{memberID},#{memberName})")
    Integer save(BillMember billMember);
    @Select("select * from bill_member where billID = #{billID}")
    List<BillMember> findBillMembers(long billID);
    @Delete("delete from bill_member where billID = #{billID} and memberID = #{memberID}")
    void deleteByBillIDAndMemberID(@Param("billID") long billID, @Param("memberID") long memberID);
    @Update("update bill_member set ratio = #{aaRatio} where billID = #{billID}")
    Integer updateAARatio(@Param("aaRatio") double aaRatio,@Param("billID") long billID);
    @Update("update bill_member set toPay = #{aaToPay} where billID = #{billID}")
    Integer updateAAToPay(@Param("aaToPay") double aaToPay,@Param("billID") long billID);
    @Update("update bill_member set ratio = #{ratio} where billID = #{billID} and memberID = #{memberID}")
    Integer updateRatio(BillMember billMember);
}

