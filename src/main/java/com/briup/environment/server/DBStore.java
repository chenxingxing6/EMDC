package com.briup.environment.server;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;

import java.util.Collection;

/**
 * @Author: cxx
 * 入库模块接口规范
 * @Date: 2018/6/12 9:51
 */
public interface DBStore extends EmdcModule{
    public void saveDb(Collection<Environment> coll) throws Exception;
}
