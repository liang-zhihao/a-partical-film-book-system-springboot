package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.TicketOrder;
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
 * @since 2020-04-27
 */
public interface ITicketOrderService extends IService<TicketOrder> {
    void warp(TicketOrder order);
    ResponseEntity<JSONObject> getOrderById(@PathVariable Integer id);
    ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject);
    ResponseEntity<JSONObject> updateOrder(@RequestBody JSONObject jsonObject);
    ResponseEntity<JSONObject> del(@RequestParam("orderId") Integer id);
    ResponseEntity<JSONObject> getList();

}
