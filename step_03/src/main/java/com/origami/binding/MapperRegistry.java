package com.origami.binding;

import com.origami.session.Configuration;
import com.origami.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Origami
 * @description 映射器注册机 扫描包路径并添加到映射器注册机中 如果类型不对则报错
 * @create 2025-02-24 18:40
 **/
public class MapperRegistry {

    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    /**
     * 获取映射器实例
     *
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);

        System.out.println((MapperProxyFactory<T>) knownMappers.get(type));

        System.out.println(mapperProxyFactory);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + "MapperRegistry未注册。");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("获取映射器实例时出错。原因：" + e, e);
        }
    }

    /**
     * 注册映射器
     *
     * @param type 映射器接口
     * @param <T>  映射器接口类型
     */
    public <T> void addMapper(Class<T> type) {
        // 必须是接口才会注册
        if (type.isInterface()) {
            if (hasmapper(type)) {
                // 已经注册过了
                throw new RuntimeException("Type " + type + " MapperRegistry已经注册。");
            }
            // 注册
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hasmapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    /**
     * 扫描包路径并添加到映射器注册机中
     *
     * @param packageName 包路径
     */
    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }


}
