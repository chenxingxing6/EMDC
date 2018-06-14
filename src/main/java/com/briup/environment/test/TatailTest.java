package com.briup.environment.test;

import com.briup.environment.client.Client;
import com.briup.environment.util.Configuration;
import com.briup.environment.util.ConfigurationImpl;
import org.junit.Test;

/**
 * @Author: cxx
 * 系统配置后测试
 * @Date: 2018/6/13 20:03
 */
public class TatailTest {
    private Configuration conf = new ConfigurationImpl();

    @Test
    public void startClient(){
        try {
            Client client = conf.getClient();
            client.send(conf.getGather().gather());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启服务端
     */
    @Test
    public void startServer(){
        try {
            conf.getServer().receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
