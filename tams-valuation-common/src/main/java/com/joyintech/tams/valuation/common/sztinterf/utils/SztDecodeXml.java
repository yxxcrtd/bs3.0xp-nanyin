package com.joyintech.tams.valuation.common.sztinterf.utils;

import com.joyintech.tams.valuation.common.sztinterf.constant.SztValConstant;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SztDecodeXml {
	
	/**
     * 加密加压
     * @param primStr
     * @return
     */
    public static String encodeZip(String primStr) {

        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        
        String decompressed = null;
        int start = primStr.lastIndexOf(SztValConstant.深圳通_接口文件头开始字段);
        int end = primStr.lastIndexOf(SztValConstant.深圳通_接口文件头结尾字段);
        if(start==-1 || end ==-1){
            return decompressed;
        }
        primStr = primStr.substring(start+4,end);
        if(StringUtils.isBlank(primStr)){
            return decompressed;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;

        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes(SztValConstant.深圳通_接口文件GBK编码));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (gzip != null) {
                try {
                    gzip.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BASE64Encoder().encode(out.toByteArray());
    }
    
    //只解压OUT里面的报文
    public static String decodeOutZip(String compressedStr) {
        String decompressed = null;
        int start = compressedStr.lastIndexOf(SztValConstant.深圳通_接口文件解析开始字段);
        int end = compressedStr.lastIndexOf(SztValConstant.深圳通_接口文件解析结尾字段);
        if(start==-1 || end ==-1){
            return decompressed;
        }
        compressedStr = compressedStr.substring(start+5,end);
        if(StringUtils.isBlank(compressedStr)){
            return decompressed;
        }
        //解压OUT里面报文内容
        decompressed = decodeZip(compressedStr);
        decompressed = SztValConstant.深圳通_接口文件头XML + SztValConstant.深圳通_接口文件解析开始字段
                + decompressed + SztValConstant.深圳通_接口文件解析结尾字段;
        return decompressed;
    }
    
    public static String decodeZip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;

        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString(SztValConstant.深圳通_接口文件GBK编码);
            int i = decompressed.indexOf(SztValConstant.深圳通_接口文件解析开始字段);
            if (i > 0) {
                decompressed = decompressed.substring(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return decompressed;
    }

}
