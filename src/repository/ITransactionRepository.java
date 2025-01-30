package repository;

import models.Transaction;

import java.util.List;

public interface ITransactionRepository {
    Transaction addTransaction(int userID, int amount);
    boolean deleteTransaction(int id);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsFromThisMonth();
}
