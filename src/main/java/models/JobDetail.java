package models;

import java.util.Objects;

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
