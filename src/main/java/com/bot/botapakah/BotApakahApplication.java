package com.bot.botapakah;

import com.bot.botapakah.command.EnableDebugCommand;
import com.bot.botapakah.command.HelpCommand;
import com.bot.botapakah.command.util.CommandExecutor;
import com.bot.botapakah.command.util.CommandManager;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@LineMessageHandler
public class BotApakahApplication extends SpringBootServletInitializer {

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BotApakahApplication.class);
    }

    public static void main(String[] args) {
        // Register utils
        SpringApplication.run(BotApakahApplication.class, args);
        // Register commands
        CommandManager.addCommand("help", new HelpCommand());
        CommandManager.addCommand("debug", new EnableDebugCommand());
        CommandManager.addCommand("about", new HelpCommand());
    }


    @EventMapping
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent){
        String pesan = messageEvent.getMessage().getText().toLowerCase();
        String[] pesanSplit = pesan.split(" ");
        // Commands
        if(pesanSplit[0].charAt(0) == '/'){
            // Handle command
            String command = pesanSplit[0].substring(1).toLowerCase();
            if (CommandManager.getCommands().containsKey(command)) {
                CommandExecutor exe = CommandManager.getCommands().get(command.toLowerCase());
                exe.onCommand(messageEvent.getReplyToken(), lineMessagingClient);
            } else {
                sendMessage("Command tidak diketahui!", messageEvent.getReplyToken(), lineMessagingClient);
            }
        } else {
            sendMessage("Gunakan / untuk berinteraksi dengan saya!", messageEvent.getReplyToken(), lineMessagingClient);
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
}
