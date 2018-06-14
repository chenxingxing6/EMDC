package com.briup.environment.util;
import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.gui.Login;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;

/**
 * @Author: cxx
 * 配置模块接口
 * @Date: 2018/6/13 14:32
 */
public interface Configuration {
    public Logger getLogger() throws Exception;
    public Server getServer() throws Exception;
    public Client getClient() throws Exception;
    public DBStore getDBStore() throws Exception;
    public Gather getGather() throws Exception;
    public BackUP getBackup() throws Exception;
    public Login getLogin() throws Exception;
}
