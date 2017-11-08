package cn.cupcat.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by xy on 2017/10/15.
 * 测试专用类
 */
public class Main {


    public static void main(String[] args) throws IOException {
        String str = "\\attachment\\admin\\20171101\\bc993a24f13745f499966227d6d714c9_u=2993396273,3023277058&fm=27&gp=0.jpg";
        System.out.println(str.replace("\\","/"));

    }




}
