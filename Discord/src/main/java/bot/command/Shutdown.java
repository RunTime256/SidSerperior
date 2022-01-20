package bot.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandPermissionType;
import org.javacord.api.interaction.SlashCommandPermissionsUpdater;
import sql.Session;

public class Shutdown extends Command
{
    public Shutdown(Server server)
    {
        super(new SlashCommandBuilder()
                        .setName("shutdown")
                        .setDescription("Shuts down the bot")
                        .setDefaultPermission(false)
                        .createForServer(server).join(),
                Shutdown::execute);
        new SlashCommandPermissionsUpdater(server)
                .addPermission(server.getOwnerId(), SlashCommandPermissionType.USER, true)
                .update(slashCommand.getId());
    }

    private static void execute(DiscordApi discordApi, SlashCommandCreateEvent event, Session session) {
        SlashCommandInteraction interaction = event.getSlashCommandInteraction();
        interaction.createImmediateResponder()
                .setContent("Shutting down...")
                .respond();
        discordApi.disconnect();
        System.exit(0);
    }
}
