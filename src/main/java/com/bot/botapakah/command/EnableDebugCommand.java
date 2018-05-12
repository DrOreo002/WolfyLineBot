package com.bot.botapakah.command;

import com.bot.botapakah.command.util.CommandExecutor;
import com.bot.botapakah.utils.BotDebug;
import com.bot.botapakah.utils.Sender;
import com.linecorp.bot.client.LineMessagingClient;

public class EnableDebugCommand implements CommandExecutor {

    @Override
    public void onCommand(String replyToken, LineMessagingClient client) {
        if (BotDebug.getInstance().isEnabled()) {
            BotDebug.getInstance().setEnabled(false);
            Sender.sendMessage("Debug sekarang di disable", replyToken, client);
        } else {
            Sender.sendMessage("Debug sekarang di enable. Warning : Akan menyebabkan spam!", replyToken, client);
            BotDebug.getInstance().setEnabled(true);
        }
    }
}
