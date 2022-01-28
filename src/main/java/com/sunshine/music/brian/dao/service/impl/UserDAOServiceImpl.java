package com.sunshine.music.brian.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunshine.music.brian.dao.entity.UserEntity;
import com.sunshine.music.brian.dao.repository.UserMapper;
import com.sunshine.music.brian.dao.service.UserDAOService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sven
 * Created on 2022/1/28 2:34 下午
 */
@Service
public class UserDAOServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserDAOService {
    @Override
    public List<UserEntity> findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }
}
