package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface IAdminService extends IService<Admin> {
    ResponseEntity<JSONObject> getList();

    ResponseEntity<JSONObject> queryById(@PathVariable Integer id);

    ResponseEntity<JSONObject> deleteAdmin(@RequestParam("adminId") int adminId);

    ResponseEntity<JSONObject> updateAdmin(@RequestBody JSONObject jsonParam);

    ResponseEntity<JSONObject> createAdmin(@RequestBody JSONObject jsonParam);

    ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username);



}