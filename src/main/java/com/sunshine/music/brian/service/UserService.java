package com.sunshine.music.brian.service;

import com.sunshine.music.brian.dao.entity.UserEntity;

import java.util.List;

/**
 * @author sven
 * Created on 2022/1/28 3:41 下午
 */
public interface UserService {
    List<UserEntity> findByUsername(String username);
}
