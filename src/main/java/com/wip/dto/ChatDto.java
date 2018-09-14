/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/8/3 16:31
 **/
package com.wip.dto;

public class ChatDto{
    /**
     * 说说id
     */
    private Integer chid;
    /**
     * 说说内容
     */
    private String content;
    /**
     * 说说生成时的GMT unix时间戳
     */
    private Integer created;
    /**
     * 评论点击数
     */
    private Integer clickNum;
    /**
     * 说说创建者
     */
    private String creator;
    /**
     * 说说总数量
     */
   private int count;
    /**
     * 评论数
     */
    private int commentNum;

    public Integer getChid() {
        return chid;
    }

    public void setChid(Integer chid) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
