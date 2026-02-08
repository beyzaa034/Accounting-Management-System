/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountingManagementSystem;

import java.sql.Timestamp;

/**
 *
 * @author beyza
 */
public class TransactionClass {

    private Integer transactionId;  // Integer, because it can be null (not yet saved).
    private Timestamp date;         // Transaction time
    private int userId;             // Whose transaction it is
    private String type;
    private String category;
    private double amount;
    private String currency;
    private String description;

    // Constructor (For data entry from the UI)
    public TransactionClass(String type, String category, double amount, String currency, String description) {
        this.transactionId = null; // Not yet saved to the database.
        this.date = null;          // Will be set during saving.
        this.userId = 0;           // Will be set at the moment of saving.
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    // Constructor (To fetch from the database)
    public TransactionClass(Integer transactionId, Timestamp date, int userId, String type,
            String category, double amount, String currency, String description) {
        this.transactionId = transactionId;
        this.date = date;
        this.userId = userId;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    // Getter ve setter methods
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
