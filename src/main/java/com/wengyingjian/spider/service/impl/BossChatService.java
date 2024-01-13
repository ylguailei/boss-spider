package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.IChatService;
import org.openqa.selenium.WebElement;

public class BossChatService implements IChatService {


    /**
     * 切换到沟通面板
     */
    public BossChatService switchTo() throws InterruptedException {
        BossDriver.clickByXpath("//*[@id=\"main\"]/div[1]/div/dl[4]/dt/a");
        Thread.sleep(1000);
        return this;
    }

    /**
     * 指定联系人，选中
     *
     * @param index 序号，第几个人
     */
    public BossChatService target(int index) throws InterruptedException {
        BossDriver.scrollAndClickByXpath(getTargetXpath(index), element -> true);
        Thread.sleep(1000);
        return this;
    }

    private String getTargetXpath(int index) {
        return "//*[@id=\"container\"]/div[1]/div[2]/div[3]/div[1]/ul[2]/li[" + index + "]";
    }


    /**
     * 输入聊天内容
     *
     * @param msg 内容
     */
    private BossChatService write(String msg) {
        WebElement webElement = BossDriver.findElementByXpath("//*[@id=\"container\"]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div[2]");
        webElement.sendKeys(msg);
        return this;
    }


    /**
     * 发送
     */
    private BossChatService send() {
        BossDriver.clickByXpath("//*[@id=\"container\"]/div[1]/div[2]/div[4]/div[2]/div[2]/div[2]/div[3]/button");
        return this;
    }

    public BossChatService send(String msg) {
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