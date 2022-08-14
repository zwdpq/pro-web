package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {
    private UserBasicService userBasicService = null;
    private TopicService topicService = null;

    public String login(String loginId, String pwd, HttpSession session){
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null){
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        }else {
            return "login";
        }
    }

    /**
     * 根据好友id查询好友日志与其基本信息
     * @param id
     * @return
     */
    public String friend(Integer id, HttpSession session){
        //好友的信息
        UserBasic currentFriend = userBasicService.getUserBasicById(id);
        List<UserBasic> friendList = userBasicService.getFriendList(currentFriend);
        currentFriend.setFriendList(friendList);
        //好友的日志信息
        List<Topic> topicList = topicService.getTopicList(currentFriend);
        currentFriend.setTopicList(topicList);
        session.setAttribute("friend", currentFriend);

        return "index";
    }
}
