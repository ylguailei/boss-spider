package com.wengyingjian.spider.components;

public class XpathConsts {
    public static String getRecommendXpathWith58(int index) {
        return "//*[@id='root']/div/div/div[2]/div[5]/div/div[2]/ul/li[" + index + "]/div/div/div/button[2]";
//        return "//*[id='infolist']/li[" + index + "]/div/div/div/button[2]";
    }


    public static String getSayHelloXpathWith58(int index) {
//        return "//*[@id=\"recommend-list\"]/div[1]/ul/li[" + index + "]/div[1]/button[2]";
//        return "//*[@class='btn chat-btn']";
        return "//*[@id='infolist']/div[" + index + "]/div[3]/div[2]";
    }

    private String getSayHelloXpathWithBoss(int index) {
        return "//*[@id=\"recommend-list\"]/div[1]/ul/li[" + index + "]/div[1]/button[2]";
    }
}
