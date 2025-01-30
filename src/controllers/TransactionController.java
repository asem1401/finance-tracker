package controllers;

import data.IDB;
import models.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionController implements ITransactionController{
    private final IDB db;

    public TransactionController(IDB db) {
        this.db = db;
    }

    private Transaction getTransactionFromResultSet(ResultSet rs) throws SQLException {
        return new Transaction(rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("amount"),
                rs.getString("created_at"),
                rs.getString("updated_at"));
    }

    @Override
    public Transaction addTransaction(int userID, int amount) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "INSERT INTO Transactions (user_id, amount) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userID);
            ps.setInt(2, amount);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return getTransactionFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteTransaction(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "DELETE FROM Transactions WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM Transactions";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) {
                transactions.add(getTransactionFromResultSet(rs));
            }
            return transactions;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactionsFromThisMonth() {
        Connection conn = null;
        try {
            conn = db.getConnection();

            ZonedDateTime firstDayOfMonth = ZonedDateTime.now(ZoneId.of("UTC")).withDayOfMonth(1).toLocalDate().atStartOfDay(ZoneId.of("UTC"));
            ZonedDateTime lastDayOfMonth = ZonedDateTime.now(ZoneId.of("UTC")).withDayOfMonth(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);
            String sql = "SELECT * FROM transactions WHERE created_at BETWEEN ? AND ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.from(firstDayOfMonth.toInstant()));
            ps.setTimestamp(2, Timestamp.from(lastDayOfMonth.toInstant()));

            ResultSet rs = ps.executeQuery();
            List<Transaction> transactions = new ArrayList<>();
            while (rs.next()) {
                transactions.add(getTransactionFromResultSet(rs));
            }
            return transactions;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
