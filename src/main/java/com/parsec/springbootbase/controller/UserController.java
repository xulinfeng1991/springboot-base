package com.parsec.springbootbase.controller;

import com.parsec.springbootbase.entitiy.User;
import com.parsec.springbootbase.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

/**
 * description
 *
 * @author xujiahong
 * @date 2019/2/14
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    Mapper<User> mapper;

    @GetMapping("/user/detail")
    public String selectUser(){
        User query = new User().setId(1L);
        User user = userMapper.selectOne(query);
        System.out.println(mapper.select(query));
        return user.toString();
    }
}
