package com.briup.environment.client;
import com.briup.environment.bean.Environment;
import com.briup.environment.util.EmdcModule;

import java.util.Collection;

/**
 * 客户端采集模块
 * 开始对物联网数据中心项目环境信息进行采集，封装成Collection集合
 * @Author: cxx
 * @Date: 2018/6/11 10:09
 */
public interface Gather extends EmdcModule{
    public Collection<Environment> gather() throws Exception;
}
