package com.joyintech.tams.valuation.common.interf.base.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * 对.gz压缩文件解压
 */
public class GZUtils {

    public static void unGzipFile(String sourcedir)
        throws IOException {
        //建立gzip压缩文件输入流
        FileInputStream fin = null;
        //建立gzip解压工作流
        GZIPInputStream gzin = null;
        //建立解压文件输出流
        FileOutputStream fout = null;
        String ouputfile = "";
        try {
            //建立gzip压缩文件输入流
            fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = sourcedir.substring(0,sourcedir.lastIndexOf('.'));
            fout = new FileOutputStream(ouputfile);

            int num;
            byte[] buf=new byte[1024];

            while ((num = gzin.read(buf,0,buf.length)) != -1)
            {
                fout.write(buf,0,num);
            }


        } catch (Exception ex){
            System.err.println(ex.toString());
        } finally {
            if (gzin != null) {
                try {
                    gzin.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fin != null) {
                try {
                    fin.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//    	try {
//    		unGzipFile("E:\\interf_file\\ZG\\20181220\\ValPrdInfoN_201812120_01.dat.gz");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
   
}
