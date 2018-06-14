package com.briup.environment.util;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.gui.Login;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;
import com.briup.environment.server.ServerImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 配置实现类
 * @Author: cxx
 * @Date: 2018/6/13 17:36
 */
public class ConfigurationImpl implements Configuration {
    private static Properties prop=null;
    private Server server;
    private Logger log;
    private Client client;
    private BackUP backUP;
    private Gather gather;
    private Login login;
    private DBStore dbStore;

    public ConfigurationImpl(){
        try {
            prop=new Properties();
            //1.或去SAXParserFactory实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2.获取SAXparser实例
            SAXParser saxParser = null;
            saxParser = factory.newSAXParser();
            //创建Handel对象
            MySaxHandel handel = new MySaxHandel(prop);
            saxParser.parse(SystemUtil.resourcePath+"emdc.xml",handel);
            System.out.println("反射初始化模块");
            List<String> classList = (List<String>)prop.get("class");
            for (String s : classList) {
                Class<?> clazz = Class.forName(s);
                Object o = clazz.newInstance();
                Method init = clazz.getDeclaredMethod("init",Properties.class);
                Object invoke = init.invoke(o,prop);
                if (o instanceof Server){
                    server=(Server)o;
                }
                if (o instanceof Logger){
                    log = (Logger) o;
                }
                if (o instanceof Client){
                    client = (Client) o;
                }
                if (o instanceof DBStore){
                    dbStore = (DBStore) o;
                }
                if (o instanceof Gather){
                    gather = (Gather) o;
                }
                if (o instanceof BackUP){
                    backUP = (BackUP) o;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Logger getLogger() throws Exception {
        return log;
    }

    @Override
    public Server getServer() throws Exception {
        return server;
    }

    @Override
    public Client getClient() throws Exception {
        return client;
    }

    @Override
    public DBStore getDBStore() throws Exception {
        return dbStore;
    }

    @Override
    public Gather getGather() throws Exception {
        return gather;
    }

    @Override
    public BackUP getBackup() throws Exception {
        return backUP;
    }

    @Override
    public Login getLogin() throws Exception {
        return login;
    }

    public static void main(String[] args) throws Exception {
        ConfigurationImpl conf = new ConfigurationImpl();
        conf.getServer().init(prop);
    }

}


//解析类
class MySaxHandel extends DefaultHandler {
    private String tagName;//标记
    private String tagWho;//谁的标签
    private Properties prop;
    private List<String> classNameList;
    public MySaxHandel(Properties prop){
        this.prop=prop;
    }

    //遍历xml文件开始标签
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("---------------------sax解析开始---------------------");
        classNameList = new ArrayList<>();
    }

    //遍历xml文件结束标签
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("---------------------sax解析结束---------------------");
        prop.put("class",classNameList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "server":
            case "dbstore":
            case "client":
            case "gather":
            case "log":
            case "backup": {
                tagWho = qName;
                String classPath=attributes.getValue("class");
                classNameList.add(classPath);
                //prop.put("class",classPath);
                break;
            }
            default: {
                tagName = qName;
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "server":
            case "dbstore":
            case "client":
            case "gather":
            case "log":
            case "backup": {
                tagName = null;
                tagWho = null;
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start, length).trim();
        if (!value.equals("")) {
            //System.out.println("***********" + tagWho + "节点***********");
            //System.out.println(tagName + "：" + value);
            prop.put(tagName,value);
        }
    }
}
