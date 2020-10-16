/*
 * 文件名：EtrWtFxswapService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrwtfxswap.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrboutrepo.dao.EtrBoutRepoMapper;
import com.joyintech.service.entrust.etrwtfxswap.dao.EtrWtFxswapMapper;
import com.joyintech.service.entrust.etrwtfxswap.dto.EtrWtFxswapDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

/**
 *  外汇掉期委托微服务Service
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月18日
 * @see EtrWtFxswapService
 * @since 1.0
 */
@Service("backEtrWtFxswapService")
public class EtrWtFxswapService extends BaseService<EtrWtFxswapDto>{
    /**
     * 投资委托基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;
    
    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    /**
     * 自定义mapper
     */
    @Autowired
    private EtrWtFxswapMapper etrWtFxswapMapper;
    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    /**
     * 自定义mapper
     */
    @Autowired
    private EtrBoutRepoMapper etrBoutRepoMapper;
    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    /**
     * 外汇掉期委托分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<EtrWtFxswapDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
        param.put("F13", DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
        return queryForPage(page, length, () -> etrWtFxswapMapper.selectForPage(param));
    }
    
    /**
     * 校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrWtFxswapDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getOperateType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
        if(dto.getEtrInvestPersonInfo()!=null && StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())){
            traderList.add(dto.getEtrInvestPersonInfo().getTrader());
        }
        return baseEtrInvestInfoService.checkDelegateOperation(entrustId, delegateOperationType,
            traderList, delegateCloseWay);
    }
    
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
    }
    /**
     * 撤回委托
     * 
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrWtFxswapDto dto)
        throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        BeanUtil.copyProperties(etrEntrust, dto);
        BeanUtil.copyProperties(etrEntrustAdd, dto);
        List<EtrDistribute> etrDistributeList = new ArrayList<>();
        EtrDistribute etrDistribute = new EtrDistribute();
        BeanUtil.copyProperties(etrDistribute, dto);
        etrDistributeList.add(etrDistribute);
        entity.setEtrEntrust(etrEntrust);
        entity.setEtrEntrustAdd(etrEntrustAdd);
        entity.setEtrDistributeList(etrDistributeList);
        return baseEtrInvestInfoService.recallDelegate(entity);
    }
    /**
     * 删除委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return baseEtrInvestInfoService.deleteDelegate(entrustId);
    }
    
    /**
     * 查询修改/查看反显数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrWtFxswapDto selectByKey(String entrustId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrWtFxswapDto etrProjectDto = etrWtFxswapMapper.selectByKey(entrustId);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        if(etrProjectDto.getRemoteDeliveryDate().equals(" - ")){
            etrProjectDto.setRemoteDeliveryDate("");
        }
        if(etrProjectDto.getProximalDeliveryDate().equals(" - ")){
            etrProjectDto.setProximalDeliveryDate("");
        }
        etrProjectDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrProjectDto;
    }
    
    /**
     * 工作流完成方法 <功能详细描述>
     * 
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(EtrWtFxswapDto dto, String operationType)
        throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
            etrEntrust.setPs(dto.getTradePs());
            etrEntrust.setCcy(dto.getTradeCcy());
            etrEntrust.setIsOutTrade(DictCodeList.是否_是.getValue());
            etrEntrust.setEntrustAmt(dto.getTradeAmt());
            if(StringUtil.isNotEmpty(dto.getTradeCcyAvailablePosition())){
                etrEntrust.setProAvailableCash(dto.getTradeCcyAvailablePosition());
            }
            if(StringUtil.isNotEmpty(dto.getSwapsPoint())){
                etrEntrust.setPrice(dto.getSwapsPoint());
             }
            etrEntrustAdd.setEntrustId(dto.getEntrustId());
            etrEntrustAdd.setExcDealType(dto.getTradeType());
            etrEntrustAdd.setCurPair(dto.getCcyPair());
            etrEntrustAdd.setCorCcy(dto.getContraCcy());
            etrEntrustAdd.setDealCurAcct(dto.getTradeCcyAcct());
            etrEntrustAdd.setCorCurAcct(dto.getContraCcyAcct());
            etrEntrustAdd.setExcFxsTermType(dto.getTermType());
            // add by wangguoxian 修复YUCHUBS-52投资端-外汇即期委托，提交后系统异常 start
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_外汇掉期.getValue());
            // add by wangguoxian 修复YUCHUBS-52投资端-外汇即期委托，提交后系统异常 end
            
            if(StringUtil.isNotEmpty(dto.getProximalDeliveryDate())){
                List<String> proximalDeliveryDateList = StringUtil.splitToList(" ", dto.getProximalDeliveryDate());
                etrEntrustAdd.setVdate(DateUtil.parse(proximalDeliveryDateList.get(0)));
                etrEntrustAdd.setVdate2(DateUtil.parse(proximalDeliveryDateList.get(2)));
             }else{
                 etrEntrustAdd.setVdate(null);
                 etrEntrustAdd.setVdate2(null); 
             }
            if(StringUtil.isNotEmpty(dto.getRemoteDeliveryDate())){
                List<String> remoteDeliveryDateList = StringUtil.splitToList(" ", dto.getRemoteDeliveryDate());
                etrEntrustAdd.setMdate(DateUtil.parse(remoteDeliveryDateList.get(0)));
                etrEntrustAdd.setMdate2(DateUtil.parse(remoteDeliveryDateList.get(2)));
             }else{
                 etrEntrustAdd.setMdate(null);
                 etrEntrustAdd.setMdate2(null); 
             }
            
            
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,dto.getEtrInvestPersonInfo().getTrader());
                for(String trader:traderList){
                    EtrDistribute etrDistribute = new EtrDistribute();
                    //设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    //设置指定交易员指定交易员 = 指定交易员       
                    etrDistribute.setTrader(trader);
                    //设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                //entity.setEtrDistributeList(etrDistributeList);
            }
            //自动分发
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        entity.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.newDelegate(entity);
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        else {
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
            etrEntrust.setPs(dto.getTradePs());
            etrEntrust.setCcy(dto.getTradeCcy());
            etrEntrust.setIsOutTrade(DictCodeList.是否_是.getValue());
            etrEntrust.setEntrustAmt(dto.getTradeAmt());
            if(StringUtil.isNotEmpty(dto.getTradeCcyAvailablePosition())){
                etrEntrust.setProAvailableCash(dto.getTradeCcyAvailablePosition());
            }
            if(StringUtil.isNotEmpty(dto.getSwapsPoint())){
                etrEntrust.setPrice(dto.getSwapsPoint());
             }
            etrEntrustAdd.setEntrustId(dto.getEntrustId());
            etrEntrustAdd.setExcDealType(dto.getTradeType());
            etrEntrustAdd.setCurPair(dto.getCcyPair());
            etrEntrustAdd.setCorCcy(dto.getContraCcy());
            etrEntrustAdd.setDealCurAcct(dto.getTradeCcyAcct());
            etrEntrustAdd.setCorCurAcct(dto.getContraCcyAcct());
            etrEntrustAdd.setExcFxsTermType(dto.getTermType());
            // add by wangguoxian 修复YUCHUBS-52投资端-外汇即期委托，提交后系统异常 start
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_外汇掉期.getValue());
            // add by wangguoxian 修复YUCHUBS-52投资端-外汇即期委托，提交后系统异常 end
            if(StringUtil.isNotEmpty(dto.getProximalDeliveryDate())){
                List<String> proximalDeliveryDateList = StringUtil.splitToList(" ", dto.getProximalDeliveryDate());
                etrEntrustAdd.setVdate(DateUtil.parse(proximalDeliveryDateList.get(0)));
                etrEntrustAdd.setVdate2(DateUtil.parse(proximalDeliveryDateList.get(2)));
             }else{
                 etrEntrustAdd.setVdate(null);
                 etrEntrustAdd.setVdate2(null); 
             }
            if(StringUtil.isNotEmpty(dto.getRemoteDeliveryDate())){
                List<String> remoteDeliveryDateList = StringUtil.splitToList(" ", dto.getRemoteDeliveryDate());
                etrEntrustAdd.setMdate(DateUtil.parse(remoteDeliveryDateList.get(0)));
                etrEntrustAdd.setMdate2(DateUtil.parse(remoteDeliveryDateList.get(2)));
             }else{
                 etrEntrustAdd.setMdate(null);
                 etrEntrustAdd.setMdate2(null); 
             }
            
            
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,dto.getEtrInvestPersonInfo().getTrader());
                for(String trader:traderList){
                    EtrDistribute etrDistribute = new EtrDistribute();
                    //设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    //设置指定交易员指定交易员 = 指定交易员       
                    etrDistribute.setTrader(trader);
                    //设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                //entity.setEtrDistributeList(etrDistributeList);
            }
            //自动分发
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        entity.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.updateDelegate(entity);
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
    }

    /**
     * 流程启动时为附件绑定工作流代码
     * <功能详细描述>
     * @param dto
     * @param operateType
     * @see
     * @since 1.0
     */
    public void doInsert(EtrWtFxswapDto dto, String operateType) {
      //上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    /**
     * 
     * 判断交易对手类型
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String judgeCounterType(String counterId){
        String result = etrBoutRepoMapper.judgeCounterType(counterId);
        if(StringUtil.equals(DictCodeListExt1.交易对手类型_内部交易对手.getValue(), result)){
            return DictCodeList.是否_是.getValue();
        }else{
            return DictCodeList.是否_否.getValue();
        }
    }
    

    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getProAvailableCash(Map<String, Object> param) {
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
            return cashTG;
        }
        else {
            return null;
        }
    }

}
