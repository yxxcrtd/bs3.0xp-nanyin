//package com.jixu.dataInterface.export;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jixu.adapter.service.export.ExportDataServiceImpl;
//import com.jixu.common.func.CommFuncService;
//import com.jixu.common.func.SecPositionCenter;
//import com.jixu.common.types.ValuationType;
//import com.jixu.common.util.DateUtil;
//import com.jixu.dao.intf.IssueInfoDao;
//import com.jixu.dao.intf.SecPriceDao;
//import com.jixu.dataInterface.model.SecPriceMiss;
//import com.orm.obj.IssueInfo;
//import com.orm.obj.PortfolioHdr;
//import com.orm.obj.TranClo;
//
///** 
// * @ClassName 		ExpSecPosition
// * @author  		Pan Wei 
// * @Date 			2017年4月25日 下午11:12:09 	
// * @Description		[导出价格缺失]  
// */
//
//@Service
//public class ExpSecPriceLoss extends ExportDataServiceImpl{
//
//	@Resource(name="commfuncService")
//	private CommFuncService comService;
//	
//	@Autowired
//	private IssueInfoDao issueInfoDao;
//	
//	@Resource(name="secPositionCenter")
//	private SecPositionCenter secPosition;
//	
//	@Autowired
//	private SecPriceDao secPriceDao;
//	
//	@Override
//	public String getSourceName() {
//		return "价格缺失";
//	}
//	
//	@Override
//	public Class<?> getReturnClassModel() {
//		return SecPriceMiss.class;
//	}
//	
//	@Override
//	public List<HashMap<String, Object>> genExportData(String[] params) {
//		String portnum=params[0];
//		String endDateStr=params[1];
//		Date endDate=DateUtil.convertStr2Date2(endDateStr);
//		PortfolioHdr portfolio=comService.loadPortfolio(portnum);
//		Date closeDate=portfolio.getLastTranClose();
//		//pages=secCashService.getSecPositions(pages,portfolio, endDate);
//		List<TranClo> lastCloList=secPosition.getSecClo(portnum, closeDate, false);
//		
//		List<String> cusipList=new ArrayList<String>();
//		for(TranClo clo:lastCloList){
//			cusipList.add(clo.getCusip());
//		}
//		
//		List<String> priceList=secPriceDao.getCusipByPriceDate(cusipList, endDate);
//		List<SecPriceMiss> missList=new ArrayList<SecPriceMiss>();
//		for(TranClo clo:lastCloList){
//			if(clo.getValuationType()==ValuationType.FV && clo.getAmount()!=0 && !priceList.contains(clo.getCusip())){
//				IssueInfo sec=issueInfoDao.loadIssueByCusip(clo.getCusip());
//				SecPriceMiss miss=new SecPriceMiss();
//				miss.setAmount(clo.getAmount());
//				miss.setAsOfDate(DateUtil.ConvertUtil2Sql(endDate));
//				miss.setCusip(clo.getCusip());
//				miss.setLastPrice(clo.getPrice());
//				miss.setSecurityID(sec.getSecurityid());
//				miss.setSecurityName(sec.getSecurityname());
//				miss.setAssetType(sec.getIssueType());
//				missList.add(miss);
//			}
//		}
//		
//		return getFieldsHM(missList);
//	}
//}
