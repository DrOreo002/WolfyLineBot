package com.bot.botapakah.command;

import com.bot.botapakah.command.util.CommandExecutor;
import com.bot.botapakah.utils.Sender;
import com.linecorp.bot.client.LineMessagingClient;

public class HelpCommand implements CommandExecutor {

    @Override
    public void onCommand(String replyToken, LineMessagingClient client) {
        Sender.sendMessage("Sedang di buat", replyToken, client);
    }
}
