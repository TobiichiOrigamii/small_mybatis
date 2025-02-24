package com.origami.session.defaults;

import com.origami.binding.MapperRegistry;
import com.origami.session.SqlSession;
import com.origami.session.SqlSessionFactory;

/**
 * @author Origami
 * @description 默认的SqlSession工厂方法
 * @create 2025-02-24 18:46
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory (MapperRegistry mapperRegistry){
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}
