package com.sunshine.music.brian.dao.service;

import com.sunshine.music.brian.dao.entity.UserEntity;

import java.util.List;

/**
 * @author sven
 * Created on 2022/1/28 2:40 下午
 */
public interface UserDAOService {
    List<UserEntity> findByUsername(String username);
}
