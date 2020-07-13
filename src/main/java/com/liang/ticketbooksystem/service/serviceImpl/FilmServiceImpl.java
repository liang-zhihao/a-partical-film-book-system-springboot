package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Film;
import com.liang.ticketbooksystem.mapper.FilmMapper;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.IFilmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.ServiceUtils;
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
 * @since 2020-04-04
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements IFilmService {
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmServiceImpl filmService;
    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private SessionServiceImpl sessionService;
    private final QueryWrapper queryWrapper = new QueryWrapper<>();

    @Override
    public ResponseEntity<JSONObject> createFilm(@RequestBody JSONObject jsonObject) {

        Film film = JSON.toJavaObject(jsonObject, Film.class);
        film.setStatus("Not released");

        if (!save(film)) {
            return Response.failedToCreate();
        }
        film = getById(film.getFilmId());
        wrap(film);
        return Response.succeedToCreate(film);

    }
    @Override
    public Film wrap(Film film) {
        if (film.getTypeId() != null) {
            String type = typeService.getById(film.getTypeId()).getType();
            film.setType(type);
        }
        queryWrapper.clear();
        queryWrapper.eq("film_id", film.getFilmId());
        List sessionList = sessionService.list(queryWrapper);
        sessionService.warpList(sessionList);
        film.setSessionList(sessionList);
        return film;
    }

    @Override
    public ResponseEntity<JSONObject> getList() {

        List<Film> list = list();

        if (list == null) {
            return Response.failedToQuery();
        }

        for (Film f : list
        ) {
            wrap(f);
        }
        return Response.succeedToQuery(list);
    }


    @Override
    public ResponseEntity<JSONObject> delFilm(@RequestParam("filmId") Integer id) {


        int i = filmMapper.deleteByPrimaryKey(id);
        return i == 1 ? Response.succeedToDelete() : Response.failedToDelete();


    }

    @Override
    public ResponseEntity queryById(@PathVariable Integer id) {
        Film film = filmMapper.selectByPrimaryKey(id);

        if (film != null) {
            wrap(film);
            return Response.succeedToQuery(film);
        }
        return Response.failedToQuery();
    }


    @Override
    public ResponseEntity<JSONObject> updateFilm(@RequestBody JSONObject jsonObject) {
        Film film = JSON.toJavaObject(jsonObject, Film.class);
        int i = filmMapper.updateByPrimaryKeySelective(film);
        return i == 1 ? Response.succeedToUpdate(film) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> isFilmDuplication(@RequestParam("name") String name) throws ClassNotFoundException {

        boolean res = ServiceUtils.isDuplication("name", name, filmService);
        return res ? Response.result(MyHttpStatus.INFO_DUPLICATION.value(), "", "Film name is duplicate") : Response.ok("no duplicate");

    }

}
