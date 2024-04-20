package com.example.lior_zalta_final_project.Model;


public class Transaction {
    public enum TransactionType{
        EXPENSE,
        INCOME
    }
    private String label;
    private String category;
    private Double amount;
    private String date;
    private TransactionType transactionType;

    public Transaction() {
    }

    public String getLabel() {
        return label;
    }

    public Transaction setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Transaction setCategory(String category) {
        this.category = category;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Transaction setDate(String date) {
        this.date = date;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Transaction setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }
}
