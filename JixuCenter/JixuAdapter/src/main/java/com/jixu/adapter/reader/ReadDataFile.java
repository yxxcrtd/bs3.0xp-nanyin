package com.jixu.adapter.reader;

import com.jcraft.jsch.JSchException;
import com.jixu.common.exception.InterfaceException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.FTPTool;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;

import javax.script.ScriptException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface ReadDataFile {
	public int getFileType();
	
	public DataReadResult readFileData(InterfaceSource interfaceSource, DataCounter dataCounter)
            throws InterfaceException, SQLException, ClassNotFoundException, JSchException, IOException, FTPTool.FTPNotLoginException, ScriptException, ParseException;
}
