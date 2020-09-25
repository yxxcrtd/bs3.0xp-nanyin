package com.jixu.journal.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.exception.JNLException;
import com.jixu.journal.obj.SubRedWrapObj;

public interface SubRedJNLService {
	List<SubRedWrapObj> loadSubReadTran(Date tradedate, String portfolio) throws JNLException;
}
