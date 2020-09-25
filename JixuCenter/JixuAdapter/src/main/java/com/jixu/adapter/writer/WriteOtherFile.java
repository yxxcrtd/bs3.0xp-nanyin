package com.jixu.adapter.writer;

import org.springframework.stereotype.Component;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;

/** 
 * @ClassName 		WriteCSVFile
 * @author  		Pan Wei 
 * @Date 			2017年5月3日 上午10:19:58 	
 * @Description		[]  
 */

@Component("adptWriteOtherFile")
public class WriteOtherFile extends WriteFileBasic{
	@Override
	public int getFileType() {
		return InterfaceConfig.txt;
	}
	
	@Override
	public boolean writeFile(InterfaceSource interfaceSource, DataReadResult dataSet) throws Exception {
		return writeFileResult(interfaceSource, dataSet, getFileType());
	}
}
