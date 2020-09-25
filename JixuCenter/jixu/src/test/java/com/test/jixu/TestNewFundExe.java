package com.test.jixu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.jixu.common.exception.NewFundException;
import com.jixu.common.util.DateUtil;
import com.jixu.mvc.control.service.ProcessControllerService;

/** 
 * @ClassName 		TestConfigFunc
 * @author  		Pan Wei 
 * @Date 			2017年12月18日 上午9:51:40 	
 * @Description		[]  
 * @return  
 */

public class TestNewFundExe extends BaseTest{
	
	@Autowired
	private ProcessControllerService processControllerService;
	
	@Test
	@Rollback(false)
	public void testCreateNewFund() {
		Date startDate=DateUtil.convertStr2Date("20180301");
		String filepath="D:\\test\\port";
		File file=new File(filepath);
		try{
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "/" + filelist[i]);
					FileInputStream fis = new FileInputStream(readfile); 
		            InputStreamReader isr = new InputStreamReader(fis); 
		            BufferedReader br = new BufferedReader(isr); 
		            String line=null;
					while((line=br.readLine())!=null){
						processControllerService.executeNewFund(startDate, line);
						break;
					}
					br.close();
				}
			}
		
			
		}catch(NewFundException ne){
			ne.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}
		
	}
}
