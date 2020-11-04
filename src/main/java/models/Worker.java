package models;

import java.util.Objects;

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
