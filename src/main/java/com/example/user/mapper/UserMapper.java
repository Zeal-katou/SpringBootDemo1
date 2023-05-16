package com.example.user.mapper;

import com.example.user.pojo.dto.UserUpdateDto;
import com.example.user.pojo.entity.User;
import com.example.user.pojo.vo.UserVO;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);

    int deleteById(int id);

    int update(UserUpdateDto userUpdateDto);

    UserVO selectByUsername(String username);

    List<User> selectAll();

    List<User> selectSearch(String username);

    int selectByAdd(User user);

    UserUpdateDto selectById(int id);
}
