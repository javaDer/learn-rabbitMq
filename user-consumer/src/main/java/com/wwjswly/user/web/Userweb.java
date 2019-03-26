package com.wwjswly.user.web;


import com.wwjswly.user.entity.User;
import com.wwjswly.user.rabbitmq.UserSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZhaoFa
 * @since 2018-04-02
 */
@RestController
@Slf4j
@Api("用户相关服务")
public class Userweb {
    @Autowired
    private UserSender userSender;

    @ApiOperation(value = "获取指定id用户详细信息", notes = "根据user的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        user.setPassword("abc");
        user.setUsername("12345");
        user.setMessageId("MSID$" + System.currentTimeMillis());
        userSender.sendUser(user);
        return user;
    }

}
