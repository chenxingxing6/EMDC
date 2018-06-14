package com.briup.environment.dao;

import com.briup.environment.bean.Environment;
import com.briup.environment.bean.MaxMinAvg;

import java.util.Collection;
import java.util.List;

/**
 * @Author: cxx
 * 后台管理系统各种api
 * @Date: 2018/6/12 16:41
 */
public interface Api {
    Collection<Environment> getData(int day,int type) throws Exception;
    List<MaxMinAvg> getMaxMinAvg(int day, int type) throws Exception;
    List<Integer> getDay();
}
