package com.wip.model;

import com.wip.utils.UUID;

/**
 * 基本的实体类
 */
public class BaseDomain {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.UU32();
    }
}
