package com.briup.environment.client;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

/**
 * 实现客户端网络模块
 * @Author: cxx
 * @Date: 2018/6/11 15:07
 */
public class ClientImpl implements Client {
    private int port = 9999;
    private String ip = "127.0.0.1";
    private Socket client = null;
    private static final Logger log = new LoggerImpl();
    private BackUP back = new BackUPImpl();
    public ClientImpl(){ }


    /**
     * 加载配置文件
     * @param prop
     * @throws Exception
     */
    @Override
    public void init(Properties prop) throws Exception {
        this.port=Integer.parseInt(prop.getProperty("port"));
        this.ip=prop.getProperty("ip");
    }

    /**
     * 发送集合中数据到服务器端
     * @param e
     */
    public void send(Collection<Environment> e) throws Exception {
        try {
            System.out.println("正在努力和服务器相连...");
            client = new Socket(ip, port);
            System.out.println("连接成功...");
            log.debug("加载客户端备份文件");
            Collection<Environment> co = (Collection<Environment>) back.load(SystemUtil.resourcePath + "clientback.txt", true);
            if (co != null) {
                log.debug("服务器数据正在备份...");
                e.addAll(co);
            }
            OutputStream os = client.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(e);
            oos.flush();
            System.out.println("数据发送成功");
            oos.close();
        }catch (Exception e1){
            log.debug("客服端发送数据失败!进行备份");
            try {
                back.store(SystemUtil.resourcePath+"clientback.txt",e,true);
                log.debug("数据备份成功");
            } catch (Exception e2) {
                log.debug("数据备份失败");
            }
        }
    }
}
