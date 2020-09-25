//package com.jixu.dataInterface.export;
//
//import java.util.HashMap;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.jixu.adapter.service.export.ExportDataServiceImpl;
//import com.jixu.common.util.DateUtil;
//import com.jixu.common.util.Pages;
//import com.jixu.mvc.control.model.SecTradeSummaryModel;
//import com.jixu.mvc.control.service.TradeService;
//
///** 
// * @ClassName 		ExpSecTran
// * @author  		Pan Wei 
// * @Date 			2017年4月30日 下午2:44:33 	
// * @Description		[]  
// */
//
//@Service("expSecTran")
//public class ExpSecTran extends ExportDataServiceImpl{
//	
//	@Resource(name="tradeService")
//	private TradeService tradeService;
//	
//	@Override
//	public String getSourceName() {
//		return "证券交易";
//	}
//	
//	@Override
//	public List<HashMap<String, Object>> genExportData(String[] params) {
//		String portfolio=params[0];
//		String endDate=params[1];
//		Pages<SecTradeSummaryModel> pages=new Pages<SecTradeSummaryModel>(1);
//		pages.setPageMaxRecords(100000);
//		Boolean displayDelete=false;
//		pages=tradeService.loadTranBasicList(pages,"0", "", portfolio, DateUtil.convertStr2DateAll(endDate), DateUtil.convertStr2DateAll(endDate),displayDelete);
//		List<SecTradeSummaryModel> tranList=pages.getResultList();
//		return getFieldsHM(tranList);
//	}
//	
//	@Override
//	public Class<?> getReturnClassModel() {
//		return SecTradeSummaryModel.class;
//	}
//}
