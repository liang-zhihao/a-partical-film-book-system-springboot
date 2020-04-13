package com.liang.ticketbooksystem.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.ticketbooksystem.mapper.AdminMapper;
import com.liang.ticketbooksystem.mapper.CinemaMapper;
import com.liang.ticketbooksystem.pojo.Admin;
import com.liang.ticketbooksystem.pojo.Cinema;
import com.liang.ticketbooksystem.service.IAdminService;
import com.liang.ticketbooksystem.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements ICinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public int deleteByPrimaryKey(Integer cinemaId) {
        int i = -1;
        try {
            i = cinemaMapper.deleteByPrimaryKey(cinemaId);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public int insert(Cinema record) {
        int i = -1;
        try {
            i = cinemaMapper.insert(record);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public int insertSelective(Cinema record) {
        int i = -1;
        try {
            i = cinemaMapper.insertSelective(record);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public Cinema selectByPrimaryKey(Integer cinemaId) {
        Cinema i ;
        try {
            i = cinemaMapper.selectByPrimaryKey(cinemaId);

        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public int updateByPrimaryKeySelective(Cinema record) {
        int i = -1;
        try {
            i = cinemaMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public int updateByPrimaryKey(Cinema record) {
        int i = -1;
        try {
            i = cinemaMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw e;
        }

        return i;
    }

    @Override
    public List<Cinema> getCinemas() {
        List<Cinema> cinemas= cinemaMapper.getCinemas();
        return cinemas;
    }
}
