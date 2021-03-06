/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:50
 **/
package com.wip.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wip.constant.ErrorConstant;
import com.wip.constant.Types;
import com.wip.constant.WebConst;
import com.wip.dao.CommentDao;
import com.wip.dao.ContentDao;
import com.wip.dao.RelationShipDao;
import com.wip.dto.cond.ContentCond;
import com.wip.exception.BusinessException;
import com.wip.model.CommentDomain;
import com.wip.model.ContentDomain;
import com.wip.model.MetaDomain;
import com.wip.model.RelationShipDomain;
import com.wip.service.article.ContentService;
import com.wip.service.meta.MetaService;
import com.wip.utils.HtmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private MetaService metaService;

    @Autowired
    private RelationShipDao relationShipDao;

    @Autowired
    private CommentDao commentDao;

    @Transactional
    @Override
    @CacheEvict(value = {"articleCache", "articleCaches"}, allEntries = true, beforeInvocation = true)
    public void addArticle(ContentDomain contentDomain) {
        if (null == contentDomain)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);

        if (StringUtils.isBlank(contentDomain.getTitle()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);

        if (contentDomain.getTitle().length() > WebConst.MAX_TITLE_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);

        if (StringUtils.isBlank(contentDomain.getContent()))
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);

        if (contentDomain.getContent().length() > WebConst.MAX_CONTENT_COUNT)
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);

        // 取到标签和分类
        String tags = contentDomain.getTags();
        String categories = contentDomain.getCategories();

        // 添加文章
        contentDao.addArticle(contentDomain);

        // 添加分类和标签
        String cid = contentDomain.getCid();
        metaService.addMetas(cid, tags, Types.TAG.getType());
        metaService.addMetas(cid, categories, Types.CATEGORY.getType());


    }

    @Override
    @Cacheable(value = "articleCache", key = "'articleById_' + #p0")
    public ContentDomain getArticleById(String cid) {
        if (null == cid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return contentDao.getArticleById(cid);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache", "articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateArticleById(ContentDomain contentDomain) {
        // 标签和分类
        String tags = contentDomain.getTags();
        String categories = contentDomain.getCategories();

        // 更新文章
        contentDao.updateArticleById(contentDomain);
        String cid = contentDomain.getCid();
        relationShipDao.deleteRelationShipByCid(cid);
        metaService.addMetas(cid,tags,Types.TAG.getType());
        metaService.addMetas(cid,categories,Types.CATEGORY.getType());

    }

    @Override
    @Cacheable(value = "articleCaches", key = "'articlesByCond_' + #p1 + 'type_' + #p0.type")
    public PageInfo<ContentDomain> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize) {
        if (null == contentCond)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum,pageSize);
        List<ContentDomain> contents = contentDao.getArticleByCond(contentCond);
        for (ContentDomain content : contents){
            content.setContent(HtmlUtil.getTextFromHtml(content.getContent()));
        }
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    @Transactional
    @CacheEvict(value = {"articleCache","articleCaches"},allEntries = true, beforeInvocation = true)
    public void deleteArticleById(String cid) {
        if (null == cid)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        // 删除文章
        contentDao.deleteArticleById(cid);

        // 同时要删除该 文章下的所有评论
        List<CommentDomain> comments = commentDao.getCommentByCId(cid);
        if (null != comments && comments.size() > 0) {
            comments.forEach(comment -> {
                commentDao.deleteComment(comment.getCoid());
            });
        }

        // 删除标签和分类关联
        List<RelationShipDomain> relationShips = relationShipDao.getRelationShipByCid(cid);
        if (null != relationShips && relationShips.size() > 0) {
            relationShipDao.deleteRelationShipByCid(cid);
        }


    }

    @Override
    @CacheEvict(value = {"articleCache","articleCaches"}, allEntries = true, beforeInvocation = true)
    public void updateContentByCid(ContentDomain content) {
        if (null != content && null != content.getCid()) {
            contentDao.updateArticleById(content);
        }
    }

    @Override
    @Cacheable(value = "articleCache", key = "'articleByCategory_' + #p0")
    public List<ContentDomain> getArticleByCategory(String category) {
        if (null == category)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return contentDao.getArticleByCategory(category);
    }

    @Override
    @Cacheable(value = "articleCache", key = "'articleByTags_'+ #p0")
    public PageInfo<ContentDomain> getArticleByTags(MetaDomain tags, int pageNum, int pageSize) {
        if (null == tags)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        PageHelper.startPage(pageNum,pageSize);
        List<RelationShipDomain> relationShip = relationShipDao.getRelationShipByMid(tags.getMid());
        List<ContentDomain> contentDomainList = null;
        if (null != relationShip && relationShip.size() > 0) {
            contentDomainList = contentDao.getArticleByTags(relationShip);
        }
        for (ContentDomain content : contentDomainList){
            content.setContent(HtmlUtil.getTextFromHtml(content.getContent()));
        }
        PageInfo<ContentDomain> pageInfo = new PageInfo<>(contentDomainList);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "articleCache", key = "'findArticlesByLimit_'+ #p0")
    public List<ContentDomain> findArticlesByLimit() {
        List<ContentDomain> contents = contentDao.findArticlesByLimit();
        for (ContentDomain content : contents){
            content.setContent(HtmlUtil.getTextFromHtml(content.getContent()));
        }
        return contents;
    }

    @Override
    public int findArticleNum() {
        return contentDao.findArticleNum();
    }
}
