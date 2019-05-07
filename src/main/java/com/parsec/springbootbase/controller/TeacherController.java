package com.parsec.springbootbase.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "教师controller")
@RestController
public class TeacherController {

    @ApiOperation(value = "新增教师", notes = "新增教师的详细描述，只有年纪主任才有权限新增教师")
    @PostMapping("/teacher/insert")
    public void insert() {
    }

    @PostMapping("/teacher/update")
    public void update() {
    }
}
