package com.briup.environment.server;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.*;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

/**
 * @Author: cxx
 * 实现类服务器端网络模块
 * @Date: 2018/6/11 15:18
 */
public class ServerImpl implements Server {
    private int port=9999;
    private Socket socket=null;
    private ServerSocket serverSocket=null;
    private InputStream is=null;
    private ObjectInputStream ois =null;
    private static final Logger log = new LoggerImpl();
    private static BackUP back = new BackUPImpl();

    @Override
    public void init(Properties prop) throws Exception {
        System.out.println("初始化server配置");
        System.out.println(prop.getProperty("port"));
        this.port=Integer.parseInt(prop.getProperty("port"));
    }
    /**
     * 负责接收客户端传 过来的数据
     * @return
     * @throws Exception
     */
    public void receive() throws Exception {
        serverSocket = new ServerSocket(port);
        log.debug("服务器已经开启，等待客户端连接...");
        try {
            while (true){
                socket = serverSocket.accept();
                Handler handler = new ServerImpl().new Handler(socket);
                handler.start();
            }
        }catch (Exception e){
            shutdown();
        }
    }

    class Handler extends Thread{
        private Socket socket=null;
        public Handler(Socket socket){
            this.socket=socket;
        }
        private Collection<Environment> collection;
        public Collection<Environment> getCollection() {
            return collection;
        }
        public void setCollection(Collection<Environment> collection) {
            this.collection = collection;
        }

        @Override
        public void run() {
            try {
                System.out.println("222");
                is = this.socket.getInputStream();
                ois = new ObjectInputStream(is);
                collection = (Collection<Environment>) ois.readObject();
                log.debug("数据接收完毕！");
                System.out.println(""+collection.size());
                //DBStore dbStore = new DBStoreImpl();
                log.debug("数据开始入库....");
                //开始加载备份文件，入库
                back = new BackUPImpl();
                Collection<Environment> co = (Collection<Environment>)back.load(SystemUtil.resourcePath + "serverback.txt", true);
                if (co!=null){
                    log.debug("服务器数据正在备份...");
                    collection.addAll(co);
                }
                DBStore d = new DBStoreImpl();
                d.saveDb(collection);
                log.debug("数据入库成功...");
            }catch (Exception e){
                log.debug("服务器发生异常，进行数据备份");
                try {
                    back.store(SystemUtil.resourcePath+"serverback.txt",collection,true);
                    log.debug("数据备份成功");
                } catch (Exception e1) {
                    log.debug("数据备份失败");
                }
            }
        }
    }

    public void shutdown() throws Exception {
        if (ois!=null){
            ois.close();
        }
        if (is!=null){
            is.close();
        }
        if (socket!=null){
            socket.close();
        }
        if (serverSocket!=null){
            serverSocket.close();
        }
        log.debug("资源关闭");
    }

    public static void main(String[] args) throws Exception {
        new ServerImpl().receive();
    }
}
