package com.briup.environment.test;

import com.briup.environment.util.SystemUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @Author: cxx
 * 日志测试文件 log4j
 * @Date: 2018/6/12 16:17
 */
public class LogTest {
    private static final Logger logger = Logger.getRootLogger();
    public static void main(String[] args) {
        PropertyConfigurator.configure(SystemUtil.resourcePath+"log4j.properties");
        logger.info("11");
    }
}
