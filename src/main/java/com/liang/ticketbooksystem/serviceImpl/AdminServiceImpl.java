package com.liang.ticketbooksystem.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.mapper.AdminMapper;
import com.liang.ticketbooksystem.mapper.CommentMapper;
import com.liang.ticketbooksystem.pojo.Admin;
import com.liang.ticketbooksystem.pojo.Comment;
import com.liang.ticketbooksystem.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int deleteByPrimaryKey(Integer adminId) {
        int i = -1;
        try {
            i = adminMapper.deleteByPrimaryKey(adminId);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public int insert(Admin record) {
        int i = -1;
        try {
            i = adminMapper.insert(record);
        } catch (Exception e) {
            throw e;
        }
        return i;
    }

    @Override
    public int insertSelective(Admin record) {
        int i = -1;
        try {
            i = adminMapper.insertSelective(record);
        } catch (Exception e) {
            throw e;
        }
        return i;
    }

    @Override
    public Admin selectByPrimaryKey(Integer adminId) {
        Admin i = null;
        try {
            i = adminMapper.selectByPrimaryKey(adminId);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        int i = -1;
        try {
            i = adminMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw e;
        }
        return i;
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        int i = -1;
        try {
            i = adminMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw e;
        }
        return i;
    }

    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> list = null;
        try {
            list = adminMapper.getAllAdmin();

        } catch (Exception e) {
            throw e;
        }
        return list;

    }
}
