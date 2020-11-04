package dao;

import models.JobDetail;
import models.Worker;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oJobDetailDao implements JobDetailDao {

    private final Sql2o sql2o;
    public Sql2oJobDetailDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(JobDetail jobDetail) {
        String sql = "INSERT INTO job_details (job_category, job_detail, client_id) VALUES (:job_category, :job_detail, :client_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(jobDetail)
                    .executeUpdate()
                    .getKey();
            jobDetail.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<JobDetail> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM job_details")
                    .executeAndFetch(JobDetail.class);
        }
    }

    @Override
    public JobDetail findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM job_details WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(JobDetail.class);
        }
    }

    @Override
    public List<JobDetail> findAllByClient(int client_id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM job_details WHERE client_id = :client_id")
                    .addParameter("client_id", client_id)
                    .executeAndFetch(JobDetail.class);
        }
    }

    @Override
    public void update(int id, String job_category, String job_detail, int client_id) {
        String sql = "UPDATE job_details SET (job_category, job_detail) = (:job_category, :job_detail) WHERE id = :id AND client_id = :client_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("job_category", job_category)
                    .addParameter("job_detail", job_detail)
                    .addParameter("client_id", client_id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from job_details WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllByClientId(int client_id) {
        String sql = "DELETE from job_details WHERE client_id = :client_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("client_id", client_id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from job_details";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
