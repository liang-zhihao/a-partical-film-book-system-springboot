package com.liang.ticketbooksystem.service;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
public interface ITypeService extends IService<Type> {
    ResponseEntity<JSONObject> isTypeDuplication(@RequestParam("type") String type) throws ClassNotFoundException;
    ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject);
    ResponseEntity<JSONObject> updateType(@RequestBody JSONObject jsonObject);
    ResponseEntity<JSONObject> del(@RequestParam("typeId") Integer id);
    ResponseEntity<JSONObject> getList();

}
