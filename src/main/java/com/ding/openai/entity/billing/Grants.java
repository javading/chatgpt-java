package com.ding.openai.entity.billing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class Grants {
    private String object;
    @JsonProperty("data")
    private List<Datum> data;
}
