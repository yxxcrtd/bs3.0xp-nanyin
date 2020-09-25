//适配器升级功能后，可以用适配器完成所有的导出功能
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
//import com.jixu.common.type.CashPositionModel;
//import com.jixu.common.util.DateUtil;
//import com.jixu.mvc.control.service.SecCashService;
//
///** 
// * @ClassName 		ExpCashPosition
// * @author  		Pan Wei 
// * @Date 			2017年4月30日 下午2:44:33 	
// * @Description		[]  
// */
//
//@Service("expCashPosition")
//public class ExpCashPosition extends ExportDataServiceImpl{
//	
//	@Resource(name="SecCashService")
//	private SecCashService secCashService;
//	
//	@Override
//	public String getSourceName() {
//		return "现金头寸";
//	}
//	
//	@Override
//	public List<HashMap<String, Object>> genExportData(String[] params) {
//		String portfolio=params[0];
//		String endDateStr=params[1].replaceAll("-", "");
//		Date endDate=DateUtil.convertStr2Date(endDateStr);
//		List<CashPositionModel> positionList=secCashService.getCashPosition(portfolio, endDate);
//		return getFieldsHM(positionList);
//	}
//	
//	@Override
//	public Class<?> getReturnClassModel() {
//		return CashPositionModel.class;
//	}
//}
