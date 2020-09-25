package com.jixu.adapter.reader;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;

public interface ReadDataFile {
	public int getFileType();
	
	public DataReadResult readFileData(InterfaceSource interfaceSource, DataCounter dataCounter) throws Exception;
}
