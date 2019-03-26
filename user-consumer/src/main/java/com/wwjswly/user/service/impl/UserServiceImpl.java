package com.wwjswly.user.service.impl;

import com.wwjswly.user.entity.User;
import com.wwjswly.user.mapper.UserMapper;
import com.wwjswly.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhaoFa
 * @since 2018-04-02 
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
