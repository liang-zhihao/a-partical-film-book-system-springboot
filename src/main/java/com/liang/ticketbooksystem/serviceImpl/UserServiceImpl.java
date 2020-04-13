package com.liang.ticketbooksystem.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.mapper.UserMapper;
import com.liang.ticketbooksystem.pojo.User;
import com.liang.ticketbooksystem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {

        try {
            List<User> users = userMapper.getUserList();

            return users;
        } catch (Exception e) {
            throw e;
//            return null;
        }
    }

    @Override
    public User findById(Integer id) {
        try {
            User user = userMapper.findById(id);

            return user;
        } catch (Exception e) {
            throw e;
//            return null;
        }
    }

    @Override
    public int auth(String proof, String password) {
        try {

            return userMapper.auth(  proof, password);
        } catch (Exception e) {
            throw e;
//            return null;
        }
    }
}
