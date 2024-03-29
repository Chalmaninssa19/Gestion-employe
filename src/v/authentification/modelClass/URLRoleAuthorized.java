package v.authentification.modelClass;

import java.util.ArrayList;
import java.util.List;

public class URLRoleAuthorized {
    private String url;
    private String roleAuthorized;

///Getters et setters
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoleAuthorized() {
        return this.roleAuthorized;
    }
    public void setRoleAuthorized(String roleAuthorized) {
        this.roleAuthorized = roleAuthorized;
    }

///Constructors
    public URLRoleAuthorized() {}
    public URLRoleAuthorized(String url, String roleAuthorized) {
        this.url = url;
        this.roleAuthorized = roleAuthorized;
    }

///Getters et setters
    //Role d'un authoriser d'une URL
    public static String getRoleUrl(String url) {
        List<URLRoleAuthorized> listURLAndRole = listURLWithRoleAuthorized();

        for(int i = 0; i < listURLAndRole.size(); i++) {
            if(listURLAndRole.get(i).getUrl().equals(url)) {
                return listURLAndRole.get(i).getRoleAuthorized();
            }
        }

        return null;
    }

    //Est ce que le URL est dans la liste
    public static boolean isInListUrl(String url) {
        List<URLRoleAuthorized> listURLAndRole = listURLWithRoleAuthorized();

        for(int i = 0; i < listURLAndRole.size(); i++) {
            if(listURLAndRole.get(i).getUrl().equals(url)) {
                return true;
            }
        }

        return false;
    }
    //Liste statique de toutes les urls avec leurs roles
    public static List<URLRoleAuthorized> listURLWithRoleAuthorized() {
        List<URLRoleAuthorized> list = new ArrayList<>();
        list.add(new URLRoleAuthorized("/V/insert-employe.do", ""));
        list.add(new URLRoleAuthorized("/V/update-employe.do", ""));
        list.add(new URLRoleAuthorized("/V/list-employe.do", ""));
        list.add(new URLRoleAuthorized("/V/delete-employe.do", ""));
        list.add(new URLRoleAuthorized("/V/insert-departement.do", ""));
        list.add(new URLRoleAuthorized("/V/list-departement.do", "admin"));
        list.add(new URLRoleAuthorized("/V/update-departement.do", ""));
        list.add(new URLRoleAuthorized("/V/delete-departement.do", ""));

        return list;
    }
}