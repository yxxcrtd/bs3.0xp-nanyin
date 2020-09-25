package com.jixu.job.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.types.LANGUAGE;

@Service("printJNLFactory")
public class PrintJNLFactory {
	
	@Resource(name="printjnlCHS")
	private PrintJNL printjnlCHS;
	
	@Resource(name="printjnlENG")
	private PrintJNL printjnlENG;
	
	private PrintJNL getPrintjnlCHS() {
		return printjnlCHS;
	}


	private PrintJNL getPrintjnlENG() {
		return printjnlENG;
	}

	public PrintJNL getPringInstance(String local) {
		PrintJNL jnl = null;
		if (local.equals(LANGUAGE.CHINESE)) {
			jnl = getPrintjnlCHS();
		} else {
			jnl = getPrintjnlENG();
		}
		return jnl;
	}
}
