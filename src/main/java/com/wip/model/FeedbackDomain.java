package com.wip.model;

import com.wip.utils.UUID;
import lombok.Data;

/**
 * @Auther: Tinko
 * @Date: 2018/9/14 14:01
 * @Description: 留言板实体类
 */
@Data
public class FeedbackDomain {

    /**
     * 留言ID
     */
    private String fid = UUID.UU32();
    /**
     * 留言生成时的GMT unix时间戳
     */
    private Integer created;
    /**
     * 留言作者
     */
    private String author;
    /**
     * 留言者邮件
     */
    private String mail;
    /**
     * 留言者网址
     */
    private String url;
    /**
     * 评留言者ip地址
     *
     */
    private String ip;
    /**
     * 父级留言
     */
    private String parent;
    /**
     * 留言内容
     */
    private String content;
}
