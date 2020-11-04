package dao;

import models.Worker;

import java.util.List;

public interface WorkerDao {
    //CREATE
    //Add
    void add(Worker worker);

    //READ
    //Get all
    List<Worker> getAll();

    //Find by Id
    Worker findById(int id);

    //UPDATE
    void update(int id, String name, String skill, int experience, String email, String phone);

    //DELETE
    //Delete by Id
    void deleteById(int id);

    //Delete all
    void deleteAll();
}
