package com.parsec.sb.mapper;

import com.parsec.sb.entity.Game;
import com.parsec.universal.dao.TKMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper extends TKMapper<Game> {
}
