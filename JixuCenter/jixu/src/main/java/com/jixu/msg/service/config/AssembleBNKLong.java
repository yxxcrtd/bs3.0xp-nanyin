package com.jixu.msg.service.config;


import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgTemplate;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */

@Service("longBnkMsg")
public class AssembleBNKLong extends AssembleBNK{

	@Override
	public String msgHeader() {
		return MsgTemplate.LongBNK_Header;
	}
}
