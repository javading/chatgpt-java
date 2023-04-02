package com.ding.openai;

import com.ding.openai.plugins.openai.chatgpt.ChatGPTStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    @Value("${chatgpt.apiKey}")
    private String apiKey;
    @Value("${chatgpt.apiHost}")
    private String apiHost;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public ChatGPTStream openAiStreamClient() {
        return ChatGPTStream.builder().apiHost(apiHost).apiKey(apiKey).build();
    }

}
