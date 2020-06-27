package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
public interface ICommentService extends IService<Comment> {
    ResponseEntity<JSONObject> queryById(@PathVariable Integer id);
    ResponseEntity<JSONObject> deleteComment(@RequestParam("commentId") int commentId);
    ResponseEntity<JSONObject> updateComment(@RequestBody JSONObject jsonParam);
    ResponseEntity<JSONObject> createComment(@RequestBody JSONObject jsonParam);
    ResponseEntity<JSONObject> getList();

}
