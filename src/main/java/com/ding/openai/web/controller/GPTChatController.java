package com.ding.openai.web.controller;

import com.ding.openai.web.entity.param.Chat3QueryParam;
import com.ding.openai.web.service.IGPTChatService;
import com.ding.openai.common.Constants;
import com.ding.openai.web.entity.Result;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(Constants.SERVICE_PREFIX + "/openai/gpt/chat")
public class GPTChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private IGPTChatService gptChatService;

    public GPTChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * gpt3.5 基于websocket的流式对话接口
     *
     * @param param
     * @return
     */
    @GetMapping("/ws/chat3")
    @CrossOrigin
    public void chat3(@RequestBody Chat3QueryParam param) {

        if(!param.valid()) {
           throw new IllegalArgumentException();
        }

        // 将 ChatGPT API 的响应发送给订阅了 "/topic/messages" 主题的客户端
        simpMessagingTemplate.convertAndSendToUser(param.getUid(), "/topic/messages", param.getMessage());
    }


    @GetMapping("/chat4")
    @CrossOrigin
    public Result<String> chat4(@RequestParam("message") String msg, @RequestHeader Map<String, String> headers) {


        return null;
    }

}
