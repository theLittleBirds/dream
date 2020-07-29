package com.zc.dream.service.impl;

import com.zc.dream.dao.UserMapper;
import com.zc.dream.model.User;
import com.zc.dream.service.UserService;
import com.zc.dream.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2020/07/27.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Resource
    private UserMapper tBaseUserMapper;

    @Override
    public void save(User user) {
        user.setCreatedate(new Date());
        mapper.insertSelective(user);
    }
}
