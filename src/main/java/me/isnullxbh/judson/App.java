package me.isnullxbh.judson;

import me.isnullxbh.judson.bot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.err.println("Token is not specified");
            return;
        }

        try
        {
            var api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new Bot(args[0]));
        }
        catch (TelegramApiException e)
        {
            System.err.printf("An error occurred while registering the bot: %s", e.getMessage());
        }
    }
}
