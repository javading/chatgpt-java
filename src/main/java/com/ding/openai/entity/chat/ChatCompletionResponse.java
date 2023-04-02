package com.ding.openai.entity.chat;

import com.ding.openai.entity.billing.Usage;

import java.util.List;

import lombok.Data;

/**
 * chat答案类
 *
 * @author admin
 */
@Data
public class ChatCompletionResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<ChatChoice> choices;
    private Usage usage;
}
