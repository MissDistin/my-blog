package com.wip.model;

import com.wip.utils.UUID;

/**
 * 说说管理
 */
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

    public String getChid() {
        return chid;
    }

    public void setChid(String chid) {
        this.chid = chid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
