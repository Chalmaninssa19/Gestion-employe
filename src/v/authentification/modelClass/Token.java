package v.authentification.modelClass;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;



@DBTable(name = "token", sequenceName="seq_token")
public class Token {
    @DBField(name = "id_token", isPrimaryKey=true)
    int idToken;
    @DBField(name = "value")
    String value;
    @DBField(name = "date_start")
    LocalDateTime date_start;
    @DBField(name = "date_end")
    LocalDateTime date_end;

    String username;
    String role;
    static long EXPIRATION = 1800;
    
///Getters et setters
    public String getUsernamme() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getIdToken() {
        return this.idToken;
    }
    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getDate_start() {
        return this.date_start;
    }
    public void setDate_start(LocalDateTime dateStart) {
        this.date_start = dateStart;
    }

    public LocalDateTime getDate_end() {
        return this.date_end;
    }
    public void setDate_end(LocalDateTime dateEnd) {
        this.date_end = dateEnd;
    }
///Constructors
    public Token() {}
    public Token(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public Token(int idToken, String value, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.idToken = idToken;
        this.value = value;
        this.date_start = dateStart;
        this.date_end = dateEnd;
    }
    public Token(String value, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.value = value;
        this.date_start = dateStart;
        this.date_end = dateEnd;
    }
///Fonctions
    //Generer un token
    public String generateToken() throws Exception {
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateEnd = dateNow.plusSeconds(EXPIRATION);
        String tokenGenerate = this.getUsernamme()+"-"+this.getRole()+"-"+dateEnd;
        String tokenDecoded = encodeBase64(tokenGenerate);
        Token token = new Token(tokenDecoded, dateNow, dateEnd);
        GenericDAO.save(token, null);
        
        return tokenDecoded;
    }

    //Est ce que le token existe
    public String isTokenExist(String token) throws Exception {
        String sql = "SELECT * FROM token WHERE value = '"+token+"'";
        List<Token> tokens = (List<Token>)GenericDAO.directQuery(Token.class, sql, null);
        LocalDateTime dateNow = LocalDateTime.now();

        if(tokens.size() > 0) {
            if(dateNow.isBefore(tokens.get(0).getDate_end())) {
                return tokens.get(0).getValue();
            } else {
                deleteToken(token);
                return null;
            }
        }

        return null;
    }

    //Supprimer le token
    public void deleteToken(String token) throws Exception {
        String sql = "SELECT * FROM token WHERE value = '"+token+"'";
        List<Token> tokens = (List<Token>)GenericDAO.directQuery(Token.class, sql, null);
        GenericDAO.deleteById(Token.class, tokens.get(0).getIdToken(), null);
    }

    //Encoder un string en base64
    public String encodeBase64(String text) {
        Base64.Encoder encoder = Base64.getEncoder();

        return encoder.encodeToString(text.getBytes());
    }

    //Decoder des donnees encoder en base64
    public static String decodeBase64(String encoded) {
        Base64.Decoder decoder = Base64.getDecoder();

        return new String(decoder.decode(encoded));
    }
}
