package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.FiveEightDriver;
import com.wengyingjian.spider.service.IHelloService;

public class FiveEightHelloService implements IHelloService {
    @Override
    public void sayHello(int index) {
        try {
            FiveEightDriver.scrollAndClickByXpath(getSayHelloXpath(index), element -> element.getText().contains("在线沟通"));
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getSayHelloXpath(int index) {
//        return "//*[@id=\"recommend-list\"]/div[1]/ul/li[" + index + "]/div[1]/button[2]";
//        return "//*[@class='btn chat-btn']";
        return "//*[@id='infolist']/div[" + index + "]/div[3]/div[2]";
    }
}
