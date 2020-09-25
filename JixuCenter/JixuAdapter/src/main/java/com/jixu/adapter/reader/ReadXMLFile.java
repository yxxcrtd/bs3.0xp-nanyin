package com.jixu.adapter.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;

@Service("readXMLFile")
public class ReadXMLFile extends ReadFileBasic {

	private static final Logger logger = LoggerFactory.getLogger(ReadXMLFile.class);

	public int getFileType() {
		return InterfaceConfig.xml;
	}

	public DataReadResult readFileDataResult(InterfaceSource source,DataCounter dataCounter) throws Exception{
		List<String> filePaths = getReadFiles(source);
		DataReadResult result = new DataReadResult();
		if(result.getFilePathList().size()==0){
			result.setFilePathList(filePaths);
		}
		
		List<List<Object>> fileContainer = new ArrayList<List<Object>>();
		//创建一个读取XML文件的对象  用来指向XML文件的输入流
		SAXReader reader = new SAXReader();
		try {
			for (String filepath : filePaths) {
				//SAXReader调用read()方法,来读取XML中的内容
				Document document = reader.read(new File(filepath));
				//获取XML内容之后   首先读取XML的根元素
				Element root = document.getRootElement();
				//通过根元素获取下面的所有子元素/子标签  
				@SuppressWarnings("unchecked")
				List<Element> elemList = root.elements();
				if(source.getTableSpilitChar()!=null&&source.getTableSpilitChar()!=""){
					elemList = root.element(source.getTableSpilitChar()).elements();
				}
				//开始行??  感觉不用加
				int readLineNum=1;
				if (elemList != null && elemList.size()> 0) {
					//对子元素进行处理   取子元素的值
					for (Element element : elemList) {
						if(readLineNum>=source.getTableStartLine()){
							List<Object> list=new ArrayList<Object>();
							list=getElementVal(element,list);
							fileContainer.add(list);
						}
						readLineNum++;
					}
				} 
			}
		} catch (Exception e) {
			logger.error("Error", e);
		}
		result.setDataResult(fileContainer);
		return result;
	}
	
	
	//递归查询子元素,输出子节点value
    public List<Object> getElementVal(Element element,List<Object> list) {
    	@SuppressWarnings("unchecked")
        Iterator<Element> iterator = element.elementIterator();
        //判断该子元素是否还存在子元素  如果存在进行递归,如果不存在子元素了则将该元素的值放入到list中
        if (iterator.hasNext()) {
            while(iterator.hasNext()){
                getElementVal(iterator.next(),list);
            }
        }else {
            if (element.isTextOnly()) {
                String value = element.getTextTrim();
                list.add(value);
            }
        }   
       return list;
    }
}
