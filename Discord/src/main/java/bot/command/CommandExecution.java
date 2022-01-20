package bot.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import sql.Session;

public interface CommandExecution
{
    void execute(DiscordApi discordApi, SlashCommandCreateEvent event, Session session);
}
