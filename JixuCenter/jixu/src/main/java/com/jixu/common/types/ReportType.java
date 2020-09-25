package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName ReportType
 * @author Pan Wei
 * @Date 2016年12月23日 上午9:38:17
 * @Description []
 */

public class ReportType {
	public final static int PDF = 1;
	public final static int EXCEL = 2;
	public final static int HTML = 3;
	public final static int WORD = 4;

	public final static String PDF_TYPE = "pdf";
	public final static String EXCEL_TYPE = "excel";
	public final static String HTML_TYPE = "html";
	public final static String WORD_TYPE = "word";

	public final static HashMap<Integer, String> fileTypeHM = new HashMap<Integer, String>();
	static {
		fileTypeHM.put(PDF, PDF_TYPE);
		fileTypeHM.put(EXCEL, EXCEL_TYPE);
		fileTypeHM.put(HTML, HTML_TYPE);
		fileTypeHM.put(WORD, WORD_TYPE);
	}
}
