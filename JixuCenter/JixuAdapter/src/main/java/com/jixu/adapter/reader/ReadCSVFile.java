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
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

@Service("readCSVFile")
public class ReadCSVFile extends ReadFileBasic {

	private static final Logger logger = LoggerFactory.getLogger(ReadCSVFile.class);

	public int getFileType() {
		return InterfaceConfig.csv;
	}

	public DataReadResult readFileDataResult(InterfaceSource source, DataCounter dataCounter) throws Exception {
		List<String> filePaths = getReadFiles(source);
		DataReadResult result = new DataReadResult();
		if(result.getFilePathList().size()==0){
			result.setFilePathList(filePaths);
		}

		BufferedReader buf = null;
		List<List<Object>> fileContainer = new ArrayList<List<Object>>();
		for (String filepath : filePaths) {
			try {
				FileInputStream fis=new FileInputStream(filepath);
//				if(fis.available()>52428800){
//					throw(new InterfaceException("暂时不支持大于50M的文件读取: " + fis.available()));
//				}
				
				buf = new BufferedReader(new InputStreamReader(fis,FileEncodeType.optionHM.get(source.getEnCodeType())));
				CSVParser parser = null;
				if (source.getTableSpilitChar() == null || source.getTableSpilitChar().trim().equals("")) {
					parser = new CSVParserBuilder().withSeparator(CSVWriter.DEFAULT_SEPARATOR).build();
				} else {
					parser = new CSVParserBuilder().withSeparator(source.getTableSpilitChar().toCharArray()[0]).build();
				}

				CSVReader reader = new CSVReaderBuilder(buf).withCSVParser(parser).build();

				//List<String[]> reads = reader.readAll();
				int startLine=source.getTableStartLine();
				
				String[] readsArr;
				int linenum=0;
				while ((readsArr = reader.readNext()) != null){
					linenum++;
					if(dataCounter.getFetchRecords()>0 && linenum<=dataCounter.getFetchRecords()){
						//getFetchRecords为已经取到的数据，如果>0,则证明并非第一次读取该文件，忽略这些数据
						continue;
					}else if(linenum==dataCounter.getFetchRecords()+200000 && dataCounter.getTotalRecords()>0){
						break;
					}
					
					if(startLine>0 && linenum<startLine){
						continue;
					}
					
					if(linenum>dataCounter.getFetchRecords()+(startLine-1) && linenum<=dataCounter.getFetchRecords()+200000){
						List<Object> fields = new ArrayList<Object>();
						for (int j=0;j<readsArr.length;j++) {
							fields.add(readsArr[j]);
						}
						fileContainer.add(fields);
					}
				}
					if(dataCounter.getTotalRecords()==0){
						dataCounter.setTotalRecords(linenum-(startLine-1));
					}
					
					dataCounter.setFetchRecords(dataCounter.getFetchRecords()+fileContainer.size());

				/*for (int i = startLine; i < reads.size(); i++) {
					List<Object> fields = new ArrayList<Object>();
					String[] readsArr=reads.get(i);
					for (int j=0;j<readsArr.length;j++) {
						fields.add(readsArr[j]);
					}
					fileContainer.add(fields);
				}*/
			} finally {
				if (buf != null) {
					try {
						buf.close();
					} catch (IOException e) {
						logger.error("Error", e);
					}
				}
			}
		}

		result.setDataResult(fileContainer);
		return result;
	}

}
