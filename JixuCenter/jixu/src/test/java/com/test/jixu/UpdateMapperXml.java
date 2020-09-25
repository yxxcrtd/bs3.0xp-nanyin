package com.test.jixu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName 		UpdateMapperXml
 * @author  		Pan Wei 
 * @Date 			2018年10月15日 上午10:15:14 	
 * @Description		[]  
 * @return  
 */

public class UpdateMapperXml {
	
	public void updateFile(String path1, String path2){
		
		//xml文件
		/*File file1=new File(path1);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				filename=filename.replace("Mapper.xml", "");
				xmlFileHM.put(filename.toLowerCase(), filename);
			}
		}*/
		
		File file2=new File(path2);
		if (file2.isDirectory()) {
			String[] filelist = file2.list();
			for(String filename:filelist){
				HashMap<String, String> valNameHM=new HashMap<String, String>();
				File file=new File(path2+"/"+filename);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
					while((line=br.readLine())!=null){
						line=line.trim();
						if(line.startsWith("private") && !line.contains("serialVersionUID")){
							line=line.substring(0, line.indexOf(";"));
							String[] valsArr=line.split(" ");
							if(valsArr.length==3){
								valNameHM.put(valsArr[2].toLowerCase(), valsArr[2]);
							}else{
								System.out.println(filename);
								System.out.println(line);
							}
						}
					}
					
					filename=filename.replace(".java", ".xml");
					updateXmlFile2(path1+"/"+filename, valNameHM);
					br.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
				
			}
		}
		
		
	}

