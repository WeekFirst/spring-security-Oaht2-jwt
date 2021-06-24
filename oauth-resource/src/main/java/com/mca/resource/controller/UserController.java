package com.mca.resource.controller;

import com.alibaba.fastjson.JSON;
import com.mca.resource.entity.UserInfoPo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

/**
 * @Author an Stark
 * @ClassName UserController
 * @Description TODO
 * @date 2021/6/24 下午1:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping(value = "/getUserInfo")
    @PreAuthorize("hasAnyAuthority('normal')")
    public Object getUserInfo(Authentication authentication) {
        String name = authentication.getName();
        Object parse = JSON.parse(name);
        return parse;
    }


}
