/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:48
 **/
package com.wip.service.chat;

import com.github.pagehelper.PageInfo;
import com.wip.model.ChatDomain;

import java.util.List;

/**
 * 说说相关Service接口
 */
public interface ChatService {

    /***
     * 添加说说
     * @param chatDomain
     */
    void addChat(ChatDomain chatDomain);

    PageInfo<ChatDomain> getChat(ChatDomain chatDomain, int page, int limit);

    ChatDomain getChatByChid(String chid);

    void updateChatByCid(ChatDomain temp);

    /**
     * 查询最近的3条说说
     * @return
     */
    List<ChatDomain> chatLimit();

    /**
     * 右侧查询说说数量
     * @return
     */
    int findChaNum();
}
