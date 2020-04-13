package com.liang.ticketbooksystem.serviceImpl;

import com.liang.ticketbooksystem.pojo.Order;
import com.liang.ticketbooksystem.mapper.OrderMapper;
import com.liang.ticketbooksystem.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
