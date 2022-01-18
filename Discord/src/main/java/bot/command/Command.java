package bot.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.interaction.SlashCommand;

public abstract class Command
{
    protected final SlashCommand slashCommand;
    protected final CommandExecution execution;

    protected Command(SlashCommand slashCommand, CommandExecution execution)
    {
        this.slashCommand = slashCommand;
        this.execution = execution;
    }

    public Long getCommandId() {
        return slashCommand.getId();
    }

    public CommandExecution getExecution() {
        return execution;
    }
}
