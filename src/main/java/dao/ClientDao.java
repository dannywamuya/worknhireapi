package dao;

import models.Client;

import java.util.List;

public interface ClientDao {
    //CREATE
    //Add
    void add(Client client);

    //READ
    //Get all
    List<Client> getAll();

    //Find by Id
    Client findById(int id);

    //UPDATE
    void update(int id, String name, String email, String phone);

    //DELETE
    //Delete by Id
    void deleteById(int id);

    //Delete all
    void deleteAll();
}
