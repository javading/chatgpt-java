package com.ding.openai.entity.billing;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class Usage {
    @JsonProperty("prompt_tokens")
    private long promptTokens;
    @JsonProperty("completion_tokens")
    private long completionTokens;
    @JsonProperty("total_tokens")
    private long totalTokens;
}
