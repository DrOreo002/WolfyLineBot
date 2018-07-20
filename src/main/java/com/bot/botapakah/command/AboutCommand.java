package com.bot.botapakah.command;

import com.bot.botapakah.command.util.CommandExecutor;
import com.bot.botapakah.utils.Sender;
import com.linecorp.bot.client.LineMessagingClient;

public class AboutCommand implements CommandExecutor {

    @Override
    public void onCommand(String replyToken, LineMessagingClient client) {
        Sender.sendMessage("Bot dibuat oleh : @DrOreo002 a.k.a Anom Suryadinata \nVersion : 1.0 - BETA",replyToken, client);
    }
}
