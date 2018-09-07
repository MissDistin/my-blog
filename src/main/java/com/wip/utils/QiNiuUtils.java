/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: QiNiuUtils
 * Author:   Tinko
 * Date:     2018/8/20 9:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wip.utils;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈七牛云工具类〉<br>
 *
 * @author Tinko
 * @create 2018/8/20
 * @since 1.0.0
 */
@Service
public class QiNiuUtils {

    public static Map updataFile(InputStream inputStream, String name) {
        Map<String, String> map = new HashMap<>();
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone0());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = "D3E3WtEktbIMXL_QAmq7ofsoiypOsuY6TJMUyVhQ";
            String secretKey = "Q7tSQQF9-TdKm2REm2lHqcPCUrhnLkmmEu4X8O5N";
            String bucket = "blog";
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            name = LocalDateTime.now() + "_" + name;
            Response response1 = uploadManager.put(inputStream, name, upToken, null, null);
            int statusCode = response1.statusCode;
            if (statusCode == 200) {
                map.put("state", "SUCCESS");
                map.put("title", name);
                map.put("type", name.split("\\.")[1]);
                map.put("url", name);
            } else {
                map.put("state", "FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}