package com.briup.environment.gui;

import com.briup.environment.bean.UserBean;
import com.briup.environment.util.DBUtil;
import com.briup.environment.util.LoggerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: cxx
 * 用户功能实现
 * @Date: 2018/6/11 22:30
 */
public class UserImpl implements User {
    private static String loginSql="SELECT * FROM TB_USER WHERE username=? and password=?";
    private static String searchByNameSql="SELECT* FROM TB_USER WHERE username=?";
    private static String registerSql="insert into TB_USER values(user_seq.nextval,?,?)";
    private static String changePwdSql="update TB_USER set password = ? where username = ?";
    public boolean login(String username, String pwd) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(loginSql);
            ps.setString(1,username);
            ps.setString(2,pwd);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean searchByName(String username) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(searchByNameSql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String username, String pwd) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(registerSql);
            ps.setString(1,username);
            ps.setString(2,pwd);
            int i = ps.executeUpdate();
            if (i>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePwd(UserBean user, String newPwd) {
        try {
            Connection conn = DBUtil.getConn();
            PreparedStatement ps = conn.prepareStatement(changePwdSql);
            ps.setString(1,newPwd);
            ps.setString(2,user.getUsername());
            int i = ps.executeUpdate();
            if (i>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new UserImpl().login("admin","12456"));
    }
}
