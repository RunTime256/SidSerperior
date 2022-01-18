package bot.api;

import bot.command.Command;
import bot.command.CommandExecution;
import bot.command.Shutdown;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.util.*;

public class BotApi {
    private final String token;
    private DiscordApi discordApi;
    private Map<Long, CommandExecution> commandExecution;
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

    public void configureCommands()
    {
        initCommandList();
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
            execution.execute(discordApi, event);
        });
    }

    private void initCommandList()
    {
       commands = Arrays.asList(new Shutdown(discordApi));
    }
}
