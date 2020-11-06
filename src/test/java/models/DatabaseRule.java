package models;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/worknhire_test", "danny", "hu8jmn3");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients * ;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            String deleteWorkersQuery = "DELETE FROM workers * ;";
            con.createQuery(deleteWorkersQuery).executeUpdate();
            String deleteJobDetailsQuery = "DELETE FROM job_details * ;";
            con.createQuery(deleteJobDetailsQuery).executeUpdate();
        }
    }

}
