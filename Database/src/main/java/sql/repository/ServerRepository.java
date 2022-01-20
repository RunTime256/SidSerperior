package sql.repository;

import sql.Session;
import sql.mapper.ServerMapper;

public class ServerRepository
{
    private ServerRepository()
    {
    }

    public static Long getServerId(String serverName, Session session)
    {
        return getMapper(session).getServerId(serverName);
    }

    private static ServerMapper getMapper(Session session)
    {
        return session.getMapper(ServerMapper.class);
    }
}
