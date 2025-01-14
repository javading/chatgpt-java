package com.ding.openai.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * 控制台测试
 * Console Stream Test Listener
 *
 * @author admin
 */
@Slf4j
public class ConsoleStreamListener extends AbstractStreamListener {


    @Override
    public void onMsg(String message) {
        System.out.print(message);
    }

    @Override
    public void onError(Throwable throwable, String response) {

    }

}
