package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

import java.net.URI;
import java.net.URISyntaxException;

public class DB {

    private static URI dbUri;
    public static Sql2o sql2o;
    private static String dblink = "postgres://localhost:5432/worknhire";

    static Logger logger = LoggerFactory.getLogger(DB.class);
    static {
        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI(dblink);
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? "danny" : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? "password" : dbUri.getUserInfo().split(":")[1];
//            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
            String connectionString = "jdbc:postgresql://ec2-52-207-124-89.compute-1.amazonaws.com:5432/dd0qh15n92qcge";
            sql2o = new Sql2o(connectionString, "sfegzszfdlckdb","9b5d7addca0974b60e7c42589bf53a7ba1b923d595cae79d084e2f2967abaa3c");
        } catch (URISyntaxException e ) {
            logger.error("Unable to connect to database.");
        }
    }

}
