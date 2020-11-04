package dao;

import models.JobDetail;

import java.util.List;

public interface JobDetailDao {
    //CREATE
    //Add
    void add(JobDetail jobDetail);

    //READ
    //Get all
    List<JobDetail> getAll();

    //Find by Id
    JobDetail findById(int id);
    List<JobDetail> findAllByClient(int client_id);

    //UPDATE
    void update(int id, String job_category, String job_detail, int client_id);

    //DELETE
    //Delete by Id
    void deleteById(int id);
    void deleteAllByClientId(int client_id);

    //Delete all
    void deleteAll();
}
