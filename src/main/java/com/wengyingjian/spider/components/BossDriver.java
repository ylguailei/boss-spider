package com.wengyingjian.spider.components;

import com.wengyingjian.spider.config.SeleniumConfigHolder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class BossDriver {

    static {
        System.setProperty(SeleniumConfigHolder.DRIVER_PATH_KEY, SeleniumConfigHolder.getDriverPath());
    }

    public static void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(options);
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

    public static List<WebElement> findElementsByXpath(String xpath) {
        try {
            return BossContext.getWebDriver().findElements(By.xpath(xpath));
        } catch (Exception ex) {
            return null;
        }
    }


    public static void clickByXpath(String xpath) {
        clickByXpath(xpath, false, element -> true);
    }

    public static void blockElement(WebElement element) {
        ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("arguments[0].style.display = 'block';", element);
    }

    public static void hiddenElement(WebElement element) {
        ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("arguments[0].style.display = 'none';", element);
    }

    public static void checkElement(WebElement element) {
        ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("arguments[0].checked = true;", element);
    }

    public static void movetoElement(WebElement element) {
        Actions actions = new Actions(BossContext.getWebDriver());
        actions.moveToElement(element).perform();
//        WebElement searchItemElement = findElement(By.xpath("//*[@class='form-item search-item search-item-city search-item-active']"));
//        if (searchItemElement != null) {
//            setAttribuate(searchItemElement, "css", "form-item search-item search-item-city search-item-active");
//        }
    }

    public static void moveAndClicktoElement(WebElement element) {
        Actions actions = new Actions(BossContext.getWebDriver());
        actions.moveToElement(element).click(element).perform();
    }

    public static void setAttribuate(WebElement eleemnt, String attrName, String attrValue) {
        ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", eleemnt, attrName, attrValue);
    }

    public static void scrollByXpath(String xpath) {
        WebElement element = findElement(By.xpath(xpath));
        //按钮不存在
        if (element == null) {
            return;
        }
        ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollByHeight(Integer height) {
        ((JavascriptExecutor) BossContext.getWebDriver()).executeScript("window.scrollTo(0, " + height + ")");
    }

    public static void scrollAndClickByXpath(String xpath, Function function) {
        clickByXpath(xpath, true, function);
    }

    private static void clickByXpath(String xpath, boolean scroll, Function function) {
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
            findElement(By.xpath("//*[@id=\"container\"]/div[2]/div[2]/div/span/a")).click();
            //findElement(By.xpath("//*[@id='infolist']/div/div[3]/div[2]")).click();
        } catch (Exception e) {

        }
        //点击
        if (function.click(element)) {
            element.click();
        }
    }

    public interface Function {
        boolean click(WebElement element);
    }
}
