package com.ding.openai.web.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat3QueryParam {

    private String message;

    private String scene;

    private String uid;

    public boolean valid() {
        return StringUtils.hasLength(this.message) && StringUtils.hasLength(this.scene) && StringUtils.hasLength(
                this.uid);
    }
}
