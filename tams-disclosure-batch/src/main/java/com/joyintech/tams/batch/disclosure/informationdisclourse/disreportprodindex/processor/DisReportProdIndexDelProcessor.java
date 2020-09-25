package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto.DisReportProdIndexResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.server.DisReportProdIndexService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
@Service
@Scope("prototype")
public class DisReportProdIndexDelProcessor extends BaseProcessor<NeedOperateEntity, DisReportProdIndexIntoResult> {

	@Override
	public DisReportProdIndexIntoResult process(NeedOperateEntity item) throws Exception {
		SysBatchgrpExecInfo execinfo = this.getBatchExecuteDetail();
        // 调用service方法进行逻辑逻辑
		DisReportProdIndexIntoResult result = new DisReportProdIndexIntoResult();
    	//result.setRepProdIndexList(new ArrayList<>(acctEntity));
        
    	Map<String,Object> map = new HashMap<>();
		map.put("startDate", execinfo.getStartDate());
		map.put("endDate", execinfo.getEndDate());
		map.put("prodid", execinfo.getOtherParam1());
		result.setParam(map);
        return result;
	}
	
}
