package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.IHelloService;

public class BossHelloService implements IHelloService {

    /**
     * 打招呼
     *
     * @param index 序号，第几个人
     */
    public void sayHello(String xpath) {
        try {
            BossDriver.scrollAndClickByXpath(xpath, element -> element.getText().contains("打招呼"));
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //获取打招呼按钮的xpath
    private String getSayHelloXpath(int index) {
        return "//*[@id=\"recommend-list\"]/div[1]/ul/li[" + index + "]/div[1]/button[2]";
    }


}
