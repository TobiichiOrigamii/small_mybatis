package com.origami.session.defaults;

import com.origami.session.Configuration;
import com.origami.session.SqlSession;
import com.origami.session.SqlSessionFactory;

/**
 * @author Origami
 * @description 默认的SqlSession工厂方法
 * @create 2025-02-24 18:46
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory (Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
