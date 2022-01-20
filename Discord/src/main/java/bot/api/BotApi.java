package bot.api;

import bot.command.ClearSlashCommands;
import bot.command.Command;
import bot.command.CommandExecution;
import bot.command.Shutdown;
import bot.exception.ConfigurationException;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.interaction.SlashCommandInteraction;
import sql.Session;
import sql.SessionFactory;
import sql.repository.ServerRepository;

import java.util.*;

public class BotApi {
    private final String token;
    private final Map<Long, CommandExecution> commandExecution;
    private DiscordApi discordApi;
    private List<Command> commands;

    public BotApi(String token)
    {
        this.token = token;
        commands = new ArrayList<>();
        commandExecution = new HashMap<>();
    }

    public void join()
    {
        discordApi = new DiscordApiBuilder().setToken(token).login().join();
    }

    public void configureCommands(Session session)
    {
        initCommandList(session);
        for (Command command: commands)
        {
            commandExecution.put(command.getCommandId(), command.getExecution());
        }
        discordApi.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();
            CommandExecution execution = commandExecution.get(interaction.getCommandId());
            if (execution == null) {
                interaction.createImmediateResponder()
                        .setContent("This command no longer exists.")
                        .respond();
                return;
            }
            execution.execute(discordApi, event, SessionFactory.getSession());
        });
    }

    private void initCommandList(Session session)
    {
        Server devServer = getDevServerId(session);
        commands = Arrays.asList(
                new Shutdown(devServer),
                new ClearSlashCommands(devServer)
        );
    }

    private Server getDevServerId(Session session)
    {
        Long serverId = ServerRepository.getServerId("developer", session);
        if (serverId == null)
            throw new ConfigurationException("Could not obtain development server ID from database");

        return discordApi.getServerById(serverId).orElseThrow(() ->
                new ConfigurationException("Could not obtain development server from API"));
    }
}
