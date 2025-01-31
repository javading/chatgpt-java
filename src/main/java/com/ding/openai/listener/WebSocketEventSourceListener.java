package com.ding.openai.listener;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ding.openai.entity.chat.ChatCompletionResponse;
import java.util.Objects;
import javax.websocket.Session;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

@Slf4j
public class WebSocketEventSourceListener extends EventSourceListener {

    private Session session;

    public WebSocketEventSourceListener(Session session) {
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onOpen(EventSource eventSource, Response response) {
        log.info("OpenAI建立sse连接...");
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("OpenAI返回数据：{}", data);

        if (data.equals("[DONE]")) {
            log.info("OpenAI返回数据结束了");
            session.getBasicRemote().sendText("[DONE]");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class); // 读取Json
        String delta = mapper.writeValueAsString(completionResponse.getChoices().get(0).getDelta());

        session.getBasicRemote().sendText(delta);
    }


    @Override
    public void onClosed(EventSource eventSource) {
        log.info("OpenAI关闭websocket连接...");
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }

        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("OpenAI websocket连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("OpenAI websocket连接异常data：{}，异常：{}", response, t);
        }

        eventSource.cancel();
    }

}
