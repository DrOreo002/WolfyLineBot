package com.bot.botapakah.command;

import com.bot.botapakah.command.util.CommandExecutor;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;

import java.util.concurrent.ExecutionException;

public class TestCommand implements CommandExecutor {

    @Override
    public void onCommand(String replyToken, LineMessagingClient client) {
        sendMessage("Hello World", replyToken, client);
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
}
