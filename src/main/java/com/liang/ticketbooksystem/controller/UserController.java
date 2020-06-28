package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.service.serviceImpl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
public class UserController {


    @Autowired
    private UserServiceImpl service;

    // must use object int

    // use alibaba's fastjson

    @PostMapping(value = "/auth")
    public ResponseEntity<JSONObject> auth(@RequestBody JSONObject jsonParam) {
     return service.auth(jsonParam);


    }

    @GetMapping("/user")
    public ResponseEntity<JSONObject> getUsers() {
      return service.getList();

    }

    @DeleteMapping("/user")
    public ResponseEntity<JSONObject> delUser(@RequestParam("userId") Integer id) {
      return service.delUser(id);

    }

    @PutMapping("/user")
    public ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject) {
      return service.updateUser(jsonObject);
    }

        @GetMapping("/user/{id}")
    public ResponseEntity<JSONObject> getUserById(@PathVariable Integer id) {
   return service.getUserById(id);

    }


    @PostMapping("/user")
    public ResponseEntity<JSONObject> saveUser(@RequestBody JSONObject jsonObject) throws ClassNotFoundException {
      return service.create(jsonObject);
    }

    @GetMapping("/user/username-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) throws ClassNotFoundException {
 return service.isUsernameDuplication(username);
    }

    @PostMapping("/user/accessToken")
    public ResponseEntity<JSONObject> getUserByToken(@RequestBody JSONObject jsonObject) {
        return service.getUserByToken(jsonObject);
    }

}
