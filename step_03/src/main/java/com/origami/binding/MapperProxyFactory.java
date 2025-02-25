package com.origami.binding;

import com.origami.session.SqlSession;
import lombok.Getter;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Origami
 * @description 映射器代理工厂
 * @create 2025-02-24 15:59
 **/
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    @Getter
    private Map<Method,MapperMethod> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    // newInstance 动态代理 创建一个代理对象 拦截对 mapperInterface 接口的所有方法调用
    // 在代理对象的行为中，实际的拦截逻辑由 MapperProxy 类中 invoke 方法实现
    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface,methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
