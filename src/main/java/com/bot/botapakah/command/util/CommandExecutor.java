package com.bot.botapakah.command.util;

import com.linecorp.bot.client.LineMessagingClient;

public interface CommandExecutor {

    void onCommand(String replyToken, LineMessagingClient client);
}
