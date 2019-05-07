package com.parsec.springbootbase;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.parsec.springbootbase.entitiy.User;
import com.parsec.springbootbase.mapper.MyUserMapper;
import com.parsec.springbootbase.mapper.UserMapper;
import com.parsec.universal.dao.CommonDaoWrap;
import org.apache.ibatis.session.RowBounds;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    CommonDaoWrap dao;

    @Autowired
    MyUserMapper myUserMapper;

    @Autowired
    UserMapper userMapper;

    User u666, u888, ua, ub, uc, ud;
    List list;

    public void resetData() {
        u666 = null;
        u888 = null;
        ua = null;
        ub = null;
        uc = null;
        ud = null;
        list = null;
    }

    //    @Before
    public void init() {
        resetData();

        u666 = new User();
        u666.setId(666L);
        u666.setName("666");
        u666.setRemark("666");

        u888 = new User();
        u888.setId(888L);
        u888.setName("888");
        u888.setRemark("888");

        ua = new User().setName("aaa").setRemark("aaa");
        ub = new User().setName("bbb").setRemark("bbb");
        uc = new User().setName("ccc").setRemark("ccc");
        ud = new User().setName("ddd").setRemark("ddd");

        list = new ArrayList();
        list.add(ua);
        list.add(ub);
        list.add(uc);
        list.add(ud);
    }

//    @After
    public void destroy(){
        dao.executeSQL("DELETE FROM `xujiahong_novel`.`tbl_user` ",null);
        dao.executeSQL("ALTER TABLE `xujiahong_novel`.`tbl_user` AUTO_INCREMENT=1; ",null);
    }

    @Test
    public void testInsert() {
        init();

        userMapper.insert(ua);
        //据说是null不插入
        userMapper.insertSelective(ub);
        //自定义ID无效
        userMapper.insertUseGeneratedKeys(u666);
        userMapper.insertList(list);
        userMapper.insert(u888);


        //commonDao
//        dao.insert(new User().setName("commondaoUser"));
//        dao.insertBatch(list);
    }

    @Test
    public void testUpdate() {

        destroy();
        testInsert();

        ua.setName("aaaupdate").setAge(null).setRemark(null);
        ub.setName("bbbupdata").setAge(null).setRemark(null);
        uc.setName("cccupdate").setAge(null).setRemark(null);
        ud.setName("dddupdate").setAge(null).setRemark(null);
        u666.setName("666update").setAge(null).setRemark(null);
        u888.setName("888update").setAge(null).setRemark(null);

        System.out.println("=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-");
        System.out.println(ua);
        System.out.println(ub);
        System.out.println(uc);
        System.out.println(ud);
        System.out.println(u666);
        System.out.println(u888);
        System.out.println("=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-");


        userMapper.updateByPrimaryKey(ua);
        //推荐
        userMapper.updateByPrimaryKeySelective(u666);
        dao.update(u888);


        Condition condition = new Condition(User.class);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.orEqualTo("id",ub.getId());
        criteria.orEqualTo("id",uc.getId());
        userMapper.updateByCondition(ub, condition);

//        dao.executeSQL("update tblxxx set name=? and age = ? where (id=? or id=?)",null);

//        userMapper.updateByConditionSelective(ub, condition);
//        userMapper.updateByExample(ud, condition);
//        userMapper.updateByExampleSelective(ud, condition);
    }

    @Test
    public void testSelect(){

        destroy();
        testInsert();
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id","3");
        criteria.andNotEqualTo("id","888");

        List list1 = userMapper.selectByExampleAndRowBounds(example,new RowBounds(1,2));
        PageInfo info1 = new PageInfo(list1);
        List list2 = userMapper.selectByExampleAndRowBounds(example,new RowBounds(2,2));
        PageInfo info2 = new PageInfo(list2);
        List list3 = userMapper.selectByExampleAndRowBounds(example,new RowBounds(3,2));
        PageInfo info3 = new PageInfo(list3);
//        List list4 = userMapper.selectByExampleAndRowBounds(example,new RowBounds(4,2));
//        PageInfo info4 = new PageInfo(list4);

        System.out.println("=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-");
        System.out.println(new JSONObject(info1));
        System.out.println(new JSONObject(info2));
        System.out.println(new JSONObject(info3));
//        System.out.println(new JSONObject(info4));
        System.out.println("=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-+=-");



    }

    @Test
    public void test() {
        destroy();
    }
}
