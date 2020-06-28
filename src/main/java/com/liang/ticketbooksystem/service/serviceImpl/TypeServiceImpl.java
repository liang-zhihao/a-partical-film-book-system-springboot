package com.liang.ticketbooksystem.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.liang.ticketbooksystem.pojo.Type;
import com.liang.ticketbooksystem.mapper.TypeMapper;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import com.liang.ticketbooksystem.utils.ServiceUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Override
    public ResponseEntity<JSONObject> getList() {
        List<Type> list = list();
        if (list != null) {
            return Response.succeedToQuery(list);
        }

        return Response.failedToQuery();
    }

    @Override
    public ResponseEntity<JSONObject> del(@RequestParam("typeId") Integer id) {
        return removeById(id) ? Response.succeedToDelete() : Response.failedToDelete();

    }
    @Override
    public ResponseEntity<JSONObject> updateType(@RequestBody JSONObject jsonObject) {

        Type type = JSONObject.toJavaObject(jsonObject, Type.class);
        boolean res = updateById(type);
        type = getById(type.getTypeId());
        return res ? Response.succeedToUpdate(type) : Response.failedToUpdate();

    }
    @Override
    public ResponseEntity<JSONObject> create(@RequestBody JSONObject jsonObject) {
        Type type = JSONObject.toJavaObject(jsonObject, Type.class);
      return save(type)?Response.succeedToCreate(type):Response.failedToCreate();
    }
@Override
    public ResponseEntity<JSONObject> isTypeDuplication(@RequestParam("type") String type) throws ClassNotFoundException {

        boolean res= ServiceUtils.isDuplication("type",type,this);
        if (res ) {
            return Response.result(MyHttpStatus.INFO_DUPLICATION.value(),"","Sorry, the type is duplicate");
        } else {
            return Response.result(HttpStatus.OK.value(),"","succeed to find");
        }

    }
}
