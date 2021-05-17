package com.jixu.adapter.reader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.file.SafelyFile;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;

@Service("readDbfFile")
public class ReadDbfFile extends ReadFileBasic {

	public int getFileType() {
		return InterfaceConfig.dbf;
	}

	public DataReadResult readFileDataResult(InterfaceSource source,DataCounter dataCounter) throws Exception {
		List<String> filePaths = getReadFiles(source);
		DataReadResult result = new DataReadResult();
		if(result.getFilePathList().size()==0){
			result.setFilePathList(filePaths);
		}

		List<List<Object>> fileContainer = new ArrayList<List<Object>>();
		for (String filepath : filePaths) {
			InputStream fis = null; 
			
			try{
				fis = new FileInputStream(new SafelyFile(filepath));
				DBFReader reader=new DBFReader(fis);
				reader.setCharactersetName("GBK");
				
				Object[] rowValues;
				int readLineNum=0;
				while ((rowValues = reader.nextRecord()) != null) { 
					if(readLineNum>=source.getTableStartLine()){
						List<Object> fieldsList = new ArrayList<Object>();
						for (Object obj:rowValues) {  
							if(source.getTableEndChar()!=null && !source.getTableEndChar().equals("")){
								if(obj.toString().contains(source.getTableEndChar()))
									break;
							}else{
								fieldsList.add(obj.toString());
							}
						}  
						fileContainer.add(fieldsList);
					}
					readLineNum++;
				} 

			}finally{
				if(fis!=null)
					fis.close();
			}
		}
		result.setDataResult(fileContainer);
		return result;
	}
}
