package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.serviceImpl.CommentServiceImpl;
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
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl service;

    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
        return service.getList();
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("commentId") Integer id) {
        return service.deleteComment(id);

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {
     return    service.updateComment(jsonObject);

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
        return service.createComment(jsonObject);
    }
}
