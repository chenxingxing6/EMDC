package com.briup.environment.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: cxx
 * 环境存储实体类，包括环境种类(温度，湿度，二氧化碳，光照强度)
 * 100|101|2|256|1|3|001003|1|1516361343403
 * 发送方id，树莓派id,实验箱模块id,传感器地址,传感器个数，指令标号，环境值，状态，采集时间
 * @Date: 2018/6/11 10:09
 */
public class Environment implements Serializable {
    //环境种类名称
    private String name;
    //发送方id
    private String srcId;
    //树莓派id
    private String desId;
    //实验箱id（1-8）
    private String devId;
    //传感器地址
    private String sersorAddress;
    //传感器个数
    private int count;
    //发送指令号 3：接收 16：发送
    private String cmd;
    //状态 1:成功
    private int status;
    //环境值
    private float data;
    //采集时间
    private Timestamp gather_date;

    /**
     * 无参构造函数
     * 全参构造函数
     * get,set方法
     * toString方法
     */
    public Environment(){}
    public Environment(Environment e){
        this.name = e.getName();
        this.srcId = e.getSrcId();
        this.desId = e.getDesId();
        this.devId = e.getDevId();
        this.sersorAddress = e.getSersorAddress();
        this.count = e.getCount();
        this.cmd = e.getCmd();
        this.status = e.getStatus();
        this.data = e.getData();
        this.gather_date = e.getGather_date();
    }
    public Environment(String name, String srcId, String desId, String devId, String sersorAddress, int count, String cmd, int status, float data, Timestamp gather_date) {
        this.name = name;
        this.srcId = srcId;
        this.desId = desId;
        this.devId = devId;
        this.sersorAddress = sersorAddress;
        this.count = count;
        this.cmd = cmd;
        this.status = status;
        this.data = data;
        this.gather_date = gather_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getDesId() {
        return desId;
    }

    public void setDesId(String desId) {
        this.desId = desId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getSersorAddress() {
        return sersorAddress;
    }

    public void setSersorAddress(String sersorAddress) {
        this.sersorAddress = sersorAddress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public Timestamp getGather_date() {
        return gather_date;
    }

    public void setGather_date(Timestamp gather_date) {
        this.gather_date = gather_date;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "name='" + name + '\'' +
                ", srcId='" + srcId + '\'' +
                ", desId='" + desId + '\'' +
                ", devId='" + devId + '\'' +
                ", sersorAddress='" + sersorAddress + '\'' +
                ", count=" + count +
                ", cmd='" + cmd + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", gather_date=" + gather_date +
                '}';
    }
}
