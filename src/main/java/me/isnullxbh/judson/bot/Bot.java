package me.isnullxbh.judson.bot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram bot for tracking packages.
 * @since 0.1.0
 */
public class Bot extends TelegramLongPollingCommandBot
{
    public Bot(String token)
    {
        super(token);
    }

    public Bot(DefaultBotOptions options, String token)
    {
        super(options, token);
    }

    @Override
    public String getBotUsername()
    {
        return "Package Tracker";
    }

    @Override
    public void processNonCommandUpdate(Update update)
    {
        if (update.hasMessage() && update.getMessage().hasText())
        {
            System.out.printf("Non command update: %s", update.getMessage().getText());
        }
    }
}
