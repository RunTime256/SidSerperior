package bot.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;

public class Shutdown extends Command
{
    public Shutdown(DiscordApi discordApi)
    {
        super(new SlashCommandBuilder()
                        .setName("shutdown")
                        .setDescription("Shuts down the bot")
                        .createGlobal(discordApi).join(),
                Shutdown::execute);
    }

    private static void execute(DiscordApi discordApi, SlashCommandCreateEvent event) {
        SlashCommandInteraction interaction = event.getSlashCommandInteraction();
        interaction.createImmediateResponder()
                .setContent("Shutting down...")
                .respond();
        discordApi.disconnect();
        System.exit(0);
    }
}
