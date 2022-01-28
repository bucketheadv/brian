package com.sunshine.music.brian.dao.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunshine.music.brian.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sven
 * Created on 2022/1/28 2:28 下午
 */
@Mapper
@DS("default")
public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> findByUsername(String username);
}
