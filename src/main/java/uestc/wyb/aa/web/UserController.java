package uestc.wyb.aa.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uestc.wyb.aa.pojo.Team;
import uestc.wyb.aa.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import uestc.wyb.aa.service.TeamService;
import uestc.wyb.aa.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public TeamService teamService;

    //启动AA账本进入登录页面
    @GetMapping(value = "/")
    public String login() {
        return "login";
    }

    //登录页面对登录提交数据的处理
    @PostMapping(value = "/login")
    public String loginCon(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session, Model m) {
        Long userID = userService.login(userName, password);
        if (Objects.isNull(userID)) {//登录失败
            return "loginFail";
        } else {
            //登录成功
            String name = userService.getNameByID(userID);
            session.setAttribute("name", name);
            session.setAttribute("userID", userID);
            boolean init = false;
            session.setAttribute("init", init);//账单查看时要初始化
            return "redirect:teamList";
            //对team页面的处理见TeamController
        }
    }

    //用户点击注册后跳转到注册页面
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //在注册页面对注册提交的处理
    @RequestMapping("/registerSubmit")
    public String addUser(User u) {
        userService.save(u);
        return "login";
    }

//
//    @ExceptionHandler(NullPointerException.class)
//    public String handleNull(){
//
//    }

}