package com.example.user.controller;

import com.example.user.mapper.UserMapper;
import com.example.user.pojo.dto.UserLoginDto;
import com.example.user.pojo.dto.UserUpdateDto;
import com.example.user.pojo.entity.User;
import com.example.user.pojo.vo.UserVO;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        int result = userService.registerUser(user);

        if (result == 3) {
            return "exists"; // 用户名密码为空
        } else if (result == 2) {
            return "exists"; // 用户名已存在
        } else if (result == 1) {
            return "success"; // 注册成功
        } else {
            return "failure"; // 注册失败
        }
    }



    @PostMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestBody User user) {
        int addUserResult = userService.addUser(user);
        return addUserResult;
    }

    @RequestMapping("/delete")
    public int delete(int id) {
        return userService.deleteUser(id);
    }

    //登录
    @PostMapping("/login")
    @ResponseBody
    public int login(@RequestBody UserLoginDto user, HttpSession session) {
        int loginResult = userService.login(user, session);
        return loginResult;
    }

    @RequestMapping("/search")
    public List<User> selectSearch(String username) {
        return userService.selectSearch(username);
    }

    @RequestMapping("/select")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("/remSelect")
    public int remSelect(HttpSession session) {
        return userService.remSelect(session);
    }

    @RequestMapping("/selectById")
    public UserUpdateDto selectById(User user) {
        return userService.selectById(user);
    }

    @RequestMapping("/update")
    public int update(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(userUpdateDto);
    }
}


