//package com.jixu.dataInterface.export;
//
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
//import com.jixu.common.type.JournalTypes;
//import com.jixu.common.util.DateUtil;
//import com.jixu.dao.intf.VoucherDao;
//import com.jixu.dataInterface.model.VouchersBean;
//import com.jixu.mvc.control.service.SecCashService;
//
///** 
// * @ClassName 		ExpSecPosition
// * @author  		Pan Wei 
// * @Date 			2017年4月25日 下午11:12:09 	
// * @Description		[]  
// */
//
//@Service
//public class ExpVouchers extends ExportDataServiceImpl{
//	
//	@Autowired
//	private VoucherDao VoucherDao;
//	
//	@Resource(name="SecCashService")
//	private SecCashService secCashService;
//	
//	@Override
//	public String getSourceName() {
//		return "凭证";
//	}
//	
//	@Override
//	public Class<?> getReturnClassModel() {
//		return VouchersBean.class;
//	}
//	
//	@Override
//	public List<HashMap<String, Object>> genExportData(String[] params) {
//		String portfolio=params[0];
//		String endDateStr=params[1];
//		Date endDate=DateUtil.convertStr2Date2(endDateStr);
//		List<VouchersBean> voucherList=VoucherDao.getVouchersBeans(portfolio, endDate);
//		for(VouchersBean bean:voucherList){
//			bean.setJournalDesc(JournalTypes.getJournalTypeHM().get(bean.getJournalCode()));
//		}
//		return getFieldsHM(voucherList);
//	}
//}
