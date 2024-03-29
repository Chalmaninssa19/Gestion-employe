package v.CORS;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import v.authentification.modelClass.Authentificate;
import v.authentification.modelClass.Token;
import v.authentification.modelClass.URLRoleAuthorized;
import v.authentification.modelClass.User;

public class CorsFilter implements Filter{

    private void setResponseAccess(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        // response.setHeader("Access-Control-Allow-Credentials", "true");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        setResponseAccess(httpResponse);

        if(httpRequest.getMethod().equals("OPTIONS")){
            httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }    

        try {
            System.out.println("FILTREEEEEEE -------------------------");
    
            if(httpRequest.getRequestURI().equals("/V/login.do")) {

                String username = httpRequest.getParameter("username");
                String password = httpRequest.getParameter("mdp");
                
                User user =  Authentificate.login(new User(username, password));
                if(user != null) {
                    Token token = new Token(user.getUsername(), user.getRole());
                    String tokenGenerated = token.generateToken();
                    httpResponse.setHeader("token", tokenGenerated);
                    chain.doFilter(httpRequest, response);
                } else {
                    throw new Exception("Erreur d'authentification: Veuillez verifier votre username et mot de passe ");
                }
            } else if(URLRoleAuthorized.isInListUrl(httpRequest.getRequestURI())) {
                String token = httpRequest.getHeader("token");
                String isTokenValided = new Token().isTokenExist(token);
                if(isTokenValided != null) {
                    String tokenDecoded = Token.decodeBase64(token);
                    String [] tokenDecodedSplitted = tokenDecoded.split("-");
                    String roleUrl = URLRoleAuthorized.getRoleUrl(httpRequest.getRequestURI());
                    if(tokenDecodedSplitted[1].equals(roleUrl) || roleUrl.equals("")) {
                        System.out.println("Authorized");
                        chain.doFilter(httpRequest, response);
                    } else {
                        throw new Exception("Vous n'etes pas authoriser a effectue cette operation");
                    }
                    chain.doFilter(httpRequest, response);
                } else {
                    throw new Exception("Vous devrez s'authentifier");
                }              
            } else if(httpRequest.getRequestURI().equals("/V/deconnect.do")) {
                String token = httpRequest.getHeader("token");
                String isTokenValided = new Token().isTokenExist(token);
                if(isTokenValided != null) {
                    new Token().deleteToken(token);
                    System.out.println("User deconnected");
                    chain.doFilter(httpRequest, response);
                } else {
                    throw new Exception("Vous devrez s'authentifier");
                }  
            } else {
                throw new Exception("Error");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy(){
    }
     
}