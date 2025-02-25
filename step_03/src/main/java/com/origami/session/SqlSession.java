package com.origami.session;

/**
 * @author Origami
 * @description SqlSession接口来执行SQL 获取映射器 管理事物
 * 通常情况下 我们在应用程序中使用的Mybatis的API都是这个接口定义的方法
 * @create 2025-02-24 18:41
 **/
public interface SqlSession {

    /**
     * 根据指定的SqlID获取一条记录的封装对象
     *
     * @param statement SqlID
     * @param <T>       封装之后的对象类型
     * @return Mapped object 封装之后的对象
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的SqlID和参数获取一条记录的封装对象
     * 一般在实际应用中 这个参数传递的是pojo 或者map或者immutableMap
     *
     * @param statement SqlID
     * @param parameter 参数
     * @param <T>       封装之后的对象类型
     * @return Mapped object 封装之后的对象
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * @param type mapper接口类型
     * @param <T>  mapper接口类型
     * @return mapper接口的实例
     */
    <T> T getMapper(Class<T> type);

    /**
     * 得到配置
     *
     * @return Configuration
     */
    Configuration getConfiguration();
}
