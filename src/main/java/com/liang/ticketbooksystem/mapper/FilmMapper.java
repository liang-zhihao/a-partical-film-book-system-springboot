package com.liang.ticketbooksystem.mapper;

import com.liang.ticketbooksystem.pojo.Film;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
public interface FilmMapper extends BaseMapper<Film> {
    int deleteByPrimaryKey(Integer filmId);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer filmId);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);
}
