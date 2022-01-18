package bot;

import sql.Session;
import sql.SessionFactory;
import sql.repository.BotRepository;

import java.io.IOException;

public class BotRunner {
    public static void main(String[] args)
    {
        String name = System.getenv("BOT_NAME");

        try
        {
            SessionFactory.init("mybatis-config.xml");
        }
        catch (IOException ignored)
        {
            return;
        }

        String token;
        try (Session session = SessionFactory.getSession())
        {
            token = BotRepository.getToken(name, session);
        }
    }
}
