package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.auth.JwtTokenUtil;
import com.liang.ticketbooksystem.pojo.User;
import com.liang.ticketbooksystem.serviceImpl.UserServiceImpl;

import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class UserController {


    @Autowired
    private UserServiceImpl userServiceImp;
    private QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    // must use object int

    // use alibaba's fastjson

    @PostMapping(value = "/auth")
    public ResponseEntity<JSONObject> auth(@RequestBody JSONObject jsonParam) {
        JSONObject result = new JSONObject();
        String proof = jsonParam.getString("proof");
        String password = jsonParam.getString("password");


        if (userServiceImp.auth(proof, password) == 1) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(proof);
            final String token = jwtTokenUtil.generateToken(userDetails);
            queryWrapper.clear();
            queryWrapper.eq("username",userDetails.getUsername());
            User user= userServiceImp.getOne(queryWrapper) ;
            result.put("access_token", token);
            result.put("userId",user.getUserId());
            return ServiceUtils.response(HttpStatus.OK.value(), result, ResponseMsg.SUCCEED_TO_LOGIN.v());
        } else {
            result.put("info", "login failed");
            result.put("code", 300);
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), result, "failed to login");
        }


    }

    @GetMapping("/user")
    public ResponseEntity<JSONObject> getUsers() {
        List<User> users = userServiceImp.getUserList();
        if (users == null) {
            return ServiceUtils.responseNotFound();
        } else {
            return ServiceUtils.response(HttpStatus.OK.value(), users, ResponseMsg.SUCCEED_TO_GET.v());
        }

    }

    @DeleteMapping("/user")
    public ResponseEntity<JSONObject> delUser(@RequestParam("userId") Integer id) {
        User user = userServiceImp.getById(id);
        boolean bool = userServiceImp.removeById(id);
        if (bool) {
            return ServiceUtils.response(HttpStatus.OK.value(), user, ResponseMsg.SUCCEED_TO_DELETE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), user, ResponseMsg.FAILED_TO_DELETE.v());
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
            return ServiceUtils.response(HttpStatus.OK.value(), user, ResponseMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), user, ResponseMsg.FAILED_TO_UPDATE.v());
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<JSONObject> getUserById(@PathVariable Integer id) {
        User user = userServiceImp.getById(id);
        if (user != null) {
            return ServiceUtils.response(HttpStatus.OK.value(), user, ResponseMsg.SUCCEED_TO_GET.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), null, ResponseMsg.FAILED_TO_GET.v());
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
                return ServiceUtils.responseBad(user, ResponseMsg.FAILED_TO_UPDATE.v());

            }

            return ServiceUtils.response(HttpStatus.CREATED.value(), user, ResponseMsg.SUCCEED_TO_CREATE.v());
        }

        return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), user, "");
    }
    @GetMapping("/user/username-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) throws ClassNotFoundException {
        queryWrapper.clear();
        queryWrapper.eq("username", username);
//        int res = userServiceImp.count(queryWrapper);
        boolean res= ServiceUtils.isDuplication("username",username,userServiceImp);
        if (res ) {

            return ServiceUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, username is duplicate");
        } else {
            return ServiceUtils.response(HttpStatus.OK.value(),"","succeed to find");
        }
    }
    @PostMapping("/user/accessToken")
    public ResponseEntity<JSONObject> getUserByToken(@RequestBody JSONObject jsonObject){
        String token=jsonObject.getString("accessToken");
        String username= jwtTokenUtil.getUsernameFromToken(token);
        queryWrapper.clear();
        queryWrapper.eq("username",username);
        User user= userServiceImp.getOne(queryWrapper);
        if (user != null) {
            return ServiceUtils.response(HttpStatus.OK.value(), user, ResponseMsg.SUCCEED_TO_GET.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), null, ResponseMsg.FAILED_TO_GET.v());
        }
    }

}
