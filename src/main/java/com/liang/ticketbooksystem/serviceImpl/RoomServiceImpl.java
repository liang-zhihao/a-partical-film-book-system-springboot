package com.liang.ticketbooksystem.serviceImpl;

import com.liang.ticketbooksystem.pojo.Room;
import com.liang.ticketbooksystem.mapper.RoomMapper;
import com.liang.ticketbooksystem.service.IRoomService;
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
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

}
