package com.briup.environment.test;

import com.briup.environment.util.BackUPImpl;
import com.briup.environment.util.SystemUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cxx
 * @Date: 2018/6/13 11:47
 */
public class BackTest {
    @Test
    public void store(){
        List list = new ArrayList();
        for (int i = 0; i <10 ; i++) {
            list.add(i);
        }
        try {
            System.out.println("***");
            new BackUPImpl().store(SystemUtil.resourcePath+"backup.txt",list,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void load(){
        try {
            List list = (List) new BackUPImpl().load(SystemUtil.resourcePath + "backup.txt", true);
            for (Object o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
