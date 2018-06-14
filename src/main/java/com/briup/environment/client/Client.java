package com.briup.environment.client;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;

import java.io.IOException;
import java.util.Collection;

/**
 * @Author: cxx
 * 客户端-网络模块
 * @Date: 2018/6/11 15:05
 */
public interface Client extends EmdcModule{
    public void send(Collection<Environment> e) throws Exception;
}
