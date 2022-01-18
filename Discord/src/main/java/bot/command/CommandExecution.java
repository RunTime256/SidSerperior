package bot.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;

public interface CommandExecution
{
    public void execute(DiscordApi discordApi, SlashCommandCreateEvent event);
}
