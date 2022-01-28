package com.sunshine.music.brian.controller;

import com.sunshine.music.brian.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sven
 * Created on 2022/1/28 2:26 下午
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/findByUsername")
    public Object findByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }
}
