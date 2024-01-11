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
        //获取城市筛选框
        List<WebElement> filterElement = BossDriver.findElementsByXpath("//*[@class='form-item search-item search-item-city']");
        for (int i = 0; i < filterElement.size(); i++) {
            BossDriver.movetoElement(filterElement.get(i));
            BossDriver.setAttribuate(filterElement.get(i), "css", "form-item search-item search-item-city search-item-active");
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {

            }
            if (i == 0) { //省
                WebElement cityElement = filterElement.get(i).findElement(By.xpath("//*[@class='city-letter-content letter-hot']/div[2]")); //上海 //BossDriver.findElementByXpath("//*[@class='city-letter-content letter-hot']/div[2]");
                if (cityElement != null) {
                    cityElement.click();
                }
            }
            if (i == 1) {
                WebElement cityElement = filterElement.get(i).findElement(By.xpath("//*[@class='tab-content tab-area tab-show']/div/div[1]")); //区域 不限
                if (cityElement != null) {
                    cityElement.click();
                }
            }
        }
        //获取其他筛选框，例如性别、年龄、学历、工作年限、期望薪资、活跃时间
        List<WebElement> otherFilterElement = BossDriver.findElementsByXpath("//*[@class='form-item search-item']");
        for (Integer i = 0; i < otherFilterElement.size(); i++) {
            WebElement filterItemElement = otherFilterElement.get(i);
            BossDriver.movetoElement(filterItemElement);
            //性别
            if (i == 0) {
                WebElement filterInnerItemElement = filterItemElement.findElement(By.xpath("//*[@class='search-list']/div[3]"));//女性
                filterInnerItemElement.click();
            }
            //年龄
            if (i == 1) {
                WebElement startAgeElement = filterItemElement.findElement(By.xpath("//*[@class='search-list']/div[7]/div[2]/input"));//21-30岁
                startAgeElement.sendKeys("21");
                WebElement endAgeElement = filterItemElement.findElement(By.xpath("//*[@class='search-list']/div[7]/div[2]/input[2]"));
                endAgeElement.sendKeys("30");
                WebElement buttonElement = filterItemElement.findElement(By.xpath("//*[@class='search-list']/div[7]/div[2]/button"));
                buttonElement.click();
            }
            //学历
            if (i == 2) {
                List<WebElement> eduFilterElement = filterItemElement.findElements(By.xpath("./div/div[2]/label")); //label //中专/技校
                eduFilterElement.stream().forEach(item -> {
                    WebElement checkboxElement = item.findElement(By.xpath("./span/input"));
                    if (checkboxElement != null) {
                        if (checkboxElement.getAttribute("value").equals("1") || checkboxElement.getAttribute("value").equals("2") || checkboxElement.getAttribute("value").equals("3")) {
                            checkboxElement.click();
                        }
                    }
                });
                WebElement eduButtonElement = filterItemElement.findElement(By.xpath("./div/button"));
                eduButtonElement.click();
//                filterInnerItemElement.click();
            }
            //工作经验
            if (i == 3) {
                List<WebElement> workexpFilterElement = filterItemElement.findElements(By.xpath("./div/div[2]/label")); //label //工作经验
                workexpFilterElement.stream().forEach(item -> {
                    WebElement checkboxElement = item.findElement(By.xpath("./span/input"));
                    if (checkboxElement != null) {
                        if (checkboxElement.getAttribute("value").equals("1") || checkboxElement.getAttribute("value").equals("2") || checkboxElement.getAttribute("value").equals("6")) {
                            checkboxElement.click();
                        }
                    }
                });
                WebElement workexpButtonElement = filterItemElement.findElement(By.xpath("./div/button"));
                workexpButtonElement.click();
            }
            //期望薪资
            if (i == 4) {
                WebElement filterInnerItemElement = filterItemElement.findElement(By.xpath("./div/div[6]"));//3000-5000元
                filterInnerItemElement.click();
            }
            //活跃时间
            if (i == 5) {
                WebElement filterInnerItemElement = filterItemElement.findElement(By.xpath("./div/div[2]"));//一天以内
                filterInnerItemElement.click();
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {

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
