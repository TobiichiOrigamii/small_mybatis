package com.origami.mapping;

/**
 * @author Origami
 * @description SQL命令类型枚举
 * @create 2025-02-25 10:31
 **/
public enum SqlCommandType {
    /**
     * 未知命令类型
     */
    UNKNOWN,

    /**
     * SELECT命令类型
     */
    SELECT,

    /**
     * INSERT命令类型
     */
    INSERT,

    /**
     * UPDATE命令类型
     */
    UPDATE,

    /**
     * DELETE命令类型
     */
    DELETE;


}
