package com.liang.ticketbooksystem.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.mapper.AdminMapper;
import com.liang.ticketbooksystem.pojo.Admin;
import com.liang.ticketbooksystem.pojo.support.Response;
import com.liang.ticketbooksystem.service.IAdminService;
import com.liang.ticketbooksystem.utils.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    private QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

    @Override
    public ResponseEntity<JSONObject> getList() {
        List<Admin> list = adminMapper.getAllAdmin();
        return list == null ? Response.notFound() : Response.ok("succeed to get admin", list);
    }

    @Override
    public ResponseEntity<JSONObject> createAdmin(@RequestBody JSONObject jsonParam) {

        Admin admin = JSON.toJavaObject(jsonParam, Admin.class);
        return adminMapper.insert(admin) == 1 ? Response.succeedToCreate(admin) : Response.failedToCreate();
    }

    @Override
    public ResponseEntity<JSONObject> updateAdmin(@RequestBody JSONObject jsonParam) {
        Admin admin = JSON.toJavaObject(jsonParam, Admin.class);
        int i = adminMapper.updateByPrimaryKeySelective(admin);
        return i == 1 ? Response.succeedToUpdate(admin) : Response.failedToUpdate();

    }

    @Override
    public ResponseEntity<JSONObject> deleteAdmin(@RequestParam("adminId") int adminId) {

        int i = adminMapper.deleteByPrimaryKey(adminId);
        return i == 1 ? Response.succeedToDelete() : Response.failedToDelete();

    }

    @Override
    public ResponseEntity<JSONObject> queryById(@PathVariable Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin == null ? Response.failedToQuery() : Response.succeedToQuery(admin);

    }
    @Override
    public ResponseEntity<JSONObject> isUsernameDuplication(@RequestParam("username") String username) {
        queryWrapper.clear();
        queryWrapper.eq("username", username);
        return count(queryWrapper) != 0 ? Response.result(MyHttpStatus.INFO_DUPLICATION.value(), "", "Username is duplicate") : Response.ok("Succeed to find");


    }

}
