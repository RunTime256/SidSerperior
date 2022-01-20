package bot.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandPermissionType;
import org.javacord.api.interaction.SlashCommandPermissionsUpdater;
import sql.Session;
import sql.repository.ServerRepository;

import java.util.Collections;
import java.util.List;

public class ClearSlashCommands extends Command
{
    public ClearSlashCommands(Server server)
    {
        super(new SlashCommandBuilder()
                        .setName("clear")
                        .setDescription("Removes all slash commands")
                        .setDefaultPermission(false)
                        .createForServer(server).join(),
                ClearSlashCommands::execute);
        new SlashCommandPermissionsUpdater(server)
                .addPermission(server.getOwnerId(), SlashCommandPermissionType.USER, true)
                .update(slashCommand.getId());
    }

    private static void execute(DiscordApi discordApi, SlashCommandCreateEvent event, Session session)
    {
        SlashCommandInteraction interaction = event.getSlashCommandInteraction();
        interaction.createImmediateResponder().setContent("Clearing slash commands...").respond();

        discordApi.bulkOverwriteGlobalSlashCommands(Collections.emptyList());

        List<Long> serverIds = ServerRepository.getAllServerIds(session);
        for (Long serverId: serverIds)
        {
            discordApi.getServerById(serverId).ifPresent(server ->
                    discordApi.bulkOverwriteServerSlashCommands(server, Collections.emptyList()));
        }
    }
}
