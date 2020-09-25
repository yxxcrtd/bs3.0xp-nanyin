package com.test.jixu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateDAOClass {
	public void test(String filepath, String outPutPath){
		File file=new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				StringBuffer buf = new StringBuffer(); 
				String interfaceName=filelist[i].replace(".java", "Dao");
				String className=filelist[i].replace(".java", "DaoImpl");
				String filename=filelist[i].replace(".", "DaoImpl.");
				String repoName= (new StringBuilder()).append(Character.toLowerCase(interfaceName.charAt(0))).append(interfaceName.substring(1)).toString();
				File newFile=new File(outPutPath+"/"+filename);
				
				buf=buf.append("package com.jixu.dao.intf.impl;");
				buf = buf.append("\r\n");
				buf = buf.append("\r\n");
				buf=buf.append("import org.springframework.stereotype.Repository;");
				buf = buf.append("\r\n");
				buf=buf.append("import com.jixu.common.func.dao.CommFuncDAOImpl;");
				buf = buf.append("\r\n");
				buf=buf.append("import com.jixu.dao.intf."+interfaceName+";");
				buf = buf.append("\r\n");
				buf = buf.append("\r\n");
				buf=buf.append("/**");
				buf=buf.append("\r\n");
				buf=buf.append(" * @ClassName 		"+className);
				buf=buf.append("\r\n");
				buf=buf.append(" * @author  		Pan Wei ");
				buf=buf.append("\r\n");
				buf=buf.append(" * @Date 			2018骞�10鏈�10鏃� 涓嬪崍23:29:03 	");
				buf=buf.append("\r\n");
				buf=buf.append(" * @Description		[]  ");
				buf=buf.append("\r\n");
				buf=buf.append(" */");
				buf=buf.append("\r\n");
				buf=buf.append("\r\n");
				
				buf=buf.append("@Repository(\""+repoName+"\")");
				buf=buf.append("\r\n");
				buf=buf.append("public class "+className+" extends CommFuncDAOImpl implements "+interfaceName+"{");
				buf=buf.append("\r\n");
				buf=buf.append("\r\n");
				buf=buf.append("}");
				
				try{
					FileOutputStream fos = new FileOutputStream(newFile); 
		            PrintWriter pw = new PrintWriter(fos); 
		            pw.write(buf.toString().toCharArray()); 
		            pw.flush(); 
		            pw.close(); 
				}catch(IOException ie){
					ie.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args){
		CreateDAOClass cdc=new CreateDAOClass();
		cdc.test("D:/PwWorkSpace/jixu/JixuCenter/jixu/src/main/java/com/orm/obj", 
				"D:/Test");
		
		
	}
}
