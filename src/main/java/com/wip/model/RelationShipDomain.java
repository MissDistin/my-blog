/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * DateTime: 2018/7/24 23:03
 **/
package com.wip.model;

/**
 * 文章关联信息表
 */
public class RelationShipDomain {

    /**
     * 文章主键
     */
    private String cid;

    /**
     * 项目编号
     */
    private Integer mid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}
