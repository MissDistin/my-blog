/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/31 15:42
 **/
package com.wip.dao;

import com.wip.model.FeedbackDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 留言相关Dao接口
 */
@Mapper
public interface FeedbackDao {

    /**
     * 添加留言
     * @param feedback
     */
    void addFeedback(FeedbackDomain feedback);

    List<FeedbackDomain> getFeedback(FeedbackDomain feedback);
//
//    /**
//     * 根据文章ID获取评论
//     * @param cid
//     * @return
//     */
//    List<CommentDomain> getCommentByCId(@Param("cid") String cid);
//
//
//    /**
//     * 删除评论
//     * @param coid
//     */
//    void deleteComment(@Param("coid") String coid);
//
//    /**
//     * 获取评论总数
//     * @return
//     */
//    Long getCommentCount();
//
//    /**
//     * 根据条件获取评论列表
//     * @param commentCond
//     * @return
//     */
//    List<CommentDomain> getCommentsByCond(CommentCond commentCond);
//
//    /**
//     * 通过ID获取评论
//     * @param coid
//     * @return
//     */
//    CommentDomain getCommentById(@Param("coid") Integer coid);
//
//    /**
//     * 更新评论状态
//     * @param coid
//     * @param status
//     */
//    void updateCommentStatus(@Param("coid") Integer coid, @Param("status") String status);
}
