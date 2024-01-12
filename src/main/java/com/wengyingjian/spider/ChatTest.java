package com.wengyingjian.spider;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.impl.BossChatService;
import com.wengyingjian.spider.service.impl.BossLoginService;

public class ChatTest {

    public static void main(String[] args) throws InterruptedException {
        //初始化浏览器
        BossDriver.init();

        //登录
        new BossLoginService().login("");

        new BossChatService().switchTo().send(1, "你好", "简历已收到");

        BossDriver.close();
    }
}
