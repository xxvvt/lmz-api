package com.bing.admin.modules.system.controller;

import com.bing.admin.common.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: 维护用户、角色、权限控制类
 * @author: Ryan
 * @date: 2020/7/21 17:24
 **/
@RestController
@RequestMapping("api/user")
public class UsersController {

    @PostMapping("list")
    public Result list() {
        return null;
    }

    @PostMapping("save")
    public Result save() {
        return null;
    }

    @GetMapping("getById")
    public Result getById(Long id) {
        return null;
    }
}
