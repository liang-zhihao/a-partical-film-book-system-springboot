package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.serviceImpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("")
    public ResponseEntity<JSONObject> getAdmin() {
        return adminService.getList();
    }

    @PostMapping("")
    public ResponseEntity<JSONObject> createAdmin(@RequestBody JSONObject jsonParam) {
    return adminService.createAdmin(jsonParam);

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> updateAdmin(@RequestBody JSONObject jsonParam) {
       return  adminService.updateAdmin(jsonParam);
    }


    @DeleteMapping("")
    public ResponseEntity<JSONObject> deleteAdmin(@RequestParam("adminId") int adminId) {

       return adminService.deleteAdmin(adminId);

    }

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getAdminByKey(@PathVariable Integer id) {
        return adminService.queryById(id);

    }
    @GetMapping("/count")
    public ResponseEntity<JSONObject> getCount() {
        int count=adminService.count();
        return Response.succeedToQuery(count);
    }
//    Bookmark: get method must include params:{ }, post {}
    @GetMapping("/admin/username-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) {
       return adminService.isUsernameDuplication(username);

    }


}
