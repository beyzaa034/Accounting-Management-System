/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountingManagementSystem;

/**
 *
 * @author beyza
 */
public class User extends Person {

    public User(int id, String username, String password) {
        super(id, username, password, "user");   // User can only access their own data.
    }

    @Override
    public String toString() {
        return super.toString() + " (User Account)"; 
    }

}
