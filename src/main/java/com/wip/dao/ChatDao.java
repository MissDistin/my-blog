/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:50
 **/
package com.wip.dao;

import com.wip.model.ChatDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说说相关Dao接口
 */
@Mapper
public interface ChatDao {

    /**
     * 添加说说
     * @param chatDomain
     */
    void addChat(ChatDomain chatDomain);

    List<ChatDomain> getChat();

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
