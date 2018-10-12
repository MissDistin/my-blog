/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/7/25 16:50
 **/
package com.wip.service.chat.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wip.dao.ChatDao;
import com.wip.model.*;
import com.wip.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;

    @Override
    public void addChat(ChatDomain chatDomain) {
        chatDao.addChat(chatDomain);
    }

    @Override
    public PageInfo<ChatDomain> getChat(ChatDomain chatDomain, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ChatDomain> contents = chatDao.getChat();
        PageInfo<ChatDomain> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    public ChatDomain getChatByChid(String chid) {
        return chatDao.getChatByChid(chid);
    }

    @Override
    public void updateChatByCid(ChatDomain temp) {
        chatDao.updateChatByCid(temp);
    }

    @Override
    @Cacheable(value = "chatCache", key = "'chatLimit_'+ #p0")
    public List<ChatDomain> chatLimit() {
        List<ChatDomain> chatList = chatDao.chatLimit();
        for (int i = 0; i < chatList.size(); i++) {
            chatList.get(i).setBgClass("shuobg" + (i+1));
        }
        return chatList;
    }
}
