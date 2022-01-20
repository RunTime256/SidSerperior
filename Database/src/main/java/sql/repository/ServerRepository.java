package sql.repository;

import sql.Session;
import sql.mapper.ServerMapper;

import java.util.List;

public class ServerRepository
{
    private ServerRepository()
    {
    }

    public static Long getServerId(String serverName, Session session)
    {
        return getMapper(session).getServerId(serverName);
    }

    public static List<Long> getAllServerIds(Session session)
    {
        return getMapper(session).getAllServerIds();
    }

    private static ServerMapper getMapper(Session session)
    {
        return session.getMapper(ServerMapper.class);
    }
}
