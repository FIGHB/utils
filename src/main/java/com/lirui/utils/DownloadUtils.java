package com.lirui.utils;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
/**
 * 下载工具类，根据 url下载相应的资源
 *
 * @author lirui
 * @date 2020/5/5 9:59
 */
public class DownloadUtils {
    /**
     * 下载文件到本地
     *
     * @param urlString
     * @param filename
     * @throws Exception
     * @author sun
     * @date 2018年3月25日 上午11:01:05
     */
    public static void download(String urlString, String filename)
            throws Exception {
        URL url = new URL(urlString);// 构造URL
        URLConnection con = url.openConnection();// 打开连接
        InputStream is = con.getInputStream();// 输入流
        String code = con.getHeaderField("Content-Encoding");
        if ((null != code) && code.equals("gzip")) {
            GZIPInputStream gis = new GZIPInputStream(is);
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = gis.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            gis.close();
            os.close();
            is.close();
        } else {
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        }
    }

    public static void main(String[] args) {
        try {
            String url = "http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg";
            String[] split = url.split("/");
            String str = split[split.length - 1];
            download(url, "C:\\Users\\SIMPLE\\Pictures\\" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

