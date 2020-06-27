package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.pojo.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface ICinemaService extends IService<Cinema> {
    ResponseEntity<JSONObject> queryById(@PathVariable Integer id);
    ResponseEntity<JSONObject> deleteCinema(@RequestParam("cinemaId") int cinemaId);
    ResponseEntity<JSONObject> updateCinema(@RequestBody JSONObject jsonParam);
    ResponseEntity<JSONObject> createCinema(@RequestBody JSONObject jsonParam);
    ResponseEntity<JSONObject> getList();
    ResponseEntity<JSONObject> isCinemaDuplication(@RequestParam("name") String name);
}