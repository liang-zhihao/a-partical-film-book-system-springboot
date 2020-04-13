package com.liang.ticketbooksystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.serviceImpl.CinemaServiceImpl;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.MyMsg;
import com.liang.ticketbooksystem.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/cinema")
public class CinemaController {

    //    @Autowired
//    private UserServiceImp userServiceImp;
//    @GetMapping("/admin/{id}")
//    public JSONObject getAdminByKey(@PathVariable Integer id){
//        Admin admin= adminServiceImp.selectByPrimaryKey(id);
//        if(admin==null){
//            return getFailJson("get, not exist");
//        }else {
//            return getSuccessfulJson("get "+admin.toString());
//        }
//
//    }
    @Autowired
    private CinemaServiceImpl cinemaServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private QueryWrapper<Cinema> queryWrapper = new QueryWrapper<>();
    @GetMapping("")
    public ResponseEntity<JSONObject> getCinemas() {
        List<Cinema> list = cinemaServiceImpl.getCinemas();

        if (list == null) {
            return MyUtils.response(HttpStatus.NO_CONTENT.value(),"","No Content");
        } else {
            return MyUtils.response(HttpStatus.OK.value(),list,"Cinemas");
        }

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> createCinema(@RequestBody JSONObject jsonParam) {
//        TODO:"Is repeated or not";
        Cinema cinema = (Cinema) JSON.toJavaObject(jsonParam, Cinema.class);

        if (cinemaServiceImpl.insert(cinema) == 1) {
            return MyUtils.response(HttpStatus.OK.value(),cinema,"succeed to create");

        }else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(),"","Failed to create");
        }

    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> DeleteCinema( @RequestParam("cinemaId") Integer id) {
        logger.info(id+"");
        Cinema cinema =cinemaServiceImpl.selectByPrimaryKey(id);
        int i = cinemaServiceImpl.deleteByPrimaryKey(id);


        if (i == 1) {
            return MyUtils.response(HttpStatus.OK.value(),id,"succeed to delete");
        } else {
            return MyUtils.response(HttpStatus.BAD_REQUEST.value(),id,"failed to delete");
        }


    }

    @PutMapping("")
    public ResponseEntity<JSONObject> upDateCinema(@RequestBody JSONObject jsonParam) {
        Cinema cinema = (Cinema) JSON.toJavaObject(jsonParam, Cinema.class);

        int i = cinemaServiceImpl.updateByPrimaryKeySelective(cinema);
        cinema=cinemaServiceImpl.selectByPrimaryKey(cinema.getCinemaId());

        if (i == 1) {
            return MyUtils.response(HttpStatus.NO_CONTENT.value(),"", MyMsg.FAILED_TO_DELETE.v());
        } else {
            return MyUtils.response(HttpStatus.NO_CONTENT.value(),"","No Content");
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<JSONObject> getCinemaById(@PathVariable int id){
        Cinema cinema=cinemaServiceImpl.selectByPrimaryKey(id);
        if(cinema==null){
            return MyUtils.response(HttpStatus.NO_CONTENT.value(),"","No Content");
        }else {
            return MyUtils.response(HttpStatus.OK.value(),cinema,MyMsg.SUCCEED_TO_GET.v());
        }
    }
    @GetMapping("/cinema/cinemaName-duplication")
    public ResponseEntity<JSONObject> isCinemaDuplication(@RequestParam("name") String name) {
        queryWrapper.clear();
        queryWrapper.eq("name", name);
        int res = cinemaServiceImpl.count(queryWrapper);

        if (res != 0) {

            return MyUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, "+name+" is duplicate");
        } else {
            return MyUtils.response(HttpStatus.OK.value(),"","succeed to find");
        }

    }

}


