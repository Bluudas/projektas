package lt.kaunascoding.web.model;

public class UserInfo {

    static int userId;
    static String username = "vartotojas4000";
    static String password = "vartotojas4000";


    public static int getGrynieji() {
        return grynieji;
    }

    public static void setGrynieji(int grynieji) {
        UserInfo.grynieji = grynieji;
    }

    static int grynieji = 1000;
    static  int saskaitoje;
    static int visoPinigu;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserInfo.username = username;
    }



    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserInfo.password = password;
    }



    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        UserInfo.userId = userId;
    }
}
