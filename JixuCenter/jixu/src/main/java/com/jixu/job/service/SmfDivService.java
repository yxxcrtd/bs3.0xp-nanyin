package com.jixu.job.service;

import java.util.List;

import com.jixu.msg.dto.TranBaseMsgDto;
import com.orm.obj.SmfDiv;

public interface SmfDivService {
	
	public int caType();
	
	public List<TranBaseMsgDto> genCorpActionTranDto(String portnum, SmfDiv div);
	
	public String description();
	
}
