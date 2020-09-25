package com.jixu.framework.msg;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.util.JsonUtil;
import com.jixu.framework.dao.intf.UsersDao;

@Service("msgAssemble")
public class MsgAssembleImpl implements MsgAssemble{
	
	@Resource(name="parseMsg")
	private ParseMessage parseMsg;
	
	@Resource(name="msgFactory")
	protected MsgFactory msgFactory;
	
	@Autowired
	private UsersDao usersDao;

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void genMsgContainer(String requiredHeader,String msg, MsgContainer container){
		parseMsg.convertMessage(msg, container);
		if(container.isCorrectFlag()){
			if(container.getHeader().equals(requiredHeader)){
				MsgService msgassemble=msgFactory.createMsgAssembleBean(container);
				if(msgassemble==null){
					container.setCorrectFlag(false);
					container.setErrorMsg(MsgError.genErrorMsg(MsgError.error10, requiredHeader, msg));
				}else{
					msgassemble.executeMessage(msg, container);
				}
			}else{
				container.setCorrectFlag(false);
				container.setErrorMsg(MsgError.genErrorMsg(MsgError.error11,requiredHeader,container.getHeader()));
			}
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void genMsgContainer(String msg, MsgContainer container){
		parseMsg.convertMessage(msg, container);
		if(container.isCorrectFlag()){
			MsgService msgassemble=msgFactory.createMsgAssembleBean(container);
			if(msgassemble==null){
				container.setCorrectFlag(false);
				container.setErrorMsg(MsgError.genErrorMsg(MsgError.error10, container.getHeader(), msg));
			}else{
				msgassemble.executeMessage(msg, container);
			}
		}

		MsgLog errormsg=new MsgLog();
		errormsg.setMsg(JSON.toJSONString(JsonUtil.stringToHashMap(msg)));
		errormsg.setErrorinfo(container.getErrorMsg());
		errormsg.setOperationTime(new Date());
		//errormsg.setSourceid(0);
		/*if(container.isCorrectFlag()){
			errormsg.setStatus(1);
		}else{
			errormsg.setStatus(0);
		}*/
		errormsg.setStatus(container.isCorrectFlag());
		errormsg.setSource(container.getSource());
		errormsg.setOperationType(container.getOperatoionStep());
		errormsg.setLedgerDate(container.getLedgerDate());
		errormsg.setIngore(false);
		if(container.getPortid()!=null && !container.getPortid().equals("")){
			errormsg.setPortfolio(container.getPortid());
		}
		
		usersDao.saveEntity(errormsg);
	}
}
