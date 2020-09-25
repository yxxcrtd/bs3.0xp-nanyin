package com.jixu.adapter.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;

@Service("readTxtFile")
public class ReadTxtFile extends ReadFileBasic {

	private static final Logger logger = LoggerFactory.getLogger(ReadTxtFile.class);

	public int getFileType() {
		return InterfaceConfig.txt;
	}

	public DataReadResult readFileDataResult(InterfaceSource source,DataCounter dataCounter) throws Exception{
		List<String> filePaths = getReadFiles(source);
		DataReadResult result = new DataReadResult();
		if(result.getFilePathList().size()==0){
			result.setFilePathList(filePaths);
		}
		
		BufferedReader buf = null;
		List<List<Object>> fileContainer = new ArrayList<List<Object>>();
		//获取文件开头需要过滤的行数
		int ignoreLine=getIgnoreLines(source);
		
		try {
			for (String filepath : filePaths) {
				//已经获取数据里有值，则说明是超过了200000条数据，第二轮进来读取，不需要对文件名进行处理
				if(dataCounter.getReadedFiles().contains(filepath)) {
					//条件11
					continue;
				}
				
				logger.info(source.getSourceName() + "		读取文件: " + filepath);
				
				int readLineNum = 0;
				buf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), FileEncodeType.optionHM.get(source.getEnCodeType())));
				String line = null;
				int readstartLine=0;
				int readEndLine=0;
				while ((line = buf.readLine()) != null) {
					readLineNum++;
					if(dataCounter.getFetchRecords()>0 && readLineNum<=dataCounter.getFetchRecords()+ignoreLine){
						//getFetchRecords为已经取到的数据，如果>0,则证明并非第一次读取该文件，忽略这些数据
						//开始读的行数>已经存入的数量+开头过滤的数量
						continue;
					}else if(readLineNum>ignoreLine+dataCounter.getFetchRecords()+readLineInOneFile && dataCounter.getTotalRecords()>0){
						//如果不是第一次读，每次读200000条数据结束，不需要再读下面的数据。第一次读需要统计总体数据量
						break;
					}
					
					if(source.getTableEndChar()!=null && !source.getTableEndChar().equals("")){
						//是否有结束符,如果有，则把统计行数-1
						if(line.contains(source.getTableEndChar())) {
							readLineNum=readLineNum-1;
							break;
						}
					}

					if(readLineNum>readLineInOneFile && dataCounter.getFetchRecords()>0) {
						//大数据，前200000已经读了一次，第二次之后读取的区间
						//起:开头过滤的数据+已经读取的数据
						//终:开头过滤的数据+已经读取的数据+每次读取的最大数量
						readstartLine=ignoreLine+dataCounter.getFetchRecords();
						readEndLine=ignoreLine+dataCounter.getFetchRecords()+readLineInOneFile;
					}else {
						//大数据，2000000读一次，第一次读取
						//起:过滤的数据
						//终：每次读取的最大数量
						readstartLine=ignoreLine;
						readEndLine=readLineInOneFile;
					}
					
					if(readLineNum>readstartLine && readLineNum<=readEndLine){
						List<Object> fieldsList=new ArrayList<Object>();
						//TODO spilit分隔,还有性能提升空间
						if(source.getTableSpilitChar()==null || source.getTableSpilitChar().equals("")){
							fieldsList.add(line);
						}else{
							String splitChar=source.getTableSpilitChar();
							splitChar=getSplitWrite(splitChar);
							String[] fields=line.split(splitChar);
							for(String field:fields){
								fieldsList.add(field);
							}
						}
						fileContainer.add(fieldsList);
					}
				}
				
				if(dataCounter.getTotalRecords()==0){
					//如果是第一次读取，设置总数量
					int firstreadLines=readLineNum-ignoreLine;
					firstreadLines=firstreadLines>0?firstreadLines:0;
					dataCounter.setTotalRecords(firstreadLines);
				}
				
				//设置已经读取的数据量
				dataCounter.setFetchRecords(dataCounter.getFetchRecords()+fileContainer.size());

				if(dataCounter.getTotalRecords()==dataCounter.getFetchRecords()) {
					//数据读取完成，则把文件路径加入到已读文件，匹配条件11
					dataCounter.getReadedFiles().add(filepath);
				}
				
				//if(source.isFileMultiFlag()) {
					//设置成true,每个文件读一次，整体处理一次。 设置成false，则是读完所有数据之后再处理
					if(dataCounter.getReadedFiles().size()==filePaths.size())
						dataCounter.setAllDataReadyFlag(true);
					else
						dataCounter.setAllDataReadyFlag(false);

					break;
				//}
			}
		} finally {
			if (buf != null) {
				try {
					buf.close();
				} catch (IOException e) {
					logger.error("Error", e);
				}
			}
		}
		result.setDataResult(fileContainer);
		return result;
	}
}
