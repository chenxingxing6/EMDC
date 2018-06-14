package com.briup.environment.gui;
import com.briup.environment.bean.UserBean;

/**
 * @Author: cxx
 * @Date: 2018/6/11 10:09
 */
public interface User {
    boolean login(String username,String pwd);
    boolean searchByName(String username);
    boolean register(String username,String pwd);
    boolean changePwd(UserBean user, String newPwd);

}
