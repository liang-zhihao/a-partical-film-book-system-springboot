package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Session;
import com.liang.ticketbooksystem.serviceImpl.CinemaServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.FilmServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.RoomServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.SessionServiceImpl;

import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionServiceImpl sessionService;
    private QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
    @Autowired
    private FilmServiceImpl filmService;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private CinemaServiceImpl cinemaService;

    @GetMapping("")
    public ResponseEntity<JSONObject> getList() {
        List<Session> list = sessionService.list();

        for (Session s : list
        ) {
          warpSession(s);
        }
        if (list == null) {
            return ServiceUtils.responseNotFound();
        } else {
            return ServiceUtils.responseOk(list, "get all sessions");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> del(@RequestParam("sessionId") Integer id) {
        Session session = sessionService.getById(id);
        boolean bool = sessionService.removeById(id);
        if (bool) {
            return ServiceUtils.response(HttpStatus.OK.value(), session, ResponseMsg.SUCCEED_TO_DELETE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), session, ResponseMsg.FAILED_TO_DELETE.v());
        }

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {

        Session session = JSONObject.toJavaObject(jsonObject, Session.class);
        boolean res = sessionService.updateById(session);
        System.out.println(session.getTime());
        session = sessionService.getById(session.getSessionId());
        if (res) {
            return ServiceUtils.response(HttpStatus.OK.value(), session, ResponseMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), session, ResponseMsg.FAILED_TO_UPDATE.v());
        }

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
        Session session = JSONObject.toJavaObject(jsonObject, Session.class);
        boolean i = sessionService.save(session);
        if (i) {
            return ServiceUtils.responseOk(session, ResponseMsg.SUCCEED_TO_CREATE.v());
        } else {
            return ServiceUtils.responseBad(session, ResponseMsg.FAILED_TO_CREATE.v());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getOne(@PathVariable Integer id) {
        Session obj = sessionService.getById(id);
        obj=warpSession(obj);
        if (obj != null) {
            return ServiceUtils.response(HttpStatus.OK.value(), obj, ResponseMsg.SUCCEED_TO_GET.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), null, ResponseMsg.FAILED_TO_GET.v());
        }
    }
    public Session warpSession(Session s){
//        s.setCinema(cinemaService.queryById(s.getCinemaId()));
        s.setFilm(filmService.getById(s.getFilmId()));
        s.setRoom(roomService.getById(s.getRoomId()));
        return s;
    }
//    @GetMapping("/session-duplication")
//    public ResponseEntity<JSONObject> isTypeDuplication(@RequestParam("type") String type) throws ClassNotFoundException {
//
//        boolean res=MyUtils.isDuplication("type",type,sessionService);
//        if (res ) {
//            return MyUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, the Sessionis duplicate");
//        } else {
//            return MyUtils.response(HttpStatus.OK.value(),"","succeed to find");
//        }
//
//    }
}
