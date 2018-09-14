package com.wip.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wip.constant.Types;
import com.wip.controller.BaseController;
import com.wip.model.ChatDomain;
import com.wip.model.ContentDomain;
import com.wip.service.chat.ChatService;
import com.wip.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api("说说相关接口")
@Controller
@RequestMapping("/admin/chat")
public class ChatController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @ApiOperation("进入说说列表页")
    @GetMapping(value = "")
    public String index(
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
            int page,
            @ApiParam(name = "limit", value = "每页条数", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "15")
            int limit,
            HttpServletRequest request

    ) {
        PageInfo<ChatDomain> chat = chatService.getChat(new ChatDomain(), page, limit);
        request.setAttribute("chat", chat);
        return "admin/chat_list";
    }

    @ApiOperation("添加说说")
    @PostMapping(value = "/add")
    @ResponseBody
    public APIResponse addChat(
            @ApiParam(name = "content", value = "说说", required = true)
            @RequestParam(name = "content", required = true) String content)
    {
        ChatDomain chatDomain = new ChatDomain();
        chatDomain.setClickNum(1);
        chatDomain.setCreator("丁涛");
        chatDomain.setContent(content);

        // 添加说说
        chatService.addChat(chatDomain);

        return APIResponse.success();
    }

}
