package uestc.wyb.aa.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uestc.wyb.aa.pojo.Team;
import uestc.wyb.aa.pojo.User;

import java.util.List;

@Component
public interface TeamMapper {
    //当前用户下的队伍
    @Select("select * from teams where leaderID = #{leaderID} ")
    List<Team> findByLeaderID(long leaderID);

    @Select("select * from teams where id = #{id}")
    Team getTeamByID(long id);

    @Insert(" insert into teams ( teamName, leaderID ) values (#{teamName},#{leaderID}) ")
    Integer save(Team team);

    @Delete(" delete from teams where id= #{id} ")
    void delete(long id);

    @Select("select * from teams where id= #{id} ")
    Team get(int id);

    @Update("update teams set teamName = #{teamName} where id = #{id} ")
    Integer update(Team team);

    @Select("select id from teams where leaderID = #{leaderID} and teamName = #{teamName}")
    Long  getIDByLeaderID_TeamName(@Param("leaderID")long leaderID, @Param("teamName")String teamName);
}