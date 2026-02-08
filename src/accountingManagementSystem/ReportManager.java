/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountingManagementSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author beyza
 */
public class ReportManager {

    public static class MonthlyReport {

        public double totalIncome;
        public double totalExpense;
        public double netBalance;

        public MonthlyReport(double income, double expense) {
            this.totalIncome = income;
            this.totalExpense = expense;
            this.netBalance = income - expense;
        }
    }

    public MonthlyReport generateMonthlyReport(int userId, int year, int month) {
        double totalIncome = 0, totalExpense = 0;

        String sql = "SELECT type, amount FROM transactions WHERE user_id = ? AND YEAR(date) = ? AND MONTH(date) = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, year);
            stmt.setInt(3, month);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");

                if (type.equalsIgnoreCase("income")) {
                    totalIncome += amount;
                } else if (type.equalsIgnoreCase("expense")) {
                    totalExpense += amount;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new MonthlyReport(totalIncome, totalExpense);
    }
}
