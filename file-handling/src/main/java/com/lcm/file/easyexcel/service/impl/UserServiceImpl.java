package com.lcm.file.easyexcel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcm.file.easyexcel.entity.User;
import com.lcm.file.easyexcel.mapper.UserMapper;
import com.lcm.file.easyexcel.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lcm
 * @since 2019-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
