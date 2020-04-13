package com.liang.ticketbooksystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface UserMapper extends BaseMapper<User> {


    List<User> getUserList();

    User findById( Integer id);
     int auth(String proof, String password);
}
