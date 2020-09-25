package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.data.pojo.Balance;
import com.jixu.dataInterface.model.VouchersBean;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.mvc.control.model.VouchersModel;
import com.orm.obj.Voucher;

/**
 * @ClassName 		VoucherDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface VoucherDao extends CommFuncDAO{
	
	public int generateKey();
	
	public List<VouchersBean> getVouchersBeans(@Param("portnum")String portnum , @Param("ledgerDate")Date ledgerDate);
	
	public void deleteVouchers(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate, @Param("journalType") int journalType);
	
	public boolean checkJournalRunable(@Param("portNum")String portNum, @Param("journalDate") Date journalDate, @Param("journalType") int journalType);

	/**
	 * 删除当天所有的凭证
	 * @param portnum
	 * @param ledgerDate
	 */
	public void deleteAllVouchers(@Param("portnum")String[] portnums, @Param("ledgerDate") Date ledgerDate);
	
	public void saveVouchers(@Param("pursaleJnlList")List<GLCOOABase> pursaleJnlList);
	
	/**
	 * 通过交易编号和凭证类型,获取凭证号
	 * @param tanum
	 * @param journalType
	 * @return
	 */
	public String getVoucherNumByTaNumAndType(@Param("tanum")String tanum, @Param("journalType") int journalType);
	
	/**
	 * 根据条件，查询凭证
	 * @param portNum
	 * @param startDate
	 * @param endDate
	 * @param journalType
	 * @param startVoucherNum
	 * @param endVoucherNum
	 * @param secID
	 * @return
	 */
	public Pages<Voucher> searchVouchers(@Param("pages")Pages<VouchersModel> pages,@Param("portList")List<String> portList, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("journalType") int journalType, @Param("startVoucherNum") String startVoucherNum, @Param("endVoucherNum") String endVoucherNum, @Param("secID") String secID, @Param("curCode") String curCode, @Param("acctnum") String acctnum);
	
	public Page<Voucher> searchPageHelperVouchers(@Param("pages")Page<Voucher> page,@Param("portList")List<String> portList, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("journalType") int journalType, @Param("startVoucherNum") String startVoucherNum, @Param("endVoucherNum") String endVoucherNum, @Param("secID") String secID, @Param("curCode") String curCode, @Param("acctnum") String acctnum);
	
	
	//根据投资组合和记账日期  查询Voucher和VoucherDetails中的当天凭证
	public List<VouchersModel> testCaseDailyVoucher(@Param("portnum")String[] portnum, @Param("ledgerDate")Date ledgerDate);
	
	public int getVoucherCountByPortnum(@Param("portnum")String portnum);
	
	/**
	 * 根据交易编号获取凭证编号
	 * @param tanum
	 * @return
	 */
	public String getVouchernumByTradeNum(@Param("tanum")String tanum);
	
	public List<Balance> genResults(@Param("portnum")String portnum ,@Param("portList")List<String> portList,@Param("closeDate")Date closeDate);
	
	/**
	 * 根据投资组合和证券id获取兆尹bokdetailID
	 * @param portnum
	 * @param secid
	 * @return
	 */
	public String genBokDetailID(String portnum,String busiID);
}