	public void updateXmlFile1(String filepath, HashMap<String, String> pojoValsHM){
		File file=new File(filepath);
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        HashMap<String, String> needUpdateName=new HashMap<String, String>();
	        while((line=br.readLine())!=null){
	        	if(line.contains("<result property=")){
	        		String valname=line.substring(line.indexOf("\"")+1, line.length());
	        		valname=valname.substring(0, valname.indexOf("\""));
	        		String corName=pojoValsHM.get(valname.toLowerCase());
	        		if(!valname.equals(corName)){
	        			if(corName!=null){
	        				line=line.replace(valname, corName);
	        				needUpdateName.put(valname, corName);
	        			}
	        			System.out.println(filepath);
	        			System.out.println(valname + "       :        " + corName);
	        		}
	        	}else if(line.contains("#{")){
	        		String valname=line.substring(line.indexOf("#{")+2, line.length());
	        		valname=valname.substring(0, valname.indexOf(","));
	        		if(needUpdateName.containsKey(valname)){
	        			line=line.replace(valname, needUpdateName.get(valname));
	        		}
	        	}
	        	
	        	buf = buf.append(line); 
            	buf = buf.append("\r\n");
	        }
	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(filepath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	//显示不同字段的信息
	public void updateXmlFile2(String filepath, HashMap<String, String> pojoValsHM){
		File file=new File(filepath);
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        HashMap<String, String> needUpdateName=new HashMap<String, String>();
	        while((line=br.readLine())!=null){
	        	if(line.contains("<result property=")){
	        		String valname=line.substring(line.indexOf("\"")+1, line.length());
	        		valname=valname.substring(0, valname.indexOf("\""));
	        		String corName=pojoValsHM.get(valname.toLowerCase());
	        		if(!valname.equals(corName)){
	        			if(corName==null){
	        				System.out.println(filepath+ ":   "+valname + "       :        " + corName);
	        				/*line=line.replace(valname, corName);
	        				needUpdateName.put(valname, corName);*/
	        			}
	        		}
	        	}else if(line.contains("#{")){
	        		String valname=line.substring(line.indexOf("#{")+2, line.length());
	        		valname=valname.substring(0, valname.indexOf(","));
	        		if(needUpdateName.containsKey(valname)){
	        			line=line.replace(valname, needUpdateName.get(valname));
	        		}
	        	}
	        	
	        	buf = buf.append(line); 
            	buf = buf.append("\r\n");
	        }
	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(filepath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	/**
	 * 更改数据库字段和变量不相同的信息
	 * @param path1
	 * @param path2
	 */
	public void updateFile1(String path1, String path2){
		//xml文件
		/*File file1=new File(path1);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				filename=filename.replace("Mapper.xml", "");
				xmlFileHM.put(filename.toLowerCase(), filename);
			}
		}*/
		
		File file2=new File(path2);
		if (file2.isDirectory()) {
			String[] filelist = file2.list();
			for(String filename:filelist){
				HashMap<String, String> valNameHM=new HashMap<String, String>();
				File file=new File(path2+"/"+filename);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
					while((line=br.readLine())!=null){
						line=line.trim();
						String tmpLine=line.replaceAll(" ", "");
						if(tmpLine.contains("@Column(name=") || tmpLine.contains("@JoinColumn(name=")){
							String colname=tmpLine.substring(tmpLine.indexOf("\"")+1, tmpLine.length());
							colname=colname.substring(0, colname.indexOf("\""));
							colname=colname.trim();
							
							while((line=br.readLine())!=null){
								if(line.contains("return"))
									break;
							}
							String varName=line.substring(line.indexOf("return")+7, line.length());
							varName=varName.substring(0, varName.indexOf(";"));
							varName=varName.trim();
							valNameHM.put(colname.toLowerCase(), varName);
						}
					}
					
					filename=filename.replace(".java", ".xml");
					updateXmlFile3(path1+"/"+filename, valNameHM);
					br.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
				
			}
		}
		
		
	}
	
	public void updateXmlFile3(String filepath, HashMap<String, String> pojoValsHM){
		File file=new File(filepath);
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        HashMap<String, String> needUpdateName=new HashMap<String, String>();
	        List<String> deleteList=new ArrayList<String>();
	        while((line=br.readLine())!=null){
	        	if(line.contains("<result property=")){
	        		String valname=line.substring(line.indexOf("\"")+1, line.length());
	        		valname=valname.substring(0, valname.indexOf("\""));
	        		
	        		String colname=line.substring(line.indexOf("column=\"")+8, line.length());
	        		colname=colname.substring(0, colname.indexOf("\"")).toLowerCase();
	        		
	        		if(pojoValsHM.containsKey(colname)){
	        			String corname=pojoValsHM.get(colname);
	        			if(!corname.equals(valname)){
	        				line=line.replaceFirst(valname, corname);
	        				needUpdateName.put(valname, corname);
	        			}
	        		}else{
	        			deleteList.add(colname);
	        			deleteList.add(valname);
	        		}
	        	}else if(line.contains("#{")){
	        		String valname=line.substring(line.indexOf("#{")+2, line.length());
	        		valname=valname.substring(0, valname.indexOf(","));
	        		if(needUpdateName.containsKey(valname)){
	        			line=line.replaceFirst(valname, needUpdateName.get(valname));
	        		}
	        	}
	        	
	        	boolean addflag=true;
	        	for(String delStr:deleteList){
	        		if(line.toLowerCase().contains(delStr.toLowerCase())){
	        			addflag=false;
	        		}
	        	}
	        	if(addflag){
	        		buf = buf.append(line); 
	        		buf = buf.append("\r\n");
	        	}
	        }
	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(filepath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	
	/**
	 * many to one更改
	 * @param path1
	 * @param path2
	 */
	public void updateFile2(String path1, String path2){
		//xml文件
		/*File file1=new File(path1);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				filename=filename.replace("Mapper.xml", "");
				xmlFileHM.put(filename.toLowerCase(), filename);
			}
		}*/
		
		File file2=new File(path2);
		if (file2.isDirectory()) {
			String[] filelist = file2.list();
			for(String filename:filelist){
				HashMap<String, String> valNameHM=new HashMap<String, String>();
				File file=new File(path2+"/"+filename);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
					while((line=br.readLine())!=null){
						line=line.trim();
						String tmpLine=line.replaceAll(" ", "");
						if(tmpLine.contains("@JoinColumn(name=")){
							String colname=tmpLine.substring(tmpLine.indexOf("\"")+1, tmpLine.length());
							colname=colname.substring(0, colname.indexOf("\""));
							colname=colname.trim();
							
							String className="";
							while((line=br.readLine())!=null){
								if(line.contains("public")){
									className=line.replace("public", "");
									className=className.trim();
									className=className.split(" ")[0];
								}else if(line.contains("return"))
									break;
							}
							String varName=line.substring(line.indexOf("return")+7, line.length());
							varName=varName.substring(0, varName.indexOf(";"));
							varName=varName.trim();
							valNameHM.put(varName, className);
						}
					}
					
					filename=filename.replace(".java", ".xml");
					updateXmlFile4(path1+"/"+filename, valNameHM, path2);
					br.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
				
			}
		}
	}
	
	public void updateXmlFile4(String filepath, HashMap<String, String> pojoValsHM, String pojoFolder){
		File file=new File(filepath);
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        HashMap<String, String> needUpdateName=new HashMap<String, String>();
	        List<String> assList=new ArrayList<String>();
	        while((line=br.readLine())!=null){
	        	boolean addflag=true;
	        	if(line.contains("<result property=")){
	        		String valname=line.substring(line.indexOf("\"")+1, line.length());
	        		valname=valname.substring(0, valname.indexOf("\""));
	        		
	        		if(pojoValsHM.containsKey(valname)){
	        			addflag=false;
	        			line=line.replace("result", "association");
	        			line=line.replace("jdbcType", "javaType");
	        			String jdbctype=line.substring(line.indexOf("javaType=")+9,line.length());
	        			jdbctype=jdbctype.substring(0, jdbctype.lastIndexOf("\"")+1);
	        			line=line.replace(jdbctype, "\"com.orm.obj."+pojoValsHM.get(valname)+"\"");
	        			String selectStr=" select=\"com.jixu.dao.intf."+pojoValsHM.get(valname)+"Dao.find\"/";
	        			line=line.replace("/", selectStr);
	        			assList.add(line);
	        			String pid=returnPrimaryPojoID(pojoFolder+"/"+pojoValsHM.get(valname)+".java");
	        			
	        			needUpdateName.put(valname, pid);
	        		}
	        	}else if(line.contains("</resultMap>")){
	        		for(String assLine:assList){
	        			buf = buf.append(assLine); 
		        		buf = buf.append("\r\n");
	        		}
	        	}
	        	
	        	for(Map.Entry<String, String> entry:needUpdateName.entrySet()){
	        		if(line.contains("#{"+entry.getKey())){
	        			line=line.replace("#{"+entry.getKey(), "#{"+entry.getKey()+"."+entry.getValue());
	        			break;
	        		}
	        	}
	        	
	        	if(addflag){
	        		buf = buf.append(line); 
	        		buf = buf.append("\r\n");
	        	}
	        }
	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(filepath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public String returnPrimaryPojoID(String filepath){
		File file=new File(filepath);
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        String line=null;
	        boolean readFlag=false;
			while((line=br.readLine())!=null){
				line=line.trim();
				String tmpLine=line.replaceAll(" ", "");
				if(tmpLine.contains("@GeneratedValue")){
					readFlag=true;
				}else if(readFlag){
					if(line.contains("return")){
						String varName=line.substring(line.indexOf("return")+7, line.length());
						varName=varName.substring(0, varName.indexOf(";"));
						varName=varName.trim();
						br.close();
						return varName;
					}
				}
			}
			br.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 修改xml文件里的大小写类
	 * @param path1
	 * @param path2
	 */
	public void updateFile3(String xmlFile, String path2){
		//xml文件
		File file1=new File(xmlFile);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				String fileFullName=xmlFile+"/"+filename;
				filename=filename.replace(".xml", "");
				
				File file=new File(fileFullName);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
			        
			        StringBuffer buf = new StringBuffer(); 
			        
					while((line=br.readLine())!=null){
						String tmpLine=line;
						if(tmpLine.contains("parameterType")){
							if(tmpLine.toLowerCase().contains(filename.toLowerCase())){
								tmpLine=tmpLine.substring(tmpLine.indexOf("parameterType=\"com.orm.obj.")+27, tmpLine.length());
								tmpLine=tmpLine.substring(0, tmpLine.indexOf("\""));
								line=line.replace("com.orm.obj."+tmpLine, "com.orm.obj."+filename);
							}
						}else if(tmpLine.contains("resultMap type=")){
							if(tmpLine.toLowerCase().contains(filename.toLowerCase())){
								tmpLine=tmpLine.substring(tmpLine.indexOf("com.orm.obj.")+12, tmpLine.length());
								tmpLine=tmpLine.substring(0, tmpLine.indexOf("\""));
								line=line.replace("com.orm.obj."+tmpLine, "com.orm.obj."+filename);
							}
						}else if(tmpLine.contains("<mapper namespace")){
							if(tmpLine.toLowerCase().contains(filename.toLowerCase())){
								System.out.println(fileFullName);
								tmpLine=tmpLine.substring(tmpLine.indexOf("com.jixu.dao.mapper.")+20, tmpLine.length());
								tmpLine=tmpLine.substring(0, tmpLine.indexOf("Dao\""));
								line=line.replace("com.jixu.dao.mapper."+tmpLine, "com.jixu.dao.mapper."+filename);
							}
						}
						
						buf = buf.append(line); 
		        		buf = buf.append("\r\n");
					}
					
					br.close(); 
		            FileOutputStream fos = new FileOutputStream(fileFullName); 
		            OutputStreamWriter pw = new OutputStreamWriter(fos); 
		            pw.write(buf.toString().toCharArray()); 
		            pw.flush(); 
		            pw.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
				
			}
		}
	}
	
	/**
	 * 修改xml文件里的conditionQuery.core
	 * @param path1
	 * @param path2
	 */
	public void updateFile4(String xmlFile, String path2){
		//xml文件
		File file1=new File(xmlFile);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				String fileFullName=xmlFile+"/"+filename;
				filename=filename.replace(".xml", "");
				
				File file=new File(fileFullName);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
			        
			        StringBuffer buf = new StringBuffer(); 
			        boolean delFlag=false;
			        int count=0;
					while((line=br.readLine())!=null){
						if(count==2)
							delFlag=false;
						
						if(line.contains("<select id=\"query\"")){
							delFlag=true;
						}else if(line.contains("</select>")){
							count++;
						}
						if(!delFlag){
							buf = buf.append(line); 
							buf = buf.append("\r\n");
						}
					}
					
					br.close(); 
		            FileOutputStream fos = new FileOutputStream(fileFullName); 
		            OutputStreamWriter pw = new OutputStreamWriter(fos); 
		            pw.write(buf.toString().toCharArray()); 
		            pw.flush(); 
		            pw.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * one to many更改
	 * @param path1
	 * @param path2
	 */
	public void updateFile5(String xmlFolder, String pojoFolder){
		
		File file2=new File(pojoFolder);
		if (file2.isDirectory()) {
			String[] filelist = file2.list();
			for(String filename:filelist){
				HashMap<String, String> valNameHM=new HashMap<String, String>();
				File file=new File(pojoFolder+"/"+filename);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
					while((line=br.readLine())!=null){
						line=line.trim();
						String tmpLine=line.replaceAll(" ", "");
						if(tmpLine.contains("private Set<")){
							String colname=tmpLine.substring(tmpLine.indexOf("\"")+1, tmpLine.length());
							colname=colname.substring(0, colname.indexOf("\""));
							colname=colname.trim();
							
							String className="";
							while((line=br.readLine())!=null){
								if(line.contains("public")){
									className=line.replace("public", "");
									className=className.trim();
									className=className.split(" ")[0];
								}else if(line.contains("return"))
									break;
							}
							String varName=line.substring(line.indexOf("return")+7, line.length());
							varName=varName.substring(0, varName.indexOf(";"));
							varName=varName.trim();
							valNameHM.put(varName, className);
						}
					}
					
					filename=filename.replace(".java", ".xml");
					updateXmlFile4(xmlFolder+"/"+filename, valNameHM, pojoFolder);
					br.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
				
			}
		}
	}
	
	
	/**
	 * dao增加注解
	 * @param path1
	 * @param path2
	 */
	public void updateDaoFile(String daoFile){
		//xml文件
		File file1=new File(daoFile);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				String fileFullName=daoFile+"/"+filename;
				
				File file=new File(fileFullName);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
			        
			        StringBuffer buf = new StringBuffer(); 
			        boolean addflag=false;
			        
					while((line=br.readLine())!=null){
						if(line.contains("public") && !line.contains("public interface")){
							String line1=line.substring(0, line.indexOf("(")+1);
							String line2=line.substring(line.indexOf("(")+1, line.indexOf(")")).trim();
							String line3=line.substring(line.indexOf(")"), line.length());
							
							if(!line2.equals("")){
								String line2Tmp="";
								String[] paramsArrs=line2.split(",");
								for(String paramArr:paramsArrs){
									String[] params=paramArr.trim().split(" ");
									if(params.length==2){
										line2Tmp=line2Tmp+"@Param(\""+params[1]+"\")"+paramArr+", ";
									}else{
										System.out.println(fileFullName);
										System.out.println(line1);
									}
								}
								
								line2Tmp=line2Tmp.substring(0, line2Tmp.length()-2);
								line=line1+line2Tmp+line3;
							}
						}else if(line.contains("import com.jixu") && !addflag){
							buf.append("import org.apache.ibatis.annotations.Param;");
							buf = buf.append("\r\n");
							addflag=true;
						}
						
						buf = buf.append(line); 
						buf = buf.append("\r\n");
					}
					
					
					br.close(); 
		            FileOutputStream fos = new FileOutputStream(fileFullName); 
		            OutputStreamWriter pw = new OutputStreamWriter(fos); 
		            pw.write(buf.toString().toCharArray()); 
		            pw.flush(); 
		            pw.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * pojo增加注解
	 * @param path1
	 * @param path2
	 */
	public void addXmlFunctionByDao(String daoFile, String xmlFile){
		//xml文件
		File file1=new File(daoFile);
		if (file1.isDirectory()) {
			String[] filelist = file1.list();
			for(String filename:filelist){
				String fileFullName=daoFile+"/"+filename;
				String className=filename.replace("Dao.java", ".xml");
				File file=new File(fileFullName);
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
			        
					while((line=br.readLine())!=null){
						if(line.contains("public") && !line.contains("public interface")){
							String line1=line.substring(0, line.indexOf("(")).trim();
							line1=line1.substring(line1.lastIndexOf(" ")+1, line1.length());
							addXmlFunction(line1, xmlFile+"/"+className);
						}
					}
					
					
					br.close(); 
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
			}
		}
	}
	
	public void addXmlFunction(String functionName, String xmlFilePath){
		File file=new File(xmlFilePath);
		if(!file.exists())
			return;
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        while((line=br.readLine())!=null){
	        	if(line.contains("</mapper>")){
	        		buf = buf.append("\r\n");
	        		buf = buf.append("	<select id=\""+functionName+"\">");
	        		buf = buf.append("\r\n");
	        		buf = buf.append("		<![CDATA[");
	        		buf = buf.append("\r\n");
	        		buf = buf.append("		]]>");
	        		buf = buf.append("\r\n");
	        		buf = buf.append("	</select>");
	        		buf = buf.append("\r\n");
	        	}
	        	buf = buf.append(line); 
	        	buf = buf.append("\r\n");
	        }	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(xmlFilePath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	/**
	 * many to one更改
	 * @param path1
	 * @param path2
	 */
	public void updateOne2Many(String xmlFileFolder, String pojoPath){
		
		File file2=new File(pojoPath);
		if (file2.isDirectory()) {
			String[] filelist = file2.list();
			for(String filename:filelist){
				HashMap<String, String> valNameHM=new HashMap<String, String>();
				File file=new File(pojoPath+"/"+filename);
				String classname=filename.replace(".java", "");
				try{
					FileInputStream fis = new FileInputStream(file); 
					InputStreamReader isr = new InputStreamReader(fis); 
			        BufferedReader br = new BufferedReader(isr);
			        String line=null;
					while((line=br.readLine())!=null){
						line=line.trim();
						if(line.contains("private Set<")){
							String mapClassName=line.substring(line.indexOf("<")+1, line.length());
							mapClassName=mapClassName.substring(0, mapClassName.indexOf(">"));
							
							String varName=line.substring(line.lastIndexOf(" ")+1, line.length()-1);
							
							String colName=fetchColumnName(pojoPath+"/"+mapClassName+".java", classname);
							
							if(!colName.equals("")){
								String upVarName=varName.substring(0, 1).toUpperCase()+varName.substring(1, varName.length());
								
								addXmlCollection(varName, colName, mapClassName, mapClassName+"Dao", "get"+upVarName+"ListBy"+classname+"ID", xmlFileFolder+"/"+classname+".xml");
							
								addXmlCollectionImplFunc("get"+upVarName+"ListBy"+classname+"ID", mapClassName, colName, xmlFileFolder+"/"+mapClassName+".xml");
							}
						}
					}
					
					filename=filename.replace(".java", ".xml");
					br.close();
				}catch(FileNotFoundException fne){
					fne.printStackTrace();
				}catch(IOException ie){
					ie.printStackTrace();
				}
				
			}
		}
	}
	
	public String fetchColumnName(String pojoPath, String className){
		File file=new File(pojoPath);
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        String line=null;
	        String varName="";
			while((line=br.readLine())!=null){
				if(line.contains(" "+className+ " ")){
					varName=line.substring(line.lastIndexOf(" ")+1, line.length()-1);
				}
				
				if(!varName.equals("")){
					if(line.contains("@JoinColumn(")){
						String colname=line.substring(line.indexOf("\"")+1, line.length());
						colname=colname.substring(0, colname.indexOf("\""));
						colname=colname.trim();
						
						
						while((line=br.readLine())!=null){
							if(line.contains("return"))
								break;
						}
						String varName1=line.substring(line.indexOf("return")+7, line.length()).trim();
						varName1=varName1.substring(0, varName1.indexOf(";"));
						varName1=varName1.trim();
						
						if(varName1.equals(varName)){
							br.close();
							return colname;
						}
					}
				}
			}
			
			br.close();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		return "";
	}
	
	public void addXmlCollection(String propname, String colname, String ofType,  String daoClass, String funName, String xmlFilePath){
		File file=new File(xmlFilePath);
		if(!file.exists())
			return;
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        while((line=br.readLine())!=null){
	        	if(line.contains("</resultMap>")){
	        		buf = buf.append("	<collection property=\""+propname+"\" javaType=\"java.util.Set\" column=\""+colname+"\" ofType=\"com.orm.obj."+ofType+"\" select=\"com.jixu.dao.intf."+daoClass+"."+funName+"\"/>");
	        		buf = buf.append("\r\n");
	        	}
	        	buf = buf.append(line); 
	        	buf = buf.append("\r\n");
	        }	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(xmlFilePath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos, "UTF-8"); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public void addXmlCollectionImplFunc(String fucName, String className, String colName, String xmlFilePath){
		File file=new File(xmlFilePath);
		if(!file.exists())
			return;
		try{
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader isr = new InputStreamReader(fis); 
	        BufferedReader br = new BufferedReader(isr);
	        StringBuffer buf = new StringBuffer(); 
	        String line=null;
	        while((line=br.readLine())!=null){
	        	if(line.contains("</mapper>")){
	        		if(line.contains("</mapper>")){
		        		buf = buf.append("\r\n");
		        		buf = buf.append("	<select id=\""+fucName+"\" resultMap=\"BaseResultMap\" >");
		        		buf = buf.append("\r\n");
		        		buf = buf.append("		<![CDATA[");
		        		buf = buf.append("\r\n");
		        		buf = buf.append("				select * from "+className+" where "+colName+"=#{0}");
		        		buf = buf.append("\r\n");
		        		buf = buf.append("		]]>");
		        		buf = buf.append("\r\n");
		        		buf = buf.append("	</select>");
		        		buf = buf.append("\r\n");
		        	}
	        	}
	        	buf = buf.append(line); 
	        	buf = buf.append("\r\n");
	        }	        
	        br.close(); 
            FileOutputStream fos = new FileOutputStream(xmlFilePath); 
            OutputStreamWriter pw = new OutputStreamWriter(fos); 
            pw.write(buf.toString().toCharArray()); 
            pw.flush(); 
            pw.close();
	        
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public void updateLimit2Rownum(String xmlFileFolder){
		File file1=new File(xmlFileFolder);
		String[] filelist = file1.list();
		
		try{
			for(String filePath:filelist){
				FileInputStream fis = new FileInputStream(xmlFileFolder+"/"+filePath); 
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
		        BufferedReader br = new BufferedReader(isr);
		        StringBuffer buf = new StringBuffer(); 
		        String line=null;
		        while((line=br.readLine())!=null){
		        	if(line.contains("limit 1") && !line.contains("order")){
		        		line=line.replace("limit 1", "and rownum=1");
		        	}else if(line.contains("limit 0,1") && !line.contains("order")){
		        		line=line.replace("limit 0,1", "and rownum=1");
		        	}
		        	buf = buf.append(line); 
		        	buf = buf.append("\r\n");
		        }	        
		        br.close(); 
	            FileOutputStream fos = new FileOutputStream(xmlFileFolder+"/"+filePath); 
	            OutputStreamWriter pw = new OutputStreamWriter(fos, "UTF-8"); 
	            pw.write(buf.toString().toCharArray()); 
	            pw.flush(); 
	            pw.close();
			}
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public void addOracleSeqOnMyBatis(String xmlFileFolder){
		File file1=new File(xmlFileFolder);
		String[] filelist = file1.list();
		
		try{
			for(String filePath:filelist){
				FileInputStream fis = new FileInputStream(xmlFileFolder+"/"+filePath); 
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
		        BufferedReader br = new BufferedReader(isr);
		        StringBuffer buf = new StringBuffer(); 
		        String line=null;
		        String keycolum="";
		        String keyprop="";
		        String classname=filePath.replace(".xml", "");
		        boolean updateFlag=false;
		        boolean readkey=false;
		        int no2Flag=0;
		        while((line=br.readLine())!=null){
		        	if(line.contains("<result property") && !readkey){
		        		keyprop=line.substring(line.indexOf("property")+10, line.length());
		        		keyprop=keyprop.substring(0, keyprop.indexOf("\""));
		        		
		        		keycolum=line.substring(line.indexOf("column")+8, line.length());
		        		keycolum=keycolum.substring(0, keycolum.indexOf("\""));
		        		readkey=true;
		        	}else if(line.contains("insert into")){
		        		String tablename=line.substring(line.indexOf("insert into")+11, line.indexOf("(")).trim();
		        		
		        		buf = buf.append("		<selectKey keyProperty=\""+keyprop+"\" resultType=\"int\" order=\"BEFORE\">");
		        		buf = buf.append("\r\n");
		        		buf = buf.append("			    select "+classname+"_ID_SEQ.NEXTVAL from dual");
		        		buf = buf.append("\r\n");
		        		buf = buf.append("		</selectKey>");
		        		buf = buf.append("\r\n");
		        		buf = buf.append(line); 
			        	buf = buf.append("\r\n");
			        	buf = buf.append("		"+keycolum+","); 
			        	buf = buf.append("\r\n");
			        	updateFlag=true;
			        	continue;
		        	}else if(updateFlag && (line.contains("values(") || line.contains("values ("))){
		        		buf = buf.append(line); 
			        	buf = buf.append("\r\n");
			        	buf = buf.append("		#{"+keyprop+"},"); 
			        	buf = buf.append("\r\n");
		        		updateFlag=false;
		        		no2Flag=no2Flag+1;
		        		continue;
		        	}
		        	buf = buf.append(line); 
		        	buf = buf.append("\r\n");
		        }	        
		        br.close(); 
	            FileOutputStream fos = new FileOutputStream(xmlFileFolder+"/"+filePath); 
	            OutputStreamWriter pw = new OutputStreamWriter(fos, "UTF-8"); 
	            pw.write(buf.toString().toCharArray()); 
	            pw.flush(); 
	            pw.close();
	            
	            if(no2Flag>=2){
	            	System.out.println(classname);
	            }
			}
		}catch(IOException ie){
			ie.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		String xmlFile="D:/PwWorkSpace/jixu/JixuCenter/jixu/src/main/java/com/jixu/dao/mapper";
		String pojoFile="C:/PWWorkSpace/jixu/JixuCenter/jixu/src/main/java/com/orm/obj";
		String daoFile="C:/PWWorkSpace/jixu/JixuCenter/jixu/src/main/java/com/jixu/dao/intf";
		
		UpdateMapperXml tt=new UpdateMapperXml();
		tt.addOracleSeqOnMyBatis(xmlFile);
	}
}
