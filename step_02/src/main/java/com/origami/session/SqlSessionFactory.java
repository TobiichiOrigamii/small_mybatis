package com.origami.session;

/**
 * @author Origami
 * @description 工厂模式的接口 创建SqlSession对象
 * @create 2025-02-24 18:41
 **/
public interface SqlSessionFactory {

    /**
     * 创建SqlSession对象
     * @return SqlSession对象
     */
    SqlSession openSession();
}
