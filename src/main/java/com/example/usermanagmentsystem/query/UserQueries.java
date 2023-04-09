package com.example.usermanagmentsystem.query;

public class UserQueries {
    public static final String REGISTER="INSERT INTO user(name,email,country) VALUES(?,?,?)";
    public static final String UPDATE_USER="UPDATE user SET name=?,email=?,country=? WHERE id=? and status=1";
    public static final String DELETE_USER="UPDATE user SET status=0 WHERE id=? and status=1";
    public static final String SHOW_USER="SELECT id,name,email,country FROM user WHERE status=1";
    public static final String SHOW_USER_BY_ID="SELECT id,name,email,country FROM user WHERE id=? and status=1";



}
