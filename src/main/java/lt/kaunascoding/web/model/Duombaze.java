package lt.kaunascoding.web.model;

import lt.kaunascoding.web.Application;
import lt.kaunascoding.web.model.tables.UserRecords;
import lt.kaunascoding.web.model.tables.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Duombaze {

    private Connection _con;
    private Statement _st;

    public static void userId(){

    }

    public Duombaze() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            _con = DriverManager.getConnection("jdbc:mysql://192.168.100.100:3306/CSP", "root", "root");
            _st = _con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean loginCheck(Users user) {
        List<Users> result = new ArrayList<Users>();
        boolean check = false;
        try {
            _st = _con.createStatement();
            ResultSet set = _st.executeQuery("SELECT * FROM `users` WHERE `username` LIKE " + "\"" + user.getUsername() + "\"" + " AND `password` LIKE " + "\"" + user.getPassword() + "\"" + "");
            if (set.next()) {
                check =true;
                UserInfo.userId = set.getInt("id");
            } else{
                check = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean registrationCheck(Users user) {
        List<Users> result = new ArrayList<Users>();
        boolean check = false;
        try {
            _st = _con.createStatement();
            ResultSet set = _st.executeQuery("SELECT * FROM `users` WHERE `username` LIKE " + "\"" + user.getUsername() + "\"" + "");
            if (!set.next()) {
                check =true;
                UserInfo.setUsername(user.getUsername());
                UserInfo.setPassword(user.getPassword());
            } else{
                check = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void insertUser() {
        try {
            PreparedStatement st  = _con.prepareStatement("INSERT INTO `CSP`.`users` " +
                    "(`username`, `password`) " +
                    "VALUES (?, ?);");
            st.setString(1, UserInfo.getUsername());
            st.setString(2, UserInfo.getPassword());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void grynujuSkaiciavimas () {
        int sum = 0;
        try {
            _st = _con.createStatement();
            ResultSet grynieji = _st.executeQuery("SELECT SUM(`sum`) * FROM `user_records` WHERE `user_id` LIKE " + "\"" + UserInfo.userId + "\"" + " AND `account` LIKE \"Grynieji\"");
            while (grynieji.next()) {
                int suma = grynieji.getInt(1);
                sum = sum + suma;
            }
            UserInfo.setGrynieji(sum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserRecords> getAllUserRecords() {
        List<UserRecords> result = new ArrayList<UserRecords>();
        try {
            _st = _con.createStatement();
            ResultSet set = _st.executeQuery("SELECT * FROM `user_records` WHERE `user_id` LIKE " + "\"" + UserInfo.userId + "\"" + "");
            while (set.next()) {
                result.add(new UserRecords(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Users> getAllUsers() {
        List<Users> result = new ArrayList<Users>();
        try {
            _st = _con.createStatement();
            ResultSet set = _st.executeQuery("SELECT * FROM `users`");
            while (set.next()) {
                result.add(new Users(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void insertUserRecord(int userId, Date date, String group, String subgroup, String comment, float sum, String account) {

        try {
            PreparedStatement st  = _con.prepareStatement("INSERT INTO `CSP`.`user_records` " +
                    "(`user_id`, `date`, `group`, `subgroup`, `comment`, `sum`, `account`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            st.setInt(1,UserInfo.getUserId());
            st.setDate(2,date);
            st.setString(3,group);
            st.setString(4,subgroup);
            st.setString(5,comment);
            st.setFloat(6,sum);
            st.setString(7,account);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
