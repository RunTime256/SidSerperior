package sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class SessionFactory
{
    private static SqlSessionFactory factory;

    private SessionFactory()
    {
    }

    public static void init(String config) throws IOException
    {
        Reader reader = Resources.getResourceAsReader(config);
        factory = new SqlSessionFactoryBuilder().build(reader, getProperties());
    }

    public static Session getSession()
    {
        return new Session(factory.openSession());
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("url", System.getenv("DATABASE_URL"));
        properties.setProperty("username", System.getenv("DATABASE_USERNAME"));
        properties.setProperty("password", System.getenv("DATABASE_PASSWORD"));
        return properties;
    }
}
