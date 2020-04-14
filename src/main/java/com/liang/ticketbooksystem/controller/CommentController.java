package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.pojo.Room;
import com.liang.ticketbooksystem.serviceImpl.CommentServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.RoomServiceImpl;
import com.liang.ticketbooksystem.utils.MyMsg;
import com.liang.ticketbooksystem.utils.MyUtils;
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
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl service;
    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
        List<Comment> list = service.list();
        if (list == null) {
            return MyUtils.responseNotFound();
        } else {
            return MyUtils.responseOk(list, "get all comments");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("commentId") Integer id) {
        Comment comment = service.getById(id);
        boolean bool = service.removeById(id);
        if (bool) {
            return MyUtils.response(HttpStatus.OK.value(), comment, MyMsg.SUCCEED_TO_DELETE.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), comment, MyMsg.FAILED_TO_DELETE.v());
        }

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {
        Comment comment = JSONObject.toJavaObject(jsonObject, Comment.class);
        boolean res = service.updateById(comment);
        comment = service.getById(comment.getCommentId());
        if (res) {
            return MyUtils.response(HttpStatus.OK.value(), comment, MyMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), comment, MyMsg.FAILED_TO_UPDATE.v());
        }

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
        Comment comment = JSONObject.toJavaObject(jsonObject, Comment.class);
        boolean i = service.save(comment);
        if (i) {
            return MyUtils.responseOk(comment, MyMsg.SUCCEED_TO_CREATE.v());
        } else {
            return MyUtils.responseBad(comment, MyMsg.FAILED_TO_CREATE.v());
        }
    }
}
