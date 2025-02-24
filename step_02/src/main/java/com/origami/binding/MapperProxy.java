package com.origami.binding;

import com.origami.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Origami
 * @description 代理类 实现 InvocationHandler 接口的 invoke 方法
 * @create 2025-02-24 15:59
 **/
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1L;

    private SqlSession sqlSession;

    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 判断该方法是否属于 Object 类 如果是则调用默认的Object类的方法（比如toString,equals,hashCode）
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            // 通过sqlSession获取对应方法的SQL映射 模拟sql执行
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
