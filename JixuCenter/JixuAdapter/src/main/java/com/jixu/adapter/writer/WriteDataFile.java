package com.jixu.adapter.writer;

import org.springframework.stereotype.Service;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;

/** 
 * @ClassName 		DataWriteService
 * @author  		Pan Wei 
 * @Date 			2017年5月3日 上午9:21:16 	
 * @Description		[]  
 */

@Service("writeDataFile")
public interface WriteDataFile {
	public int getFileType();
	
	public boolean writeFile(InterfaceSource interfaceSource, DataReadResult dataSet) throws Exception;
}
