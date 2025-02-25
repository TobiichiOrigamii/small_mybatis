package com.origami.session;

import com.origami.binding.MapperRegistry;
import com.origami.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Origami
 * @description 配置类
 * @create 2025-02-25 10:24
 **/
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句 存放在 map 中
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();


    /**
     * 扫描包路径并添加到映射器注册机中
     * @param packageName
     */
    public void addMappers(String packageName){
        mapperRegistry.addMappers(packageName);
    }

    /**
     * 添加映射器
     * @param type
     * @param <T>
     */
    public<T> void addMapper(Class<T> type){
        mapperRegistry.addMapper(type);
    }

    /**
     * 获取映射器
     * @param type
     * @param sqlSession
     * @return
     * @param <T>
     */
    public <T>  T getMapper(Class<T> type, SqlSession sqlSession){
        return mapperRegistry.getMapper(type, sqlSession);
    }

    /**
     * 是否存在映射器
     * @param type
     * @return
     */
    public boolean hasMapper(Class<?> type){
        return mapperRegistry.hasmapper(type);
    }

    /**
     * 添加映射语句
     * @param mappedStatement
     */
    public void addMappedStatement(MappedStatement mappedStatement){
        mappedStatements.put(mappedStatement.getId(),mappedStatement);
    }

    /**
     * 获取映射语句
     * @param id
     * @return
     */
    public MappedStatement getMappedStatement(String id){
        return mappedStatements.get(id);
    }




}
