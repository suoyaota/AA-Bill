package uestc.wyb.aa.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import uestc.wyb.aa.pojo.TeamUser;

import java.util.List;

@Component
public interface TeamMemberMapper {
    //wyb:注意使用*时数据库字段名和实体类中的命名要一致！！
    @Select("select * from team_member where teamID = #{teamID}")
    List<TeamUser> findByTeamID(long teamID);

    @Delete("delete from team_member where memberID = #{memberID}")
    void deleteByMemberID(long memberID);

    @Delete("delete from team_member where teamID = #{teamID}")
    void deleteByTeamID(long teamID);

    @Insert(" insert into team_member ( teamID, memberID ) values (#{teamID},#{memberID}) ")
    Integer save(TeamUser teamUser);
}
