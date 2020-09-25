package com.joyintech.invest.interf.common.core.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientWebservice {

	//调用接口
	public static String send(String json ,String url) {
		OutputStream out = null;
		BufferedReader in = null;
		String result="";
		HttpURLConnection connection=null;
		try{
			
		
		//创建URL资源
		URL urlResource = new URL(url);
		
		//建立http连接
		connection = (HttpURLConnection)urlResource.openConnection();
		
		//设置允许输出
		connection.setDoOutput(true);
		
		//设置允许输入
		connection.setDoInput(true);
		
		//设置不用缓存
		connection.setUseCaches(false);
		
		//设置请求方式
		connection.setRequestMethod("POST");
		
		//设置文件字符集
		connection.setRequestProperty("Charset", "UTF-8");
		
		//设置维持长连接
		connection.setRequestProperty("Connection", "Keep-Alive");
		
		//设置文件类型
		connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		
		//连接超时时间
		connection.setConnectTimeout(30 * 1000);

		//响应时间
		connection.setReadTimeout(120 * 1000);
		
		//开始请求
		connection.connect();
		
		 // 获取connection对象对应的输出流
	    out = connection.getOutputStream();
		out.write(json.getBytes("UTF-8"));
        
     // flush输出流的缓冲
		out.flush();
        
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        
		} catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
        }  // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (connection != null) {
                	connection.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
		return result;
		
		
	}
}
