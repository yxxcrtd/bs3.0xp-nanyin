/*
 * 文件名：HttpUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年2月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.interf.common.dto.HttpResult;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lihuadong
 * @version 1.0 2017年2月28日
 * @see HttpUtil
 * @since 1.0
 */
@Repository
public class HttpUtil {

    /**
     * Http json 状态：成功
     */
    public static final String HTTP_STATUS0 = "0";

    /**
     * Http json 状态：HTTP发送前异常
     */
    public static final String HTTP_STATUS1 = "1";

    /**
     * Http json 状态：HTTP发送中异常
     */
    public static final String HTTP_STATUS2 = "2";

    /**
     * Http json 状态：HTTP结果读取中异常
     */
    public static final String HTTP_STATUS3 = "3";

    /**
     * Http 提交方式
     */
    public static final String POST = "POST";

    /**
     * Http RequestProperty
     */
    public static final String REQUEST_PROPERTY1 = "Content-Type";

    /**
     * Http RequestProperty2
     */
    public static final String REQUEST_PROPERTY2 = "application/json";

    /**
     * Http json 返回 errMsg URL CHECK FAILURE
     */
    public static final String ERR_URL_CHECK = "URL CHECK FAILURE";

    /**
     * Http json 返回 errMsg ProtocolException Happened
     */
    public static final String ERR_PROTOCOL_EXCEPTION = "ProtocolException Happened";

    /**
     * Http json 返回 errMsg CONNECTION FAILURE
     */
    public static final String ERR_CONNECTION_EXCEPTION = "CONNECTION FAILURE";

    /**
     * Http json 返回 errMsg 写入json时发生异常
     */
    public static final String ERR_WRITE_JSON = "写入json时发生异常";

    /**
     * Http json 返回 errMsg 关闭输出流时发生异常
     */
    public static final String ERR_CLOSE_CONNECTION = "关闭输出流时发生异常";

    /**
     * Http json 返回 errMsg 不支持的编码格式
     */
    public static final String ERR_UNSUPPORTEDENCOD = "不支持的编码格式";

    /**
     * Http json 返回 errMsg 获取输入流异常
     */
    public static final String ERR_GET_INPUTSTREAM = "获取输入流异常";

    /**
     * Http json 返回 errMsg 关闭输入流时发生异常
     */
    public static final String ERR_CLOSE_INPUTSTREAM = "关闭输入流时发生异常";

    /**
     * Http json 返回 errMsg 参数check 异常
     */
    public static final String ERR_CHECK_PARAMETER = "参数check 异常";

    /**
     * 通过http 将Json数据发给核心
     * 
     * @param url ip地址
     * @param jsonString json字符串
     * @param seq 流水号
     * @param encode 编码方式
     * @return HttpResult 将Json数据发给核心的结果
     * @see
     * @since 1.0
     */
    public static HttpResult sendMessage(String url, String jsonString, String encode,
                                         String seq) {
        // http json 发挥结果
        HttpResult result = new HttpResult();
        if (checkParameter(url, jsonString, encode)) {
            result.setStatus(HTTP_STATUS1);
            result.setErrMsg(ERR_CHECK_PARAMETER);
        }
        // HttpURLConnection
        HttpURLConnection connection = null;
        // StringBuffer
        StringBuffer sbf = new StringBuffer();
        if (StringUtils.isEmpty(result.getErrMsg())) {
            // 获取 HttpURLConnection
            connection = getHttpURLConnection(url, result);
        }
        if (StringUtils.isEmpty(result.getErrMsg())) {
            writeJson(connection, jsonString, encode, result);
        }
        if (StringUtils.isEmpty(result.getErrMsg())) {
            readResult(connection, encode, sbf, result);
        }
        if (connection != null) {
            // 断开连接
            connection.disconnect();
        }
        if (StringUtils.isEmpty(result.getErrMsg())) {
            // 处理成功
            result.setStatus(HTTP_STATUS0);
            // 返回数据
            result.setReturnMsg(sbf.toString());
        }
        return result;
    }

