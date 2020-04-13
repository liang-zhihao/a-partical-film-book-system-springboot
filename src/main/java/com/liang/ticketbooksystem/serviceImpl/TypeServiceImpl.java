package com.liang.ticketbooksystem.serviceImpl;

import com.liang.ticketbooksystem.pojo.Type;
import com.liang.ticketbooksystem.mapper.TypeMapper;
import com.liang.ticketbooksystem.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
