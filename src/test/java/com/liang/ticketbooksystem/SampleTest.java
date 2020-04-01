package com.liang.ticketbooksystem;


import com.liang.ticketbooksystem.mapper.UserMapper;
import com.liang.ticketbooksystem.service.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
    @Autowired(required = false)
//    @Resource
    private UserMapper userMapper;

    @Test
    public void test(){
        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
