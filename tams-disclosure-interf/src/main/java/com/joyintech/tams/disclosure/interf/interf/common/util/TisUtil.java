package com.joyintech.tams.disclosure.interf.interf.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.disclosure.interf.interf.common.dto.InterfLog;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class TisUtil {

	public static BufferedReader bufread;
	// 指定文件路径和名称
	private static String path = "suncity.txt";
	private static File filename = new File(path);
	private static String readStr = "";

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(SocketClient.class);
	/**
	 * 判断类型是否 为空
	 * @param str 判断Object
	 * @return
	 */
	public static boolean isEmpty(Object str){
		
		if(str == null || "".equals(str.toString().trim())){
			return true;
		}
		
		return false;
	}

	/** */
	/**
	 * 创建文本文件.
	 * 
	 * @throws IOException
	 * 
	 */
	public static void creatTxtFile(String path) throws IOException {
		filename = new File(path);
		//如果本地已存在同名文件，删除本地重新生成
		if (filename.exists()) {
			filename.delete();
		}
		filename.createNewFile();
	}
	/**
	 * 修改文件的名字
	 * @param path
	 * @throws IOException
	 */
	public static void reTxtFilename(String path) throws IOException {
		File file = new File(path);
		if (filename.exists()) {
			filename.renameTo(file);
		}
	}
	/** */
	/**
	 * 读取文本文件.
	 * 
	 */
	public static String readTxtFile() {
		readStr="";
		String read;
		FileReader fileread = null;
		try {
			fileread = new FileReader(filename);
			bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "\r\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("error:",e);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("error:",e);
		}
		finally {
			try {
				if(fileread!=null)
				{
					fileread.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return readStr;
	}
	/** */
	/**
	 * 读取文本文件.
	 * 
	 */
	public static String readTxtFile(File file) throws UnsupportedEncodingException {
		InterfLog log = new InterfLog();
		readStr="";
		String read;
		FileReader fileread;
		FileInputStream fis=null;
		InputStreamReader read2=null;
		try {
		     fis=new FileInputStream(file);
			 read2 = new InputStreamReader(fis,"UTF-8");
			bufread=new BufferedReader(read2);  
			//bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "\r\n";
				}
		        log.setTaskStatus(file+"读取文件成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("error:",e);
		        log.setTaskStatus(file+"读取文件失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("error:",e);
	        log.setTaskStatus(file+"读取文件失败！");
		}
		finally{
		    if(fis!=null){
		        try {
                    fis.close();
                }
                catch (IOException e) {
                	logger.error("error:",e);
                } 
		    } 
		    if(read2!=null){
                try {
                    read2.close();
                }
                catch (IOException e) {
                	logger.error("error:",e);
                } 
            } 
		    if(bufread!=null){
                try {
                    bufread.close();
                }
                catch (IOException e) {
                	logger.error("error:",e);
                } 
            } 
		}
        log.setTaskStatus(file+"文件内容是:" + "\r\n" + readStr);
		return readStr;
	}
	/** */
	/**
	 * 写文件.
	 * 
	 */
	public static void writeTxtFile(String newStr) throws IOException {
		InterfLog log = new InterfLog();
		// 先读取原有文件内容，然后进行写入操
		String filein = newStr;//+"\r\n"+ readStr;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(filename);
			o.write(filein.getBytes("GBK"));
			o.close();
			log.setTaskStatus("生成文件成功！");
	        log.setTaskStatus("文件内容是:" + "\r\n" + filein);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("error:",e);
			log.setTaskStatus("生成文件失败！");
		} finally {
			if (o != null) {
				o.close();
			}
		}
	}

	/** */
	/**
	 * 将文件中指定内容的第一行替换为其它内容.
	 * 
	 * @param oldStr
	 *            查找内容
	 * @param replaceStr
	 *            替换内容
	 */
	public static void replaceTxtByStr(String oldStr, String replaceStr) {
		String temp = "";
		FileInputStream fis=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		FileOutputStream fos=null;
		PrintWriter pw =null;
		try {
			File file = new File(path);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该行前面的内容
			for (int j = 1; (temp = br.readLine()) != null
					&& !temp.equals(oldStr); j++) {
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}

			// 将内容插入
			buf = buf.append(replaceStr);

			// 保存该行后面的内容
			while ((temp = br.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(temp);
			}
			fis.close();
			isr.close();
			br.close();
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			fos.close();
			pw.close();
		} catch (IOException e) {
			logger.error("error:",e);
		}
		finally{
		    if(fis!=null){
		        try {
                    fis.close();
                }
                catch (IOException e) {
                	logger.error("error:",e);
                }  
		    }
		    if(isr!=null){
		        try {
		            isr.close();
		        }
		        catch (IOException e) {
		        	logger.error("error:",e);
		        }  
		    }
		    if(br!=null){
		        try {
		            br.close();
		        }
		        catch (IOException e) {
		        	logger.error("error:",e);
		        }  
		    }
		    if(fos!=null){
		        try {
                        fos.close();
                  }
             catch (IOException e) {
            	 logger.error("error:",e);
                }
		    }
		    if(pw!=null){
		        pw.close();  
		    }
		}
	}
	public static String  getBeanToText(Object o) { 
		StringBuffer sb=new StringBuffer();
		try {               
			if(o != null ) {  
				Field[] fields = o.getClass().getDeclaredFields();      
				for (int i = 0; i < fields.length; i++){                           
					boolean accessFlag = fields[i].isAccessible();                              
					fields[i].setAccessible(true);                              
					Object objText = null;                              
					try {                                  
						objText = fields[i].get(o);                              
					}catch (Exception e) {                                  
//							logger.error("class反射读取失败:", e);                              
					}                              
					if (objText == null){                                  
						sb.append("|");                             
					}else{                                 
						sb.append(objText.toString()+"|");
					}                              
					fields[i].setAccessible(accessFlag);
				}
			}
		}catch(Exception e) {              
//			logger.error("bean转为dom失败:", e);          
		}
		return sb.toString()+"\r\n";
	}
	  /**
     * txt生成调用此方法即可
     * @param list
     * @param
     * @throws IOException 
     */
     public static void writeDataToTxtFile(List list,String fpath) throws IOException {
        creatTxtFile(fpath);
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<list.size();i++){
            sb = sb.append(TisUtil.getBeanToText(list.get(i)));
       }
        TisUtil.writeTxtFile(sb.toString().trim());
    }

	  /**
    * txt生成调用此方法即可
    * @param
    * @param
    * @throws IOException 
    */
    public static void writeDataToTxtFile(String newStr,String fpath) {
       try{
		   creatTxtFile(fpath);
		   TisUtil.writeTxtFile(newStr.trim());
	   }catch (Exception e){
		   logger.error(String.valueOf(e));
	   }

   }

	/** */
	/**
	 * main方法测试
	 * 
	 * @param s
	 * @throws IOException
	 */
	public static void main(String[] s) throws IOException {

	}
//	public static void main(String[] args) throws MalformedURLException,Exception{
//		Service s=new ObjectServiceFactory().create(IHelloWorldService.class);
//	}
}