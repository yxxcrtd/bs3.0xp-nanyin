package com.joyintech.tams.invest.risk.valtblimport.filtertools.util;

import java.util.ArrayList;
import java.util.List;

public class XlsUtil {

	private static final int segment = 'Z' - 'A' + 1;
	
	private XlsUtil() {}
	
	/**
	 * 
	 * @param colCode column code(A, B, ... Z, AA, AB, ...)
	 * @return column sequence(1-based)
	 */
	public static int convertColumnCodeToSeq(String colCode) {
		if(colCode == null || colCode.trim().length() == 0) {
			return -1;
		}
		
		List<Character> charList = new ArrayList<Character>();
		String colCode0 = colCode.trim().toUpperCase();
		for(int i = 0; i < colCode0.length(); i ++) {
			char c = colCode0.charAt(i);
			if(c < 'A' || c > 'Z') {
				return -1;
			} else {
				charList.add(c);
			}
		}
		
		int colSeq = 0;
		int digit = charList.size();
		for(int i = 0; i < charList.size(); i ++) {
			char c = charList.get(i);
			int systemPower = 1;
			for(int j = i + 1; j < digit; j ++) {
				systemPower *= segment;
			}
			colSeq += (c - 'A' + 1) * systemPower;
		}
		
		return colSeq;
	}
	
	/**
	 * 
	 * @param colSeq column sequence(1-based)
	 * @return column code(A, B, ... Z, AA, AB, ...)
	 */
	public static String convertColumnSeqToCode(int colSeq) {
		if(colSeq < 1) return null;
		
		StringBuffer sBuf = new StringBuffer();
		
		int times = colSeq;
		while(times > 0) {
			int remainder = times % segment;
			times = times / segment;
			
			int offset = remainder;
			if(offset == 0) {
				offset = segment;
				times --;
			}
			
			char c = (char)('A' - 1 + offset);
			sBuf.append(c);
		}
		
		return sBuf.reverse().toString();
	}
}
