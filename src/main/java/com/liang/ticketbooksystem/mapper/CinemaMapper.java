package com.liang.ticketbooksystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.ticketbooksystem.pojo.Cinema;

import java.util.List;

public interface CinemaMapper extends BaseMapper<Cinema> {
    int deleteByPrimaryKey(Integer cinemaId);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer cinemaId);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);

    List<Cinema> getCinemas();
}
