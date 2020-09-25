package com.jixu.adapter.writer;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;

/** 
 * @ClassName 		WriteCSVFile
 * @author  		Pan Wei 
 * @Date 			2017年5月3日 上午10:19:58 	
 * @Description		[]  
 */

@Service("adptWriteCSVFile")
public class WriteCSVFile extends WriteFileBasic{
	@Override
	public int getFileType() {
		return InterfaceConfig.csv;
	}
	
	@Override
	public boolean writeFile(InterfaceSource source, DataReadResult dataSet) throws Exception {
		return writeFileResult(source, dataSet, getFileType());
	}
}
