package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Film;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
public interface IFilmService extends IService<Film> {
    ResponseEntity<JSONObject> isFilmDuplication(@RequestParam("name") String name) throws ClassNotFoundException;

    ResponseEntity<JSONObject> updateFilm(@RequestBody JSONObject jsonObject);

    ResponseEntity queryById(@PathVariable Integer id);

    ResponseEntity<JSONObject> delFilm(@RequestParam("filmId") Integer id);

    ResponseEntity<JSONObject> getList();

    ResponseEntity<JSONObject> createFilm(@RequestBody JSONObject jsonObject);
    Film wrap(Film film);
}
