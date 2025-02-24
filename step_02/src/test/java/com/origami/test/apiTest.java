package com.origami.test;

import com.origami.binding.MapperProxyFactory;
import com.origami.binding.MapperRegistry;
import com.origami.session.SqlSession;
import com.origami.session.SqlSessionFactory;
import com.origami.session.defaults.DefaultSqlSessionFactory;
import com.origami.test.dao.IUserDao;
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
        // 1.注册 Mapper
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.origami.test.dao");

        // 2.从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4.验证测试
        String result = userDao.queryUserName("1111");
        log.info("测试结果：{}",result);

    }


}
