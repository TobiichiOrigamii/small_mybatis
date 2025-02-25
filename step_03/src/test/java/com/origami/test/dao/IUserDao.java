package com.origami.test.dao;

import com.origami.test.po.User;

/**
 * @author Origami
 * @description
 * @create 2025-02-24 16:12
 **/
public interface IUserDao {

    User queryUserInfoById(Long userId);


}
