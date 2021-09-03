package com.jixu.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
	private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

	public static String ReadFile(String Path){
		BufferedReader reader = null;
		String laststr = "";
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		try{
			File file=new File(Path);
			//判断是否为目录,在junit测试时,所有的web文件不会被引入到项目中
			if(file.isDirectory()){
				return null;
			}
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while((tempString = reader.readLine()) != null){
				laststr += tempString;
			}
			reader.close();
		}catch(IOException e){
			logger.error(e.getMessage(),e);
		}finally{
			try {
			if(reader != null){
					reader.close();
				}
				if (fileInputStream != null){
					fileInputStream.close();
				}
				if (inputStreamReader != null){
					inputStreamReader.close();
				}
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			if (fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
			if (inputStreamReader != null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return laststr;
	}
}
