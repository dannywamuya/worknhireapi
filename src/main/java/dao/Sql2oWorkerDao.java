package dao;

import models.Worker;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oWorkerDao implements WorkerDao {

    private final Sql2o sql2o;
    public Sql2oWorkerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Worker worker) {
        String sql = "INSERT INTO workers (name, skill, experience, email, phone) VALUES (:name,  :skill, :experience, :email, :phone)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(worker)
                    .executeUpdate()
                    .getKey();
            worker.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Worker> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM workers")
                    .executeAndFetch(Worker.class);
        }
    }

    @Override
    public Worker findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM workers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Worker.class);
        }
    }

    @Override
    public void update(int id, String name, String skill, int experience, String email, String phone) {
        String sql = "UPDATE workers SET (name, skill, experience, email, phone) = (:name, :skill, :experience, :email, :phone) WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("phone", phone)
                    .addParameter("skill", skill)
                    .addParameter("experience", experience)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from workers WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from workers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
