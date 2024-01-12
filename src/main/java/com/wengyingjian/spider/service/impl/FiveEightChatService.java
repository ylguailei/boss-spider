package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.IChatService;
import org.openqa.selenium.WebElement;

public class FiveEightChatService implements IChatService {
    /**
     * 切换到沟通面板
     */
    public FiveEightChatService switchTo() throws InterruptedException {
        BossDriver.clickByXpath("//*[@id=\"main\"]/div[1]/div/dl[4]/dt/a");
        Thread.sleep(1000);
        return this;
    }

    /**
     * 指定联系人，选中
     *
     * @param index 序号，第几个人
     */
    public FiveEightChatService target(int index) throws InterruptedException {
        BossDriver.scrollAndClickByXpath(getTargetXpath(index), element -> true);
        Thread.sleep(1000);
        return this;
    }

    private String getTargetXpath(int index) {
//        return "//*[@id=\"container\"]/div[1]/div[2]/div[3]/div[1]/ul[2]/li[" + index + "]";
        return "//*[@class='todo-list']/div[" + index + "]";
    }


    /**
     * 输入聊天内容
     *
     * @param msg 内容
     */
    private FiveEightChatService write(String msg) {
//        WebElement webElement = BossDriver.findElementByXpath("//*[@id=\"container\"]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div[2]");
        WebElement webElement = BossDriver.findElementByXpath("//*[@class='mmc-chat-body']/div[2]/div/div[3]/div");
        webElement.sendKeys(msg);
        return this;
    }


    /**
     * 发送
     */
    private FiveEightChatService send() {
//        BossDriver.clickByXpath("//*[@id=\"container\"]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div[3]/button");
        BossDriver.clickByXpath("//*[@class='mmc-chat-body']/div[2]/div/div[4]/button");
        return this;
    }

    public FiveEightChatService send(String msg) {
        return write(msg).send();
    }


    /**
     * 发送消息
     *
     * @param index 序号，第几个人
     * @param msgs  内容
     */
    public IChatService send(int index, String... msgs) {
        try {
            target(index);
            for (String msg : msgs) {
                send(msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this;
    }
}
