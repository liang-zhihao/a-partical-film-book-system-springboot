package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.service.serviceImpl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeServiceImpl service;


    @GetMapping("")
    public ResponseEntity<JSONObject> getTypes() {
     return service.getList();
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("typeId") Integer id) {
       return service.del(id);

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject) {
    return service.updateType(jsonObject);

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> saveType(@RequestBody JSONObject jsonObject) {
        return service.create(jsonObject);
    }
    @GetMapping("/type-duplication")
    public ResponseEntity<JSONObject> isTypeDuplication(@RequestParam("type") String type) throws ClassNotFoundException {

        return service.isTypeDuplication(type);

    }



}
