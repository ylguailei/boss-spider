package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.ISearchService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BossSearchService implements ISearchService {


    public void doSearch(String postKeyWord) {
        try {
            //提示点击"知道了"
            //*[@id="wrap"]/div[1]/div[2]/div[1]/div[2]/a
//        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[1]/div[2]/a");
            BossDriver.findElement(By.className("btn chat-btn"));
            //== 筛选
            doSearchByConditions();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void doFilter() {

    }

    @Override
    public Integer searchResultCount() {
        return null;
    }

    @Override
    public void pageSearch() {

    }


    private void doSearchByConditions() throws InterruptedException {
        //点击筛选
//        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[2]/div/span");
        BossDriver.clickByXpath("//*[@class='btn chat-btn']");
        //点击3-5年
        setExp3_5();
        Thread.sleep(1000);

        //继续筛选
        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[2]/div/span");
        //点击本科
        setSt_benke();
        Thread.sleep(1000);
    }


    private void setExp1_3() {
        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[2]/div/div/div/dl[2]/dd/a[4]");
    }

    private void setExp3_5() {
        //*[@id="wrap"]/div[1]/div[2]/div[2]/div/div/div/dl[2]/dd/a[5]
        //*[@id="wrap"]/div[1]/div[2]/div[2]/div/div/div/dl[2]/dd/a[5]
        //*[@id="wrap"]/div[1]/div[2]/div[2]/div/div/div/dl[2]/dd/a[5]
        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[2]/div/div/div/dl[2]/dd/a[5]");
    }

    private void setSt_benke() {
        //*[@id="wrap"]/div[1]/div[2]/div[2]/div/div/div/dl[3]/dd/a[6]
        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[2]/div/div/div/dl[3]/dd/a[6]");
    }

    private void setSt_shuoshi() {
        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[2]/div/div/div/dl[3]/dd/a[7]");
    }

}
