package lt.kaunascoding.web.model.tables;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRecords {
    private int userId;
    private Date date;
    private String group;
    private String subgroup;
    private String comment;
    private float sum;
    private String account;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UserRecords() {
    }

    public UserRecords(ResultSet eilute) {
        try {
            userId = eilute.getInt("user_id");
            date = eilute.getDate("date");
            group = eilute.getString("group");
            subgroup = eilute.getString("subgroup");
            comment = eilute.getString("comment");
            sum = eilute.getFloat("sum");
            account = eilute.getString("account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}