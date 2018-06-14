package com.briup.environment.bean;

/**
 * @Author: cxx
 * 封装最大最小平均值
 * @Date: 2018/6/12 20:25
 */
public class MaxMinAvg {
    //type 0:all 1:温度  2:湿度   3:光照强度  4：二氧化碳
    private int type;
    private float max;
    private float min;
    private float avg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "MaxMinAvg{" +
                "type=" + type +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                '}';
    }
}
