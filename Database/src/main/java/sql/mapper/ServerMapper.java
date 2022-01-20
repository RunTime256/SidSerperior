package sql.mapper;

import java.util.List;

public interface ServerMapper
{
    Long getServerId(String serverName);

    List<Long> getAllServerIds();
}
