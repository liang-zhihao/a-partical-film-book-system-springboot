package com.liang.ticketbooksystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdminService extends IService<Admin> {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAllAdmin();
}