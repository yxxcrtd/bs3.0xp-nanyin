/**
 * 
 */package com.jixu.secTran;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.MsgPackage;
import com.jixu.framework.msg.MsgAssemble;

/** 
 * @ClassName 		SecurityBasicService
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:08:56 	
 * @Description		[]  
 */

@Service("secBasicService")
public abstract class SecurityBasicService implements SecurityBasic{
	
	@Resource(name="msgPackage")
	protected MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	protected MsgAssemble msgAssemble;
	
	protected String genCollatMsg(List<Object> collFields){
		String collatMsg=MsgTemplate.Collat_Header+"(";
		String subCollatMsgTmp="";
		for(Object obj:collFields){
			List<String> collatArrList=(List<String>)obj;
			String secacct=collatArrList.get(0);
			String secid=collatArrList.get(1).split("\\|")[0];
			String units=collatArrList.get(2);
			
			
			String subcollmsg=msgPackage.collatMsgSub(secacct, secid, units);
			subCollatMsgTmp=subCollatMsgTmp+subcollmsg+",";
		}
		if(subCollatMsgTmp.length()>1){
			subCollatMsgTmp=subCollatMsgTmp.substring(0,subCollatMsgTmp.length()-1);
		}
		collatMsg=collatMsg+subCollatMsgTmp+")";
		
		return collatMsg;
	}
}
