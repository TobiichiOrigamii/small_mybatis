package com.origami.session;

import com.origami.builer.xml.XMLConfigBuilder;
import com.origami.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;


/**
 * @author Origami
 * @description SqlSessionFactoryBuilder作为整个Mybatis的入口类
 * 通过制定解析XML的IO 引导整个流程的启动
 * @create 2025-02-25 09:56
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }



}
