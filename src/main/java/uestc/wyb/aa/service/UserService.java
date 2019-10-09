package uestc.wyb.aa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uestc.wyb.aa.mapper.UserMapper;

import uestc.wyb.aa.pojo.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    //登录用
    public Long login(String userName,String password){
        return userMapper.login(userName,password);
    }
    public String getNameByID(Long id){
        return userMapper.getNameByID(id);
    }

    //传当前ID
    public Long nowID(String userName){
        return userMapper.nowID(userName);
    }

    //注册用
    public Integer save(User user) {
        return userMapper.save(user);
    }

    public User findByID(long id) { return userMapper.findByID(id);}

    public List<User> findAll(){
        return  userMapper.findAll();
    }


}
