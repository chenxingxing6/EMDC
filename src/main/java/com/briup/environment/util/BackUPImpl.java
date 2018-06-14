package com.briup.environment.util;

import java.io.*;
import java.util.Properties;

/**
 * 备份模块实现
 * 1.客户端向服务器发送数据
 * 2.入库的时候，服务端备份
 * @Author: cxx
 * @Date: 2018/6/13 9:55
 */
public class BackUPImpl implements BackUP {
    @Override
    public void init(Properties prop) throws Exception {
        System.out.println("备份init");
    }

    @Override
    public void store(String filePath, Object obj, boolean append) throws Exception {
        File file=new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos=new FileOutputStream(file,append);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        fos.close();
    }

    @Override
    public Object load(String filePath, boolean del) throws Exception{
        File file=new File(filePath);
        if(!file.exists()) return null;
        FileInputStream fis=new FileInputStream(file);
        ObjectInputStream ois=new ObjectInputStream(fis);
        Object o=ois.readObject();
        ois.close();
        fis.close();
        if(del==true){
            file.delete();
        }
        return o;
    }
}
