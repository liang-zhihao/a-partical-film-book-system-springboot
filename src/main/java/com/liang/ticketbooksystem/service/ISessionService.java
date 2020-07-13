package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    ResponseEntity<JSONObject> getList();

    ResponseEntity<JSONObject> del(@RequestParam("sessionId") Integer id);

    ResponseEntity<JSONObject> updateSession(@RequestBody JSONObject jsonObject);

    ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject);

    ResponseEntity<JSONObject> getSessionById(@PathVariable Integer id);

    void warp(Session s);
    void warpList(List<Session> list);
}
