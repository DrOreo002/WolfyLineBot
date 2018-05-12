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

    public void sendLog(String message, String replyToken, LineMessagingClient lineMessagingClient) {
        if (enabled) {
            sendMessage(message, replyToken, lineMessagingClient);
        }
    }

    private void sendMessage(String message, String replyToken, LineMessagingClient lineMessagingClient){
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(message);
        try {
            lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, jawabanDalamBentukTextMessage))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ada error saat ingin membalas chat");
        }
    }

    public void setEnabled(boolean value) {
        this.enabled = value;
    }
}
