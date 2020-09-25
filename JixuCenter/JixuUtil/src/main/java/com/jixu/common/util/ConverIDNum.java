package com.jixu.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConverIDNum {
	private static final Logger logger = LoggerFactory.getLogger(ConverIDNum.class);
	
	public static synchronized int converIDNumByString(String idnumStr){
		int idnum=0;
		try{
			idnum=Integer.valueOf(idnumStr);
		}catch(NumberFormatException ne){
			logger.error("Conver Excepton", ne);
		}
		return idnum;
	}
}
