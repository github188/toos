package cn.cupcat.utils.file;

import system.lib.AppFileHandle;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by xy on 2017/10/20.
 */
public class FileUtil {


    /**
     * 说明：删除文件或者文件夹下的所有文件（包括文件夹本身）
     *
     * @param file
     * @return boolean
     */
    public static boolean deleteFileOrDir(File file) {
        boolean flag = false;
        try {
            if (file.exists()) {
                if (file.isFile()) {//文件，直接删除
                    flag = file.delete();
                } else {//文件夹，删除文件夹下所有文件
                    //得到file文件夹下面的所有文件
                    File[] files = file.listFiles();
                    for (File file1 : files) {
                        deleteFileOrDir(file1);
                    }
                    file.delete();//最后删除本身
                    flag = true;
                }
            } else {
                System.out.println("删除失败！" + file.getAbsolutePath() + "文件或者文件夹不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 删除文件或者文件夹下的所有文件（包括文件夹本身）
     *
     * @param filePath
     * @return boolean
     */
    public static boolean deleteFileOrDir(String filePath) {
        File file = new File(filePath);
        return deleteFileOrDir(file);
    }

    /**
     * 说明：只是清空文件夹内容（文件夹下的所有文件），文件夹仍然保留;
     * 若传入文件夹不不存在返回false；
     * 若传入文件则返回false
     * <p>
     * 功能： 清空文件夹；
     *
     * @param dirPath
     */
    public static boolean emptyFileDir(String dirPath) {
        File dir = new File(dirPath);
        return emptyFileDir(dir);
    }

    /**
     * 说明：只是清空文件夹内容（文件夹下的所有文件），文件夹仍然保留;
     * 若传入文件夹不不存在返回false；
     * 若传入文件则返回false
     * <p>
     * 功能： 清空文件夹；
     *
     * @param dir 文件夹
     */
    public static boolean emptyFileDir(File dir) {
        boolean flag = false;
        if (dir.exists() && dir.isDirectory()) {
            boolean b = true;
            File[] files = dir.listFiles();
            for (File file1 : files) {
                b = b && deleteFileOrDir(file1);
            }
            flag = b;
        }
        return flag;
    }

    /**
     * 将内容写入到文件中
     *
     * @param content  写入内容
     * @param filePath 文件保存路径
     */
    public static void writeFile(String content, String filePath) throws IOException {
        //判断路径是否有文件夹，或者文件；  若没有-创建； 若有：覆盖写入；
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();//文件父级创建目录不存在，则创建
            file.createNewFile();//创建文件
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        //将写入内容转换为byte[]
        byte[] writeContent = content.getBytes();
        bufferedOutputStream.write(writeContent);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }


    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路径，不包含压缩文件名
     * @param fileName       :压缩后文件的名称
     * @return
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false; //表示是否压缩成功
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            File zipFile = new File(zipFilePath + "/" + fileName);
            if (!zipFile.getParentFile().exists()) {
                zipFile.getParentFile().mkdirs();
            } else {
                //清空文件夹
                FileUtil.emptyFileDir(zipFile.getParentFile());
            }
            zipFile.createNewFile();
            File[] sourceFiles = sourceFile.listFiles();
            if (null == sourceFiles || sourceFiles.length < 1) {
                System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
            } else {
                fos = new FileOutputStream(zipFile);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                byte[] bufs = new byte[1024 * 10];
                for (int i = 0; i < sourceFiles.length; i++) {
                    //创建ZIP实体，并添加进压缩包
                    ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                    zos.putNextEntry(zipEntry);
                    //读取待压缩的文件并写进压缩包里
                    fis = new FileInputStream(sourceFiles[i]);
                    bis = new BufferedInputStream(fis, 1024 * 10);
                    int read = 0;
                    while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                        zos.write(bufs, 0, read);
                    }
                }
                flag = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭流
            try {
                if (null != bis) bis.close();
                if (null != zos) zos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return flag;
    }

    /**
     *  上传保存文件
     * */
    public static long saveFile(String filePath,AppFileHandle fileHandler){



        return 1L;
    }

}
