package com.jixu.adapter.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;

/**
 * @ClassName WriteCSVFile
 * @author Pan Wei
 * @Date 2017年5月3日 上午10:19:58
 * @Description []
 */

@Component("adptWriteHengshengTA")
public class WriteHengshengTA extends WriteFileBasic {
	
	@Override
	public int getFileType() {
		return InterfaceConfig.hengshengTA;
	}

	@Override
	public boolean writeFile(InterfaceSource source, DataReadResult dataSet) throws Exception {
		boolean writeSuccessFlag=false;
		File file=getWriteFile(source, dataSet);
		FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        Date curDateTime=new Date();
        
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String timestamp = simpleDateFormat1.format(curDateTime);
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out, FileEncodeType.optionHM.get(source.getOutFileEnCodeType()));
            
            List<List<Object>> fieldsDataSet=dataSet.getDataResult();
            
            if(fieldsDataSet!=null && !fieldsDataSet.isEmpty()){
            	List<String> appendList=new ArrayList<String>();	//补齐
            	List<String> rlCheckList=new ArrayList<String>();	//左右补齐
            	List<Integer> fieldsLengthList=new ArrayList<Integer>();	//列长度
           		List<String> headerList=dataSet.getDataHeader();
           		String startChar="";
            	String endChar="";
            	boolean timestampFlag=false;
            	boolean dingchangexportFlag=true;	//是否定长导出	true 定长导出	false 按分隔符导出
            	String exportSplit=source.getWriteSpilitChar();
            	if(!StringUtils.isEmpty(exportSplit))
            		dingchangexportFlag=false;
            	
           		for(int i=0;i<headerList.size();i++){
               		String fieldheader=headerList.get(i);
               		String[] params=fieldheader.split(",");
               		
               		if(dingchangexportFlag) {
               			//按位导出
	               		//常规
	               		appendList.add(params[1]);	//补齐字符
	               		rlCheckList.add(params[2]);	//左右补齐标记	
	               		fieldsLengthList.add(Integer.parseInt(params[3]));	//列总长
	               		
	               		if(i==0) {
	               			startChar=params[4];
	               			endChar=params[5];
	               			if(params.length>5 && params[6].equals("1"))
	               				timestampFlag=true;
	               		}
               		}else {
               			if(i==0) {
	               			startChar=params[1];
	               			endChar=params[2];
	               			if(params.length>2 && params[3].equals("1"))
	               				timestampFlag=true;
               			}
               		}
               	}
            	
            	bw =new BufferedWriter(osw);
				bw.write(startChar);
				bw.newLine();
				bw.write("20");
				bw.newLine();
				bw.write(DateUtil.convertD2YYYYMMDD(curDateTime));
				bw.newLine();
				if(timestampFlag) {
					//加时间戳
					bw.write(timestamp.substring(11,13) + timestamp.substring(14,16)+timestamp.substring(17,19)+timestamp.substring(20,23));
					bw.newLine();
				}
				bw.write(String.valueOf(fieldsDataSet.size()));
				bw.newLine();
				int num = 0;
				int num1 =0;
            	
            	for(List<Object> fieldsList : fieldsDataSet){
                    StringBuilder sb=new StringBuilder();
                    if(dingchangexportFlag) {
                    	//定长
	                	for(int i=0;i<fieldsList.size();i++){
	                		Object field=fieldsList.get(i);
	                		String fieldStr="";
	                		if(field==null)
	                			fieldStr="";
	                		else
	                			fieldStr=field.toString();
	                		
	                		//fieldStr=convertDate(field.toString(), fieldsLengthList.get(i), appendList.get(i));
	                		fieldStr=convertDate(fieldStr, fieldsLengthList.get(i), appendList.get(i),
	                				rlCheckList.get(i), FileEncodeType.optionHM.get(source.getOutFileEnCodeType()));
	                		sb.append(fieldStr);
	                		
	                		logger.debug(fieldStr+"字段的长度为："+fieldStr.length());
	                		logger.debug("第"+(i+1)+"个字段"+fieldStr+"字段的起始位为："+num);
	                		logger.debug("第"+(i+1)+"个字段"+fieldStr+"字段的起始位应该为："+num1);
	                		num = num + fieldStr.length();
	                		num1= num1+ fieldsLengthList.get(i);
	                	}
                    }else {
                    	//分隔符
                    	String finalSplit=getSplitWrite(exportSplit);
                    	sb.append(StringUtils.join(fieldsList, finalSplit));
                    }
                	
                	bw.write(sb.toString());
                    bw.newLine();
                    num = 0;
                    num1 = 0;
                }
            	 bw.write(endChar);
                 writeSuccessFlag=true;
            }
        } catch (Exception e) {
        	writeSuccessFlag=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                	logger.error("Error", e);
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                } catch (IOException e) {
                	logger.error("Error", e);
                } 
            }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                	logger.error("Error", e);
                } 
            }
        }
        return writeSuccessFlag;
	}
	
	private String convertDate(String field, int length, String appendChar, String lrCheckFlag, String charEnCode) throws UnsupportedEncodingException {
		//根据总长度，把剩余的字符补足
		byte[] bytes=field.getBytes(charEnCode);
		if(appendChar.getBytes().length>1) {
			//如果用appendChar超过两个字节，默认转为空格分隔
			appendChar=" ";
		}
		int leftLength=length-bytes.length;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<leftLength;i++) {
			sb.append(appendChar);
		}
		if(lrCheckFlag.toUpperCase().equals("L")){
			//左补齐
			return sb.toString() +field;
		}else{
			//右补齐
			return field+sb.toString();
		}
	}
	
	/**
	 * 处理分隔符，有恒生的byte分隔符特殊处理
	 * @param splitstr
	 * @return
	 */
	private String getSplitWrite(String splitstr) {
		if(splitstr==null) {
			return splitstr;
		}else if(splitstr.startsWith("byte{") && splitstr.endsWith("}")) {
			//针对用byte分隔的，目前用在恒生ta系统
			splitstr=splitstr.substring(5, splitstr.length()-1);
			String[] splitStrArrs=splitstr.split(",");
			byte[] bytessplit=new byte[splitStrArrs.length];
			for(int i=0;i<splitStrArrs.length;i++) {
				bytessplit[i]=Byte.valueOf(splitStrArrs[i]);
			}
			splitstr=new String(bytessplit);
		}
		return splitstr;
	}
}
