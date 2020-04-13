package com.liang.ticketbooksystem.serviceImpl;

import com.liang.ticketbooksystem.pojo.Film;
import com.liang.ticketbooksystem.mapper.FilmMapper;
import com.liang.ticketbooksystem.service.IFilmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements IFilmService {

}
