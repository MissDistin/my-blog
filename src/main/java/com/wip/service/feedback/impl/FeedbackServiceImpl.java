package com.wip.service.feedback.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wip.dao.FeedbackDao;
import com.wip.model.FeedbackDomain;
import com.wip.service.feedback.FeedbackService;
import com.wip.utils.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;
//
//    @Autowired
//    private ContentService contentService;
//
//    @Autowired
//    private ChatService chatService;
//
//    private static final Map<String,String> STATUS_MAP = new ConcurrentHashMap<>();
//
//    /**
//     * 评论状态：正常
//     */
//    private static final String STATUS_NORMAL = "approved";
//    /**
//     * 评论状态：不不显示
//     */
//    private static final String STATUS_BLANK = "not_audit";
//
//    static {
//        STATUS_MAP.put("approved",STATUS_NORMAL);
//        STATUS_MAP.put("not_audit",STATUS_BLANK);
//    }


    @Override
    @Transactional
    @CacheEvict(value = "feedbackCache", allEntries = true)
    public void addFeedback(FeedbackDomain feedback) {
        feedback.setCreated(DateKit.getCurrentUnixTime());
        feedbackDao.addFeedback(feedback);
    }

//    @Override
//    @Cacheable(value = "commentCache", key = "'commentsByCId_' + #p0")
//    public List<CommentDomain> getCommentsByCId(String cid) {
//        if (null == cid)
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        return commentDao.getCommentByCId(cid);
//    }

    @Override
    @Cacheable(value = "feedbackCache")
    public PageInfo<FeedbackDomain> getFeedback(FeedbackDomain feedback, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<FeedbackDomain> feedbacks = feedbackDao.getFeedback(feedback);
        PageInfo<FeedbackDomain> pageInfo = new PageInfo<>(feedbacks);
        return pageInfo;
    }

//    @Override
//    @Cacheable(value = "commentCache",key = "'commentById_' + #p0")
//    public CommentDomain getCommentById(Integer coid) {
//        if (null == coid)
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        return commentDao.getCommentById(coid);
//    }
//
//    @Override
//    @CacheEvict(value = "commentCache", allEntries = true)
//    public void updateCommentStatus(Integer coid, String status) {
//        if (null == coid)
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        commentDao.updateCommentStatus(coid, status);
//    }
}
