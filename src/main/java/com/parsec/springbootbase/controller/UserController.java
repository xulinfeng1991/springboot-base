package com.parsec.springbootbase.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parsec.springbootbase.entitiy.User;
import com.parsec.springbootbase.mapper.MyUserMapper;
import com.parsec.springbootbase.mapper.UserMapper;
import com.parsec.universal.dao.CommonDaoWrap;
import com.parsec.universal.utils.Result;
import io.swagger.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author xujiahong
 * @date 2019/2/14
 */
@Api(value = "用户controller", tags = {"用户分组"})
@RestController
public class UserController {

    @Autowired
    CommonDaoWrap dao;

    @Autowired
    MyUserMapper myUserMapper;

    @Autowired
    UserMapper userMapper;


    @ApiOperation(value = "新增用户", tags = {"家洪自定义分组"}, notes = "根据提交的用户信息，新增用户，用户名必填")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "age", value = "年龄", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "remark", value = "备注", required = false, dataType = "String")
    })
    @PostMapping("/user/insert")
    public Result insert(User user) {
        return Result.returnSuccess(null, "测试新增成功");
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/user/update")
    public Result update(@RequestBody User user) {
        return Result.returnSuccess(null, "测试修改成功");
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @DeleteMapping("/user/delete")
    public Result delete(Integer id) {
        return Result.returnSuccess(null, "测试删除成功");
    }


    @ApiOperation(value = "查询用户信息", tags = {"家洪自定义分组"}, notes = "注意问题点")
    @GetMapping("/user/list")
    public Result selectList(@RequestBody User user, @RequestBody Pageable pageable) {
        System.out.println(pageable);
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List list = userMapper.selectAll();
        PageInfo info = new PageInfo(list);

        List list1 = userMapper.selectByRowBounds(user, new RowBounds(pageable.getPageNumber(), pageable.getPageSize()));
        PageInfo info1 = new PageInfo(list1);

        Map map = new HashMap();
        map.put("info", info);
        map.put("info1", info1);


        return Result.returnSuccess(map, "success");
    }

    @ApiIgnore
    @GetMapping("/user/ignoretest")
    public Result test(User user, Pageable pageable) {
        return Result.returnFail("");
    }


    @GetMapping("/user/method")
    public Result controllerMethod() {
        return Result.returnFail("");
    }

    public void notControllerMethod() {

    }

}
