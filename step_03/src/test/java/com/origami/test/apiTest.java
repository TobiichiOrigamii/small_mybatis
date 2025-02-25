package com.origami.test;

import com.origami.io.Resources;
import com.origami.session.SqlSession;
import com.origami.session.SqlSessionFactory;
import com.origami.session.SqlSessionFactoryBuilder;
import com.origami.test.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Origami
 * @description
 * @create 2025-02-24 15:59
 **/
@Slf4j
public class apiTest {

    @Test
    public void test_MapperProxyFactory() throws IOException {
        // 1.注册 Mapper
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");

        // 2.从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4.验证测试
        String result = userDao.queryUserName("1111");
        log.info("测试结果：{}",result);

    }


}
