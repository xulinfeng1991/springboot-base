package com.parsec.springbootbase.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "管理员controller", tags = {"用户和管理员分组"})
@RestController
public class AdminController {

    @ApiOperation(value = "新增管理员", tags = {"家洪自定义分组"}, notes = "新增管理员的详细描述，仅后台超级管理员可以新增普通管理员")
    @PostMapping("/admin/insert")
    public void insert() {
    }

    @PostMapping("/admin/update")
    public void update() {
    }
}
