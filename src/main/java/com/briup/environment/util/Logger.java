package com.briup.environment.util;

/**
 * @Author: cxx
 * @Date: 2018/6/11 10:12
 */
public interface Logger extends EmdcModule{
    void debug(String msg);
    void info(String msg);
    void warn(String msg);
    void error(String msg);
    void fatal(String msg);
}
