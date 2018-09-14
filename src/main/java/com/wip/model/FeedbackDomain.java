package com.wip.model;

import com.wip.utils.UUID;

/**
 * @Auther: Tinko
 * @Date: 2018/9/14 14:01
 * @Description: 留言板实体类
 */
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

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
