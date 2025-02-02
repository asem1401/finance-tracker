package models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String currency;
    private String created_at; //timestamp with timezone
    private String updated_at; // timestamp with timezone

    public User() {}

    public User(String name, String surname, String currency) {
        this.name = name;
        this.surname = surname;
        this.currency = currency;
    }

    public User(int id, String name, String surname, String currency, String created_at, String updated_at) {
        this(name, surname, currency);
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency.toUpperCase();
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", currency='" + currency + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
