package com.parsec.springbootbase.mapper;

import com.parsec.springbootbase.entitiy.User;
import com.parsec.universal.dao.TKMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends TKMapper<User> {
}
