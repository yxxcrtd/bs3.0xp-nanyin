package com.test.jixu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ConvertGBK2UTF8 {
	
	public void getFilepath(String filepath) {
		File file=new File(filepath);
		File[] files=file.listFiles();
		for(File tmpFile:files) {
			if(tmpFile.isDirectory()) {
				getFilepath(tmpFile.getPath());
			}else if(tmpFile.getName().endsWith(".java") && !tmpFile.getName().equals("ConvertGBK2UTF8.java")){
				test(tmpFile);
			}
		}
	}
	
	public void test(File file) {
		try {
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis, "GBK"); 
	        BufferedReader br = new BufferedReader(isr);
	        
	        FileOutputStream fos = new FileOutputStream(file.getPath()+".tmp"); 
            OutputStreamWriter pw = new OutputStreamWriter(fos, "UTF-8"); 
           
	        
	        while(br.ready()) {
	        	pw.write(br.readLine());
	        	pw.write("\r\n");
	        }
	        
	        br.close();
	        isr.close();
	        pw.flush(); 
            pw.close();
            fos.close();
            
            String filepath=file.getPath();
            file.delete();
            
            File file2=new File(file.getPath()+".tmp");
            file2.renameTo(new File(filepath));
            
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConvertGBK2UTF8 cg=new ConvertGBK2UTF8();
		cg.getFilepath("D:\\PwWorkSpace\\jixu\\JoyinJixuSourceCode\\JixuCenter");
	}
}
