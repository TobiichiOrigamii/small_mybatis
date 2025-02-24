package com.origami.test;

import com.origami.binding.MapperProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Origami
 * @description
 * @create 2025-02-24 15:59
 **/
@Slf4j
public class apiTest {

    @Test
    public void test_MapperProxyFactory() {
        // 创建 MapperProxyFactory 对象, 传入 IUserDao 接口
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        // 创建模拟的 SQL 查询语句
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("queryUserName", "模拟查询用户姓名");
        sqlSession.put("queryUserAge", "模拟查询用户年龄");

        // 通过工厂创建代理的对象
        IUserDao userDao = factory.newInstance(sqlSession);

        // 调用代理方法，实际会被拦截并返回模拟结果
        String result = userDao.queryUserName("123");
        log.info("查询结果：{}", result);

    }

    @Test
    public void test_proxy() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                (proxy, method, args) -> "你被代理了");

        String result = userDao.queryUserName("123");
        log.info("查询结果：{}", result);
    }


}
