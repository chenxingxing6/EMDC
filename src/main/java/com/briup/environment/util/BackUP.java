package com.briup.environment.util;

import java.io.IOException;

/**
 * 公共的备份模块
 * @Author: cxx
 * @Date: 2018/6/13 9:49
 */
public interface BackUP extends EmdcModule{
    void store(String filePath,Object obj,boolean append) throws Exception;
    Object load(String filePath, boolean del) throws Exception;
}
