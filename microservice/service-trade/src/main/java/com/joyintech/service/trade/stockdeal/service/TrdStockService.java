/*
 * 文件名：TrdStockService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.stockdeal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.trade.bond.dao.TrdBondInvestTradeMapper;
import com.joyintech.service.trade.bond.dto.TrdBondInvestTradeCSTPDto;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.stockdeal.dao.TrdStockMapper;
import com.joyintech.service.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.InterfTradStockdeal;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetailKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 股票交易Service
 *
 * @author wangpeng
 * @version 1.0 2019年7月29日
 * @see TrdStockService
 * @since 1.0
 */
@Service
public class TrdStockService extends BaseService<TrdStockDto> {

	@Autowired
	private TrdStockMapper trdStockMapper;
	@Autowired
	private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;
	/**
	 * 投资委托基础组件
	 */
	@Autowired(required = false)
	private BaseEtrInvestInfoService baseEtrInvestInfoService;

	@Autowired
	private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

	@Autowired
	private BasePtlSecPositionInfoService basePtlSecPositionInfoService;
    @Autowired
	private TrdBondInvestTradeMapper trdBondInvestTradeMapper;
    @Autowired
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;

	    /**
     * 分页查询
     *
     * @param page 页编号
     * @param length 页条数
     * @param pageParam 画面参数
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<TrdStockDto> selectForPage(int page, int length, Map<String, Object> pageParam)
         {
    	pageParam.put("VALID_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        List<String> psListDict = new ArrayList<>();
        psListDict.add(DictCodeListExt2.交易方向_配股.getValue());
        psListDict.add(DictCodeListExt2.交易方向_增发.getValue());
        psListDict.add(DictCodeListExt2.交易方向_新股中签.getValue());
        psListDict.add(DictCodeList.交易方向_买入.getValue());
        psListDict.add(DictCodeList.交易方向_卖出.getValue());
        pageParam.put("psListDict", psListDict);
        return this.queryForPage(page, length,
            () -> trdStockMapper.selectDeal(pageParam));
    }



    /**
     * 查询投资委托信息
     *
     * @param entrustId 委托代码
     * @return 投资委托信息
     * @see
     * @since 1.0
     */
    public TrdStockDto getEntrustInfo(String entrustId) {
        if (baseEtrInvestInfoService == null) {
            return new TrdStockDto();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        TrdStockDto TrdStockDto = trdStockMapper.selectEtrByKey(entrustId);
        return TrdStockDto;
    }



    /**
     * 查询通道：联想框文本框
     *
     * @param param 画面参数
     * @return 通道：联想框文本框
     * @see
     * @since 1.0
     */
/*    public List<SuggestDto> getChannel(Map<String, Object> param) {
        return this.queryForSuggest(() -> trdStockMapper.getChannel(param),
            null);
    }*/
    
    
    /**
     * 通道
     *
     * @param param 画面参数
     * @return 
     * @see
     * @since 1.0
     */
/*    public List<String> getChannelInfo(Map<String, Object> param) {
        return  trdStockMapper.getChannelInfo(param);
    }*/

    /**
     * 资产代码：下拉框
     *
     * @param param 画面参数
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> param) throws Exception {
        param.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
//        List<SelectOptionDto> resultList = trdBondInvestTradeMapper.getFinprodId(param);
        Page<SelectOptionDto> resultList = this.queryForPageForSelectReturn(page, length,
                () -> trdStockMapper.getFinprodId(param));
        return resultList;
    }



    /**
     * 股票交易新建画面查看模式查询股票交易信息
     *
     * @param tradeId 交易编号
     * @return 股票投资交易信息
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public TrdStockDto getTrdProductDealDto(String tradeId)  {
    	Map<String, Object> params = new HashMap<>();
    	params.put("tradeId", tradeId);
    	TrdStockDto trd = trdStockMapper.getTrdProductDealDto(params);
    	TrdTradeFeeDetailKey key = new TrdTradeFeeDetailKey();
    	key.setTradeId(tradeId);
    	key.setFeeType(DictCodeListExt1.一次性费用类型_佣金.getValue());
    	TrdTradeFeeDetail fee =trdTradeFeeDetailMapper.selectByPrimaryKey(key);
    	if(fee!=null){
    		trd.setCommission(fee.getFeeAmt());
        	trd.setCommissionIsPayWithTrade(fee.getIsPayWithTrade());
    	}
    	key.setFeeType(DictCodeListExt1.一次性费用类型_印花税.getValue());
    	fee =trdTradeFeeDetailMapper.selectByPrimaryKey(key);
    	if(fee!=null){
    		trd.setStampDuty(fee.getFeeAmt());
        	trd.setStampDutyIsPayWithTrade(fee.getIsPayWithTrade());
    	}
    	return trd;
    }


    
    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSecManageAcctId(Map<String, Object> param) {
        return this.queryForSuggest(() -> trdStockMapper.getSecManageAcctId(param),
            null);
    }


    /**
     * 取得证券托管户的登记托管机构
     * 
     * @param param Map<String, Object>
     * @return 证券托管户的登记托管机构
     * @see
     * @since 1.0
     */
    public String getRegistOrg(Map<String, Object> param) {
        return trdStockMapper.getRegistOrg(param);
    }

    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    /*public BigDecimal getTongDaoCash(Map<String, Object> map) {
        map.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        map.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        return trdStockMapper.getTongDaoCash(map);
    }*/
    
    
    /**
     *@title: getProAvailableCash
     *@description: 组合可用现金查询
     *@author: junYin
     *@date: 2019/06/04 11:22
     *@param: * @param param 1
     *@return: java.util.Map<java.lang.String,java.lang.Object>
     *@throws:
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {

        if (basePtlCashPositionInfoService != null) {
            PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
            Date vdate = DateUtil.parse((String)param.get("vdate"));
            dto.setCdate(vdate);
            List<String> acctTypeList = new ArrayList<>();
            acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
            dto.setAcctType(acctTypeList);
            dto.setPortfolioId((String)param.get("portfolioId"));
            dto.setCcy((String)param.get("ccy"));
            BigDecimal cashTG = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            Map<String, Object> result = new HashMap<>();
            result.put("cashTG", cashTG);
            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT", cashT);
            if (StringUtil.isNotEmpty(param.get("calId"))) {
                PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
                Date vdate1 = DateUtil.parse((String)param.get("vdate1"));
                dto2.setCdate(vdate1);
                dto2.setAcctType(acctTypeList);
                dto2.setPortfolioId((String)param.get("portfolioId"));
                dto2.setCcy((String)param.get("ccy"));
                BigDecimal cashT1 = basePtlCashPositionInfoService.queryUsableCashPosition(dto2);
                result.put("cashT1", cashT1);
            }
            BigDecimal cashS = BigDecimal.ZERO;
            if (StringUtil.isNotEmpty(param.get("secAcctId"))) {
            	//查询账户可用现金
            	StlSecAcctInfo secAcct = stlSecAcctInfoMapper.selectByPrimaryKey((String)param.get("secAcctId"));
            	if(secAcct!=null&&StringUtil.isNotEmpty(secAcct.getTrdAcctId())){
            		dto.setAcctType(null);
            		dto.setAcctId(secAcct.getTrdAcctId());
            		cashS = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            	}
            	
            }
            result.put("cashS", cashS);
            return result;
        }
        else {
            return null;
        }
    }

    /**
     *@title: getAssetAvailablePosition
     *@description: 组合可用持仓查询
     *@author: junYin
     *@date: 2019/06/04 11:23
     *@param: * @param dto 1
     *@return: java.math.BigDecimal
     *@throws:
     */
    public BigDecimal getAssetAvailablePosition(PtlSecPositionFlowDto dto) {

        BigDecimal result = null;
//        List<String> acctTypeList = new ArrayList<>();
//        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
//        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//        dto.setAcctType(acctTypeList);
        if (basePtlSecPositionInfoService != null) {
            result = basePtlSecPositionInfoService.queryUsableSecPosition(dto);
        }
        return result;
    }
    /**
     * 查询债券cstp交易下拉框
     * @param param 输入参数
     * @return
     */
    public List<SuggestDto> queryRepoCstpInfoList(Map<String, Object> param){
        List<SuggestDto> suggestDtoList = new ArrayList<SuggestDto>();
        param.put("tradeStatusInit", BussinessDictCodeList.交易状态_初始.getValue());
        param.put("noFlag", BussinessDictCodeList.是否_否.getValue());
        if(StringUtils.isNotBlank((String)param.get("search"))){
            param.put("param",(String)param.get("search"));
        }
        suggestDtoList = trdStockMapper.queryRepoCstpInfoList(param);
        if (StringUtil.isEmptyList(suggestDtoList)){
            if (StringUtil.isNotEmpty(param.get("initValue"))){
                SuggestDto suggestDto = new SuggestDto();
                suggestDto.setCode(param.get("initValue").toString());
                suggestDto.setName(param.get("initValue").toString());
                suggestDtoList.add(suggestDto);
            }
        }
        return suggestDtoList;
    }
    /**
     * 根据成交编号查询cstp交易信息
     * @param
     * @param loginUserName 登录用户
     * @return cstp交易信息
     */
    public TrdStockDto querySecCstpInfoByNo(String fedealno, String loginUserName){

        TrdStockDto trdBondInvestTradeCSTPDto = new TrdStockDto();

        InterfTradStockdeal interfTradSecdeal = trdStockMapper.queryRepoCstpInfoByNo(fedealno);
        if (interfTradSecdeal != null){
            BeanUtil.copyPropertiesIgnoreNull(trdBondInvestTradeCSTPDto, interfTradSecdeal);
            if(StringUtils.isNotEmpty(interfTradSecdeal.getTradeDate())){
                trdBondInvestTradeCSTPDto.setTradeDate(DateUtil.parse(interfTradSecdeal.getTradeDate()));
            }
            if(StringUtils.isNotEmpty(interfTradSecdeal.getSettleDate())){
                trdBondInvestTradeCSTPDto.setSettleDate(DateUtil.parse(interfTradSecdeal.getTradeDate()));
            }
            if(StringUtils.isNotEmpty(interfTradSecdeal.getUnitCprice())){
                BigDecimal bigDecimal = new BigDecimal(interfTradSecdeal.getUnitCprice());
                trdBondInvestTradeCSTPDto.setUnitCprice(bigDecimal);
            }
            if(StringUtils.isNotEmpty(interfTradSecdeal.getShareAmt())){
                BigDecimal bigDecimal = new BigDecimal(interfTradSecdeal.getShareAmt());
                trdBondInvestTradeCSTPDto.setShareAmt(bigDecimal);
            }
            if(StringUtils.isNotEmpty(interfTradSecdeal.getTradeAmt())){
                BigDecimal bigDecimal = new BigDecimal(interfTradSecdeal.getTradeAmt());
                trdBondInvestTradeCSTPDto.setTradeAmt(bigDecimal);
            }
            if(StringUtils.isNotEmpty(interfTradSecdeal.getCommissionAmt())){
                BigDecimal bigDecimal = new BigDecimal(interfTradSecdeal.getCommissionAmt());
                trdBondInvestTradeCSTPDto.setCommission(bigDecimal);
            }
            if(StringUtils.isNotEmpty(interfTradSecdeal.getStampDutyAmt())){
                BigDecimal bigDecimal = new BigDecimal(interfTradSecdeal.getStampDutyAmt());
                trdBondInvestTradeCSTPDto.setStampDuty(bigDecimal);
            }
            FinProduct finProduct = new FinProduct();
            if(StringUtils.isNotBlank(interfTradSecdeal.getFinprodMarketId())){
                // 根据债券代码查询资产代码
                 finProduct = trdBondInvestTradeMapper.queryFinProductInfoByMark(interfTradSecdeal.getFinprodMarketId(), DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            }
            if (finProduct != null){
                trdBondInvestTradeCSTPDto.setFinprodId(finProduct.getFinprodId());
            }
//            // 交易方向转换
//            if (StringUtil.equals(trdBondInvestTradeCSTPDto.getPs(), "P")){
//                trdBondInvestTradeCSTPDto.setPs(BussinessDictCodeList.交易方向_买入.getValue());
//            } else{
//                trdBondInvestTradeCSTPDto.setPs(BussinessDictCodeList.交易方向_卖出.getValue());
//            }
            // 交易对手代码转换
//            if (StringUtil.isNotEmpty(trdBondInvestTradeCSTPDto.getCno())) {
//                MstCounterParty mstCounterParty = trdBondInvestTradeMapper.queryMstCounterPartyByCounterName(trdBondInvestTradeCSTPDto.getCno());
//                if (mstCounterParty != null) {
//                    trdBondInvestTradeCSTPDto.setCno(mstCounterParty.getCounterId());
//                }
//            } else{
//                trdBondInvestTradeCSTPDto.setCno(null);
//            }
            StlSecAcctInfo stlSecAcctInfo = new StlSecAcctInfo();
            // 查询证券账户是否系统存在
            if(StringUtils.isNotBlank(trdBondInvestTradeCSTPDto.getSecManageAcctId())){
                stlSecAcctInfo = stlSecAcctInfoMapper.selectByPrimaryKey(trdBondInvestTradeCSTPDto.getSecManageAcctId());
            }
            if (StringUtil.isEmpty(trdBondInvestTradeCSTPDto.getSecManageAcctId()) || stlSecAcctInfo== null){
                // 不存在默认设置甲类户
                trdBondInvestTradeCSTPDto.setSecManageAcctId("A000000001");
            }else{
                trdBondInvestTradeCSTPDto.setSecManageAcctId(stlSecAcctInfo.getSecAcctId());
            }
            // 根据证券账户查询关联关联组合
//            List<String> portfolioList = trdBondInvestTradeMapper.queryPortfolioIdBySecAcctId(loginUserName, trdBondInvestTradeCSTPDto.getOurdepositaccountnumber());
//            if (!StringUtil.isEmptyList(portfolioList) && portfolioList.size() == 1){
//                trdBondInvestTradeCSTPDto.setPortfolioId(portfolioList.get(0));
//            }else{
//                trdBondInvestTradeCSTPDto.setOurdepositaccountnumber(null);
//            }
        }
        return trdBondInvestTradeCSTPDto;
    }
}


   

