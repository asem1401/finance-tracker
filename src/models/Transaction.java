package models;

public class Transaction {
    private int id;
    private int user_id;
    private int amount;
    private String category;
    private String created_at;
    private String updated_at;

    public Transaction() {}

    public Transaction(int id, int user_id, int amount, String category, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.amount = amount;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
