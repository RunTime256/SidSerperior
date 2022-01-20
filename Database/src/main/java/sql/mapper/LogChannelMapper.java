package sql.mapper;

import model.ServerChannel;

public interface LogChannelMapper
{
    ServerChannel getLogChannel(String logName);
}
