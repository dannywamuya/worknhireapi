package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

import static models.DB.sql2o;

public class JobDetail {
    private int id;
    private String job_category;
    private String job_detail;
    private int client_id;

    public JobDetail(String job_category, String job_detail, int client_id){
        this.job_category = job_category;
        this.job_detail = job_detail;
        this.client_id = client_id;
    }

    public void add() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO job_details (job_category, job_detail, client_id) VALUES (:job_category, :job_detail, :client_id);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("job_category", this.job_category)
                    .addParameter("job_detail", this.job_detail)
                    .addParameter("client_id", this.client_id)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<JobDetail> getAll() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM job_details;")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(JobDetail.class);
        }
    }

    public JobDetail findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM job_details WHERE id = :id;")
                    .addParameter("id", id)
                    .executeAndFetchFirst(JobDetail.class);
        }
    }

    public List<JobDetail> findAllByClient(int client_id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM job_details WHERE client_id = :client_id;")
                    .addParameter("client_id", client_id)
                    .executeAndFetch(JobDetail.class);
        }
    }

    public void update(int id, String job_category, String job_detail, int client_id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE job_details SET (job_category, job_detail) = (:job_category, :job_detail) WHERE id = :id AND client_id = :client_id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("job_category", job_category)
                    .addParameter("job_detail", job_detail)
                    .addParameter("client_id", client_id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public void deleteById() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE from job_details WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteAllByClientId(int client_id) {
        String sql = "DELETE from job_details WHERE client_id = :client_id;";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("client_id", client_id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE from job_details;";
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getJob_category() { return job_category; }
    public void setJob_category(String job_category) { this.job_category = job_category; }

    public String getJob_detail() { return job_detail; }
    public void setJob_detail(String job_detail) { this.job_detail = job_detail; }

    public int getClient_id() { return client_id; }
    public void setClient_id(int client_id) { this.client_id = client_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDetail jobDetail = (JobDetail) o;
        return getId() == jobDetail.getId() &&
                getClient_id() == jobDetail.getClient_id() &&
                Objects.equals(getJob_category(), jobDetail.getJob_category()) &&
                Objects.equals(getJob_detail(), jobDetail.getJob_detail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJob_category(), getJob_detail(), getClient_id());
    }
}
