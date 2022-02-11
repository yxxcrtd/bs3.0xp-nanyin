package com.joyintech.tams.exchange.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.Executors;

public class HttpTestUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpTestUtils.class);

    public static String doRequest(String httpUrl, String method, String content, int count) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：GET\POST
            connection.setRequestMethod(method);
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：30000毫秒
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // 发送请求
            connection.connect();
            if (Objects.nonNull(content)) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.write((content + "\"" + count + "\"}").getBytes(StandardCharsets.UTF_8));
                // 刷新、关闭
                out.flush();
                out.close();
            }
            // 通过connection连接，获取输入流
            logger.info("send request:{}", count);
            if (connection.getResponseCode() == 200) {
                logger.info("response:{}", count);
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } finally {
            // 关闭资源
            if (null != br) {
                br.close();
            }

            if (null != is) {
                is.close();
            }
            connection.disconnect();// 关闭远程连接
        }
        return result;
    }

    public static void main(String[] args) {
        String url = "http://joyin.gaoyusoft.com:8989/exchange/joyin/order?user_id=tj&_=1594704350310";
        String serviceUrl = "http://joyin.gaoyusoft.com:8091/joyin/order?user_id=tj&_=1594704350310";
        //WTSHARE202003271003 000002.SZ 28.29
        //WTSHARE202003271004 000026.SZ 12.29
        String str1 = "{\"instructionId\":\"WTSHARE202003271003\",\"portfolioId\":\"JYXTCSCP01\",\"userId\":\"tj\",\"username\":\"tj\",\"accountId\":\"1234:27004935\",\"businessFlag\":1,\"entrustProp\":\"0\",\"entrustBs\":1,\"stockCode\":\"000002.SZ\",\"entrustVolume\":700,\"entrustPrice\":28.02,\"algorithmId\":";
        for (int i = 0; i < 100; ++i) {
            int j = i;
            try {
                Executors.newSingleThreadExecutor().submit(() -> {
                    try {
//                        System.out.println("doRequest url:" + System.currentTimeMillis());
                        doRequest(url, "POST", str1, j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("doRequest end, wait for service call");
        try {
            Thread.sleep(10000);
            System.out.println("sleep end, service call");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str2 = "{\"instructionId\":\"WTSHARE202003271003\",\"portfolioId\":\"JYXTCSCP01\",\"userId\":\"tj\",\"username\":\"tj\",\"accountId\":\"1234:27004935\",\"businessFlag\":1,\"entrustProp\":\"0\",\"entrustBs\":1,\"stockCode\":\"000002.SZ\",\"entrustVolume\":700,\"entrustPrice\":28.03,\"algorithmId\":";
        for (int i = 100; i < 200; ++i) {
            int j = i;
            try {
                Executors.newSingleThreadExecutor().submit(() -> {
                    try {
//                        System.out.println("doRequest service:" + System.currentTimeMillis());
                        doRequest(serviceUrl, "POST", str2, j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("test end");

    }
}
