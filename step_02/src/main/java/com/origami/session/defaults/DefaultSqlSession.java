package com.origami.session.defaults;

import com.origami.binding.MapperRegistry;
import com.origami.session.SqlSession;
import com.origami.session.SqlSessionFactory;

/**
 * @author Origami
 * @description 默认SqlSession实现类
 * @create 2025-02-24 18:46
 **/
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = new MapperRegistry();
    }


    @Override
    public <T> T selectOne(String statement) {
        return (T) ("selectOne:你被代理了： "+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("selectOne:你被代理了： "+statement + "  参数： " + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
