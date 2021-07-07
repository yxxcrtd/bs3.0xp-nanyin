package com.jixu.adapter.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;

/**
 * @ClassName WriteCSVFile
 * @author Pan Wei
 * @Date 2017年5月3日 上午10:19:58
 * @Description []
 */

@Component("adptWriteXmlFile")
public class WriteXmlFile extends WriteFileBasic {
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
				String levelList="";	//循环的key。xml层级一层一层下去，倒数第二层是循环的key
				String firstheader="";	//第一列的名称
				Element ele=null;
				if(headerList.size()>0) {
					//xml配置，第一列的配置应包括整体xml层级关系
					String levelStr=headerList.get(0);
					String[] levelArr=levelStr.split("\\.");
					if(levelArr.length<2) {
						throw new Exception("XML层级配置错误");
					}
					for(int i=0;i<levelArr.length;i++) {
						if(i==0) {
							ele=doc.addElement(levelArr[i]);
						}else if(i<levelArr.length-2) {
							//循环加入层级关系
							ele=addElement(ele, levelArr[i]);
						}else if(i==levelArr.length-2) {
							levelList=levelArr[i];
						}else if(i==levelArr.length-1) {
							firstheader=levelArr[i];;
						}
					}
				}
				
				//写具体数据
				for (List<Object> fieldsList : fieldsDataSet) {
					if (ele != null){
						Element info=ele.addElement(levelList);
						for(int i=0;i<headerList.size();i++) {
							String fieldStr="";
							Object fieldObj=fieldsList.get(i);
							if(fieldObj!=null)
								fieldStr=fieldObj.toString();
							if(i==0) {
								info.addElement(firstheader).addText(fieldStr);
							}else {
								info.addElement(headerList.get(i)).addText(fieldStr);
							}
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
	
	private Element addElement(Element ele, String key) {
		return ele.addElement(key);
	}
}
