package com.liang.ticketbooksystem.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.mapper.CinemaMapper;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.service.ICinemaService;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements ICinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;
    private QueryWrapper<Cinema> queryWrapper = new QueryWrapper<>();

    @Override
    public ResponseEntity<JSONObject> getList() {
        List<Cinema> list = cinemaMapper.getCinemas();
        return list == null ? Response.notFound() : Response.ok(ResponseMsg.SUCCEED_TO_GET.v(), list);
    }

    @Override
    public ResponseEntity<JSONObject> createCinema(@RequestBody JSONObject jsonParam) {

        Cinema cinema = JSON.toJavaObject(jsonParam, Cinema.class);
        return cinemaMapper.insert(cinema) == 1 ? Response.succeedToCreate(cinema) : Response.failedToCreate();
    }

    @Override
    public ResponseEntity<JSONObject> updateCinema(@RequestBody JSONObject jsonParam) {
        Cinema cinema = JSON.toJavaObject(jsonParam, Cinema.class);
        int i = cinemaMapper.updateByPrimaryKeySelective(cinema);
        cinema = cinemaMapper.selectByPrimaryKey(cinema.getCinemaId());
        return i == 1 ? Response.succeedToUpdate(cinema) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> deleteCinema(@RequestParam("cinemaId") int cinemaId) {

        int i = cinemaMapper.deleteByPrimaryKey(cinemaId);
        return i == 1 ? Response.succeedToDelete() : Response.failedToDelete();

    }

    @Override
    public ResponseEntity<JSONObject> queryById(@PathVariable Integer id) {
        Cinema cinema = cinemaMapper.selectByPrimaryKey(id);
        return cinema == null ? Response.failedToQuery() : Response.succeedToQuery(cinema);

    }

    @Override
    public ResponseEntity<JSONObject> isCinemaDuplication(@RequestParam("name") String name) {
        queryWrapper.clear();
        queryWrapper.eq("name", name);
        return count(queryWrapper) != 0 ? Response.result(MyHttpStatus.INFO_DUPLICATION.value(), "", "Sorry, " + name + " is duplicate") : Response.ok("");

    }

}
