/*
 * 文件名：FinStockInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.stock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.service.asset.project.dto.FinProjectTargetAssetDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetEquityInfoService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.common.service.AssetCommonService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.service.FinInterestFactorService;

import com.joyintech.service.asset.stock.dao.FinStockInfoMapper;
import com.joyintech.service.asset.stock.dto.FinStockInfoDto;

import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepDespositRepInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepDlRepoInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepDespositAssetInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepDlRepoInfoDto;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.common.valuationinterf.dto.InterBorrowBaseInfoInitInputDto;

/**
 * 存款类标的信息service
 * @author zhangnian
 * @version 1.0 2018年9月14日
 * @see FinStockInfoService
 * @since 1.0
 */
@Service
public class FinStockInfoService extends BaseService<FinStockInfoDto>{
   
    // 一体化对应etliu
    /**
     * ValuationInterfService
     */
    @Autowired
    private ValuationInterfService valuationInterfService;
   
    /**
     * 定义Mapper(mime)
     */
    @Autowired
    private  FinStockInfoMapper finStockInfoMapper;
    
    @Autowired
    private PaFinProductMapper paFinProductMapper;
    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 基础--标的类金融产品
     */
    @Autowired(required = false)
    private  BaseFinProductBaseInfoService baseFinProductBaseInfoService;

    /**
     * 基础--中债报送(负债)
     */
    @Autowired(required = false)
    private BaseRepAssetDeptInfoService baseRepAssetDeptInfoService;
    /**
     * 权益类资产中债报送(基础组件)
     */
    @Autowired(required = false)
    private BaseRepAssetEquityInfoService baseRepAssetEquityInfoService;
    
    /**
     * 基础--中债报送(资产)
     */
    @Autowired(required = false)
    private BaseRepDespositRepInfoService baseRepDespositRepInfoService;
    /**
     *  基础--拆放同业及买入返售报送信息
     */
    @Autowired(required = false)
    private BaseRepDlRepoInfoService baseRepDlRepoInfoService;

