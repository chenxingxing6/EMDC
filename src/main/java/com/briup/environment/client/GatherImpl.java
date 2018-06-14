package com.briup.environment.client;
import com.briup.environment.bean.Environment;
import com.briup.environment.util.SystemUtil;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/**
 * @Author: cxx
 * @Date: 2018/6/11 11:01
 */
public class GatherImpl implements Gather {
    Collection<Environment> coll = new ArrayList<Environment>();
    //采集原始文件路径
    private String path = SystemUtil.srcPath;
    //保存上传读取的字节数文件
    private String path2 = SystemUtil.savPath;


    @Override
    public void init(Properties prop) throws Exception {
        this.path=prop.getProperty("srcfile");
        this.path2=prop.getProperty("recoredfile");
    }

    /**
     *采集数据
     * @return
     * @throws Exception
     */
    public Collection<Environment> gather() throws Exception {
        /**
         *1.读取上次读取文件的字节数，第一次文件不存在,num=0;
         *2.读取radwtmp有效字节数，将返回值保存到record
         *3.先略过上传读取的字节，进行解析
         */
        //第一步
        File file = new File(path2);
        long num =0;
        if (file.exists()){
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            num = dis.readLong();
        }
        //第二步
        RandomAccessFile raf = new RandomAccessFile(path,"r");
        long num2 = raf.length();
        raf.seek(num);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(path2));
        //dos.writeLong(num2);
        dos.writeLong(0);
        dos.flush();
        //第三步
        /**
         * 1.构建缓存字符流按行读取
         * 2.根据|分割，根据第4个字段 16：温度湿度 256：光 1280：二氧化碳
         * 3.第七字段，16进制->10
         * （温度2，湿度2字节） 光，二氧化碳（前2个字节）
         * 4.温度和湿度，读取一行要创建2个Enviroment对象
         * 5.添加到集合
         */
        String s = null;
        String str[] = null;
        Environment env = null;
        int count=0;//统计温度和湿度条数
        int count2=0;//统计光照强度条数
        int count3=0;//统计二氧化碳条数
        while ((s=raf.readLine())!=null){
            env = new Environment();
            str=s.split("[|]");
            env.setSrcId(str[0]);
            env.setDesId(str[1]);
            env.setDevId(str[2]);
            env.setSersorAddress(str[3]);
            env.setCount(Integer.parseInt(str[4]));
            env.setCmd(str[5]);
            env.setStatus(Integer.parseInt(str[7]));
            env.setGather_date(new Timestamp(Long.parseLong(str[8])));
            if (str.length!=9){
                continue;
            }
            if ("16".equals(str[3])){
                //温度  ((float)value＊0.00268127)-46.85
                //湿度  ((float)value*0.00190735)-6
                //根据温度转换公式将16进制转10进制
                float temp = (float) Integer.parseInt(str[6].substring(0,4),16);
                float value = (float) ((temp*0.00268127)-46.85);
                env.setName("温度");
                env.setData(value);
                coll.add(env);
                count++;
                //再处理湿度
                Environment env2 = new Environment(env);
                float temp1 = (float) Integer.parseInt(str[6].substring(4,8),16);
                float value1 = (float) ((temp1*0.00190735)-6);
                env2.setName("湿度");
                env2.setData(value1);
                coll.add(env2);
                count++;
            }else {
                float value = Integer.valueOf(str[6].substring(0,4),16);
                if ("256".equals(str[3])){
                    env.setName("光照强度");
                    env.setData(value);
                    coll.add(env);
                    count2++;
                }
                if ("1280".equals(str[3])){
                    env.setName("二氧化碳");
                    env.setData(value);
                    coll.add(env);
                    count3++;
                }
            }
        }
        System.out.println("采集环境数据："+coll.size());
        System.out.println("温度湿度："+count);
        System.out.println("光照强度："+count2);
        System.out.println("二氧化碳："+count3);
        return coll;

    }

    public static void main(String[] args) throws Exception {
        new GatherImpl().gather();
    }
}
