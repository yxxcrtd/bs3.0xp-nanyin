/*
 * 文件名：FinDepositTargetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.depo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.common.dao.AssetCommonMapper;
import com.joyintech.service.asset.common.dao.BussPaFinProductBuyoutRateMapper;
import com.joyintech.tams.common.base.dao.FinPostInvestInfoMapper;
import com.joyintech.tams.common.base.dao.PaFinProductBuyoutRateMapper;
import com.joyintech.tams.common.base.entity.PaFinProductBuyoutRateKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaFinProductMapper;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.common.service.AssetCommonService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.service.FinInterestFactorService;
import com.joyintech.service.asset.depo.dao.FinDepositTargetMapper;
import com.joyintech.service.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetDeptInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepDespositRepInfoService;
import com.joyintech.tams.component.base.report.asset.BaseRepDlRepoInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepDespositAssetInfoDto;
import com.joyintech.tams.component.base.report.dto.BaseRepDlRepoInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.common.valuationinterf.dto.InterBorrowBaseInfoInitInputDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 存款类标的信息service
 * @author zhangnian
 * @version 1.0 2018年9月14日
 * @see FinDepositTargetService
 * @since 1.0
 */
@Service("backFinDepositTargetService")
public class FinDepositTargetService extends BaseService<FinDepositTargetDto>{
   
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
    private  FinDepositTargetMapper finDepositTargetMapper;
    
    @Autowired
    private PaFinProductMapper paFinProductMapper;
    /**
     * 基础--标的类金融产品
     */
    @Autowired(required = false)
    private  BaseFinProductBaseInfoService baseFinProductBaseInfoService;
    
    @Autowired(required = false)
    private FinInterestFactorService finInterestFactorService;
    
    /**
     * 基础 --附件
     */
   // @Autowired(required = false)
    //private MstFileCommonService mstFileCommonService;
    
    /**
     * 基础--中债报送(负债)
     */
    @Autowired(required = false)
    private BaseRepAssetDeptInfoService baseRepAssetDeptInfoService;
    
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
  //标的共通service
    @Autowired(required=false)
    private AssetCommonService assetCommonService;

    /**
     * 投后信息
     */
    @Autowired
    private FinPostInvestInfoMapper finPostInvestInfoMapper;

    /**
     * 金融产品买断利率(平安,生成)
     */
    @Autowired
    private PaFinProductBuyoutRateMapper paFinProductBuyoutRateMapper;

    /**
     * 金融产品买断利率(平安,组件)
     */
    @Autowired
    private BussPaFinProductBuyoutRateMapper bussPaFinProductBuyoutRateMapper;
    
    @Autowired 
    private AssetCommonMapper assetCommonMapper;
    
