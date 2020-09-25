package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealReadResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto.InfoDiscAutoRevealResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.service.InfoDiscAutoRevealService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
@Service
@Scope("prototype")
public class InfoDiscAutoRevealProcessor extends BaseProcessor<NeedOperateEntity, InfoDiscAutoRevealResult> {
	/**
     * processor方法的 Service
     */
	@Autowired
	private InfoDiscAutoRevealService infoDiscAutoRevealService;
	@Override
	public InfoDiscAutoRevealResult process(NeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值
		InfoDiscAutoRevealReadResult acctEntity = (InfoDiscAutoRevealReadResult)item;
		Map<String, Object> thisParams = new HashMap<>();
	    thisParams.putAll(this.getParams());
	    // 将返回值塞入参数中
	    thisParams.put(ReportGlobalConstant.REP_READER_RESULT, acctEntity);
        // 调用service方法进行逻辑逻辑
	    InfoDiscAutoRevealResult result = infoDiscAutoRevealService.execute(thisParams);
	    //当为null时候，给予初始化
        if(result == null){
            result = new InfoDiscAutoRevealResult();
        }
		return result;
	}

}
