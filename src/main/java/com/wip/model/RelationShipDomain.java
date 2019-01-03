/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * DateTime: 2018/7/24 23:03
 **/
package com.wip.model;

import lombok.Data;

/**
 * 文章关联信息表
 */
@Data
public class RelationShipDomain {

    /**
     * 文章主键
     */
    private String cid;

    /**
     * 项目编号
     */
    private Integer mid;
}
