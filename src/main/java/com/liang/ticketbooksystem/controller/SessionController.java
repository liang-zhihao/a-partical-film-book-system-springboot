package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Session;
import com.liang.ticketbooksystem.pojo.Type;
import com.liang.ticketbooksystem.serviceImpl.CinemaServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.FilmServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.RoomServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.SessionServiceImpl;

import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.MyMsg;
import com.liang.ticketbooksystem.utils.MyUtils;
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
            s.setCinema(cinemaService.getById(s.getCinemaId()));
            s.setFilm(filmService.getById(s.getFilmId()));
            s.setRoom(roomService.getById(s.getRoomId()));
        }
        if (list == null) {
            return MyUtils.responseNotFound();
        } else {
            return MyUtils.responseOk(list, "get all sessions");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> delUser(@RequestParam("sessionId") Integer id) {
        Session session = sessionService.getById(id);
        boolean bool = sessionService.removeById(id);
        if (bool) {
            return MyUtils.response(HttpStatus.OK.value(), session, MyMsg.SUCCEED_TO_DELETE.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), session, MyMsg.FAILED_TO_DELETE.v());
        }

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject) {
//        todo: com.alibaba.fastjson.JSONException: can not cast to : java.time.LocalDateTime
        Session session = JSONObject.toJavaObject(jsonObject, Session.class);
        boolean res = sessionService.updateById(session);
        session = sessionService.getById(session.getSessionId());
        if (res) {
            return MyUtils.response(HttpStatus.OK.value(), session, MyMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(), session, MyMsg.FAILED_TO_UPDATE.v());
        }

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> saveType(@RequestBody JSONObject jsonObject) {
        Session session = JSONObject.toJavaObject(jsonObject, Session.class);
        boolean i = sessionService.save(session);
        if (i) {
            return MyUtils.responseOk(session, MyMsg.SUCCEED_TO_CREATE.v());
        } else {
            return MyUtils.responseBad(session, MyMsg.FAILED_TO_CREATE.v());
        }
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
