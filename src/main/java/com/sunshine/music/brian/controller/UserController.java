package com.sunshine.music.brian.controller;

import com.sunshine.music.brian.dao.entity.UserEntity;
import com.sunshine.music.brian.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.winterframework.core.support.ApiResponse;

import java.util.List;

/**
 * @author sven
 * Created on 2022/1/28 2:26 下午
 */
@Slf4j
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/findByUsername")
    public ApiResponse<List<UserEntity>> findByUsername(@RequestParam String username) {
        ApiResponse<List<UserEntity>> response = new ApiResponse<>();
        response.setData(userService.findByUsername(username));
        response.setCode(200);
        return response;
    }

    @RequestMapping("/testErrorLog")
    public ApiResponse<?> testErrorLog() {
        log.error("TestFor Error: {}", "错误信息");
        return new ApiResponse<>();
    }
}
