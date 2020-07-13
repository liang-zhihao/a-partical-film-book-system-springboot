package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.auth.JwtTokenUtil;
import com.liang.ticketbooksystem.mapper.UserMapper;
import com.liang.ticketbooksystem.pojo.User;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.service.IUserService;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userServiceImp;
    private final QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    // must use object int

    // use alibaba's fastjson
    @Override
    public ResponseEntity<JSONObject> auth(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        String proof = jsonParam.getString("proof");
        String password = jsonParam.getString("password");

        if (userMapper.auth(proof, password) == 1) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(proof);
            final String token = jwtTokenUtil.generateToken(userDetails);
            queryWrapper.clear();
            queryWrapper.eq("username", userDetails.getUsername());
            User user = userServiceImp.getOne(queryWrapper);
            user.setAccessToken(token);
            return Response.ok(ResponseMsg.SUCCEED_TO_LOGIN.v(), user);
        }
        return Response.bad("failed to login");


    }

    @Override
    public ResponseEntity<JSONObject> getList() {
        List<User> list = list();
        if (list != null) {
            return Response.succeedToQuery(list);
        }

        return Response.failedToQuery();

    }

    @Override
    public ResponseEntity<JSONObject> delUser(@RequestParam("userId") Integer id) {
        return removeById(id) ? Response.succeedToDelete() : Response.failedToDelete();
    }

    @Override
    public ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject) {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        boolean res = updateById(user);
        user = getById(user.getUserId());
        return res ? Response.succeedToUpdate(user) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> getUserById(@PathVariable Integer id) {
        User user = userServiceImp.getById(id);
        if (user != null) {
            return ServiceUtils.response(HttpStatus.OK.value(), user, ResponseMsg.SUCCEED_TO_GET.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), null, ResponseMsg.FAILED_TO_GET.v());
        }

    }

    @Override
    public ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject) throws ClassNotFoundException {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
//        if (ServiceUtils.isDuplication("username", user.getUsername(), this)) {
//
//
//        }
        return save(user) ? Response.succeedToCreate(user) : Response.failedToCreate();
//        return Response.result(MyHttpStatus.INFO_DUPLICATION.value(), "", "username has existed");

    }
    @Override
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) throws ClassNotFoundException {

        boolean res = ServiceUtils.isDuplication("username", username, this);
        return res ? Response.ok("") : Response.result(MyHttpStatus.INFO_DUPLICATION.value(), "", "username can not use");
    }

    @Override
    public ResponseEntity<JSONObject> getUserByToken(@RequestBody JSONObject jsonObject) {
        String token = jsonObject.getString("accessToken");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        queryWrapper.clear();
        queryWrapper.eq("username", username);
        User user = userServiceImp.getOne(queryWrapper);
        return user != null ? Response.succeedToQuery(user) : Response.failedToQuery();

    }
    public void warp(User user){

    }


}
