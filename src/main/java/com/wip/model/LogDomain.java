/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/23 17:00
 **/
package com.wip.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 日志表
 */
@Data
public class LogDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Integer id;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Integer authorId;

    /**
     * 日志产生的IP
     */
    private String ip;

    /**
     * 日志创建时间
     */
    private Integer created;
}
