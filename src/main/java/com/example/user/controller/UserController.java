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

    @RequestMapping("/add")
    public int add(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping("/delete")
    public int delete(int id) {
        return userService.deleteUser(id);
    }

    @RequestMapping("/login")
    public int login(@RequestBody UserLoginDto user, HttpSession session) {
        return userService.login(user, session);
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


