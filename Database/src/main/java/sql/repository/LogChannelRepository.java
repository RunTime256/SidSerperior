package sql.repository;

import model.ServerChannel;
import sql.Session;
import sql.mapper.LogChannelMapper;

public class LogChannelRepository
{
    private LogChannelRepository()
    {
    }

    public static ServerChannel getLogChannel(String logName, Session session)
    {
        return getMapper(session).getLogChannel(logName);
    }

    private static LogChannelMapper getMapper(Session session)
    {
        return session.getMapper(LogChannelMapper.class);
    }
}
