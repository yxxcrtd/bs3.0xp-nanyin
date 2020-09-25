package com.jixu.framework.msg;

import java.util.List;

public class MsgError {
	public final static String error1="'('和 ')'括号不匹配";
	public final static String error2="没有以')'结尾";
	public final static String error3="数字格式不正确";
	public final static String error4="数字格式不正确";
	public final static String error5="日期格式不正确";
	public final static String error6="数据不存在";
	public final static String error7="数据已存在";
	public final static String error8="消息不存在";
	public final static String error9="消息头不正确";
	public final static String error10="消息头不存在";
	public final static String error11="消息头不正确";
	public final static String error12="外键已存在";
	public final static String error13="所需字段在系统中不存在";
	public final static String error14="所需字段不能为空字段";
	public final static String error15="操作已提交";
	public final static String error16="数据验算错误";
	public final static String error17="科目输入不正确";
	public final static String error18="现金核对不一致";
	public final static String error19="借贷不一致";
	public final static String error20="该记录不能删除";
	public final static String error21="当天该开仓交易之前不能有平仓交易";
	public final static String error22="公司行为类型不存在";
	public final static String error23="该证券交割日期应大于开仓日期小于或等于合约到期日";
	
	public static String genErrorMsg(String errornum, String header, String errormsg){
		return errornum + ": " + header + ": " + errormsg;
	}
	
	public static String genErrorMsg(String errornum, String errormsg){
		return errornum + ": " + errormsg;
	}
	
	public static String genErrorMsg(String errornum, String header, List<String> fileds){
		StringBuilder errormsg=new StringBuilder();
		
		errormsg.append(errornum);
		errormsg.append(": ");
		
		errormsg.append(header);
		errormsg.append(": ");
		
		for(int i=0;i<fileds.size();i++){
			if(i>0){
				errormsg.append(",");
			}
			errormsg.append(fileds.get(i));
		}
		return errormsg.toString();
	}
}
