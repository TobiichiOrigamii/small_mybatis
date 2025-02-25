package com.origami.test.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Origami
 * @description
 * @create 2025-02-25 16:38
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    private Long id;
    private String userId;
    private String userHead;
    private Date createTime;
    private Date updateTime;


}
