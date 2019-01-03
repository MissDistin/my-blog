package com.wip.model;

import com.wip.utils.UUID;
import lombok.Data;

/**
 * 说说管理
 */
@Data
public class ChatDomain {

    /**
     * 说说id
     */
    private String chid = UUID.UU32();
    /**
     * 说说内容
     */
    private String content;
    /**
     * 说说生成时的GMT unix时间戳
     */
    private Integer created;
    /**
     * 说说评论数
     */
    private Integer commentNum;
    /**
     * 说说点击数
     */
    private Integer clickNum;
    /**
     * 说说创建者
     */
    private String creator;

    /**
     * 当前说说背景色
     */
    private String bgClass;
}
