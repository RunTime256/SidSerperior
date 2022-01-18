package sql.repository;

import sql.Session;
import sql.mapper.BotMapper;

public class BotRepository {
    private BotRepository() {
    }

    public static String getToken(String botName, Session session)
    {
        return getMapper(session).getToken(botName);
    }

    private static BotMapper getMapper(Session session)
    {
        return session.getMapper(BotMapper.class);
    }
}
