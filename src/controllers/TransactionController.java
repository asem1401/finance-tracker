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
    public Transaction addTransaction(int userID, int amount) {
        return transactionRepository.addTransaction(userID, amount);
    }

    @Override
    public boolean deleteTransaction(int id) {
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
}
