package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.mapper.CommentMapper;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public ResponseEntity<JSONObject> getList() {
        List<Comment> list = list();
        return list == null ? Response.notFound() : Response.ok(ResponseMsg.SUCCEED_TO_GET.v(), list);
    }

    @Override
    public ResponseEntity<JSONObject> createComment(@RequestBody JSONObject jsonParam) {
        Comment comment = JSON.toJavaObject(jsonParam,Comment.class);
        return commentMapper.insert(comment) == 1 ? Response.succeedToCreate(comment) : Response.failedToCreate();
    }

    @Override
    public ResponseEntity<JSONObject> updateComment(@RequestBody JSONObject jsonParam) {

        Comment comment = JSON.toJavaObject(jsonParam, Comment.class);
       boolean res= updateById(comment);
        comment=getById(comment.getCommentId());
        return res ? Response.succeedToUpdate(comment) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> deleteComment(@RequestParam("commentId") int commentId) {

        int i =  commentMapper.deleteById(commentId);
        return i == 1 ? Response.succeedToDelete() : Response.failedToDelete();

    }

    @Override
    public ResponseEntity<JSONObject> queryById(@PathVariable Integer id) {
        Comment comment = commentMapper.selectById(id);
        return comment == null ? Response.failedToQuery() : Response.succeedToQuery(comment);
    }

}
