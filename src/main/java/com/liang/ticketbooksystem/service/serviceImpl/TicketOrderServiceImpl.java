package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.*;
import com.liang.ticketbooksystem.mapper.TicketOrderMapper;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.ITicketOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liang
 * @since 2020-04-27
 */
@Service
public class TicketOrderServiceImpl extends ServiceImpl<TicketOrderMapper, TicketOrder> implements ITicketOrderService {
    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private FilmServiceImpl filmService;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public ResponseEntity<JSONObject> getList() {
        List<TicketOrder> list = list();
        if (list != null) {
            for (TicketOrder order : list
            ) {
                warp(order);
            }
            return Response.succeedToQuery(list);
        }

        return Response.failedToQuery();
    }

    @Override
    public ResponseEntity<JSONObject> del(@RequestParam("orderId") Integer id) {
        return removeById(id) ? Response.succeedToDelete() : Response.failedToDelete();

    }

    @Override
    public ResponseEntity<JSONObject> updateOrder(@RequestBody JSONObject jsonObject) {

        TicketOrder order = JSONObject.toJavaObject(jsonObject, TicketOrder.class);
        boolean res = updateById(order);
        order = getById(order.getOrderId());
        return res ? Response.succeedToUpdate(order) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject) {

        TicketOrder order = JSONObject.toJavaObject(jsonObject, TicketOrder.class);
        if (save(order)) {
            warp(order);
            return Response.succeedToCreate(order);
        } else {
            return Response.failedToCreate();
        }
    }

    @Override
    public ResponseEntity<JSONObject> getOrderById(@PathVariable Integer id) {
        TicketOrder obj = getById(id);
        if (obj != null) {
            warp(obj);
            return Response.succeedToQuery(obj);
        }
        return Response.failedToQuery();
    }
@Override
    public void warp(TicketOrder order) {
        User user = userService.getById(order.getUserId());
        Session session = sessionService.getById(order.getSessionId());
        Film film = filmService.getById(session.getFilmId());
        Room room = roomService.getById(session.getRoomId());
        order.setFilm(film);
        order.setRoom(room);
        order.setSession(session);
        order.setUser(user);

    }

}
