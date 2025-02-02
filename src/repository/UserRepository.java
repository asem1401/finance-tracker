package repository;

import data.IDB;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("currency"),
                rs.getString("created_at"),
                rs.getString("updated_at"));
    }

    @Override
    public User addUser(String name, String surname, String currency) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "INSERT INTO Users (name, surname, currency) VALUES (?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setString(2, surname);
            st.setString(3, currency);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    return getUserFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM Users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM Users";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
            return users;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String getBalance(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT COALESCE(SUM(t.amount), 0) AS balance, u.currency " +
                    "FROM transactions t " +
                    "JOIN users u ON t.user_id = u.id " +
                    "WHERE t.user_id = ? " +
                    "GROUP BY u.currency";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String balance = Integer.toString(rs.getInt("balance"));
                String currency = rs.getString("currency");

                switch (currency) {
                    case "EUR":
                        return "€" + balance;
                    case "USD":
                        return "$" + balance;
                    case "KZT":
                        return balance + " ₸";
                    case "RUB":
                        return balance + " ₽";
                    default:
                        return balance;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
