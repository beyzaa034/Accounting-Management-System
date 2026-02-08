/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package accountingManagementSystem;

import java.awt.Frame;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.Timestamp;
import javax.swing.ImageIcon;

/**
 *
 * @author beyza
 */
public class UserDashboard extends javax.swing.JFrame {

    int currentUserId;
    
    public UserDashboard() {
        initComponents();
    }

    public UserDashboard(String username) {
        initComponents();

        currentUserId = CurrentUser.getUserId();
        loadUserTransactions(currentUserId);
        // Sadece kullanıcı adını göstermek için:
        jLabelUsername.setText("Welcome, " + username + "!");

        // Önceki toString() kullanımını log'a taşıyalım, arayüzde görmenize gerek yok
        if (CurrentUser.getPerson() != null) {
            System.out.println("User Dashboard initialized for: " + CurrentUser.getPerson().toString());
        }

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("images/budget.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        jButtonAddTransaction.setIcon(resizedIcon);

        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("images/accounting (4).png"));
        Image originalImage1 = originalIcon1.getImage();
        Image resizedImage1 = originalImage1.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
        jButtonReport.setIcon(resizedIcon1);

        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("images/update.png"));
        Image originalImage2 = originalIcon2.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        jButtonUpdate.setIcon(resizedIcon2);

        ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("images/logout.png"));
        Image originalImage3 = originalIcon3.getImage();
        Image resizedImage3 = originalImage3.getScaledInstance(14, 14, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        Logout.setIcon(resizedIcon3);
    }

    public List<TransactionClass> getUserTransactionsFromDB(int userId) {
        List<TransactionClass> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                Timestamp date = rs.getTimestamp("date");
                String type = rs.getString("type");
                String category = rs.getString("category");
                double amount = rs.getDouble("amount");
                String currency = rs.getString("currency");
                String description = rs.getString("description");

                TransactionClass t = new TransactionClass(transactionId, date, userId, type, category, amount, currency, description);
                transactions.add(t);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return transactions;
    }

    public void loadUserTransactions(int currentUserId) {
        // Only the transactions of the relevant user.
        String sql = "SELECT date, type, category, amount, currency, description FROM transactions WHERE user_id = ? ORDER BY date DESC";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, currentUserId);

            ResultSet rs = pstmt.executeQuery();

            // Get the table model and clear all rows first.
            DefaultTableModel model = (DefaultTableModel) jTableMyTransactions.getModel();
            model.setRowCount(0);

            // Add each transaction from the ResultSet to the table.
            while (rs.next()) {
                Object[] row = {
                    rs.getTimestamp("date"), // Tarih
                    rs.getString("type"), // Tip (Income/Expense)
                    rs.getString("category"),
                    rs.getDouble("amount"),
                    rs.getString("currency"),
                    rs.getString("description")
                };

                model.addRow(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load transactions: "
                    + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuMyTransactions = new javax.swing.JPopupMenu();
        jMenuItemDeleteTransaction = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonAddTransaction = new javax.swing.JButton();
        jLabelUsername = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jButtonReport = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMyTransactions = new javax.swing.JTable();
        jButtonUpdate = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Logout = new javax.swing.JMenuItem();

        jPopupMenuMyTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPopupMenuMyTransactionsMousePressed(evt);
            }
        });

        jMenuItemDeleteTransaction.setText("Delete");
        jMenuItemDeleteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteTransactionActionPerformed(evt);
            }
        });
        jPopupMenuMyTransactions.add(jMenuItemDeleteTransaction);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonAddTransaction.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonAddTransaction.setText("Add Transaction");
        jButtonAddTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddTransactionActionPerformed(evt);
            }
        });

        jLabelUsername.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtonReport.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonReport.setText("Generate Report");
        jButtonReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButtonAddTransaction)
                        .addGap(83, 83, 83)
                        .addComponent(jButtonReport)
                        .addGap(0, 291, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddTransaction)
                    .addComponent(jButtonReport))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "My Transactions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 20))); // NOI18N

        jTableMyTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Type", "Category", "Amount", "Currency", "Description"
            }
        ));
        jTableMyTransactions.setComponentPopupMenu(jPopupMenuMyTransactions);
        jTableMyTransactions.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableMyTransactions.setShowGrid(true);
        jTableMyTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMyTransactionsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMyTransactionsMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMyTransactions);

        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jButtonUpdate)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jMenu1.setText("Settings");

        Logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        jMenu1.add(Logout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);

        if (result == 0) {
            for (Frame frame : Frame.getFrames()) {
                if (frame.isDisplayable()) {
                    frame.dispose();
                }
            }
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        }
    }//GEN-LAST:event_LogoutActionPerformed

    private void jButtonAddTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTransactionActionPerformed
        TransactionFrame addTransaction = new TransactionFrame();
        addTransaction.setVisible(true);
    }//GEN-LAST:event_jButtonAddTransactionActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        currentUserId = CurrentUser.getUserId();
        loadUserTransactions(currentUserId);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTableMyTransactionsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMyTransactionsMousePressed

    }//GEN-LAST:event_jTableMyTransactionsMousePressed

    private void jTableMyTransactionsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMyTransactionsMouseReleased

    }//GEN-LAST:event_jTableMyTransactionsMouseReleased

    private void jMenuItemDeleteTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteTransactionActionPerformed
        int selectedRow = jTableMyTransactions.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = jTableMyTransactions.convertRowIndexToModel(selectedRow);

            int currentUserId = CurrentUser.getUserId();
            List<TransactionClass> transactions = getUserTransactionsFromDB(currentUserId);

            if (modelRow >= transactions.size()) {
                JOptionPane.showMessageDialog(this, "Invalid transaction.");
                return;
            }

            TransactionClass selectedTransaction = transactions.get(modelRow);
            Integer transactionId = selectedTransaction.getTransactionId();

            if (transactionId == null) {
                JOptionPane.showMessageDialog(this, "Transaction ID not found in this operation.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this transaction?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM transactions WHERE transaction_id = ?")) {

                stmt.setInt(1, transactionId);
                int result = stmt.executeUpdate();

                if (result > 0) {
                    ((DefaultTableModel) jTableMyTransactions.getModel()).removeRow(modelRow);
                    JOptionPane.showMessageDialog(this, "Transaction deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Transaction could not be deleted.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemDeleteTransactionActionPerformed

    private void jPopupMenuMyTransactionsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenuMyTransactionsMousePressed

    }//GEN-LAST:event_jPopupMenuMyTransactionsMousePressed

    private void jButtonReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportActionPerformed
        try {
            String input = JOptionPane.showInputDialog(this,
                    "Enter Year and Month for the report (e.g., 05-25 or 05-2025).:");
            if (input == null || input.trim().isEmpty()) {
                return;
            }

            // Regex
            String regexMMYY = "^(0[1-9]|1[0-2])-\\d{2}$"; // MM-YY format
            String regexMMYYYY = "^(0[1-9]|1[0-2])-(200[0-9]|201[0-9]|202[0-5])$"; // MM-YYYY format, 2000-2025 

            if (!input.matches(regexMMYY) && !input.matches(regexMMYYYY)) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid date: it must be in 'MM-YY' or "
                        + "'MM-YYYY' format, and the year should be between 2000 and 2025.");
                return;
            }

            String[] parts = input.split("-");
            if (parts.length != 2) {
                JOptionPane.showMessageDialog(this, "Please enter in 'MM-YY' or 'MM-YYYY' format! "
                        + "Example: 05-25 or 05-2025");
                return;
            }

            int month = Integer.parseInt(parts[0].trim());
            int yearPart = Integer.parseInt(parts[1].trim());

            int year;
            if (parts[1].length() == 2) {
                year = 2000 + yearPart;
            } else {
                year = yearPart;
            }

            int userId = CurrentUser.getUserId();
            ReportManager reportManager = new ReportManager();
            ReportManager.MonthlyReport report = reportManager.generateMonthlyReport(userId, year, month);

            ReportFrame reportFrame = new ReportFrame(report, year, month);
            reportFrame.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year and month must contain only numbers!");
        }
    }//GEN-LAST:event_jButtonReportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Logout;
    private javax.swing.JButton jButtonAddTransaction;
    private javax.swing.JButton jButtonReport;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemDeleteTransaction;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenuMyTransactions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTableMyTransactions;
    // End of variables declaration//GEN-END:variables
}
