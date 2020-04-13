package com.liang.ticketbooksystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICinemaService extends IService<Cinema> {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer cinemaId);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);
    List<Cinema> getCinemas();
}