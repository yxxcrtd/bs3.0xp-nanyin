/**
 * 文件名：PbcClassicsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenxc
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.svreport.pbc.classic.data.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.svreport.seq.service.SysSeqService;

/**
 *人行单产品保本基本公共类 
 * @author chenxc
 * @version 1.0 2019年6月10日
 * @see PbcClassicsUtil
 * @since 1.0
 */
@Component
public class PbcClassicsUtil {
	
    public final static String PROD_PROFIT_TYPE_FIX="01";
    public final static String PROD_PROFIT_TYPE_COVERCOSTF="02";
    public final static String PROD_PROFIT_TYPE_NOCOVERCOSTF="03";
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PbcClassicsUtil.class);
    
	private static SysSeqService sysSeqService;
    @Autowired
    public void setSysSeqService(SysSeqService sysSeqLogic) {
    	PbcClassicsUtil.sysSeqService = sysSeqLogic;
    }		
	/**
	 * 人行产品代码生成
	 * @param issueOrgCode 产品发行机构代码
	 * @param prodProfitType 产品收益类型：PROFIT_TYPE产品收益类型
	 *     PROD_PROFIT_TYPE_FIX:保证收益
	 *     PROD_PROFIT_TYPE_COVERCOSTF:保本浮动收益时;
	 *     PROD_PROFIT_TYPE_NOCOVERCOSTF:非保本浮动收益时
	 * @param seqCode 序列编码
	 * @return
	 */
	public static String getPbcProdId(String issueOrgCode,String prodProfitType,String seqCode,Date vdate){
		StringBuffer strbuf = new StringBuffer();

		//取6位产品发行机构代码，不足补0
		if(issueOrgCode == null || issueOrgCode.length()!=6){
		    logger.error("产品发行机构代码错误");
		    return null;
		}
        strbuf.append(issueOrgCode);
        
		//类型码：PROFIT_TYPE产品收益类型 为 01 保证收益、02 保本浮动收益时，为Z;03 非保本浮动收益时，为1
		String typeCode="Z";
		if ( PROD_PROFIT_TYPE_FIX.equals(prodProfitType) ) {
		    typeCode="Z";
		} else if ( PROD_PROFIT_TYPE_COVERCOSTF.equals(prodProfitType) ) {
		    typeCode="Z";
		} else if ( PROD_PROFIT_TYPE_NOCOVERCOSTF.equals(prodProfitType) ) {
		    typeCode="1";
		}
		strbuf.append(typeCode);
		
		//2位年份
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(vdate==null?new Date():vdate);
		strbuf.append(yearLast);
		//5位顺序码
		//PbcClassicsUtil pbcUtil = new PbcClassicsUtil();
		long seqNo = sysSeqService.getSysSeq(seqCode,vdate,"Y");
		if ( seqNo>99999) {
		    logger.error("产品顺序码长度大于5位!");
		    return null;
		}
		strbuf.append(String.format("%05d",seqNo));
		//校验位,用前面规则生成的码获取
		strbuf.append(theLuhnMod10(strbuf.toString()));
		return strbuf.toString();
	}
	
	/**
	 * 人行资产池代码生成
	 * @param issueOrgCode 产品发行机构代码
	 * @param seqCode 序列编码
	 * @return
	 */
	public static String getPbcAccountId(String issueOrgCode,String seqCode,Date vdate){
		StringBuffer strbuf = new StringBuffer();

		//取6位产品发行机构代码，不足补0
		if(issueOrgCode == null || issueOrgCode.length()!=6){
		    logger.error("产品发行机构代码错误");
		    return null;
		}
		String typeCode="Z";
		strbuf.append(typeCode);
        strbuf.append(issueOrgCode);
		
		//2位年份
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(vdate==null?new Date():vdate);
		strbuf.append(yearLast);
		//5位顺序码
		//PbcClassicsUtil pbcUtil = new PbcClassicsUtil();
		long seqNo = sysSeqService.getSysSeq(seqCode,new Date(),"Y");
		if ( seqNo>99999) {
		    logger.error("产品顺序码长度大于5位!");
		    return null;
		}
		strbuf.append(String.format("%05d",seqNo));
		return strbuf.toString();
	}
	/**
	 * 根据The Luhn Mod-10 Method校验位算法, 返回一位检验码
	 * @param nonCheckCode
	 * @return
	 * @return_type String
	 */
	private static String theLuhnMod10(String nonCheckCode) {
		if (nonCheckCode == null || nonCheckCode.trim().length() == 0) {
			return "";
		}
		String reg = "[a-zA-Z]";
		char[] chs = nonCheckCode.replaceAll(" ", "").toUpperCase().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			char c = chs[i];
			if(String.valueOf(c).matches(reg)){
				//字母转数字
				c = letterToNumber(String.valueOf(c));
			}
			int k = c - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}else{
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? "0" : String.valueOf((10 - luhmSum % 10));
	}
		
	/**
	 * 
	 * 字符字母转数字
	 * @param letter
	 * @return
	 * @return_type char
	 */
//	private static char letterToNum(String letter) {
//		return Character.forDigit((letter.getBytes()[0] - 96),10);
//	}
	public static char letterToNumber(String letter) {
		int length = letter.length();
	    int num = 0;
	    int number = 0;
	    for(int i = 0; i < length; i++) {
	        char ch = letter.charAt(length - i - 1);
	        num = (char)(ch - 'A' + 1) ;
	        num *= Math.pow(26, i);
	        number += num;
	    }
	    return (char) (number+'0');
	}
	public static void  main(String args[] ) {
        System.out.println(PbcClassicsUtil.theLuhnMod10("C10307Z1900003"));
//        System.out.println(Character.highSurrogate(26));
//        System.out.println((char) (1+'0'));
//        System.out.println(letterToNum(String.valueOf((char) 'i')));
//		  System.out.println(letterToNumber("B"));
//        System.out.println(String.format("%05d",99L));
	}
}
