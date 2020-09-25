package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.types.AcctLocalFunc;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.obj.NavAssetModel;
import com.jixu.job.obj.PeriodSetDownType;
import com.jixu.mvc.control.model.VoucherDetailsView;

/**
 * @ClassName 		VoucherDetailsDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface VoucherDetailsDao extends CommFuncDAO{
	
	public List<NavAssetModel> getNetAsset(@Param("portList")List<String> portList, @Param("calDate") Date calDate);
	
	/**
	 * 获取某天
	 * @param portnum
	 * @param calDate
	 * @return
	 */
	public List<AcctLocalFunc> getCurDateGlResult(@Param("portnum")String portnum, @Param("calDate") Date calDate);
	
	/**
	 * 获取各个科目的各类余额
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<AcctDCBalance> loadAcctDCBalance(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
	
	/**
	 * 银行资管，用于获取排除组合间交易的交易凭证
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<AcctDCBalance> loadAcctDCBalanceTranUnInternalTran(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
	
	public List<PeriodSetDownType> loadPreSetDownList(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate);
	
	public void deleteVoucherDetailsByVoucherNum(@Param("vouchernum")String vouchernum);
	
	/**
	 * 根据凭证号，获取凭证详情
	 * @param voucherNum
	 * @return
	 */
	public List<VoucherDetailsView> searchVoucherDetails(@Param("voucherNum")String voucherNum);
	
	/**
	 * 获取使用兆尹科目表后的凭证详情
	 * @param voucherNum
	 * @return
	 */
	public List<VoucherDetailsView> searchVoucherDetailsBokSubject(String voucherNum);
}