    /**
     * 分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinDepositTargetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length, () -> finDepositTargetMapper.queryForPage(pageParam));
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public void insert(FinDepositTargetDto entity) {
        //标的金融产品信息
        baseFinProductBaseInfoService.insert(entity.getBaseFinProductBaseInfoList());
        //现金流计算规则扩展
        if(null!=finInterestFactorService){
            finInterestFactorService.insertPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
        }
        //标的金融产品信息扩展--单独针对平安
        paFinProductMapper.insert(entity.getPaFinProduct());
        //金融产品买断利率(单独针对平安)
        if(null!=entity.getPaFinProductBuyoutRate()){
            paFinProductBuyoutRateMapper.insert(entity.getPaFinProductBuyoutRate());
        }
        //附件信息
        //mstFileCommonService.updateBusinessId(entity.getMstFileInputParameter());
        //中债报送信息
        if(null!=entity.getBaseRepDespositAssetInfoDto()){
            baseRepDespositRepInfoService.insert(entity.getBaseRepDespositAssetInfoDto().getRepAssetBankDepositList());   
        }
        //拆放同业及买入返售报送信息
        if(null!=entity.getBaseRepDlRepoInfoDto()){
            baseRepDlRepoInfoService.insert(entity.getBaseRepDlRepoInfoDto().getRepDlRepoInfoList());
        }
        //负债
        if(null!=entity.getBaseRepAssetDeptInfoDto()){
            baseRepAssetDeptInfoService.insert(entity.getBaseRepAssetDeptInfoDto().getRepAssetDeptList());
        }
        
        // 一体化对应etliu start
        // 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
        this.interBorrowBaseInfoInit(DictCodeList.动作标志_新增.getValue(), entity);
        // 一体化对应etliu end
        /**
         * 增加授信关联
         */
        if(entity.getPflsh() != null && !StringUtil.equals(entity.getPflsh(), "")){
            CreditLines creditLines = new CreditLines();
            creditLines.setCustomerId(entity.getFinprodId());
            creditLines.setSeqno(entity.getSeqno());
            creditLines.setCreditMstId(entity.getCreditMstId());
            assetCommonService.insertCredit(creditLines);
        }
        //投后信息
        if(null!=entity.getBussPostInvestInfoDto()){
            finPostInvestInfoMapper.insert(entity.getBussPostInvestInfoDto());
        }
    }
    
    public void update(FinDepositTargetDto entity){
        //标的金融产品信息
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        baseFinProductBaseInfoService.update(finprodIdList, entity.getBaseFinProductBaseInfoList());
        //计算规则扩展--单独针对平安
        if(null!=finInterestFactorService){
            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
            finInterestFactorService.insertPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
        }
        //标的金融产品信息扩展--单独针对平安
        paFinProductMapper.deleteByPrimaryKey(entity.getFinprodId());
        paFinProductMapper.insert(entity.getPaFinProduct());
        //金融产品买断利率(单独针对平安)
        if(null!=entity.getPaFinProductBuyoutRate()){
            PaFinProductBuyoutRateKey key = new PaFinProductBuyoutRateKey();
            key.setFinprodId(entity.getFinprodId());
            key.setEffDate(entity.getOldVdate());
            paFinProductBuyoutRateMapper.deleteByPrimaryKey(key);
            paFinProductBuyoutRateMapper.insert(entity.getPaFinProductBuyoutRate());
        }
        //中债报送
        if(null!=entity.getBaseRepDespositAssetInfoDto()){
            baseRepDespositRepInfoService.update(entity.getBaseRepDespositAssetInfoDto());
        }
        if(null!=entity.getBaseRepDlRepoInfoDto()){
            baseRepDlRepoInfoService.update(entity.getBaseRepDlRepoInfoDto());
        }
        //负债
        if(null!=entity.getBaseRepAssetDeptInfoDto()){
            baseRepAssetDeptInfoService.updateBaseRepAssetDeptInfoDto(entity.getBaseRepAssetDeptInfoDto());
        }
        
        // 一体化对应etliu start
        // 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
        this.interBorrowBaseInfoInit(DictCodeList.动作标志_修改.getValue(), entity);
        // 一体化对应etliu end
        /**
         * 修改授信关联
         */
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
        List<Map> credMsg = assetCommonMapper.getMstIdByFinproId(entity.getFinprodId());
        if(credMsg.size()>0) {
        	Map<String,String> credMsgMap = (Map<String,String>) credMsg.get(0);
        	creditLines.setCreditMstId(credMsgMap.get("CREDIT_MST_ID"));
        	creditLines.setSeqno(credMsgMap.get("SEQNO"));
        }
        assetCommonService.updateCredit(creditLines);
        //投后信息
        if(null!=entity.getBussPostInvestInfoDto()){
            finPostInvestInfoMapper.deleteByPrimaryKey(entity.getFinprodId());
            finPostInvestInfoMapper.insert(entity.getBussPostInvestInfoDto());
        }
    }
    /**
     * 删除
     * @param entity
     * @see
     * @since 1.0
     */
    public void delete(FinDepositTargetDto entity) {
        List<String> finprodIdList = new ArrayList<String>();
        finprodIdList.add(entity.getFinprodId());
        //标的金融产品信息
        baseFinProductBaseInfoService.delete(finprodIdList);
        //计算规则扩展--单独针对平安
        if(null!=finInterestFactorService){
            finInterestFactorService.deletePaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule()); 
        }
        //标的金融产品信息扩展--单独针对平安
        paFinProductMapper.deleteByPrimaryKey(entity.getFinprodId());
        //金融产品买断利率(单独针对平安)
        if(null!=entity.getPaFinProductBuyoutRate()){
            for(String finprodId:finprodIdList){
                bussPaFinProductBuyoutRateMapper.deleteByfinprodId(finprodId);
            }
        }
        //中债报送
        BaseRepDespositAssetInfoDto baseRepDespositAssetInfoDto = new BaseRepDespositAssetInfoDto();
        baseRepDespositAssetInfoDto.setRepFinprodIdList(finprodIdList);
        //存款资产中债报送信息
        if(null!=baseRepDespositAssetInfoDto){
            baseRepDespositRepInfoService.delete(baseRepDespositAssetInfoDto);
        }
        BaseRepDlRepoInfoDto baseRepDlRepoInfoDto = new BaseRepDlRepoInfoDto();
        baseRepDlRepoInfoDto.setRepFinprodIdList(finprodIdList);
        //拆放同业及买入返售报送信息
        if(null!=entity.getBaseRepDlRepoInfoDto()){
            baseRepDlRepoInfoService.delete(entity.getBaseRepDlRepoInfoDto());
        }
        BaseRepAssetDeptInfoDto BaseRepAssetDeptInfoDto = new BaseRepAssetDeptInfoDto();
        BaseRepAssetDeptInfoDto.setFinprodIdList(finprodIdList);
        //负债
        baseRepAssetDeptInfoService.deleteByPrimaryKey(entity.getBaseRepAssetDeptInfoDto());
        
        // 一体化对应etliu start
        // 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
        this.interBorrowBaseInfoInit(DictCodeList.动作标志_删除.getValue(), entity);
        // 一体化对应etliu end
      //删除授信关联
        CreditLines creditLines = new CreditLines();
        creditLines.setCustomerId(entity.getFinprodId());
        assetCommonService.deleteCredit(creditLines);
        //投后信息
        if(null!=entity.getBussPostInvestInfoDto()){
            for(String finprodId:finprodIdList){
                finPostInvestInfoMapper.deleteByPrimaryKey(finprodId);
            }
        }
    }
    
 // 一体化对应etliu start
    /**
     * 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
     *
     * @param dataActionSign 动作标志
     * @param entity 画面数据dto
     * @see
     * @since 1.0
     */
    private void interBorrowBaseInfoInit(String dataActionSign, FinDepositTargetDto entity) {
        // 投管在存同业借款时，调估值接口，生成交易类金融产品表数据。
        if (StringUtil.equals(entity.getType6(), DictCodeList.存款类资产类型_同业借款.getValue())) {
            InterBorrowBaseInfoInitInputDto inputDto = new InterBorrowBaseInfoInitInputDto();
            inputDto.setDataActionSign(dataActionSign);
            inputDto.setFinProduct(entity.getBaseFinProductBaseInfoList().get(0).getFinProduct());
            inputDto.setFinProductType(entity.getBaseFinProductBaseInfoList().get(0).getFinProductType());
            inputDto.setFinRateInfo(entity.getFinInterestFactorDto());
            inputDto.setPaFinCashCalcRule(entity.getFinInterestFactorDto().getPaFinCashCalcRule());
            List<FinCashPlan> planlist = entity.getBaseFinProductBaseInfoList().get(0).getFinCashPlanList();
            valuationInterfService.interBorrowBaseInfoInit(inputDto, planlist);
        }
    }
 // 一体化对应etliu end
    
    /**
     * 获取基本信息--修改/详细
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinDepositTargetDto getBasicInfo(String finprodId,String finprodType,String cashType,String finprodMarketId){
        return finDepositTargetMapper.getBasicInfo(finprodId,finprodType,cashType,finprodMarketId,DictCodeListExt1.金融产品类型_投管用_存款.getValue());
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
    
    /**
     * 交易对手下拉框
     * @param param
     * @param ounterType
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTradeOpponent(String param,String counterType){
        return super.queryForSuggest(()->{
            return finDepositTargetMapper.getTradeOpponent(counterType);
        }, null, param);
    }
    
    /**
     * 交易日历下拉框
     * @param param
     * @param isTradeCalendar
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTradeCalendarName(String param,String isTradeCalendar){
        return super.queryForSuggest(()->{
            return finDepositTargetMapper.getTradeCalendarName(isTradeCalendar);
        }, null,param);
    }
    
    /**
     * 存款机构下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 start*/
    public List<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> param) {
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        param.put("CUSTOMER_TYPE", DictCodeList.客户类型_法人.getValue());
        param.put("IS_DEPOSIT_BANK", DictCodeList.是否_是.getValue());
        param.put("IS_FINANCIER", DictCodeList.是否_是.getValue());
        return finDepositTargetMapper.getCustomerName(param);
    }
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/
}
