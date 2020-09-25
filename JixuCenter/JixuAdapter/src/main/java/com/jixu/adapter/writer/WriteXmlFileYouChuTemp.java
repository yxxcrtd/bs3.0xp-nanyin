package com.jixu.adapter.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.util.DateUtil;

/**
 * @ClassName WriteCSVFile
 * @author Pan Wei
 * @Date 2017年5月3日 上午10:19:58
 * @Description [为邮储银行导出特殊的xml配置专门实现的一个类，到时候实现xml头配置后，会去除这个方法]
 */

public class WriteXmlFileYouChuTemp extends WriteFileBasic {
	@Override
	public int getFileType() {
		return InterfaceConfig.xml;
	}

	@Override
	public boolean writeFile(InterfaceSource source, DataReadResult dataSet) throws Exception {
		boolean writeSuccessFlag = false;
		OutputStream out=null;
		XMLWriter write=null;
		try {
			List<List<Object>> fieldsDataSet = dataSet.getDataResult();
			if (fieldsDataSet != null && !fieldsDataSet.isEmpty()) {
				Document doc=DocumentHelper.createDocument();
				List<String> headerList = dataSet.getDataHeader();
				String filename=source.getOutFileName();
				Date curDate = interfaceSourceDao.getCurDate().get(0);
				Date curDateTime=new Date();
		        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		        String timestamp = simpleDateFormat1.format(curDateTime);
				
				Element ele=null;
				ele=doc.addElement("DATAFILE");
				Element head=ele.addElement("DATAHEAD");
				
				for(int i=0;i<headerList.size();i++){
					if(i==0) {
						String fieldheader=headerList.get(i);
						String[] params=fieldheader.split(",");
						if(params.length>1) {
							String xmltype=params[1];
							if(xmltype.equals("HS")) {
								head.addElement("TYPE").addText(params[2]);
								head.addElement("VERSION").addText(params[3]);
								if(filename.contains("<")) {
									head.addElement("DATE").addText(DateUtil.convertD2YYYYMMDD(curDate));
								}else {
									head.addElement("DATE").addText(DateUtil.convertD2YYYYMMDD(curDateTime));
								}
								
								String timestampFlag=params[4];
								if(timestampFlag.equals("1")) {
									head.addElement("TIMESTAMP").addText(timestamp.substring(11,13) + timestamp.substring(14,16)+timestamp.substring(17,19)+timestamp.substring(20,23));
								}
								head.addElement("NUM").addText(String.valueOf(fieldsDataSet.size()));
							}
						}else {
							head.addElement("TRADE_CODE").addText(source.getSourceDesc());
							head.addElement("TRADE_NAME").addText(source.getSourceName());
							head.addElement("TRADE_SIZE").addText(String.valueOf(fieldsDataSet.size()));
							if(filename.contains("<")) {
								head.addElement("TRADE_DATE").addText(DateUtil.convertD2YYYY_MM_DD(curDate));
							}else {
								head.addElement("TRADE_DATE").addText(DateUtil.convertD2YYYY_MM_DD(new Date()));
							}
							head.addElement("TRADE_SERIAL").addText(UUID.randomUUID().toString());
						}
						break;
					}
				}
				
				Element body=ele.addElement("DATABODY");
				
//				if(headerList.size()>0) {
//					//xml配置，第一列的配置应包括整体xml层级关系
//					String levelStr=headerList.get(0);
//					String[] levelArr=levelStr.split("\\.");
//					if(levelArr.length<2) {
//						throw new Exception("XML层级配置错误");
//					}
//					for(int i=0;i<levelArr.length;i++) {
//						if(i==0) {
//							ele=doc.addElement(levelArr[i]);
//						}else if(i<levelArr.length-2) {
//							//循环加入层级关系
//							ele=addElement(ele, levelArr[i]);
//						}else if(i==levelArr.length-2) {
//							levelList=levelArr[i];
//						}else if(i==levelArr.length-1) {
//							firstheader=levelArr[i];;
//						}
//					}
//				}
				
				//写具体数据
				for (List<Object> fieldsList : fieldsDataSet) {
					for(int i=0;i<headerList.size();i++) {
						String fieldStr="";
						Object fieldObj=fieldsList.get(i);
						if(fieldObj!=null)
							fieldStr=fieldObj.toString();
						if(i==0) {
							body.addElement("ENTITY").addText(fieldStr);
						}else {
							body.addElement("ENTITY").addText(fieldStr);
						}
					}
				}
				File file = getWriteFile(source, dataSet);
				out = new FileOutputStream(file);
				OutputFormat outputFormat = OutputFormat.createPrettyPrint();  
		        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
		        outputFormat.setEncoding(FileEncodeType.optionHM.get(source.getOutFileEnCodeType()));
		        outputFormat.setSuppressDeclaration(false); //是否生产xml头
		        outputFormat.setIndent(true); //设置是否缩进
		        outputFormat.setIndent("    "); //以四个空格方式实现缩进
		        outputFormat.setNewLineAfterDeclaration(false);
		        outputFormat.setNewlines(true); //设置是否换行
				
				write=new XMLWriter(out, outputFormat);	
				write.write(doc);
				write.close();
			}
		} catch (IOException e) {
			writeSuccessFlag = false;
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				if(write!=null){
					write.close();
				}
				writeSuccessFlag=true;
			} catch (IOException e) {
				writeSuccessFlag = false;
			}
		}

		return writeSuccessFlag;
	}
}
