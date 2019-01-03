/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/24 16:36
 **/
package com.wip.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 项目表
 */
@Data
public class MetaDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 项目主键
     */
    private Integer mid;
    /**
     * 名称
     */
    private String name;

    /**
     * 项目缩略名
     */
    private String slug;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 对应的文章类型
     */
    private String contentType;

    /**
     * 选项描述
     */
    private String description;

    /**
     * 项目排序
     */
    private Integer sort;

    private Integer parent;
}
