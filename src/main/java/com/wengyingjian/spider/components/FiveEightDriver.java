package com.wengyingjian.spider.components;

import com.wengyingjian.spider.config.SeleniumConfigHolder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FiveEightDriver {
    static {
        System.setProperty(SeleniumConfigHolder.DRIVER_PATH_KEY, SeleniumConfigHolder.getDriverPath());
    }

    public static void init() {
        WebDriver webDriver = new ChromeDriver();
        BossContext.setWebDriver(webDriver);
    }

    public static void close() {
        BossContext.getWebDriver().close();
    }

    public static WebElement findElement(By by) {
        try {
            return BossContext.getWebDriver().findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static WebElement findElementByXpath(String xpath) {
        try {
            return BossContext.getWebDriver().findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return null;
        }
    }


    public static void clickByXpath(String xpath) {
        clickByXpath(xpath, false, element -> true);
    }


    public static void scrollAndClickByXpath(String xpath, BossDriver.Function function) {
        clickByXpath(xpath, true, function);
    }

    private static void clickByXpath(String xpath, boolean scroll, BossDriver.Function function) {
        WebElement element = findElement(By.xpath(xpath));
        //按钮不存在
        if (element == null) {
            return;
        }
        //滚动
        if (scroll) {
            ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
        }
        //有则关闭提示
        try {
            ////*[@id="container"]/div[2]/div[2]/div/span/a
            Thread.sleep(200);
            if (function.click(element)) {
                element.click();
                Thread.sleep(1000);
                try {
                    findElement(By.xpath("//*[@class='chat-select-ok']")).click();
                    Thread.sleep(1000);
                }catch (Exception ex){

                }

                //footer-button svelte-jhvdkk
                try {
                    findElement(By.xpath("//*[@class='footer-button svelte-jhvdkk']")).click();
                    Thread.sleep(1000);
                }catch (Exception ex){

                }
                //batch-chat-dialog__close
                try {
                    findElement(By.xpath("//*[@class='batch-chat-dialog__close']")).click();
                    Thread.sleep(200);
                }catch (Exception e){

                }
                try{
                    findElement(By.xpath("//*[@class='dialog-close']")).click();
                    Thread.sleep(200);
                }catch (Exception ex){

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //点击
//        if (function.click(element)) {
//            element.click();
//        }
    }

    public interface Function {
        boolean click(WebElement element);
    }
}
