package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.server.DisReportAssetIndexService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
//import com.joyintech.tams.disclosure.common.util.ReportGlobalConstant;
@Service
@Scope("prototype")
public class DisReportAssetIndexProcessor extends BaseProcessor<NeedOperateEntity, DisReportAssetIndexIntoResult> {
	/**
     * processor方法的 Service
     */
	@Autowired
	private DisReportAssetIndexService disReportAssetIndexService;
	@Override
	public DisReportAssetIndexIntoResult process(NeedOperateEntity item) throws Exception {
		// 获取reader方法的返回值
		DisReportAssetIndexResult acctEntity = (DisReportAssetIndexResult)item;
		Map<String, Object> thisParams = new HashMap<>();
	    thisParams.putAll(this.getParams());
	    // 将返回值塞入参数中
	    thisParams.put(ReportGlobalConstant.REP_READER_RESULT, acctEntity);
        // 调用service方法进行逻辑逻辑
	    DisReportAssetIndexIntoResult result = disReportAssetIndexService.execute(thisParams);
	    //当为null时候，给予初始化
        if(result == null){
            result = new DisReportAssetIndexIntoResult();
        }
        
        
		return result;
	}

}
