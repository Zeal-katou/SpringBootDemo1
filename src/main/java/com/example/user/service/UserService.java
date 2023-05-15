package com.example.user.service;

import com.example.user.mapper.UserMapper;
import com.example.user.pojo.dto.UserLoginDto;
import com.example.user.pojo.dto.UserUpdateDto;
import com.example.user.pojo.entity.User;
import com.example.user.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    /*注册*/
    public int registerUser(User user) {
        if (user.getUsername() == null || user.getUsername().equals(" ") ||
                user.getPassword() == null || user.getPassword().equals(" ")) {
            // 用户名密码为空
            return 3;
        }

        user.setRole(1); // 设置默认权限为1

        int row = mapper.selectByAdd(user);
        if (row == 1) {
            // 用户名已存在
            return 2;
        }

        mapper.insert(user);
        // 添加成功
        return 1;
    }


    /*添加用户*/
    public int addUser(User user) {
        if (user.getUsername() == null || user.getUsername().equals(" ") ||
                user.getPassword() == null || user.getPassword().equals(" ")) {
            // 用户名密码为空
            return 3;
        }
        int row = mapper.selectByAdd(user);
        if (row == 1) {
            // 用户名已存在
            return 2;
        }
        mapper.insert(user);
        // 添加成功
        return 1;
    }

    public int deleteUser(int id) {
        int row = mapper.deleteById(id);
        if (row == 1) {
            // 删除成功
            return 1;
        }
        // 删除失败
        return 2;
    }

    /*登陆*/

    public int login(UserLoginDto user, HttpSession session) {
        UserVO u = mapper.selectByUsername(user.getUsername());
        if (u != null) {
            if (u.getPassword().equals(user.getPassword())) {
                // 把当前登录的用户对象保存到当前客户端对应的会话对象里面
                session.setAttribute("user", u);
                return 1; // 登录成功
            }
            return 2; // 密码错误
        }
        return 3; // 用户名不存在
    }

    public List<User> selectSearch(String username) {
        List<User> users = mapper.selectSearch(username);
        System.out.println(users);
        return users;
    }

    public List<User> selectAll() {
        return mapper.selectAll();
    }

    public int remSelect(HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        System.out.println(user);
        return user.getRole();
    }

    public UserUpdateDto selectById(User user) {
        UserUpdateDto userDto = mapper.selectById(user.getId());
        return userDto;
    }

    public int updateUser(UserUpdateDto userUpdateDto) {
        System.out.println(userUpdateDto);
        int row = mapper.update(userUpdateDto);
        if (row == 1) {
            return 1;
        } else {
            return 2;
        }
    }
}

