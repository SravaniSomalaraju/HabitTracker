package model;

import java.time.LocalDate;

public class Habit {
    private int id;
    private String name;
    private LocalDate createdOn;

    public Habit() {}

    public Habit(int id, String name, LocalDate createdOn) {
        this.id = id;
        this.name = name;
        this.createdOn = createdOn;
    }

    public Habit(String name, LocalDate createdOn) {
        this.name = name;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}

