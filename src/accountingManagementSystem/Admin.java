/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountingManagementSystem;

/**
 *
 * @author beyza
 */
public class Admin extends Person {

    public Admin(int id, String username, String password) {
        super(id, username, password, "admin");  // Admin can access all user data.
    }

    @Override
    public String toString() {
        return super.toString() + " (Admin Account)"; // super.toString() Person'ın toString'ini çağırır
    }
}
