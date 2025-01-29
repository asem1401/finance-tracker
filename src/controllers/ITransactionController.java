package controllers;

import models.Transaction;

import java.util.List;

public interface ITransactionController {
    Transaction addTransaction(int userID, int amount);
    boolean deleteTransaction(int id);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsFromThisMonth()
}
