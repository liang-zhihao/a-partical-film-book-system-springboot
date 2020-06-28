package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.service.serviceImpl.SessionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionServiceImpl service;


    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
        return service.getList();
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("sessionId") Integer id) {
       return service.del(id);

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {

        return service.updateSession(jsonObject);

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
       return service.create(jsonObject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getOne(@PathVariable Integer id) {
   return   service.getSessionById(id);
    }

//    @GetMapping("/session-duplication")
//    public ResponseEntity<JSONObject> isTypeDuplication(@RequestParam("type") String type) throws ClassNotFoundException {
//
//        boolean res=MyUtils.isDuplication("type",type,sessionService);
//        if (res ) {
//            return MyUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, the Sessionis duplicate");
//        } else {
//            return MyUtils.response(HttpStatus.OK.value(),"","succeed to find");
//        }
//
//    }
}
