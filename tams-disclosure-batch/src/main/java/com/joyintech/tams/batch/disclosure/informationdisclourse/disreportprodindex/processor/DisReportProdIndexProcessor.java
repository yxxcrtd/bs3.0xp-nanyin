package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.server.DisReportProdIndexService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
@Service
@Scope("prototype")
public class DisReportProdIndexProcessor extends BaseProcessor<NeedOperateEntity, DisReportProdIndexIntoResult> {
	/**
     * processor方法的 Service
     */
	@Autowired
	private DisReportProdIndexService disReportProdIndexService;
	@Override
	public DisReportProdIndexIntoResult process(NeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值
		DisReportProdIndexResult acctEntity = (DisReportProdIndexResult)item;
		Map<String, Object> thisParams = new HashMap<>();
	    thisParams.putAll(this.getParams());
	    // 将返回值塞入参数中
	    thisParams.put(ReportGlobalConstant.REP_READER_RESULT, acctEntity);
        // 调用service方法进行逻辑逻辑
	    DisReportProdIndexIntoResult result = disReportProdIndexService.execute(thisParams);
	    //当为null时候，给予初始化
        if(result == null){
            result = new DisReportProdIndexIntoResult();
        }
		return result;
	}
	
}
