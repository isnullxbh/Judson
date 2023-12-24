package me.isnullxbh.judson.bot;

import me.isnullxbh.judson.bot.commands.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram bot for tracking packages.
 * @since 0.1.0
 */
public class Bot extends TelegramLongPollingCommandBot
{
    private final Logger logger = LoggerFactory.getLogger(Bot.class);

    public Bot(String token)
    {
        super(token);
        registerCommands();
    }

    public Bot(DefaultBotOptions options, String token)
    {
        super(options, token);
        registerCommands();
    }

    private void registerCommands()
    {
        register(new Where());
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
            logger.warn("Non command update: {}", update.getMessage().getText());
        }
    }
}
