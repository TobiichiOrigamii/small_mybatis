package com.origami.binding;

import com.origami.mapping.MappedStatement;
import com.origami.mapping.SqlCommandType;
import com.origami.session.Configuration;
import com.origami.session.SqlSession;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * @author Origami
 * @description 映射器方法
 * @create 2025-02-25 16:12
 **/
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Configuration configuration, Class<?> mapperInterface, Method method) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
            default:
                throw new RuntimeException("未知的查询方法" + command.getType());
        }
        return result;
    }


    @Getter
    public static class SqlCommand {
        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            this.name = mappedStatement.getId();
            this.type = mappedStatement.getSqlCommandType();
        }
    }
}
