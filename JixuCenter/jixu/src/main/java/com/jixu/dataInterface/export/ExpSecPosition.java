//package com.jixu.dataInterface.export;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.jixu.adapter.service.export.ExportDataServiceImpl;
//import com.jixu.common.util.DateUtil;
//import com.jixu.mvc.control.model.SecPositionModel;
//import com.jixu.mvc.control.service.SecCashService;
//
///** 
// * @ClassName 		ExpSecPosition
// * @author  		Pan Wei 
// * @Date 			2017年4月25日 下午11:12:09 	
// * @Description		[]  
// */
//
//@Service("expSecPosition")
//public class ExpSecPosition extends ExportDataServiceImpl{
//	
//	@Resource(name="SecCashService")
//	private SecCashService secCashService;
//	
//	@Override
//	public String getSourceName() {
//		return "证券头寸";
//	}
//	
//	@Override
//	public Class<?> getReturnClassModel() {
//		return SecPositionModel.class;
//	}
//	
//	@Override
//	public List<HashMap<String, Object>> genExportData(String[] params) {
//		String portfolio=params[0];
//		String endDateStr=params[1];
//		Date endDate=DateUtil.convertStr2Date2(endDateStr);
//		//pages=secCashService.getSecPositions(pages,portfolio, endDate);
//		List<SecPositionModel> positionList=secCashService.getSecPositions(portfolio, endDate);
//		return getFieldsHM(positionList);
//	}
//}
