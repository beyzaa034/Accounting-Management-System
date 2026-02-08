/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountingManagementSystem;

/**
 *
 * @author beyza
 */
public class CurrentUser {

    private static Person person;

    public static void setPerson(Person p) {
        person = p;
    }

    public static Person getPerson() {
        return person;
    }

    public static int getUserId() {
        return person.getId();
    }

    public static String getRole() {
        return person.getRole();
    }
}
