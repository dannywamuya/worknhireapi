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
            String password = (dbUri.getUserInfo() == null) ? "hu8jmn3" : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
//            String connectionString = "jdbc:postgresql://ec2-52-207-124-89.compute-1.amazonaws.com/dcpboes6ga3uup";
//            sql2o = new Sql2o(connectionString, "syanrrqosgagxj","f36253c6d3903e1c03223f2cfa2e44aa341b423d503063785e1031bed98661c8");
        } catch (URISyntaxException e ) {
            logger.error("Unable to connect to database.");
        }
    }
}
