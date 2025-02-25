package com.origami.session.defaults;

import com.origami.session.Configuration;
import com.origami.session.SqlSession;

/**
 * @author Origami
 * @description 默认SqlSession实现类
 * @create 2025-02-24 18:46
 **/
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
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
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
