package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Room;
import com.liang.ticketbooksystem.mapper.RoomMapper;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2020-04-12
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    public ResponseEntity<JSONObject> getList() {
        List<Room> list = list();
        return list != null ? Response.succeedToQuery(list) : Response.failedToQuery();

    }

    public ResponseEntity<JSONObject> del(@RequestParam("roomId") Integer id) {

        boolean bool = removeById(id);
        return bool ? Response.succeedToDelete() : Response.failedToDelete();

    }


    public ResponseEntity<JSONObject> update(@RequestBody JSONObject jsonObject) {
        Room room = JSONObject.toJavaObject(jsonObject, Room.class);
        boolean res = updateById(room);
        room = getById(room.getRoomId());
        return res ? Response.succeedToUpdate(room) : Response.failedToUpdate();

    }

    public ResponseEntity<JSONObject> save(@RequestBody JSONObject jsonObject) {
        Room room = JSONObject.toJavaObject(jsonObject, Room.class);
        boolean i = save(room);
        return i ? Response.succeedToCreate(room) : Response.failedToCreate();

    }
}
