package com.wip.model;

import com.wip.utils.UUID;
import lombok.Data;

/**
 * 基本的实体类
 */
@Data
public class BaseDomain {

    private String id = UUID.UU32();
}