    /**
     * 参数check
     * 
     * @param url ip地址
     * @param jsonString json字符串
     * @param encode 编码方式
     * @return check结果
     * @see
     * @since 1.0
     */
    private static Boolean checkParameter(String url, String jsonString, String encode) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(jsonString)
            || StringUtils.isEmpty(encode)) {
            return true;
        }

        return false;

    }

    /**
     * 获取 HttpURLConnection
     * 
     * @param url ip地址
     * @param result 将Json数据发给核心的结果
     * @return HttpURLConnection connection
     * @see
     * @since 1.0
     */
    private static HttpURLConnection getHttpURLConnection(String url, HttpResult result) {
        // URL
        URL urlConnection = null;
        // HttpURLConnection
        HttpURLConnection connection = null;
        try {
            // 新建URL
            urlConnection = new URL(url);
            // 获取HttpURLConnection
            connection = (HttpURLConnection)urlConnection.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // POST方式
            connection.setRequestMethod(POST);
            connection.setUseCaches(false);
            // connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty(REQUEST_PROPERTY1, REQUEST_PROPERTY2);
            // // 设置维持长连接
            // connection.setRequestProperty("Connection", "Keep-Alive");
            // // 设置文件字符集:
            // connection.setRequestProperty("Charset", "UTF-8");
            // // connection.setRequestProperty("Accept-Language", "en-us,en;q=0.5");
            // connection.setRequestProperty("User-Agent",
            // "Mozilla/5.0 (Windows NT //5.1)AppleWebKit/535.11 (KHTML, like Gecko)
            // Chrome/17.0.963.46 Safari/535.11");
            // 连接
            connection.connect();
        }
        // URL CHECK FAILURE
        catch (MalformedURLException e) {
            result.setStatus(HTTP_STATUS1);
            result.setErrMsg(ERR_URL_CHECK);
        }
        // ProtocolException Happened
        catch (ProtocolException e) {
            result.setStatus(HTTP_STATUS1);
            result.setErrMsg(ERR_PROTOCOL_EXCEPTION);
        }
        // CONNECTION FAILURE
        catch (IOException e) {
            result.setStatus(HTTP_STATUS1);
            result.setErrMsg(ERR_CONNECTION_EXCEPTION);
        }
        return connection;
    }

    /**
     * 获取 HttpURLConnection
     * 
     * @param connection HttpURLConnection
     * @param jsonString json字符串
     * @param encode 编码方式
     * @param result 将Json数据发给核心的结果
     * @see
     * @since 1.0
     */
    private static void writeJson(HttpURLConnection connection, String jsonString, String encode,
                                  HttpResult result) {
        // 输出流
        DataOutputStream out = null;
        try {
            // 获取输出流
            out = new DataOutputStream(connection.getOutputStream());
            out.write(jsonString.getBytes(encode));
            out.flush();
        }
        // 写入json时发生异常
        catch (IOException e) {
            result.setStatus(HTTP_STATUS2);
            result.setErrMsg(ERR_WRITE_JSON);
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                // 关闭流时发生异常
                catch (IOException e) {
                    result.setStatus(HTTP_STATUS2);
                    result.setErrMsg(ERR_CLOSE_CONNECTION);
                }
            }

        }
    }

    /**
     * 获取 HttpURLConnection
     * 
     * @param connection HttpURLConnection
     * @param encode 编码方式
     * @param sbf 返回数据
     * @param result 将Json数据发给核心的结果
     * @see
     * @since 1.0
     */
    private static void readResult(HttpURLConnection connection, String encode, StringBuffer sbf,
                                   HttpResult result) {
        // BufferedReader
        BufferedReader reader = null;
        String lines = null;
        try {
            // 新建输入流
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 逐行读取
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), encode);
                sbf.append(lines);

            }
        }
        // 不支持的编码格式
        catch (UnsupportedEncodingException e) {
            result.setStatus(HTTP_STATUS3);
            result.setErrMsg(ERR_UNSUPPORTEDENCOD);
        }
        // 获取读出流异常
        catch (IOException e) {
            result.setStatus(HTTP_STATUS3);
            result.setErrMsg(ERR_GET_INPUTSTREAM);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                // 关闭输入流时发生异常
                catch (IOException e) {
                    result.setStatus(HTTP_STATUS2);
                    result.setErrMsg(ERR_CLOSE_INPUTSTREAM);
                }
            }
        }
    }
}
