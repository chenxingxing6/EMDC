package com.briup.environment.util;

import java.util.Properties;

/**
 * @Author: cxx
 * 所有模块接口的父接口
 * @Date: 2018/6/13 14:57
 */
public interface EmdcModule {
    public void init(Properties prop) throws Exception;
}