    /**
     * 分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinStockInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> finStockInfoMapper.queryForPage(pageParam));
    }
    public FinStockInfoDto getShares(Map<String, Object> Param) {
        return finStockInfoMapper.getShares(Param);
    }

    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
       // param.put("FINPROD_TYPE2", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        param.put("finProdMarketId", finprodMarketId);
        return finStockInfoMapper.getFinProdMarketIdList(param);
    }
    /**
     * 资产代码重复Check
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto findEntity(Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return finStockInfoMapper.findEntity(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity FinStockInfoDto
     * @see
     * @since 1.0
     */
    public void doInsert(FinStockInfoDto entity) {

    }
    /**
     * 插入(from findeposittarget)
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(FinStockInfoDto entity) {
        //标的金融产品信息
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
        if(null!=entity.getBaseRepDespositAssetInfoDto()){
            baseRepDespositRepInfoService.insert(entity.getBaseRepDespositAssetInfoDto().getRepAssetBankDepositList());
        }
        //权益类资产中债报送
        if(null!=baseRepAssetEquityInfoService){
            if(null!=entity.getBaseRepAssetEquityInfoDto()){
                baseRepAssetEquityInfoService.insert(entity.getBaseRepAssetEquityInfoDto().getRepAssetEquityList());
            }
        }
        //资产负债
        if(null!=baseRepAssetDeptInfoService){
            if(null!=entity.getBaseRepAssetDeptInfoDto()){
                baseRepAssetDeptInfoService.insert(entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList());
            }
        }
    }
    
    public void update(FinStockInfoDto entity){
        //标的金融产品信息
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        //中债报送
        if(null!=entity.getBaseRepDespositAssetInfoDto()){
            baseRepDespositRepInfoService.update(entity.getBaseRepDespositAssetInfoDto());
        }
        if(null!=entity.getBaseRepDlRepoInfoDto()){
            baseRepDlRepoInfoService.update(entity.getBaseRepDlRepoInfoDto());
        }
        //权益类资产中债报送
        if(null!=baseRepAssetEquityInfoService){
            if(null!=entity.getBaseRepAssetEquityInfoDto()){
                baseRepAssetEquityInfoService.update(entity.getBaseRepAssetEquityInfoDto());
            }
        }
        //资产负债
        if(null!=baseRepAssetDeptInfoService){
            if(null!=entity.getBaseRepAssetDeptInfoDto()){
                baseRepAssetDeptInfoService.updateBaseRepAssetDeptInfoDto(entity.getBaseRepAssetDeptInfoDto());
            }
        }
    }


    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(FinStockInfoDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
//        //计算规则扩展--单独针对平安
//        if(null!=finInterestFactorService){
//            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
//        }
        //标的金融产品信息扩展--单独针对平安
        paFinProductMapper.deleteByPrimaryKey(entity.getFinprodId());
        //中债报送
        BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto = new BaseRepDespositAssetInfoDto();
        baseRepDespositAssetInfoDto.setRepFinprodIdList(finprodIdList);
            //权益类资产中债报送
            if(null!=baseRepAssetEquityInfoService){
                if(null!=entity.getBaseRepAssetEquityInfoDto()){
                    baseRepAssetEquityInfoService.delete(entity.getBaseRepAssetEquityInfoDto());
                }
            }
            //资产负债
            if(null!=baseRepAssetDeptInfoService){
                if(null!=entity.getBaseRepAssetDeptInfoDto()){
                    baseRepAssetDeptInfoService.deleteByPrimaryKey(entity.getBaseRepAssetDeptInfoDto());
                }
            }
    }
//    MODIFY BY WUJIE ON 20191130 REMARK:调用接口共通【同业借款基本信息初始化】操作取消
// // 一体化对应etliu start
//    /**
//     * 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
//     *
//     * @param dataActionSign 动作标志
//     * @param entity 画面数据dto
//     * @see
//     * @since 1.0
//     */
//    private void interBorrowBaseInfoInit(String dataActionSign, FinStockInfoDto entity) {
//        // 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
//        if (StringUtil.equals(entity.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())) {
//            InterBorrowBaseInfoInitInputDto inputDto = new InterBorrowBaseInfoInitInputDto();
//            inputDto.setDataActionSign(dataActionSign);
//            inputDto.setFinProduct(entity.getBaseFinProductBaseInfoList().get(0).getFinProduct());
//            inputDto.setFinProductType(entity.getBaseFinProductBaseInfoList().get(0).getFinProductType());
//            inputDto.setFinRateInfo(entity.getFinInterestFactorDto());
//            inputDto.setPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
//            List<FinCashPlan> planlist = entity.getBaseFinProductBaseInfoList().get(0).getFinCashPlanList();
//            valuationInterfService.interBorrowBaseInfoInit(inputDto, planlist);
//        }
//    }
// // 一体化对应etliu end

    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto getBasicInfo(String finprodId,String finprodType,String cashType){
        return finStockInfoMapper.getBasicInfo(finprodId,finprodType,cashType);
        /*FinDepositTargetDto dto =new FinDepositTargetDto();
        BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto = new BaseRepAssetDeptInfoDto();
        BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto = new BaseRepDespositAssetInfoDto();
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(finprodId);
        //获取标的金融产品基本信息
        dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoService.select(finprodIdList));
        //存款类资产二级资产类型---存业同放
        if(StringUtil.equals(dto.getType6(), DictCodeList.存款类资产类型_存放同业.getValue())){
          //获取中债信息  --- 负债信息
            baseRepAssetDeptInfoDto.setFinprodIdList(finprodIdList);
            List<RepAssetDept> repAssetDeptList = baseRepAssetDeptInfoService.selectRepAssetDeptInfo(baseRepAssetDeptInfoDto);
            //获取中债报送 --- 资产信息
            List<RepAssetBankDeposit> repAssetBankDepositList= baseRepDespositRepInfoService.select(baseRepDespositAssetInfoDto);
            baseRepAssetDeptInfoDto.setRepAssetDeptList(repAssetDeptList);
            baseRepDespositAssetInfoDto.setRepAssetBankDepositList(repAssetBankDepositList);
            dto.setBaseRepAssetDeptInfoDto(baseRepAssetDeptInfoDto);dto.setBaseRepDespositAssetInfoDto(baseRepDespositAssetInfoDto);
        }
        return dto;*/
    }
    
//    /**
//     * 交易对手下拉框
//     * @param param
//     * @param ounterType
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public List<SuggestDto> getTradeOpponent(String param,String counterType){
//        return super.queryForSuggest(()->{
//            return finStockInfoMapper.getTradeOpponent(counterType);
//        }, null, param);
//    }
    /**
     * 根据金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinStockInfoDto selectByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        //  param.put("CASH_TYPE_2", DictCodeListExt1.现金流二级类型_利息.getValue());
        //  param.put("FIN_CASH_BUSINESS_TYPE", DictCodeListExt1.金融产品类型_理财产品.getValue());
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return finStockInfoMapper.selectByFinprodId(param);
    }
    /**
     * 交易日历下拉框
     * @param tradeMarket
     * @param tradeMarket
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCalendarId(String tradeMarket){
            return finStockInfoMapper.getCalendarId(tradeMarket);
    }
    
    /**
     * 存款机构下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getCustomerName(String param){
        return super.queryForSuggest(()->{
            return finStockInfoMapper.getCustomerName();
        }, null,param);
    }
    
    /**
     * 获取当前资产是否有用户编辑 如果有 返回用户
     * @param param
     * @param 
     * @return
     * @see
     * @since 1.0
     */
	public String getIsInWorkFlowReturnUser(Map<String, Object> param) {
		String user =finStockInfoMapper.getBusinessUser(param);
		String username= null;
		if(StringUtil.isNotEmpty(user)){
			username= finStockInfoMapper.getRealName(user);
		}
		return username;
	}
}
