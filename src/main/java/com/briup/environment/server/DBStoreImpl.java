package com.briup.environment.server;

import com.briup.environment.bean.Environment;
import com.briup.environment.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: cxx
 * @Date: 2018/6/12 9:54
 */
public class DBStoreImpl implements DBStore {
    private String driver="oracle.jdbc.driver.OracleDriver";
    private String url="jdbc:oracle:thin:@localhost:1521:XE";
    private String username="sa";
    private String password="123456";
    private Connection conn;
    private PreparedStatement ps;
    private int batchSize = 500;
    private static final Logger log = new LoggerImpl();
    private BackUP back = new BackUPImpl();

    @Override
    public void init(Properties prop) throws Exception {
        this.driver=prop.getProperty("driver");
        this.url=prop.getProperty("url");
        this.username=prop.getProperty("username");
        this.password=prop.getProperty("password");
    }

    public void saveDb(Collection<Environment> coll) throws Exception {
        Class.forName(driver);
        conn = DriverManager.getConnection(url,username,password);
        int count=0;
        int day=0;
        log.debug("加载数据库端备份文件");
        Collection<Environment> co = (Collection<Environment>)back.load(SystemUtil.resourcePath + "dbback.txt", true);
        if (co!=null){
            log.debug("服务器数据正在备份...");
            coll.addAll(co);
        }
        try {
            for (Environment e : coll) {
                /**
                 * 创建ps对象2种情况
                 * 1.第一次ps=null 进入for循环
                 * 2.当日期发送变化的时候
                 * 比如：1:300   2:200
                 * 这时没达到批处理大小，要自己提交
                 */
                if (ps == null || day != e.getGather_date().getDay()) {
                    day = e.getGather_date().getDate();
                    if (ps != null) {
                        //处理前一天保留的数据
                        ps.executeBatch();
                        ps.clearBatch();
                        //关闭ps,构建新的sql语句ps对象
                        ps.close();
                    }
                    String sql = "insert into e_detail_" + day + " values(?,?,?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(sql);
                }
                ps.setString(1, e.getName());
                ps.setString(2, e.getSrcId());
                ps.setString(3, e.getDesId());
                ps.setString(4, e.getSersorAddress());
                ps.setInt(5, e.getCount());
                ps.setString(6, e.getCmd());
                ps.setInt(7, e.getStatus());
                ps.setFloat(8, e.getData());
                ps.setTimestamp(9, e.getGather_date());
                ps.addBatch();
                count++;
                if (count % batchSize == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
                if (ps != null) {
                    ps.executeBatch();
                    ps.clearBatch();
                    ps.close();
                }
            }
        }catch (Exception e){
            log.debug("入库失败!进行备份");
            try {
                back.store(SystemUtil.resourcePath+"dbback.txt",coll,true);
                log.debug("数据备份成功");
            } catch (Exception e1) {
                log.debug("数据备份失败");
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =sdf.parse(t.toString());
        System.out.println(sdf.format(date).toString());
    }
}
