package lt.kaunascoding.web.model.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {

    private int id;
    private String username;
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Users(){

    }

    public Users(ResultSet eilute) {
        try {
            id = eilute.getInt("id");
            username = eilute.getString("username");
            password = eilute.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
