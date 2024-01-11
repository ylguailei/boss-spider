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
//            WebElement chooseJobElement = searchElement.findElement(By.xpath("//*[@class='choose-job job-check']"));
//            if (chooseJobElement != null) {
//                BossDriver.blockElement(chooseJobElement);
//            }
//            List<WebElement> postList = searchElement.findElements(By.xpath("//*[@class='choose-job job-check']/div[2]/div/div[2]/div[2]/div"));
//            for (int i = 0; i < postList.size(); i++) {
//                WebElement element = postList.get(i).findElement(By.xpath("//*[@class='job-type-item-text']"));
//                if (element != null) {
//                    element.click();
//                    BossDriver.hiddenElement(chooseJobElement);
//                    searchService.doSearch("保安");
//                    Thread.sleep(1000L);
//                    Integer totalSize = searchService.searchResultCount();
//                    //打招呼
//                    IHelloService helloService = new FiveEightHelloService();
//                    for (int j = 0; j < totalSize; j++) {
//                        System.out.println("current=" + j);
//                        helloService.sayHello(j);
//                    }
//                }
//            }
            try {
                BossDriver.findElement(By.xpath("//*[@class='search-form-area']")).click();
            } catch (Exception ex) {

            }
            try {
                WebElement zcmMaskElement = BossDriver.findElement(By.xpath("//*[@class='zcm-mask']"));
                BossDriver.hiddenElement(zcmMaskElement);
            } catch (Exception ex) {

            }
            searchService.doFilter();
            searchService.doSearch("保安");
            Thread.sleep(1000L);
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
