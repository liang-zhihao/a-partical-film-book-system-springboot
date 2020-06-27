package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.serviceImpl.CinemaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/cinema")
public class CinemaController {


    @Autowired
    private CinemaServiceImpl cinemaService;

    @GetMapping("")
    public ResponseEntity<JSONObject> getCinemas() {
        return cinemaService.getList();

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> createCinema(@RequestBody JSONObject jsonParam) {

        return cinemaService.createCinema(jsonParam);

    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> DeleteCinema(@RequestParam("cinemaId") Integer id) {
        return cinemaService.deleteCinema(id);

    }


    @PutMapping("")
    public ResponseEntity<JSONObject> upDateCinema(@RequestBody JSONObject jsonParam) {
        return cinemaService.updateCinema(jsonParam);

    }

    @GetMapping("{id}")
    public ResponseEntity<JSONObject> getCinemaById(@PathVariable int id) {
       return cinemaService.queryById(id);
    }

    @GetMapping("/cinema/cinemaName-duplication")
    public ResponseEntity<JSONObject> isCinemaDuplication(@RequestParam("name") String name) {
       return isCinemaDuplication(name);

    }

}


