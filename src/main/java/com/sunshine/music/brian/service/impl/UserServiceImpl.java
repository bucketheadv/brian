package com.sunshine.music.brian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunshine.music.brian.dao.entity.UserEntity;
import com.sunshine.music.brian.dao.repository.UserMapper;
import com.sunshine.music.brian.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sven
 * Created on 2022/1/28 3:42 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Override
    public List<UserEntity> findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }
}
