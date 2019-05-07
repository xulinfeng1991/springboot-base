package com.parsec.springbootbase.mapper;


import com.parsec.springbootbase.entitiy.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface MyUserMapper {

    @Select("select * from tbl_user")
    List<User> mySelectList();

}
