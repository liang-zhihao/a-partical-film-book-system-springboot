package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.User;
import com.liang.ticketbooksystem.serviceImpl.UserServiceImpl;

import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.MyMsg;
import com.liang.ticketbooksystem.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class UserController {


    @Autowired
    private UserServiceImpl userServiceImp;
    private QueryWrapper<User> queryWrapper = new QueryWrapper<>();

    // must use object int

    // use alibaba's fastjson
    @PostMapping(value = "/auth")
    public ResponseEntity<JSONObject> auth(@RequestBody JSONObject jsonParam) {
        JSONObject result = new JSONObject();

        String proof = jsonParam.getString("proof");
        String password = jsonParam.getString("password");
        if (userServiceImp.auth(proof, password) == 1) {
            String token = UUID.randomUUID().toString();
            result.put("info", "login successfully");
            result.put("access_token", token);
            result.put("code", 200);
            return MyUtils.response(HttpStatus.OK.value(), result, MyMsg.SUCCEED_TO_LOGIN.v());
        } else {
            result.put("info", "login failed");
            result.put("code", 300);
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), result, MyMsg.SUCCEED_TO_LOGIN.v());
        }


    }

    @GetMapping("/user")
    public ResponseEntity<JSONObject> getUsers() {
        List<User> users = userServiceImp.getUserList();
        if (users == null) {
            return MyUtils.responseNotFound();
        } else {
            return MyUtils.response(HttpStatus.OK.value(), users, MyMsg.SUCCEED_TO_GET.v());
        }

    }

    @DeleteMapping("/user")
    public ResponseEntity<JSONObject> delUser(@RequestParam("userId") Integer id) {
        User user = userServiceImp.getById(id);
        boolean bool = userServiceImp.removeById(id);
        if (bool) {
            return MyUtils.response(HttpStatus.OK.value(), user, MyMsg.SUCCEED_TO_DELETE.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), user, MyMsg.FAILED_TO_DELETE.v());
        }

    }

    @PutMapping("/user")
    public ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        queryWrapper.eq("username", user.getUsername());
        boolean res = userServiceImp.update(user, queryWrapper);
        user = userServiceImp.getOne(queryWrapper);
        if (res) {
            return MyUtils.response(HttpStatus.OK.value(), user, MyMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), user, MyMsg.FAILED_TO_UPDATE.v());
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<JSONObject> getUserById(@PathVariable Integer id) {
        User user = userServiceImp.getById(id);
        if (user != null) {
            return MyUtils.response(HttpStatus.OK.value(), user, MyMsg.SUCCEED_TO_GET.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), null, MyMsg.FAILED_TO_GET.v());
        }

    }

    @PostMapping("/user")
    public ResponseEntity<JSONObject> saveUser(@RequestBody JSONObject jsonObject) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = JSONObject.toJavaObject(jsonObject, User.class);

        queryWrapper.eq("username", user.getUsername());
        if (userServiceImp.count(queryWrapper) == 0) {
            Boolean i = userServiceImp.save(user);
            if (i == false) {
                return MyUtils.responseBad(user, MyMsg.FAILED_TO_UPDATE.v());

            }

            return MyUtils.response(HttpStatus.CREATED.value(), user, MyMsg.SUCCEED_TO_CREATE.v());
        }

        return MyUtils.response(HttpStatus.BAD_REQUEST.value(), user, "");
    }
    @GetMapping("/user/username-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) throws ClassNotFoundException {
        queryWrapper.clear();
        queryWrapper.eq("username", username);
//        int res = userServiceImp.count(queryWrapper);
        boolean res=MyUtils.isDuplication("username",username,userServiceImp);
        if (res ) {

            return MyUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, username is duplicate");
        } else {
            return MyUtils.response(HttpStatus.OK.value(),"","succeed to find");
        }

    }

}
