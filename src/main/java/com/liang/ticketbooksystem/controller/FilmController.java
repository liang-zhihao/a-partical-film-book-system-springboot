package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.mapper.FilmMapper;
import com.liang.ticketbooksystem.pojo.Film;
import com.liang.ticketbooksystem.pojo.Type;
import com.liang.ticketbooksystem.serviceImpl.FilmServiceImpl;
import com.liang.ticketbooksystem.serviceImpl.TypeServiceImpl;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.MyMsg;
import com.liang.ticketbooksystem.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/api")
public class FilmController {
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmServiceImpl filmService;
    @Autowired
    private TypeServiceImpl typeService;
    private QueryWrapper<Film> queryWrapper = new QueryWrapper<>();

    @PostMapping("/film")
    public ResponseEntity<JSONObject> insertFilm(@RequestBody JSONObject jsonObject) {
        queryWrapper.clear();
        Film film = JSON.toJavaObject(jsonObject, Film.class);
        film.setStatus("Not released");
        queryWrapper.eq("name", film.getName());

        if (filmService.save(film)) {
            film = filmService.getById(film.getFilmId());
            if (film.getTypeId() != null) {
                String type = typeService.getById(film.getTypeId()).getType();
                JSONObject json = MyUtils.responseJson(HttpStatus.OK.value(), film, MyMsg.SUCCEED_TO_CREATE.v());
                Map<String, Object> map = new HashMap<>(1);
                map.put("type", type);
                json = MyUtils.addValToData(json, map);
                return ResponseEntity.status(HttpStatus.OK.value()).body(json);
            }
            return MyUtils.responseOk(film, MyMsg.SUCCEED_TO_CREATE.v());


        }
        return MyUtils.response(HttpStatus.BAD_REQUEST.value(), "", MyMsg.FAILED_TO_CREATE.v());


    }

    @GetMapping("/film")
    public ResponseEntity<JSONObject> getFilms() {
        queryWrapper.clear();
        queryWrapper.eq("1", "1");
        List<Film> list = filmMapper.selectList(queryWrapper);

        if (list != null) {
            JSONArray array = new JSONArray();
            for (Film film : list
            ) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(film);
                String type = "None";
                if (film.getTypeId() != null) {
                    Type t = typeService.getById(film.getTypeId());
                    type = t.getType();
                }
                jsonObject.put("type", type);
                array.add(jsonObject);
            }
            return MyUtils.response(HttpStatus.OK.value(), array, MyMsg.SUCCEED_TO_GET.v());
        }
        return ResponseEntity.badRequest().body(new JSONObject());
    }

    @DeleteMapping("/film")
    public ResponseEntity<JSONObject> delFilm(@RequestParam("filmId") Integer id) {


        if (filmService.removeById(id)) {
            return ResponseEntity.ok(new JSONObject());
        } else {
            return ResponseEntity.badRequest().body(new JSONObject());
        }


    }

    @GetMapping("/film/{id}")
    public ResponseEntity getFilmById(@PathVariable Integer id) {
        Film film = filmService.getById(id);
        return film != null ? MyUtils.responseOk(film,MyMsg.SUCCEED_TO_GET.v()) : ResponseEntity.badRequest().body(new JSONObject());
    }

    @PutMapping("/film")
    public ResponseEntity<JSONObject> updateFilm(@RequestBody JSONObject jsonObject) {
        queryWrapper.clear();
        String newName = jsonObject.getString("newFilm");

        Film film = JSON.toJavaObject(jsonObject, Film.class);

        if (film.getFilmId() != null) {
            return filmMapper.updateByPrimaryKeySelective(film) == 1 ? MyUtils.responseOk(film, MyMsg.SUCCEED_TO_UPDATE.v()) : MyUtils.responseNotFound();

        } else {
            queryWrapper.eq("name", film.getName());
            film.setName(newName);
            return filmService.update(film, queryWrapper) ? MyUtils.responseOk(film, MyMsg.SUCCEED_TO_UPDATE.v()) : MyUtils.responseNotFound();


        }

    }

    @GetMapping("/film/filmName-duplication")
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("name") String name) throws ClassNotFoundException {
        queryWrapper.clear();
        queryWrapper.eq("name", name);
        boolean res = MyUtils.isDuplication("name", name, filmService);
        if (res) {
            return MyUtils.response(MyHttpStatus.INFO_DUPLICATION.value(), "", "Sorry, name is duplicate");
        } else {
            return MyUtils.response(HttpStatus.OK.value(), "", "succeed to find");
        }

    }


}
