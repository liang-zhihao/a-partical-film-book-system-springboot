package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.service.serviceImpl.RoomServiceImpl;
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
@RequestMapping("/api/room")

public class RoomController {
    @Autowired
    private RoomServiceImpl service;

    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
        return service.getList();

    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("roomId") Integer id) {
        return service.del(id);

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {
        return service.update(jsonObject);

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
        return service.save(jsonObject);
    }
}
