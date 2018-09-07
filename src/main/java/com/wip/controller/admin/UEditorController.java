/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UEditorController
 * Author:   Tinko
 * Date:     2018/8/14 15:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wip.controller.admin;

import com.wip.utils.QiNiuUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 〈UEditor用于上次文章内容〉<br>
 * 〈〉
 *
 * @author Tinko
 * @create 2018/8/14
 * @since 1.0.0
 */
@Controller
@RequestMapping("/article")
public class UEditorController {

    @RequestMapping("/ueditor")
    @ResponseBody
    public Object ueditor(HttpServletRequest request) throws Exception {
        String action = request.getParameter("action");
        //图片上传
        if ("uploadimage".equals(action)) {
            String contentType = request.getContentType();
            if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
                MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                //获取上传的文件
                Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
                if (fileMap.size() > 0) {
                    //获取第一个就是
                    for (String key : fileMap.keySet()) {
                        MultipartFile multipartFile = fileMap.get(key);
                        InputStream inputStream = multipartFile.getInputStream();
                        String name = multipartFile.getOriginalFilename();
                        //上传七牛云服务器
                        Map map = QiNiuUtils.updataFile(inputStream, name);
                        return map;
                    }
                }
            }
            return null;
            //所有图片列表
        } else if ("config".equals(action)) {
            File file1 = ResourceUtils.getFile("classpath:config.json");
            return FileUtils.readFileToString(file1, "UTF-8");
        } else {
            //其他的可以自己去实现
            return null;
        }

    } }