/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package v.authentification.modelClass;

import java.util.List;

import generalisation.GenericDAO.GenericDAO;

/**
 *
 * @author chalman
 */
public class Authentificate {
    private User user;
    
///Getters et setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
///Constructors

    public Authentificate() {
    }

    public Authentificate(User user) {
        this.user = user;
    }
    
///Fonctions
    //S'authentifier par user
    public static User login(User user) throws Exception {
        String sql = "SELECT * FROM users WHERE username = '"+user.getUsername()+"' AND mdp = '"+user.getPassword()+"'";
        List<User> users = (List<User>)GenericDAO.directQuery(User.class, sql, null);

        if(users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
