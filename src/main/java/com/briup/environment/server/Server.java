package com.briup.environment.server;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;

import java.io.IOException;
import java.util.Collection;

/**
 * @Author: cxx
 * 服务端网络模块
 * @Date: 2018/6/11 10:11
 */
public interface Server extends EmdcModule {
    public void receive() throws Exception;
    public void shutdown() throws Exception;
}
