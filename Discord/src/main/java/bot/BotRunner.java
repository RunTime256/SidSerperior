package bot;

import bot.api.BotApi;
import bot.exception.ConfigurationException;
import sql.Session;
import sql.SessionFactory;
import sql.repository.BotRepository;

import java.io.IOException;

public class BotRunner {
    public static void main(String[] args)
    {
        String name = System.getenv("BOT_NAME");
        if (name == null)
            throw new ConfigurationException("Bot name not provided in Environment variables");

        try
        {
            SessionFactory.init("mybatis-config.xml");
        }
        catch (IOException ignored)
        {
            throw new ConfigurationException("Could not find MyBatis configuration file");
        }

        String token;
        BotApi api;
        try (Session session = SessionFactory.getSession())
        {
            token = BotRepository.getToken(name, session);
            if (token == null)
                throw new ConfigurationException("Could not obtain token from database");
            api = new BotApi(token);
            api.join();
            api.configureCommands(session);
        }
    }
}
