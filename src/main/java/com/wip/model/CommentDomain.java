/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/31 15:27
 **/
package com.wip.model;

import com.wip.utils.UUID;
import lombok.Data;

/**
 * 评论表
 */
@Data
public class CommentDomain {
    /**
     * comment表主键
     */
    private String coid = UUID.UU32();
    /**
     * contents表主键,关联字段
     */
    private String cid;
    /**
     * 评论生成时的GMT unix时间戳
     */
    private Integer created;
    /**
     * 评论作者
     */
    private String author;
    /**
     * 评论所属用户id
     */
    private String authorId;
    /**
     * 评论所属内容作者id
     */
    private Integer ownerId;
    /**
     * 评论者邮件
     */
    private String mail;
    /**
     * 评论者网址
     */
    private String url;
    /**
     * 评论者ip地址
     *
     */
    private String ip;
    /**
     * 评论者客户端
     */
    private String agent;
    /**
     * 评论类型
     */
    private String type;
    /**
     * 评论状态
     */
    private String status;
    /**
     * 父级评论
     */
    private Integer parent;
    /**
     * 评论内容
     */
    private String content;
}
