package com.jixu.framework.msg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;

@Service("parseMsg")
public class ParseMessage {
	public void convertMessage(String msg, MsgContainer container){
		
		int bch=msg.indexOf('(');
		if(bch==-1){
			container.setCorrectFlag(false);
			container.setErrorMsg(MsgError.genErrorMsg(MsgError.error9,msg));
		}else if(msg.endsWith(")")){
			String header=msg.substring(0,bch);
			msg=msg.substring(bch+1, msg.length()-1);
			container.setHeader(header);
			
			List<String> fieldsList=new ArrayList<String>();
			if(parseMsg(msg, fieldsList)){
				container.setFields(fieldsList);
			}else{
				container.setCorrectFlag(false);
				container.setErrorMsg(MsgError.genErrorMsg(MsgError.error1,msg));
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorMsg(MsgError.genErrorMsg(MsgError.error2,msg));
		}
	}
	
	private boolean parseMsg(String msg, List<String> fieldsList){
		String oldMsg=msg;
		char ach=',';
		char bch='(';
		char cch=')';
		
		int apoint=0;
		int bpoint=0;
		String field="";
		while(msg.length()>0){
			apoint=msg.indexOf(ach);
			bpoint=msg.indexOf(bch);
			if((apoint<bpoint || bpoint<0)  && apoint>=0){
				field=msg.substring(0,apoint);
				fieldsList.add(field.trim().toUpperCase());
				msg=msg.substring(apoint+1);
			}else if((bpoint<apoint || apoint<0) && bpoint>=0){
				int offset = 0;
				String innerMsg="";
				
				//排除直接没有)的情况
				if(msg.indexOf(cch)==-1){
					return false;
				}
				
				//往下查找(和)，直到(的数量==)的数量
				while((offset = msg.indexOf(cch, offset)) != -1){
					int bchNum=0;
					int cchNum=0;
					innerMsg=msg.substring(0, offset+1);
					char[] chars = innerMsg.toCharArray();
					for(int i=0;i<chars.length;i++){
						if(chars[i]==bch){
							bchNum++;
						}else if(chars[i]==cch){
							cchNum++;
						}
					}
					if(bchNum==cchNum){
						field=innerMsg;
						fieldsList.add(field.trim().toUpperCase());
						bpoint=offset;
						break;
					}else if(offset==msg.length()-1){
						return false;
					}
					offset++;
				}
				msg=msg.substring(bpoint+1).trim();
				apoint=msg.indexOf(ach);
				msg=msg.substring(apoint+1);
			}else{
				fieldsList.add(msg.trim().toUpperCase());
				break;
			}
		}
		if(oldMsg.endsWith(",")){
			fieldsList.add("");
		}
		
		return true;
	} 
}
