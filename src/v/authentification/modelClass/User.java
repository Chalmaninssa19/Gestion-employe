/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package v.authentification.modelClass;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

/**
 *
 * @author chalman
 */
@DBTable(name = "users", sequenceName="seq_user")
public class User {
    @DBField(name = "id_user", isPrimaryKey=true)
    int idUser;
    @DBField(name = "username")
    String username;
    @DBField(name = "mdp")
    String password;
    @DBField(name = "role")
    String role;
   
///Getters et setters
    public int getIdUser() {
        return this.idUser;
    }
    public void setUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return this.role;
    }
    public void setRole(String role) {
        this.role = role;
    }
 ///Constructors

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(int idUser, String username, String mdp, String role) {
        this.idUser = idUser;
        this.username = username;
        this.password = mdp;
        this.role = role;
    }

    public User(String username, String mdp, String role) {
        this.username = username;
        this.password = mdp;
        this.role = role;
    }

///Fonctions
}
