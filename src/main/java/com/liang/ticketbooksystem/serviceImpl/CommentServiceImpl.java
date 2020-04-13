package com.liang.ticketbooksystem.serviceImpl;

import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.mapper.CommentMapper;
import com.liang.ticketbooksystem.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
