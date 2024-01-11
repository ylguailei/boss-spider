package com.wengyingjian.spider.config;

import com.wengyingjian.spider.components.BossContext;
import org.openqa.selenium.Cookie;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CookieHolder {

    public static String BOSS_COOKIE_KEY = "boss.cookie";

    public static List<Cookie> getCookieData(String key) {
        String cookieStrs = BossContext.getProperty(key);
        List<String> cookies = Arrays.asList(cookieStrs.split("; "));
        return cookies.stream().map(cookieStr -> {
            String[] cookieSplit = cookieStr.split("=>");
            return new Cookie(cookieSplit[0], cookieSplit.length > 1 ? cookieSplit[1] : "", ".58.com", "/", Date.from(LocalDate.now().plusYears(1L).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }).collect(Collectors.toList());
    }
}
