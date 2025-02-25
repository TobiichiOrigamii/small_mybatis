package com.origami.builer;


import com.origami.session.Configuration;

/**
 * @author Origami
 * @description 构造器基类 为子类提供对 Configuration 配置对象的统一访问与管理
 * 规范子类行为 子类初始化时必须传入 Configuration 对象 确保所有构造器共享一个配置中心
 * @create 2025-02-25 10:07
 **/
public abstract class BaseBuilder {

    /**
     * 存储Mybatis的配置信息
     */
    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration(){
        return configuration;
    }

}
