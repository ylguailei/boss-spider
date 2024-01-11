package com.wengyingjian.spider.service.impl;

import com.wengyingjian.spider.components.BossContext;
import com.wengyingjian.spider.config.BossUrlConstants;
import com.wengyingjian.spider.config.CookieHolder;
import com.wengyingjian.spider.service.ILoginService;
import org.openqa.selenium.WebDriver;

public class FiveEightLoginService implements ILoginService {
    @Override
    public void login() {
        try {
            WebDriver driver = BossContext.getWebDriver();
            driver.get(BossUrlConstants.URL_58_MAIN);
            Thread.sleep(1000);
            addCookie(driver);
            Thread.sleep(1000);
            driver.get(BossUrlConstants.URL_58_SEARCH);
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void addCookie(WebDriver driver) {
        CookieHolder.getCookieData(CookieHolder.BOSS_COOKIE_KEY).forEach(cookie -> {
            driver.manage().addCookie(cookie);
        });
    }
}
