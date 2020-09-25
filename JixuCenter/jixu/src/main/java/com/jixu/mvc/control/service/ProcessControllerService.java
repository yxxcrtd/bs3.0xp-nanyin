package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jixu.common.exception.NewFundException;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.NewFund;
import com.orm.obj.NewFundContent;

/** 
 * @ClassName 		ProcessControllerService
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 上午11:26:28 	
 * @Description		[]  
 */

public interface ProcessControllerService {
	
	public NewFund loadNewFund(int configID);
	
	public List<NewFund> loadNewFundList(int clientID, int newFundType);
	
	public void createNewFund(NewFund newfund);
	
	public void saveNewFundConfig(int newFundID, String newFundContend);
	
	/**
	 * 注意:这里传入的是NewFundID不是NewFundContentID
	 * @param newFundID
	 * @return
	 */
	public NewFundContent loadNewFundContent(int newFundID);
	
	public ResultContainer executeNewFundProcess(int userID, int newFundID, Date initDate,double initShares)  throws NewFundException; 
	
	/**
	 * 老基金期初
	 * @param exeDate	在执行日期
	 * @param initShares	初实份额
	 * @param newFundContent	新基金
	 * @param portnum	投资组合
	 * @param userID	用户ID
	 * @return
	 * @throws NewFundException
	 */
	public ResultContainer executeOldFundQiChu(Date exeDate, double initShares, String newFundContent, String portnum, int userID) throws NewFundException;
	
	/**
	 * 新基金上线
	 * @param exeDate	执行日期
	 * @param newFundContent	新基金内容
	 * @return
	 * @throws NewFundException
	 */
	public ResultContainer executeNewFund(Date exeDate, String newFundContent) throws NewFundException;
	
	
	/**
	 * 获取配置的完成度百分比
	 * @param newFundID	新基金上线配置编号
	 * @return 完成度
	 */
	public double getComletePercent(int newFundID);
	
	public List<List<String>> loadCustomCoa(String portnum, int newFundID, List<MultipartFile> mpfList, ResultContainer container);
	
	public List<List<String>> loadCustomRcpy(String portnum, int newFundID, List<MultipartFile> mpfList, ResultContainer container);

	public List<List<String>> loadCustomSecPosition(String portnum, int newFundID, List<MultipartFile> mpfList, ResultContainer container);
}
