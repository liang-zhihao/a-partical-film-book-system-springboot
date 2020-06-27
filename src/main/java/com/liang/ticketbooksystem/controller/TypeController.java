package com.liang.ticketbooksystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.Type;
import com.liang.ticketbooksystem.serviceImpl.TypeServiceImpl;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.pojo.support.ResponseMsg;
import com.liang.ticketbooksystem.utils.ServiceUtils;
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
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;
    private QueryWrapper<Type> queryWrapper = new QueryWrapper<>();

    @GetMapping("")
    public ResponseEntity<JSONObject> getTypes() {
        List<Type> list = typeService.list();
        if (list == null) {
            return ServiceUtils.responseNotFound();
        } else {
            return ServiceUtils.responseOk(list, "get all types");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<JSONObject> delUser(@RequestParam("typeId") Integer id) {
        Type type = typeService.getById(id);
        boolean bool = typeService.removeById(id);
        if (bool) {
            return ServiceUtils.response(HttpStatus.OK.value(), type, ResponseMsg.SUCCEED_TO_DELETE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), type, ResponseMsg.FAILED_TO_DELETE.v());
        }

    }

    @PutMapping("")
    public ResponseEntity<JSONObject> updateUser(@RequestBody JSONObject jsonObject) {
        Type type = JSONObject.toJavaObject(jsonObject, Type.class);
        boolean res = typeService.updateById(type);
        type = typeService.getById(type.getTypeId());
        if (res) {
            return ServiceUtils.response(HttpStatus.OK.value(), type, ResponseMsg.SUCCEED_TO_UPDATE.v());
        } else {
            return ServiceUtils.response(HttpStatus.BAD_REQUEST.value(), type, ResponseMsg.FAILED_TO_UPDATE.v());
        }

    }

    @PostMapping("")
    public ResponseEntity<JSONObject> saveType(@RequestBody JSONObject jsonObject) {
        Type type = JSONObject.toJavaObject(jsonObject, Type.class);
        boolean i = typeService.save(type);
        if(i){
            return  ServiceUtils.responseOk(type, ResponseMsg.SUCCEED_TO_CREATE.v());
        }else {
            return  ServiceUtils.responseBad(type, ResponseMsg.FAILED_TO_CREATE.v());
        }
    }
    @GetMapping("/type-duplication")
    public ResponseEntity<JSONObject> isTypeDuplication(@RequestParam("type") String type) throws ClassNotFoundException {

        boolean res= ServiceUtils.isDuplication("type",type,typeService);
        if (res ) {
            return ServiceUtils.response(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, the type is duplicate");
        } else {
            return ServiceUtils.response(HttpStatus.OK.value(),"","succeed to find");
        }

    }



}
