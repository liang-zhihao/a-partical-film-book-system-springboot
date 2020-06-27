package com.liang.ticketbooksystem.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.mapper.CinemaMapper;
import com.liang.ticketbooksystem.mapper.FilmMapper;
import com.liang.ticketbooksystem.mapper.RoomMapper;
import com.liang.ticketbooksystem.pojo.Session;
import com.liang.ticketbooksystem.mapper.SessionMapper;
import com.liang.ticketbooksystem.service.ISessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private SessionMapper sessionMapper;
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private RoomMapper roomMapper;
    private QueryWrapper queryWrapper = new QueryWrapper();

    @Override
    public List<Session> myGetList(QueryWrapper queryWrapper) {

        if (queryWrapper.isEmptyOfWhere()) {
            queryWrapper.eq("1", "1");
        }
        List<Session> list = sessionMapper.selectList(queryWrapper);


        for (Session s : list
        ) {
            s.setCinema(cinemaMapper.selectById(s.getCinemaId()));
            s.setFilm(filmMapper.selectById(s.getFilmId()));
            s.setRoom(roomMapper.selectById(s.getRoomId()));
        }
        return list;
    }
    @Override
    public List<Session> myGetList() {

        queryWrapper.eq("1", "1");
        List<Session> list = sessionMapper.selectList(queryWrapper);


        for (Session s : list
        ) {
            s.setCinema(cinemaMapper.selectById(s.getCinemaId()));
            s.setFilm(filmMapper.selectById(s.getFilmId()));
            s.setRoom(roomMapper.selectById(s.getRoomId()));
        }
        return list;
    }
}
