package com.test.jixu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {
	public void test(String filepath) {
		try {
			File file=new File(filepath);
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        String column="";
	        String tablename="";
	        while((line=br.readLine())!=null){
				line=line.trim();
				column="";
				if(line.contains("CREATE TABLE")) {
					tablename=line.substring(line.indexOf("\""), line.indexOf("\"", line.indexOf("\"")+1)+1);
				}else if(line.startsWith("\"")){
					column=line.substring(0, line.indexOf("\"", 1)+1);
					
					if(!column.equals("")) {
						if(column.contains("CREATE_TIME")) {
							buf = buf.append("comment on column "+tablename+"."+column+" is '创建时间';"); 
						}else if(column.contains("CREATE_DEPT")){
							buf = buf.append("comment on column "+tablename+"."+column+" is '创建部门';"); 
						}else if(column.contains("UPDATE_TIME")){
							buf = buf.append("comment on column "+tablename+"."+column+" is '更新时间';"); 
						}else if(column.contains("UPDATE_USER")){
							buf = buf.append("comment on column "+tablename+"."+column+" is '更新用户';"); 
						}else if(column.contains("CREATE_USER")){
							buf = buf.append("comment on column "+tablename+"."+column+" is '创建用户';"); 
						}else {
							buf = buf.append("comment on column "+tablename+"."+column+" is '';"); 
						}
						buf = buf.append("\r\n");
					}
				}
			}
	        buf = buf.append(line); 
    		buf = buf.append("\r\n");
    		br.close(); 
            FileOutputStream fos = new FileOutputStream("C:\\Users\\84900\\Desktop\\Test\\222.sql"); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Test test=new Test();
		test.test("C:\\Users\\84900\\Desktop\\Test\\111.sql");
	}
}
