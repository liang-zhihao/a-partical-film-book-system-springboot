package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Admin;
import com.liang.ticketbooksystem.serviceImpl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/admin")
    public ResponseEntity<JSONObject> getAdmin() {
       return adminService.getList();
    }

    @PostMapping("/admin")
    public ResponseEntity<JSONObject> createAdmin(@RequestBody JSONObject jsonParam) {
    return adminService.createAdmin(jsonParam);

    }

    @PutMapping("/admin")
    public ResponseEntity<JSONObject> updateAdmin(@RequestBody JSONObject jsonParam) {
       return  adminService.updateAdmin(jsonParam);
    }


    @DeleteMapping("/admin")
    public ResponseEntity<JSONObject> deleteAdmin(@RequestParam("adminId") int adminId) {

       return adminService.deleteAdmin(adminId);

    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<JSONObject> getAdminByKey(@PathVariable Integer id) {
        return adminService.queryById(id);

    }
//    Bookmark: get method must include params:{ }, post {}
    @GetMapping("/admin/username-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) {
       return adminService.isUsernameDuplication(username);

    }


}
