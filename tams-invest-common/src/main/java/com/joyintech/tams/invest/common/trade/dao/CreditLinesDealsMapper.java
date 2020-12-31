/*
 * 文件名：DateCalcMapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：何阳阳 修改时间：2018年9月21日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.trade.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.trade.dto.FinProductCredit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditLinesDeal;
import com.joyintech.tams.common.base.entity.RiskWeight;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 业务共通组件.交易占用/释放授信共通组件的Mapper
 *
 * @author sjj
 * @version 1.0 2019年1月14日
 * @see CreditLinesDealsMapper
 * @since 1.0
 */
@Repository
public interface CreditLinesDealsMapper {

	int isNbDeal(@Param("counterId") String counterId, @Param("counterType") String counterType);

	List<CreditLines> getCeLinesInfoforasset(Map<String,Object> model);

	List<CreditLines> getCeLinesInfoforOrg01(Map<String,Object> model);
	
	List<CreditLines> getCeLinesInfoforOrg02(Map<String,Object> model);
	
	RiskWeight getRsk(@Param("finprodId")String finprodId, @Param("tradedate")Date tradedate);

	BigDecimal getFaceValue(TrdProductDeal model);

	int updateLinesByPrimaryKeySelective(CreditLines creditLines);
	
	int insertLinesDeal(CreditLinesDeal creditLinesDeal);

	CreditLines isOccupyCreBottom(String finProdId);

	String getTypeByFinprodId(@Param("finprodId") String finprodId);

	int deleteLinesDealByTradeid(@Param("seqno") String seqno,@Param("tradeId") String tradeId);

//	List<CreditLines> getCeLinesInfoforOrg01Rep(@Param("ps") String ps, @Param("counterId") String counterId, @Param("tradeDate")Date tradeDate,@Param("repType")String repType);
	List<CreditLines> getCeLinesInfoforOrg01Rep(Map<String,Object> model);
	
	List<CreditLines> getCeLinesInfoforOrg01Lend(Map<String,Object> model);

	String getPartyIdBycounterId(@Param("counterId")String counterId, @Param("counterType") String counterType);

	String getPartyIdByFinprodId(@Param("finprodId") String finprodId);

	BigDecimal getPledgeNum(@Param("finprodId") String finprodId);

	FinProductCredit getAssetInfoByFinId(@Param("finprodId")String finprodId, @Param("tradedate")Date tradedate, @Param("execmode")String execmode);
	String getMstName(@Param("creditMstId") String creditMstId);
	BigDecimal getPrehapamtForPingan(Map<String, Object> map);

	List<CreditLines> getCeLinesInfoforVariety(Map<String, Object> map);

	//add 20191019  股票取标的对应的公司名称
	String getCounterIdbyFinprodId(@Param("finprodId") String finprodId);
	
	int selectCreditDealCount(@Param("seqno") String seqno,@Param("tradeId") String tradeId);
	
	//撤销时根据交易编号查找授信交易
	List<CreditLinesDeal> selectCreditDealByTradeId(@Param("tradeId") String tradeId);
	
	CreditLines selectCeLinesInfoBySeqno(@Param("seqno") String seqno);
	
	RiskWeight getRskByAssetType(Map<String, Object> map);
	
	RiskWeight getRskBySingleAssetType(@Param("type1")String type1 , @Param("tradedate")Date tradedate);
	
	//回购存续期根据交易编号待处理额度
	String getFinprodIdByTradeId(@Param("tradeId") String tradeId);
	
	/**
	 * <b>方法：</b>getEntrustAmtByEntrustId<br>
	 * <b>描述：</b>根据委托代码查询委托交易的金额<br>
	 * <b>作者：</b>zhuruqing<br>
	 * <b>时间：</b>2020/5/23 15:23<br>
	 * <b>参数：</b>entrustId<br>
	 * <b>返回值：</b>BigDecimal<br>
	 */
    BigDecimal getEntrustAmtByEntrustId(@Param("entrustId")String entrustId);

    /**
     * <b>方法：</b>updateLinesByPrimaryKey<br>
     * <b>描述：</b>更新授信主表<br>
     * <b>作者：</b>zhuruqing<br>
     * <b>时间：</b>2020/5/23 15:27<br>
     * <b>参数：</b>CreditLines<br>
     * <b>返回值：</b>int<br>
     */
	int updateLinesByPrimaryKey(CreditLines creditLines);

	CreditLinesDeal getCeLinesInfo(Map<String,Object> map);
	
	/**
	 * 指令生成交易生效时，扣减指令对应交易额度
	 * 
	 * @param creditLines
	 * @return
	 */
	int updateLinesDealByPrimaryKey(CreditLinesDeal creditLinesDeal);
	
	/**
	 * 根据前置交易标号查交易编号
	 * @param bTradeId
	 * @return
	 */
	List<String> selectTradeidByBTtradid(@Param("bTradeId") String bTradeId);
	
	/**
	 * 根据交易编号查该笔交易指令编号
	 * @param bTradeId
	 * @return
	 */
	String selectEntrustIdByTradeid(@Param("tradeId") String tradeId);
}
