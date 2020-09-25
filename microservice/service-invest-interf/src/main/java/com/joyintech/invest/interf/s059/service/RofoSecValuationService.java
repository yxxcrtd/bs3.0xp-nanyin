package com.joyintech.invest.interf.s059.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 *	债券估值信息
 */
@Service
@INF("S059")
public class RofoSecValuationService extends InterfSendBaseService<String> {
	
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		 // 消息头
        BusinessUtil.createSendInterfHeader(request, response, getInfId());

        // 消息体
        Map<String, Object> body = response.getBody();

        // 场外金融产品估值Map
        Map<String, Object> mstBondValuationInfo = (Map<String, Object>)request.get("mstBondValuationInfo");
        // 市场代码
        body.put("SEC_ID", mstBondValuationInfo.get("secId"));
        // 估值日 
        body.put("VALUE_DATE", mstBondValuationInfo.get("valDate"));
        //估值来源
        body.put("VALUE_SOURCE", mstBondValuationInfo.get("valueSource"));
        //剩余期限 
        body.put("LAST_PERIOD", mstBondValuationInfo.get("lastPeriod"));
//         	 估价全价 
        body.put("PRICE", mstBondValuationInfo.get("price"));
//         	 估价净价 
        body.put("NETPRICE", mstBondValuationInfo.get("netprice"));
//         	 估价久期 
        body.put("M_DURATION", mstBondValuationInfo.get("mDuration"));
//         	 估价凸性 
        body.put("M_CONVEXITY", mstBondValuationInfo.get("mConvexity"));
//         	 基点价值 
        body.put("BPVALUE", mstBondValuationInfo.get("bpvalue"));
//         	 利差久期 
        body.put("SDURATION", mstBondValuationInfo.get("sduration"));
//         	 利差凸性 
        body.put("SCNVXTY", mstBondValuationInfo.get("scnvxty"));
//         	 利率久期 
        body.put("INTEREST_DURATION", mstBondValuationInfo.get("interestDuration"));
//         	 利率凸性 
        body.put("INTEREST_CNVXTY", mstBondValuationInfo.get("interestCnvxty"));
//         	 估价收益率 
        body.put("BPYIELD", mstBondValuationInfo.get("bpyield"));
//         	 估价基点价值 
        body.put("MBPVALUE", mstBondValuationInfo.get("bpvalue"));
//        VAR 	 风险价值 
//        CVAR 	 条件风险价值 
//        MDURATION 	 麦考利久期 
//        IMPLICIT_GRADE 	 市价隐含评级（中债） 
//        IMPLICIT_HGRADE 	 市价历史隐含评级（中债） 
        return response;
	}

	@Override
	protected void beforeHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}
}

