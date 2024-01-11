package com.wengyingjian.spider;

import com.wengyingjian.spider.components.BossDriver;
import com.wengyingjian.spider.service.IHelloService;
import com.wengyingjian.spider.service.ILoginService;
import com.wengyingjian.spider.service.ISearchService;
import com.wengyingjian.spider.service.impl.FiveEightHelloService;
import com.wengyingjian.spider.service.impl.FiveEightLoginService;
import com.wengyingjian.spider.service.impl.FiveEightSearchSearchService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author hzwengyingjian
 */
public class Starter {


    public static void main(String[] args) throws InterruptedException {
        //初始化浏览器
        BossDriver.init();
        ILoginService loginService = new FiveEightLoginService();
        loginService.login();

        //筛选查询
//        new SearchService().doSearch();
        ISearchService searchService = new FiveEightSearchSearchService();
        BossDriver.clickByXpath("//*[@id=\"wrap\"]/div[1]/div[2]/div[1]/div[2]/a");
        WebElement searchElement = BossDriver.findElementByXpath("//*[@class='cate-search']");
        if (searchElement != null) {
            Thread.sleep(100);
            try {
                //随便在搜索区域点击一下
                BossDriver.findElement(By.xpath("//*[@class='search-form-area']")).click();
            } catch (Exception ex) {

            }
            try {
                //将网页上的透明浮层隐藏掉
                WebElement zcmMaskElement = BossDriver.findElement(By.xpath("//*[@class='zcm-mask']"));
                BossDriver.hiddenElement(zcmMaskElement);
            } catch (Exception ex) {

            }
            try {
                //将网页上的透明浮层隐藏掉
                WebElement zcmTipsElement = BossDriver.findElement(By.xpath("//*[@class='zcm-tips zcm-tips-show']"));
                BossDriver.hiddenElement(zcmTipsElement);
            } catch (Exception ex) {

            }
            try {
                //bannerWraper
                //将网页上的透明浮层隐藏掉
                WebElement bannerElement = BossDriver.findElement(By.xpath("//*[@id='bannerContainer']"));
                BossDriver.hiddenElement(bannerElement);
            } catch (Exception ex) {

            }
            //做筛选
            searchService.doFilter();
            //做查询
            searchService.doSearch("");
            Thread.sleep(1000L);
            //获取查询结果数量
            Integer totalSize = searchService.searchResultCount();
            //打招呼
            IHelloService helloService = new FiveEightHelloService();
            for (int j = 1; j <= totalSize; j++) {
                System.out.println("current=" + j);
                helloService.sayHello(j);
            }
        }
        BossDriver.close();
        //登录
//        new LoginService().login();
    }


}
