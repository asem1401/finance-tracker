package controllers;

import models.Transaction;

import java.util.List;

public interface ITransactionController {
    Transaction addTransaction(int userID, int amount, String category);
    boolean deleteTransaction(int id);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUserID(int userID);
    List<Transaction> getTransactionsFromThisMonth();
    List<Transaction> getTransactionsFromThisMonthByUserID(int userID);
    List<Transaction> getTransactionsByCategory(String category);
    List<Transaction> getTransactionsByCategoryAndUserID(String category, int userID);
}
