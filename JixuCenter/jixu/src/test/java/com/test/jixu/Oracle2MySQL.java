package com.test.jixu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Oracle2MySQL {
	public void test1(String filepath){
		File file=new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);//reports(Daily Source)
				if (readfile.isDirectory()) {
					test1(filepath + "/" + filelist[i]);
				}else{
					try{
						FileInputStream fis = new FileInputStream(readfile); 
			            InputStreamReader isr = new InputStreamReader(fis); 
			            BufferedReader br = new BufferedReader(isr); 
			            StringBuffer buf = new StringBuffer(); 
			            String line=null;
						while((line=br.readLine())!=null){
							if(line.contains("javax.persistence.SequenceGenerator"))
								continue;
							else if(line.contains("sequenceName")){
								line=br.readLine();
								if(line.contains("sequence")){
									line="	@GeneratedValue(strategy = GenerationType.IDENTITY)";
								}
			            	}
			            	buf = buf.append(line); 
			            	buf = buf.append("\r\n");
			            }
						br.close(); 
			            FileOutputStream fos = new FileOutputStream(readfile); 
			            PrintWriter pw = new PrintWriter(fos); 
			            pw.write(buf.toString().toCharArray()); 
			            pw.flush(); 
			            pw.close(); 
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		Oracle2MySQL t=new Oracle2MySQL();
		//String filepath="D:\\PwWorkSpace\\jixu\\JixuCenter\\jixu\\src\\main\\java\\com\\orm\\obj";
		String filepath="D:\\PwWorkSpace\\jixu\\JixuCenter\\jixu\\src\\main\\java\\com\\orm\\obj";
		t.test1(filepath);
	}
}
