/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:48
 **/
package com.wip.service.article;

import com.github.pagehelper.PageInfo;
import com.wip.dto.cond.ContentCond;
import com.wip.model.ContentDomain;
import com.wip.model.MetaDomain;

import java.util.List;

/**
 * 文章相关Service接口
 */
public interface ContentService {

    /***
     * 添加文章
     * @param contentDomain
     */
    void addArticle(ContentDomain contentDomain);

    /**
     * 根据编号获取文章
     * @param cid
     * @return
     */
    ContentDomain getArticleById(String cid);

    /**
     * 更新文章
     * @param contentDomain
     */
    void updateArticleById(ContentDomain contentDomain);

    /**
     * 根据条件获取文章列表
     * @param contentCond
     * @param page
     * @param limit
     * @return
     */
    PageInfo<ContentDomain> getArticlesByCond(ContentCond contentCond, int page, int limit);

    /**
     * 删除文章
     * @param cid
     */
    void deleteArticleById(String cid);

    /**
     * 添加文章点击量
     * @param content
     */
    void updateContentByCid(ContentDomain content);

    /**
     * 通过分类获取文章
     * @param category
     * @return
     */
    List<ContentDomain> getArticleByCategory(String category);

    /**
     * 通过标签获取文章
     * @param tags
     * @return
     */
    PageInfo<ContentDomain> getArticleByTags(MetaDomain tags, int pageNum, int pageSize);

    /**
     * 右侧显示图文详情
     * @return
     */
    List<ContentDomain> findArticlesByLimit();

    /**
     * 查询所有的文章数量
     * @return
     */
    int findArticleNum();
}
