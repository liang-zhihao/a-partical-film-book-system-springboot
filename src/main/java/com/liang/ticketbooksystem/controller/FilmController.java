package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.serviceImpl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/api/film")
public class FilmController {
    @Autowired
    private  FilmServiceImpl filmService;

    @PostMapping("/")
    public ResponseEntity<JSONObject> insertFilm(@RequestBody JSONObject jsonObject) {
        return filmService.createFilm(jsonObject);

    }

    @GetMapping("")
    public ResponseEntity<JSONObject> getFilms() {
        return filmService.getList();
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> delFilm(@RequestParam("filmId") Integer id) {

        return filmService.delFilm(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity getFilmById(@PathVariable Integer id) {
      return filmService.queryById(id);
    }

    @PutMapping("")
    public ResponseEntity<JSONObject> updateFilm(@RequestBody JSONObject jsonObject) {
      return   filmService.updateFilm(jsonObject);

    }
    @GetMapping("/count")
    public ResponseEntity<JSONObject> getCount() {
        return Response.succeedToQuery(filmService.count());
    }
    @GetMapping("/filmName-duplication")
    public ResponseEntity<JSONObject> isFilmDuplication(@RequestParam("name") String name) throws ClassNotFoundException {
        return filmService.isFilmDuplication(name);

    }


}
