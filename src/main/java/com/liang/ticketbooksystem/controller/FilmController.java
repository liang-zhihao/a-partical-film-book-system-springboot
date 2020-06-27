package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.serviceImpl.FilmServiceImpl;
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
@RequestMapping("/api")
public class FilmController {
    @Autowired
    private FilmServiceImpl filmService;

    @PostMapping("/film")
    public ResponseEntity<JSONObject> insertFilm(@RequestBody JSONObject jsonObject) {
        return filmService.createFilm(jsonObject);

    }

    @GetMapping("/film")
    public ResponseEntity<JSONObject> getFilms() {
        return filmService.getList();
    }

    @DeleteMapping("/film")
    public ResponseEntity<JSONObject> delFilm(@RequestParam("filmId") Integer id) {

        return filmService.delFilm(id);

    }

    @GetMapping("/film/{id}")
    public ResponseEntity getFilmById(@PathVariable Integer id) {
      return filmService.queryById(id);
    }

    @PutMapping("/film")
    public ResponseEntity<JSONObject> updateFilm(@RequestBody JSONObject jsonObject) {
      return   filmService.updateFilm(jsonObject);

    }

    @GetMapping("/film/filmName-duplication")
    public ResponseEntity<JSONObject> isFilmDuplication(@RequestParam("name") String name) throws ClassNotFoundException {
        return filmService.isFilmDuplication(name);

    }


}
