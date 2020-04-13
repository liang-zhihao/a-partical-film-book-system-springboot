package com.liang.ticketbooksystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.ticketbooksystem.pojo.Admin;
import com.liang.ticketbooksystem.pojo.Comment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAllAdmin();
}