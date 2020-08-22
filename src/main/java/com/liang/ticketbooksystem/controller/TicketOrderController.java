package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/api/order")
public class TicketOrderController {
    @Autowired
    private TicketOrderServiceImpl service;


    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
       return service.getList();
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("orderId") Integer id) {
     return service.del(id);

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {

        return service.updateOrder(jsonObject);

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
      return service.create(jsonObject);
    }
    @GetMapping("/count")
    public ResponseEntity<JSONObject> getCount() {
        return Response.succeedToQuery(service.count());
    }
    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getOne(@PathVariable Integer id) {
      return service.getOrderById(id);
    }
}
