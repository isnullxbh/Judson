package me.isnullxbh.judson.bot.commands;

import me.isnullxbh.judson.TrackingInfo;
import me.isnullxbh.judson.couriers.cainiao.CainiaoTrackingServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Bot command used for getting information about package by the specified tracking number.
 * @since 0.1.0
 * @todo  Add support for difference couriers.
 */
public class Where extends BotCommand
{
    final CainiaoTrackingServiceClient trackingClient = new CainiaoTrackingServiceClient();
    final Logger logger = LoggerFactory.getLogger(Where.class);

    /**
     * Creates an instance of the command with the default parameters.
     */
    public Where()
    {
        super("where", "Get current package status");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings)
    {
        if (strings.length != 1)
        {
            logger.error("Invalid command arguments: {}", (Object) strings);
            return;
        }

        logger.info("Tracking package with number {}...", strings[0]);

        final var reply = new SendMessage();
        reply.setChatId(chat.getId());
        reply.setText(trackingClient.track(strings[0]).map(TrackingInfo::toString).orElse("Cannot track the package"));

        try
        {
            absSender.execute(reply);
        }
        catch (TelegramApiException e)
        {
            logger.info("An error occurred while tracking package with number {}: {}", strings[0], e.getMessage());
        }

        logger.info("Tracking package with number {}... Done", strings[0]);
    }
}
