package com.jixu.adapter.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.adapter.types.FileEncodeType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;
/** 
 * @ClassName 		ReadCSVFile
 * @author  		Pan Wei 
 * @Date 			2016年12月5日 下午3:08:08 	
 * @Description		[]  
 */


@Service("readGZFile")
public class ReadGZFile extends ReadFileBasic {

	private static final Logger logger = LoggerFactory.getLogger(ReadGZFile.class);

	public int getFileType() {
		return InterfaceConfig.gz;
	}

	public DataReadResult readFileDataResult(InterfaceSource source, DataCounter dataCounter) throws Exception{
		List<String> filePaths = getReadFiles(source);
		DataReadResult result = new DataReadResult();

		int readLineNum = 0;
		boolean checkFlag = true;
		BufferedReader buf = null;
		List<List<Object>> fileContainer = new ArrayList<List<Object>>();
		try {
			for (String filepath : filePaths) {
				buf = new BufferedReader(new InputStreamReader(new GZIPInputStream(new  FileInputStream(filepath)), FileEncodeType.optionHM.get(source.getEnCodeType())));
				String line = null;
				while ((line = buf.readLine()) != null) {
					readLineNum++;
					checkFlag = preFilterLine(fileContainer, line, source, readLineNum);
					if (!checkFlag) {
						break;
					}
				}
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
