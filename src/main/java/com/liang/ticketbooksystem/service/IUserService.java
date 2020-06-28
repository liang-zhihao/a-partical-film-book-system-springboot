package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface IUserService extends IService<User> {

    ResponseEntity<JSONObject> getUserByToken(@RequestBody JSONObject jsonObject);
    ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) throws ClassNotFoundException;
    ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject) throws ClassNotFoundException;
    ResponseEntity<JSONObject> getUserById(@PathVariable Integer id);
    ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject);
    ResponseEntity<JSONObject> delUser(@RequestParam("userId") Integer id);
    ResponseEntity<JSONObject> getList();
    ResponseEntity<JSONObject> auth(@RequestBody JSONObject jsonParam);

}
