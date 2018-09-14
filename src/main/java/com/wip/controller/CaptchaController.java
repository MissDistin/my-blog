package com.wip.controller;

import com.wip.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/kaptcha")
public class CaptchaController {

    @RequestMapping(value="/image",method=RequestMethod.GET)
    public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        VerifyCodeUtils randomValidateCode = new VerifyCodeUtils();
        randomValidateCode.getRandcode(request, response);//输出验证码图片
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 删除以前的
        session.removeAttribute("verCode");
        session.removeAttribute("codeTime");
        session.setAttribute("verCode", (String)request.getSession().getAttribute("RANDOMREDISKEY"));
        session.setAttribute("codeTime", LocalDateTime.now());
    }


}
