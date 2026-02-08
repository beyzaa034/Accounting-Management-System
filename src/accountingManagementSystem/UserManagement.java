/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package accountingManagementSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author beyza
 */
public class UserManagement extends javax.swing.JFrame {

    
    int adminCount = 0;
    int currentUserId = CurrentUser.getUserId();
    private DefaultTableModel model;

    public UserManagement() {
        initComponents();
        model = (DefaultTableModel) jTableUsers.getModel();
        loadUsersToTable();
    }
    
    public int adminCounter() {
        String sql = "SELECT role FROM users";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                String role = rs.getString("role");
                if(role != null && role.equalsIgnoreCase("admin")) {
                    adminCount++;
                }
            }
            return adminCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void deleteUser() {
        int selectedRow = jTableUsers.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int userId = (int) jTableUsers.getValueAt(selectedRow, 0); // id column
        String role = String.valueOf(jTableUsers.getValueAt(selectedRow, 2));
        
        adminCount = adminCounter();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the selected user?",
                "Confirm", JOptionPane.YES_NO_OPTION);

        
        if (confirm == JOptionPane.YES_OPTION) {

            if ((role.equalsIgnoreCase("admin") && adminCount == 1)) {
                JOptionPane.showMessageDialog(this,
                        "There must be at least one admin in the system. "
                        + "You cannot change the role of the last admin!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // cancel the update
            }
            if(userId == currentUserId) {
                JOptionPane.showMessageDialog(this,
                        "You can't delete yourself :) ",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // cancel the update
            }
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "DELETE FROM users WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, userId);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully.");
                    // update the table
                    ((DefaultTableModel) jTableUsers.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(this, "User not found or could not be deleted.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred during the deletion process: " + ex.getMessage());
            }
        }
    }

    public void loadUsersToTable() {
        model.setRowCount(0); // clear table

        String sql = "SELECT id, username, role FROM users";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String role = rs.getString("role");

                model.addRow(new Object[]{id, username, role});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jButtonAddNewUser = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jMenuItem1.setText("Delete");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("All Users"));

        jTableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Username", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUsers.setComponentPopupMenu(jPopupMenu1);
        jTableUsers.setShowGrid(true);
        jScrollPane1.setViewportView(jTableUsers);

        jButtonAddNewUser.setText("Add new user");
        jButtonAddNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNewUserActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jButtonAddNewUser)
                .addGap(51, 51, 51)
                .addComponent(jButtonUpdate)
                .addGap(57, 57, 57)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddNewUser)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

    private void jButtonAddNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNewUserActionPerformed
        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter username (must start with a lowercase letter "
                    + "and can contain lowercase letters, digits, '.' or '\\_', 3-28 characters):");
            if (username == null) {
                return;
            }
            if (username.matches(".*[ğüşöçıİĞÜŞÖÇ].*")) {
                JOptionPane.showMessageDialog(null, "Username cannot contain Turkish characters!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (username.matches("^[a-z][a-z0-9_.]{2,27}$")) {
                break; // Correct username
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username! Please try again following the conditions.");
            }
        }

        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Enter password (6-20 characters, "
                    + "must contain at least 1 uppercase letter, 1 lowercase letter, and 1 digit,"
                    + " no special characters):");
            if (password == null) {
                return;
            }
            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$")) {
                break; // correct password
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password! "
                        + "Please try again following the conditions.");
            }
        }

        String role;
        while (true) {
            role = JOptionPane.showInputDialog("Enter role (admin or user):");
            if (role == null) {
                return;
            }
            if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user")) {
                role = role.toLowerCase();
                break;
            } else {
                JOptionPane.showMessageDialog(this, "Role can only be 'admin' or 'user'.");
            }
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "User added.");
            loadUsersToTable();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding the user.: " + e.getMessage());
        }
    }//GEN-LAST:event_jButtonAddNewUserActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE users SET username = ?, role = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             int selectedRow = jTableUsers.getSelectedRow();
             int userIdd = (int) jTableUsers.getValueAt(selectedRow, 0); // id column
            if(userIdd == currentUserId) {
                JOptionPane.showMessageDialog(this,
                        "You can't change your role! ",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // cancel the update
            }

            for (int row = 0; row < model.getRowCount(); row++) {
                int userId = (int) model.getValueAt(row, 0);
                String username = (String) model.getValueAt(row, 1);
                String role = (String) model.getValueAt(row, 2);

                stmt.setString(1, username);
                stmt.setString(2, role);
                stmt.setInt(3, userId);

                stmt.addBatch();
            }

            stmt.executeBatch();
            JOptionPane.showMessageDialog(this, "All users have been updated.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred during the update: " + e.getMessage());
        }

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteUser();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        deleteUser();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddNewUser;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsers;
    // End of variables declaration//GEN-END:variables
}
