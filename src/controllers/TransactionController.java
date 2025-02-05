package controllers;

import models.Transaction;
import repository.ITransactionRepository;
import java.util.List;

public class TransactionController implements ITransactionController{
    private final ITransactionRepository transactionRepository;

    public TransactionController(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction addTransaction(int userID, int amount, String category) throws IllegalArgumentException {
        if (userID <= 0) {
            throw new IllegalArgumentException("Invalid user ID. Must be a positive number.");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Transaction amount cannot be zero.");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null.");
        }
        return transactionRepository.addTransaction(userID, amount, category);
    }

    @Override
    public boolean deleteTransaction(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid transaction ID. Must be a positive number.");
        }
        Transaction transaction = transactionRepository.getTransactionById(id);
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction with ID " + id + " does not exist.");
        }
        return transactionRepository.deleteTransaction(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    @Override
    public List<Transaction> getTransactionsFromThisMonth() {
        return transactionRepository.getTransactionsFromThisMonth();
    }

    @Override
    public List<Transaction> getTransactionsByUserID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("Invalid user ID. Must be a positive number.");
        }

        return transactionRepository.getTransactionsByUserID(userID);
    }

    @Override
    public List<Transaction> getTransactionsFromThisMonthByUserID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("Invalid user ID. Must be a positive number.");
        }

        return transactionRepository.getTransactionsFromThisMonthByUserID(userID);
    }
}
