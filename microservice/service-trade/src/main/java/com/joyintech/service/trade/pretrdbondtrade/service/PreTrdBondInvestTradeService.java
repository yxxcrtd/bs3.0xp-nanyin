/*
 * 文件名：TrdBondInvestTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.pretrdbondtrade.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.pretrdbondtrade.dao.PreTrdBondInvestTradeMapper;
import com.joyintech.service.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade001Dto;
import com.joyintech.service.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;

/**
 * 债券交易Service
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see PreTrdBondInvestTradeService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class PreTrdBondInvestTradeService extends BaseService {


    @Autowired
    private PreTrdBondInvestTradeMapper preTrdBondInvestTradeMapper;


    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * FinProductMapper
     */
    @Autowired(required = false)
    private FinProductMapper finProductMapper;

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
    @SuppressWarnings("unchecked")
	public Page<PreTrdBondInvestTrade001Dto> selectForPage(int page, int length,
                                                     Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("VALID_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("VALID_STATUS_02", DictCodeListExt1.有效状态_待处理.getValue());
        pageParam.put("VALID_STATUS_03", DictCodeListExt1.有效状态_撤销.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        
        // 一体化对应etliu start
        List<String> psList = new ArrayList<String>();
        psList.add(DictCodeList.交易方向_买入.getValue());
        psList.add(DictCodeList.交易方向_卖出.getValue());
        psList.add(DictCodeList.交易方向_行权_回售.getValue());
        psList.add(DictCodeList.交易方向_行权_赎回.getValue());
        pageParam.put("psList", psList);
        // 一体化对应etliu end

        return this.queryForPage(page, length,
            () -> preTrdBondInvestTradeMapper.selectDeal(pageParam));
    }

    public PreTrdBondInvestTrade002Dto getPreTrdBondInvestTradeDto(Map<String, Object> params) {
    	PreTrdBondInvestTrade002Dto preTrdBondInvestTrade002Dto = preTrdBondInvestTradeMapper.getPreTrdBondInvestTradeDto(params);
        return preTrdBondInvestTrade002Dto;
    }

    /**
     * 资产代码：下拉框
     *
     * @param
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> param) throws Exception {
        param.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return this.queryForPage(page, length,
                () -> preTrdBondInvestTradeMapper.getFinprodId(param));

    }
    /**
     * 资金账户下拉框
     * 
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public Page<SelectOptionDto> getacctNo(int page, int length, Map<String, Object> param) throws Exception {
        return this.queryForPage(page, length, () -> preTrdBondInvestTradeMapper.getacctNo(param));
    }

    /**
     * 资金账户下拉框
     * 
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public Page<SelectOptionDto> getsecAcctId(int page, int length, Map<String, Object> param) throws Exception {
        return this.queryForPage(page, length, () -> preTrdBondInvestTradeMapper.getsecAcctId(param));
    }
    /**
     * 结算方式下拉框
     * 
     * @param param
     * @return
     */
    public List<SelectOptionDto> getdeliveryType(Map<String, Object> param) {
        return preTrdBondInvestTradeMapper.getdeliveryType(param);
	}
    /**
     * 清算类型下拉框
     * 
     * @param param
     * @return
     */
    public List<SelectOptionDto> getclearType(Map<String, Object> param) {
        return preTrdBondInvestTradeMapper.getclearType(param);
	}
    public PreTrdBondInvestTrade002Dto getPreTrdBondInvestTradeDto(String tradeId) {
    	 Map<String, Object> params = new HashMap<>();
         params.put("tradeId", tradeId);
         PreTrdBondInvestTrade002Dto resultDto = new PreTrdBondInvestTrade002Dto();
         return resultDto;
    	
    }
    /**
     * 查询投资委托信息
     *
     * @param entrustId 委托代码
     * @return 投资委托信息
     * @see
     * @since 1.0
     */
    public BaseEtrInvestInfoDto getEntrustInfo(String entrustId) {
        if (baseEtrInvestInfoService == null) {
            return new BaseEtrInvestInfoDto();
        }
        BaseEtrInvestInfoDto resultDto = getBaseEtrInvestInfoDtoResult(entrustId);
        return resultDto;
    }

    public BaseEtrInvestInfoDto getBaseEtrInvestInfoDtoResult(String entrustId) {
        BaseEtrInvestInfoDto resultDto = baseEtrInvestInfoService.queryActiveMandate(entrustId);
        if (resultDto != null && resultDto.getEtrEntrust() != null) {
            // 委托数量中扣除已完成额度
            if (!StringUtil.isEmptyList(resultDto.getEtrFinishStatusList())) {
                BigDecimal 已完成额度 = BigDecimal.ZERO;
                for (EtrFinishStatus etrFinishStatus : resultDto.getEtrFinishStatusList()) {
                    已完成额度 = ArithmeticUtil.doAdd(已完成额度,etrFinishStatus.getFinishAmt());
                }
                if(StringUtil.isNotEmpty(resultDto.getEtrEntrust().getEntrustNum())){
                	resultDto.getEtrEntrust().setEntrustNum(
                            ArithmeticUtil.subtract(resultDto.getEtrEntrust().getEntrustNum(), 已完成额度));
                }else if(StringUtil.isNotEmpty(resultDto.getEtrEntrust().getEntrustAmt())){
                	resultDto.getEtrEntrust().setEntrustAmt(
                            ArithmeticUtil.subtract(resultDto.getEtrEntrust().getEntrustAmt(), 已完成额度));
                }else{
                	resultDto.getEtrEntrust().setEntrustNum(
                            ArithmeticUtil.subtract(resultDto.getEtrEntrust().getEntrustNum(), 已完成额度));
                }
            }

            if (!StringUtil.equals(DictCodeListExt1.金融产品类型_债券.getValue(),
                resultDto.getEtrEntrust().getFinprodType2())) {
                resultDto.getEtrEntrust().setPrice(ArithmeticUtil.multiply(
                    resultDto.getEtrEntrust().getPrice(), BigDecimal.valueOf(100)));
            }
            else {
                resultDto.getEtrEntrust().setEntrustNum(ArithmeticUtil.multiply(
                    resultDto.getEtrEntrust().getEntrustNum(), BigDecimal.valueOf(100)));
            }

            // 查询金融产品名称
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(resultDto.getEtrEntrust().getFinprodId());
            if (finProduct != null) {
                resultDto.setFinprodName(finProduct.getFinprodAbbr());//资产简称
            }
        }
        if (resultDto != null && resultDto.getEtrEntrustAdd() != null) {
            // 清算速度
            if (resultDto.getEtrEntrustAdd().getSettleSpreed() == null
                || resultDto.getEtrEntrustAdd().getSettleSpreed() == 0L) {
                resultDto.setSettleSpeedStr(DictCodeList.清算速度_T_0.getValue());
            }
            else {
                resultDto.setSettleSpeedStr(DictCodeList.清算速度_T_1.getValue());
            }

            // 到期收益率
            if (resultDto.getEtrEntrustAdd().getMatureYield() != null) {
                resultDto.getEtrEntrustAdd().setMatureYield(ArithmeticUtil.multiply(
                    resultDto.getEtrEntrustAdd().getMatureYield(), BigDecimal.valueOf(100)));
            }
            // 行权收益率
            if (resultDto.getEtrEntrustAdd().getExerYield() != null) {
                resultDto.getEtrEntrustAdd().setExerYield(ArithmeticUtil.multiply(
                    resultDto.getEtrEntrustAdd().getExerYield(), BigDecimal.valueOf(100)));
            }
        }
        return resultDto;
    }

    /**
     * 交易录单多组合模式
     * @param entrustId
     * @return
     */
    public List<BaseEtrInvestInfoDto> getEntrustInfos(String entrustId){
        List<BaseEtrInvestInfoDto> dtos = new ArrayList<>();
        if (baseEtrInvestInfoService == null) {
            return dtos;
        }
        String[] split = entrustId.split(",");
        if(split.length>0){
            for (String str:split) {
                BaseEtrInvestInfoDto baseEtrInvestInfoDtoResult = getBaseEtrInvestInfoDtoResult(str);
                //
                dtos.add(baseEtrInvestInfoDtoResult);
            }
        }
        return dtos;
    }


}
