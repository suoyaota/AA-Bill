package uestc.wyb.aa.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import uestc.wyb.aa.pojo.User;

//import uestc.wyb.aa.pojo.User;

import java.util.List;


@Component
public interface UserMapper {

    //登录用
    @Select("select id from users where userName=#{userName} and password=#{password}")
    Long login(@Param("userName") String userName, @Param("password") String password);

    @Select("select name from users where id=#{userID}")
    String getNameByID(long id);

    //传值用
    @Select("select id from users where userName=#{userName}")
    Long nowID(@Param("userName") String userName);

    @Select("select * from users where id = #{id}")
    User findByID(long id);

    @Select("select * from users ")
    List<User> findAll();

    @Insert(" insert into users ( userName, password, name ) values (#{userName},#{password},#{name}) ")
    Integer save(User user);

    @Delete(" delete from users where id= #{id} ")
    void delete(int id);

    @Select("select * from user where id= #{id} ")
    User get(int id);

    @Update("update users set userName=#{userName},name=#{name},password=#{password} where id=#{id} ")
    Integer update(User user);
}
