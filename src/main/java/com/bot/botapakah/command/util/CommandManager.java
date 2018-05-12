package com.bot.botapakah.command.util;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private static final Map<String, CommandExecutor> commands = new HashMap<>();

    public static void addCommand(String cmd, CommandExecutor clazz) {
        synchronized (commands) {
            commands.computeIfAbsent(cmd.toLowerCase(), k -> clazz);
        }
    }

    public static Map<String, CommandExecutor> getCommands() {
        return commands;
    }
}
