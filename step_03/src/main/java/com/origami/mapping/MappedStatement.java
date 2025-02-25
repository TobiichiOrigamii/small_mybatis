package com.origami.mapping;

import com.origami.session.Configuration;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author Origami
 * @description SQL信息记录对象
 * 包括：SQL类型 SQL语句 入参 出参
 * @create 2025-02-25 10:29
 **/
@Builder
@Data
public class MappedStatement {

    // 配置
    private Configuration configuration;
    // id
    private String id;
    // SQL操作类型
    private SqlCommandType sqlCommandType;
    // 入参类型
    private String parameterType;
    // 出参类型
    private String resultType;
    // SQL语句
    private String sql;
    // 参数占位符 parameter.put(1, "id");
    private Map<Integer, String> parameter;



}
