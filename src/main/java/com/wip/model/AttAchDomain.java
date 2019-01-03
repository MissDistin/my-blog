/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/8/3 16:18
 **/
package com.wip.model;

import lombok.Data;

/**
 * 网站图片文件相关表
 */
@Data
public class AttAchDomain extends BaseDomain{

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源标题
     */
    private String title;

    /**
     * 资源描述
     */
    private String content;

    /**
     * 缩略图
     */
    private String picUrl;

    /**
     * 下载资源地址
     */
    private String downloadUrl;

    /**
     * 创建的时间戳
     */
    private Integer created;
}
