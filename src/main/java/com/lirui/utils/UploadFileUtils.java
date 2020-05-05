package com.lirui.utils;

import cn.hutool.core.lang.UUID;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * 上传文件到服务器
 *
 * @author lirui
 * @date 2020/5/5 10:33
 */
public class UploadFileUtils {
    static ResponseBody upload(String url, String filePath, String fileName) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName,
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath)))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        return response.body();
    }


    public static void main(String[] args) throws IOException {
        try {
            String fileName = "e53c868ee9e8e7b28c424b56afe2066d.jpg";
            String filePath = "C:\\Users\\SIMPLE\\Pictures\\e53c868ee9e8e7b28c424b56afe2066d.jpg";
            String url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=X5N260h0qgviYzF53HDcPxapxvXhRn9tA3l_MHG7cPCFu6_v7VARbRxMierESlg2PkC6UvEfG1qJdmFF5myDSYqGGNPXOZulHr334bwa9LX5AegCwY_w4DXWI4Oqnf3fDtzQyQXm7GOyH15Ta25vEX2Xp7dL0DXmdnikrWpEUy6BOSlU9tySOLlwCxNxIgyz_uXA_lvFHTG8Haz1ylCaWw&type=image";
            System.out.println(upload(url, filePath, fileName).string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
