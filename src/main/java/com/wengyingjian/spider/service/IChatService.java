package com.wengyingjian.spider.service;

import com.wengyingjian.spider.service.impl.BossChatService;

public interface IChatService {
    IChatService send(int index, String... msgs);
}
