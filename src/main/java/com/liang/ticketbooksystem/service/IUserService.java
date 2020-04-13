package com.liang.ticketbooksystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService extends IService<User> {

     List<User> getUserList();
     User findById(Integer id);
    int auth(String proof, String password);
}
