package com.liang.ticketbooksystem.serviceImpl;

import com.liang.ticketbooksystem.pojo.Session;
import com.liang.ticketbooksystem.mapper.SessionMapper;
import com.liang.ticketbooksystem.service.ISessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang
 * @since 2020-04-12
 */
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session> implements ISessionService {

}
