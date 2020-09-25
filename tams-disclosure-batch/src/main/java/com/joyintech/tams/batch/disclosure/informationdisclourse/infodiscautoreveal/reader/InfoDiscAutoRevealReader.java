package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dao.InfoDiscAutoRevealMapper;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealReadResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
@Service
@Scope("prototype")
public class InfoDiscAutoRevealReader extends BaseReader<InfoDiscAutoRevealReadResult> {
	@Autowired
	private InfoDiscAutoRevealMapper infoDiscAutoRevealMapper;
	@Autowired
	private RevealDeadLineService revealDeadLineService;
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<InfoDiscAutoRevealReadResult> createList() {
		//获取需要自动披露的披露列表
		String holidayIsWork = CacheUtil.getSysParam(ReportGlobalConstant.HOLIDAY_IS_WORK);
		List<InfoDiscAutoRevealReadResult> revealList = new ArrayList<InfoDiscAutoRevealReadResult>();
		logger.info("reder开始执行");
		//系统设置节假日执行时，直接执行；设置为不执行时，则判断是否为节假日，为节假日则不执行
		if(StringUtil.equals(holidayIsWork, DictCodeList.是否_是.getValue())){
			revealList = infoDiscAutoRevealMapper.getRepRevealList(DictCodeListExt2.披露状态_未披露.getValue(), 
					DictCodeList.确认状态_已确认.getValue());
		}
		else{
			Date execDate = this.getBatchExecuteInfo().getExecuteDate();
			if(revealDeadLineService.isWorkDay(execDate, DisclosureDictCodeList.交易市场_中国法定节假日.getValue())){
				revealList = infoDiscAutoRevealMapper.getRepRevealList(DictCodeListExt2.披露状态_未披露.getValue(), 
						DictCodeList.确认状态_已确认.getValue());
			}
		}
		logger.info("reder执行结束,并且listSize"+revealList.size());
		return revealList;
	}
	

}
