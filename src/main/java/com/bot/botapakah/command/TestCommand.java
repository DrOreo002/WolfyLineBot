package com.bot.botapakah.command;

import com.bot.botapakah.command.util.CommandExecutor;
import com.bot.botapakah.utils.Sender;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;

import java.util.concurrent.ExecutionException;

public class TestCommand implements CommandExecutor {

    @Override
    public void onCommand(String replyToken, LineMessagingClient client) {
        Sender.sendMessage("Hello World", replyToken, client);
    }
}
