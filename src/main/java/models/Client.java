package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String email;
    private String phone;

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void add() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (name, email, phone) VALUES (:name, :email, :phone);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("phone", this.phone)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Client> getAll() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM clients;")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Client.class);
        }
    }

    public Client findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM clients WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
        }
    }

    public static void update(int id, String name, String email, String phone) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE clients SET (name, email, phone) = (:name, :email, :phone) WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("phone", phone)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public void deleteById() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE from clients WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void deleteAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE from clients";
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

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return getId() == client.getId() &&
                getPhone() == client.getPhone() &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getEmail(), client.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPhone());
    }
}
