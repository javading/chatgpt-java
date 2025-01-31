package com.ding.openai.util;

import com.ding.openai.entity.chat.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatContextHolder {

    private static Map<String, List<Message>> context = new HashMap<>();


    /**
     * 获取对话历史
     *
     * @param id
     * @return
     */
    public static List<Message> get(String id) {
        List<Message> messages = context.get(id);
        if (messages == null) {
            messages = new ArrayList<>();
            context.put(id, messages);
        }

        return messages;
    }


    /**
     * 添加对话
     *
     * @param id
     * @return
     */
    public static void add(String id, String msg) {

        Message message = Message.builder().content(msg).build();
        add(id, message);
    }


    /**
     * 添加对话
     *
     * @param id
     * @return
     */
    public static void add(String id, Message message) {
        List<Message> messages = context.get(id);
        if (messages == null) {
            messages = new ArrayList<>();
            context.put(id, messages);
        }
        messages.add(message);
    }
}
