package com.bot.botapakah.utils;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;

import java.util.concurrent.ExecutionException;

public class BotDebug {

    private static BotDebug instance;
    private boolean enabled;

    private BotDebug() {
        // Constructor
        this.enabled = false;
    }

    public static BotDebug getInstance() {
        if (instance == null) {
            instance = new BotDebug();
            return instance;
        }
        return instance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void sendLog(String message, String replyToken, LineMessagingClient lineMessagingClient) {
        if (enabled) {
            Sender.sendMessage(message, replyToken, lineMessagingClient);
        }
    }

    public void setEnabled(boolean value) {
        this.enabled = value;
    }
}
