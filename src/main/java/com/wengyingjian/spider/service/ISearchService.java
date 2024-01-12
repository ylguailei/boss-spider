package com.wengyingjian.spider.service;

import org.openqa.selenium.WebElement;

public interface ISearchService {
    void doSearch(String postKeyWord);

    void doFilter();

    Integer searchResultCount(String xpath);

    void pageSearch();
}
