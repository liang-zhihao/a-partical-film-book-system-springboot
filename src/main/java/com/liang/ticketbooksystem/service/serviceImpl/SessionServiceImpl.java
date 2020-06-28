package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Session;
import com.liang.ticketbooksystem.mapper.SessionMapper;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.ISessionService;
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
 * @since 2020-04-12
 */
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session> implements ISessionService {

    @Autowired
    private FilmServiceImpl filmService;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private CinemaServiceImpl cinemaService;

    @Override
    public ResponseEntity<JSONObject> getList() {
        List<Session> list = list();
        if (list != null) {
            for (Session s : list
            ) {
                warp(s);
            }
            return Response.succeedToQuery(list);
        }
        return Response.failedToQuery();
    }

    @Override
    public ResponseEntity<JSONObject> del(@RequestParam("sessionId") Integer id) {

        return removeById(id) ? Response.succeedToDelete() : Response.failedToDelete();

    }

    @Override
    public ResponseEntity<JSONObject> updateSession(@RequestBody JSONObject jsonObject) {

        Session session = JSONObject.toJavaObject(jsonObject, Session.class);
        boolean res = updateById(session);
        session = getById(session.getSessionId());
        return res ? Response.succeedToUpdate(session) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject) {

        Session session = JSONObject.toJavaObject(jsonObject, Session.class);
      return save(session)? Response.succeedToCreate(session):Response.failedToCreate();
    }

    @Override
    public ResponseEntity<JSONObject> getSessionById(@PathVariable Integer id) {
        Session obj = getById(id);
        if(obj!=null){
            warp(obj);
            return  Response.succeedToQuery(obj);
        }
        return Response.failedToQuery();


    }
@Override
    public void warp(Session s) {
        s.setCinema(cinemaService.getById(s.getCinemaId()));
        s.setFilm(filmService.getById(s.getFilmId()));
        s.setRoom(roomService.getById(s.getRoomId()));
    }

}
