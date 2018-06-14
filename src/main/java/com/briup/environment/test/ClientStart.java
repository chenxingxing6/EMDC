package com.briup.environment.test;

import com.briup.environment.bean.Environment;
import com.briup.environment.client.Client;
import com.briup.environment.client.ClientImpl;
import com.briup.environment.client.Gather;
import com.briup.environment.client.GatherImpl;
/**
 * @Author: cxx
 * @Date: 2018/6/11 15:26
 */
public class ClientStart {
    public static void main(String[] args) throws Exception {
        //客户端采集
        Gather gather = new GatherImpl();
        //客户端发送数据
        Client client = new ClientImpl();
        client.send(gather.gather());

    }
}
