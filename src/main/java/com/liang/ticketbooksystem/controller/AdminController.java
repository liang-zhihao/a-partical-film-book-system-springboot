package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Admin;
import com.liang.ticketbooksystem.serviceImpl.AdminServiceImpl;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    private QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

    @GetMapping("/admin")
    public ResponseEntity<JSONObject> getAdmins() {
        List<Admin> list = adminServiceImpl.getAllAdmin();
        if (list == null) {
            return MyUtils.response(HttpStatus.NO_CONTENT.value(),"","No Content");
        } else {
            return MyUtils.response(HttpStatus.OK.value(),list,"succeed to get admin");
        }


    }

    @PostMapping("/admin")
    public ResponseEntity<JSONObject> createAdmin(@RequestBody JSONObject jsonParam) {

        Admin admin = (Admin) JSON.toJavaObject(jsonParam, Admin.class);


        if (adminServiceImpl.insert(admin) == 1) {
            logger.info("--------------insert successful-----------");

            return MyUtils.response(HttpStatus.OK.value(),admin,"Admin insert successful");

        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(),"","Failed to insert admin");
        }

    }

    @PutMapping("/admin")
    public ResponseEntity<JSONObject> editAdmin(@RequestBody JSONObject jsonParam) {
        logger.info("---------start to update");
        Admin admin = (Admin) JSON.toJavaObject(jsonParam, Admin.class);
        int i = adminServiceImpl.updateByPrimaryKeySelective(admin);

        if (i == 1) {
            return MyUtils.response(HttpStatus.OK.value(),admin,"succeed to edit");

        } else {

            return MyUtils.response(HttpStatus.BAD_REQUEST.value(),admin,"failed to edit");
        }

    }

    @DeleteMapping("/admin")
    public ResponseEntity<JSONObject> deleteAdmin(@RequestParam("adminId") int adminId) {
        logger.info("---------start to delete");
        int i = adminServiceImpl.deleteByPrimaryKey(adminId);
        if (i == 1) {
            logger.info("---------succeed to delete");
            return MyUtils.response(HttpStatus.OK.value(),"","succeed to delete");
        } else {
            logger.info("---------failed to delete");
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(),"","failed to delete");
        }

    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<JSONObject> getAdminByKey(@PathVariable Integer id) {
        Admin admin = adminServiceImpl.selectByPrimaryKey(id);

        if (admin == null) {
            return MyUtils.response(HttpStatus.NOT_FOUND.value(),"","not found");
        } else {
            return MyUtils.response(HttpStatus.OK.value(),admin,"succeed to find");
        }

    }
//    Bookmark: get method must include params:{ }, post {}
    @GetMapping("/admin/username-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) {
        queryWrapper.clear();
        queryWrapper.eq("username", username);
        int res = adminServiceImpl.count(queryWrapper);

        if (res != 0) {

            return MyUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, username is duplicate");
        } else {
            return MyUtils.response(HttpStatus.OK.value(),"","succeed to find");
        }

    }


}
