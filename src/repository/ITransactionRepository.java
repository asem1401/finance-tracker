package repository;

import models.Transaction;

import java.util.List;

public interface ITransactionRepository {
    Transaction addTransaction(int userID, int amount, String category);
    boolean deleteTransaction(int id);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsFromThisMonth();
    Transaction getTransactionById(int id);
    List<Transaction> getTransactionsByUserID(int userID);
    List<Transaction> getTransactionsFromThisMonthByUserID(int userID);
    List<Transaction> getTransactionsByCategory(String category);
    List<Transaction> getTransactionsByCategoryAndUserID(String category, int userID);
}
