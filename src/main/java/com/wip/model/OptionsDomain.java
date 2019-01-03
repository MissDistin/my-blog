/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * DateTime: 2018/7/27 21:52
 **/
package com.wip.model;

import lombok.Data;

/**
 * 系统相关信息
 */
@Data
public class OptionsDomain {

    //运行天数
    private String runDay;
    //访问总数
    private String visitNum;
    //文章数量（不入库，查询所得）
    private int articleNum;
    //说说条数（不入库，查询所得）
    private int chatNum;
}
