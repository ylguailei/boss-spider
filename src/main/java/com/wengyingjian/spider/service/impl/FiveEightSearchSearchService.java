package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.ISearchService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FiveEightSearchSearchService implements ISearchService {
    @Override
    public void doSearch(String postKeyWord) {
        try {
            //提示点击"知道了"
            //*[@id="wrap"]/div[1]/div[2]/div[1]/div[2]/a
//        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[1]/div[2]/a");
//            BossDriver.findElementByXpath("//*[@class='cate-search']").click();
//            Thread.sleep(100);
//            List<WebElement> postList =  BossDriver.findElementsByXpath("//*[@class='job-type-item']");
//            for (int i = 0; i < postList.size(); i++) {
//                WebElement element = postList.get(i).findElement(By.xpath("//*[@class='job-type-item-text']"));
//                if(element != null){
//                    element.click();
//                }
//            }
            BossDriver.findElementByXpath("//*[@class='ant-input job-search-input']").sendKeys(postKeyWord);
            BossDriver.findElementByXpath("//*[@class='ant-btn ant-btn-primary']").click();
            //== 筛选
//            doSearchByConditions();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doFilter() {
        List<WebElement> filterElement = BossDriver.findElementsByXpath("//*[@class='form-item search-item search-item-city']");
        if (filterElement != null) {
            BossDriver.movetoElement(filterElement.get(0));
            BossDriver.setAttribuate(filterElement.get(0), "css", "form-item search-item search-item-city search-item-active");
            WebElement cityElement = BossDriver.findElementByXpath("//*[@class='city-letter-content letter-hot']/div[2]");
            if (cityElement != null) {
                cityElement.click();
                WebElement areaElement = BossDriver.findElementByXpath("//*[@class='form-item search-item search-item-city']");
                if (areaElement != null) {
                    BossDriver.movetoElement(areaElement);
                    BossDriver.setAttribuate(areaElement, "css", "form-item search-item search-item-city search-item-active");
                    WebElement cityItemElement = BossDriver.findElementByXpath("//*[@class='tab-content tab-area tab-show']/div/div[1]");
                    if (cityItemElement != null) {
                        cityElement.click();
                    }
                }
            }
            List<WebElement> otherFilterElement = BossDriver.findElementsByXpath("//*[@class='form-item search-item']");
            for (Integer i = 0; i < otherFilterElement.size(); i++) {
                WebElement filterItemElement = otherFilterElement.get(i);

                WebElement filterInnerItemElement = filterItemElement.findElement(By.xpath("//*[@class='search-list']/div[3]"));//女性
                filterInnerItemElement.click();
            }
        }
    }

    @Override
    public Integer searchResultCount() {
        Integer pageCount = 0;
        try {
            List<WebElement> list = BossDriver.findElementsByXpath("//*[@id='infolist']/div");
            if (list != null && list.size() > 0) {
                pageCount = list.size();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageCount;
    }

    @Override
    public void pageSearch() {
        try {
            BossDriver.findElementByXpath("//*[@class='ant-btn ant-btn-link']").click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
