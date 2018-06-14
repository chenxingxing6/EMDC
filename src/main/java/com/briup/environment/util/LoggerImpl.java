package com.briup.environment.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/**
 * @Author: cxx
 * @Date: 2018/6/12 9:10
 */
public class LoggerImpl implements com.briup.environment.util.Logger{
    private String path= SystemUtil.resourcePath+"log4j.properties";
    @Override
    public void init(Properties prop) throws Exception {
        this.path=prop.getProperty("backupfile");
    }

    //sl4j
    //private static final Logger log = LoggerFactory.getLogger(LoggerImpl.class);
    //log4j
    private static final Logger log = Logger.getRootLogger();
    public LoggerImpl(){
        PropertyConfigurator.configure(path);
    }
    @Override
    public void debug(String msg) {
        log.debug(msg);
    }

    @Override
    public void info(String msg) {
        log.info(msg);
    }

    @Override
    public void warn(String msg) {
        log.warn(msg);
    }

    @Override
    public void error(String msg) {
        log.error(msg);
    }

    @Override
    public void fatal(String msg) {
    }
}
