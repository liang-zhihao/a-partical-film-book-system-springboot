package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Room;
import com.liang.ticketbooksystem.serviceImpl.RoomServiceImpl;
import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/api/room")
//TODO: start with here! update your code!
public class RoomController {
    @Autowired
    private RoomServiceImpl service;
    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
        List<Room> list = service.list();
        if (list == null) {
            return ServiceUtils.responseNotFound();
        } else {
            return ServiceUtils.responseOk(list, "get all rooms");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("roomId") Integer id) {
        Room room = service.getById(id);
        boolean bool = service.removeById(id);
        if (bool) {
            return ServiceUtils.response(HttpStatus.OK.value(), room, ResponseMsg.SUCCEED_TO_DELETE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), room, ResponseMsg.FAILED_TO_DELETE.v());
        }

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {
        Room room = JSONObject.toJavaObject(jsonObject, Room.class);
        boolean res = service.updateById(room);
        room = service.getById(room.getRoomId());
        if (res) {
            return ServiceUtils.response(HttpStatus.OK.value(), room, ResponseMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), room, ResponseMsg.FAILED_TO_UPDATE.v());
        }

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
        Room room = JSONObject.toJavaObject(jsonObject, Room.class);
        boolean i = service.save(room);
        if (i) {
            return ServiceUtils.responseOk(room, ResponseMsg.SUCCEED_TO_CREATE.v());
        } else {
            return ServiceUtils.responseBad(room, ResponseMsg.FAILED_TO_CREATE.v());
        }
    }
}
