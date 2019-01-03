/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * DateTime: 2018/7/27 21:55
 **/
package com.wip.service.option;

import com.wip.model.OptionsDomain;

import java.util.List;
import java.util.Map;

/**
 * 网站选项相关Service接口
 */
public interface OptionService {

    /**
     * 获取系统相关信息
     * @return
     */
    OptionsDomain getOptions();

    /**
     * 保存系统设置
     * @param
     */
    void saveOptions(OptionsDomain options);

    /**
     * 更新网站配置
     * @param
     * @param
     */
    void updateOptionByName(String name, String value);

    /**
     * 通过名称获取网站配置
     * @param site_record
     * @return
     */
    OptionsDomain getOptionByName(String site_record);
}
