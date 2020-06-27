package com.liang.ticketbooksystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.pojo.Session;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang
 * @since 2020-04-12
 */
public interface ISessionService extends IService<Session> {
    List<Session> myGetList();
    List<Session> myGetList(QueryWrapper queryWrapper);
}
