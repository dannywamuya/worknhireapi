package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

import static models.DB.sql2o;

public class Worker {
    private int id;
    private String name;
    private String skill;
    private int experience;
    private String email;
    private String phone;

    public Worker(String name, String skill, int experience, String email, String phone) {
        this.name = name;
        this.skill = skill;
        this.experience = experience;
        this.email = email;
        this.phone = phone;
    }

    public void add() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO workers (name, skill, experience, email, phone) VALUES (:name,  :skill, :experience, :email, :phone)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("skill", this.skill)
                    .addParameter("experience", this.experience)
                    .addParameter("email", this.email)
                    .addParameter("phone", this.phone)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Worker> getAll() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM workers;")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Worker.class);
        }
    }

    public Worker findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM workers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Worker.class);
        }
    }

    public static void update(int id, String name, String skill, int experience, String email, String phone) {
        String sql = "UPDATE workers SET (name, skill, experience, email, phone) = (:name, :skill, :experience, :email, :phone) WHERE id = :id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("phone", phone)
                    .addParameter("skill", skill)
                    .addParameter("experience", experience)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public void deleteById() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE from workers WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteAll() {
        String sql = "DELETE from workers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSkill() { return skill; }
    public void setSkill(String skill) { this.skill = skill; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return getId() == worker.getId() &&
                getPhone() == worker.getPhone() &&
                getExperience() == worker.getExperience() &&
                Objects.equals(getName(), worker.getName()) &&
                Objects.equals(getEmail(), worker.getEmail()) &&
                Objects.equals(getSkill(), worker.getSkill());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getSkill(), getPhone(), getExperience());
    }
}
