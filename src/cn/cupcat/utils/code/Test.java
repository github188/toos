package cn.cupcat.utils.code;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 测试类
 */
public class Test {

    public static void main(String[] args) throws IOException, SQLException {


        Factory factory = new ModuleFactory();
        factory.createFile();//创建module文件
        factory = new LogicFactory();
        factory.createFile();//创建logic文件
        factory = new ModelFactory();
        factory.createFile();//创建model文件
        factory = new PageFactory();
        factory.createFile();//创建init、add、edit、detial页面文件
        ScriptFactory.execute();//将生成的module方法，添加到数据库




    }

    public static void sop(Object obj) {
        System.out.println(obj);
    }
}
