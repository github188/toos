package cn.cupcat.utils.file;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * Created by xy on 2017/10/19.
 * @author zxy
 * 生成二维码工具类
 */
public class QRCodeUtil {

    /**
     * 二维码生成器
     *
     * @param content content可以是一个url 也可以是文本内容
     * @return String       成功返回文件的base64字符串；失败返回null
     * @throws IOException
     */
    public static String generalQRCodeStr(String content) {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        String binary = null;
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    content, BarcodeFormat.QR_CODE, 200, 200, hints);

       /*    实现一： 输出图片到指定目录
            File outputFile = new File("d://1.jpg");
             MatrixToImageWriter.writeToFile(bitMatrix, "png", outputFile);

             */

            // 实现二：生成二维码图片并将图片转为二进制传递给前台
            // 1、读取文件转换为字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            BufferedImage image = toBufferedImage(bitMatrix);

            ImageIO.write(image, "jpg", out);
            byte[] bytes = out.toByteArray();

            // 2、将字节数组转为二进制
            BASE64Encoder encoder = new BASE64Encoder();
            binary = encoder.encodeBuffer(bytes).trim();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return binary;
    }

    /**
     * 二维码生成器
     * @param content content可以是一个url 也可以是文本内容
     * @param filePath  生成文件的路径，包含文件名称
     * @return boolean       成功true；失败false
     * @throws IOException
     */
    @SuppressWarnings({"unchecked", "rawtypes", "restriction"})
    public static boolean generalQRCodeFile(String content, String filePath) {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    content, BarcodeFormat.QR_CODE, 200, 200, hints);
            // 实现一： 输出图片到指定目录
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();//文件父级创建目录不存在，则创建
                file.createNewFile();//创建文件
            }

            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


   /**
    *  辅助方法
    *  生成图片
    *  */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

}
