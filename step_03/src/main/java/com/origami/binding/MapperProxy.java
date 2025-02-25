package com.origami.binding;

import com.origami.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
        this.methodCache = methodCache;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 判断该方法是否属于 Object 类 如果是则调用默认的Object类的方法（比如toString,equals,hashCode）
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MapperMethod mapperMethod = cacheMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }

    }

    private MapperMethod cacheMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            // 找不到缓存 则创建新的MapperMethod
            mapperMethod = new MapperMethod(sqlSession.getConfiguration(), mapperInterface, method);
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
