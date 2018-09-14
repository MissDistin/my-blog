package com.wip.service.feedback;

import com.github.pagehelper.PageInfo;
import com.wip.model.FeedbackDomain;

/**
 * @Auther: Tinko
 * @Date: 2018/9/14 14:09
 * @Description: 留言相关接口
 */
public interface FeedbackService {

    /**
     * 添加留言
     * @param feedback
     */
    void addFeedback(FeedbackDomain feedback);

//
//    /**
//     * 通过文章ID获取评论
//     * @param cid
//     * @return
//     */
//    List<CommentDomain> getCommentsByCId(String cid);

    /**
     * 根据条件获取评论列表
     * @param feedback   查询条件
     * @param pageNum       分页参数 第几页
     * @param pageSize      分页参数 每页条数
     * @return
     */
    PageInfo<FeedbackDomain> getFeedback(FeedbackDomain feedback, int pageNum, int pageSize);

//    /**
//     * 通过ID获取评论
//     * @param coid
//     * @return
//     */
//    CommentDomain getCommentById(Integer coid);
//
//    /**
//     * 更新评论状态
//     * @param coid
//     * @param status
//     */
//    void updateCommentStatus(Integer coid, String status);
}
