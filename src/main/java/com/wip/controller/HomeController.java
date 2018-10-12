package com.wip.controller;

import com.github.pagehelper.PageInfo;
import com.wip.constant.ErrorConstant;
import com.wip.constant.Types;
import com.wip.constant.WebConst;
import com.wip.dto.MetaDto;
import com.wip.dto.cond.ContentCond;
import com.wip.exception.BusinessException;
import com.wip.model.*;
import com.wip.service.article.ContentService;
import com.wip.service.chat.ChatService;
import com.wip.service.comment.CommentService;
import com.wip.service.feedback.FeedbackService;
import com.wip.service.meta.MetaService;
import com.wip.utils.APIResponse;
import com.wip.utils.HtmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("博客前台页面")
@Controller
public class HomeController extends BaseController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MetaService metaService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private FeedbackService feedBackService;


    @GetMapping(value = "/")
    public String index(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
            int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "5")
            int limit
    ) {
        PageInfo<ContentDomain> articles = contentService.getArticlesByCond(new ContentCond(), page, limit);
        //右侧最新文章
        request.setAttribute("articles",articles);

        aside(request);
        return "home/index";
    }

    /**
     * 获取右侧数据
     * @param request
     * @return
     */
    public HttpServletRequest aside(HttpServletRequest request){
        List<ContentDomain> contents = contentService.findArticlesByLimit();
        List<ChatDomain> chats = chatService.chatLimit();
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        //右侧最新说说
        request.setAttribute("chats",chats);
        //右侧推荐文章
        request.setAttribute("contents", contents);
        //右侧标签云
        request.setAttribute("tags",tags);
        return request;
    }

    @ApiOperation("归档内容页")
    @GetMapping(value = "/archives")
    public String archives(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
            int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "10")
            int limit
    ) {
        PageInfo<ContentDomain> articles = contentService.getArticlesByCond(new ContentCond(), page, limit);
        request.setAttribute("articles", articles);
        return "blog/archives";
    }

    @ApiOperation("分类内容页")
    @GetMapping(value = "/categories")
    public String categories(HttpServletRequest request) {
        // 获取分类
        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(),null,WebConst.MAX_POSTS);
        // 分类总数
        Long categoryCount = metaService.getMetasCountByType(Types.CATEGORY.getType());
        request.setAttribute("categories", categories);
        request.setAttribute("categoryCount", categoryCount);
        return "blog/category";
    }

    @ApiOperation("分类详情页")
    @GetMapping(value = "/categories/{name}")
    public String categoriesDetail(
            HttpServletRequest request,
            @ApiParam(name = "name", value = "分类名称", required = true)
            @PathVariable("name")
            String name
    ) {
        MetaDomain category = metaService.getMetaByName(Types.CATEGORY.getType(),name);
        if (null == category.getName())
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        List<ContentDomain> articles = contentService.getArticleByCategory(category.getName());
        request.setAttribute("category", category.getName());
        request.setAttribute("articles", articles);
        return "blog/category_detail";
    }

    @ApiOperation("标签内容页")
    @GetMapping(value = "/tags")
    public String tags(HttpServletRequest request) {
        // 获取标签
        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
        // 标签总数
        Long tagCount = metaService.getMetasCountByType(Types.TAG.getType());
        request.setAttribute("tags", tags);
        request.setAttribute("tagCount", tagCount);
        return "blog/tags";
    }

    @ApiOperation("标签详情页")
    @GetMapping(value = "/tags/{name}")
    public String tagsDetail(
            HttpServletRequest request,
            @ApiParam(name = "name", value = "标签名", required = true)
            @PathVariable("name")
            String name
    ) {
        MetaDomain tags = metaService.getMetaByName(Types.TAG.getType(),name);
        List<ContentDomain> articles = contentService.getArticleByTags(tags);
        request.setAttribute("articles",articles);
        request.setAttribute("tag",tags.getName());
        aside(request);
        return "home/tag";
    }

    @ApiOperation("文章内容页")
    @GetMapping(value = "/detail/{cid}")
    public String detail(
            @ApiParam(name = "cid", value = "文章主键", required = true)
            @PathVariable("cid")
            String cid,
            HttpServletRequest request
    ) {
        ContentDomain article = contentService.getArticleById(cid);
        request.setAttribute("article", article);

        // 更新文章的点击量
//        this.updateArticleHits(article.getCid(),article.getHits());
        Integer hits = article.getHits() + 1;
        ContentDomain temp = new ContentDomain();
        temp.setCid(cid);
        temp.setHits(hits);
        contentService.updateContentByCid(temp);
        // 获取评论
        List<CommentDomain> comments = commentService.getCommentsByCId(cid);
        request.setAttribute("comments", comments);
        aside(request);
        return "home/detail";
    }

    /**
     * 更新文章的点击率
     * @param cid
     * @param chits
     */
    private void updateArticleHits(String cid, Integer chits) {
        Integer hits = cache.hget("article", "hits");
        if (chits == null) {
            chits = 0;
        }
        hits = null == hits ? 1 : hits + 1;
        if (hits >= WebConst.HIT_EXEED) {
            ContentDomain temp = new ContentDomain();
            temp.setCid(cid);
            temp.setHits(chits + hits);
            contentService.updateContentByCid(temp);
            cache.hset(cid, "hits", 1);
        } else {
            cache.hset(cid, "hits", hits);
        }

    }

    /**
     * 文章和说说的评论
     * @param request
     * @param response
     * @param type
     * @param cid
     * @param coid
     * @param author
     * @param email
     * @param url
     * @param content
     * @return
     */
    @PostMapping(value = "/comment")
    @ResponseBody
    public APIResponse comment(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "type", required = true) String type,
                               @RequestParam(name = "cid", required = true) String cid,
                               @RequestParam(name = "coid",required = false) Integer coid,
                               @RequestParam(name = "author",required = false) String author,
                               @RequestParam(name = "email",required = false) String email,
                               @RequestParam(name = "url",required = false) String url,
                               @RequestParam(name = "content",required = true) String content
                               ) {
        CommentDomain comments = new CommentDomain();
        comments.setAuthor(author);
        comments.setCid(cid);
        comments.setIp(request.getRemoteAddr());
        comments.setUrl(url);
        comments.setContent(content);
        comments.setEmail(email);
        comments.setParent(coid);

        commentService.addComment(comments, type);
        return APIResponse.success();
    }

    @ApiOperation("关于我内容页")
    @GetMapping(value = "/about")
    public String about(HttpServletRequest request) {
//        PageInfo<ContentDomain> articles = contentService.getArticlesByCond(new ContentCond(), page, limit);
//        request.setAttribute("articles", articles);
        aside(request);
        return "home/about";
    }

    @ApiOperation("说说内容页")
    @GetMapping(value = "/chat")
    public String chat(HttpServletRequest request,
                       @ApiParam(name = "page", value = "页数", required = false)
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @ApiParam(name = "limit", value = "每页数量", required = false)
                       @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        PageInfo<ChatDomain> chat = chatService.getChat(new ChatDomain(), page, limit);
        request.setAttribute("chat", chat);
        aside(request);
        return "home/chat";
    }

    @ApiOperation("进入说说详情页")
    @GetMapping(value = "/chatDetail/{chid}")
    public String chatDetail(@ApiParam(name = "chid", value = "文章主键", required = true)
                         @PathVariable("chid") String chid,
                         HttpServletRequest request){
        ChatDomain chat = chatService.getChatByChid(chid);
        List<CommentDomain> comments = commentService.getCommentsByCId(chid);
        request.setAttribute("chat", chat);
        request.setAttribute("comments", comments);
        aside(request);
        return "home/chat_detail";
    }

    @ApiOperation("进入留言页")
    @GetMapping(value = "/feedback")
    public String feedback(HttpServletRequest request,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                           @ApiParam(name = "limit", value = "每页数量", required = false)
                           @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        PageInfo<FeedbackDomain> feedback = feedBackService.getFeedback(new FeedbackDomain(), page, limit);
        request.setAttribute("feedback", feedback);
        aside(request);
        return "home/feedback";
    }

    /**
     * 添加留言
     * @param request
     * @param parent
     * @param author
     * @param email
     * @param url
     * @param content
     * @return
     */
    @PostMapping(value = "/feedback")
    @ResponseBody
    public APIResponse feedback(HttpServletRequest request,
                               @RequestParam(name = "parent", required = false) String parent,
                               @RequestParam(name = "author",required = false) String author,
                               @RequestParam(name = "email",required = false) String email,
                               @RequestParam(name = "url",required = false) String url,
                               @RequestParam(name = "content",required = true) String content
    ) {
        FeedbackDomain feedback = new FeedbackDomain();
        feedback.setAuthor(author);
        feedback.setContent(content);
        feedback.setMail(email);
        feedback.setUrl(url);
        feedback.setParent(parent);
        feedBackService.addFeedback(feedback);
        return APIResponse.success();
    }

    /**
     * 链接页
     * @return
     */
    @GetMapping("/link")
    public String link(HttpServletRequest request){
        aside(request);
        return "home/link";
    }

    /**
     * 素材页
     * @param request
     * @return
     */
    @GetMapping("/download")
    public String download(HttpServletRequest request){
        aside(request);
        return "home/download";
    }

    /**
     * 分类页（该分类下所有文章）
     * @param request
     * @return
     */
    @GetMapping("/category")
    public String category(HttpServletRequest request){
        aside(request);
        return "home/category";
    }

    private void cookie(String name, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(false);
        response.addCookie(cookie);
    }

}
