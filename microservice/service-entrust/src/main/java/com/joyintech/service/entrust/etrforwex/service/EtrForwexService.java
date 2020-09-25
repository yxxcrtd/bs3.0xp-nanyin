/*
 * 文件名：EtrForwexService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrforwex.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrforwex.dao.EtrForwexMapper;
import com.joyintech.service.entrust.etrforwex.dto.EtrForwexDto;
import com.joyintech.service.entrust.etrfund.dao.EtrFundMapper;
import com.joyintech.service.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年6月25日
 * @see EtrForwexService
 * @since 1.0
 */
@Service
public class EtrForwexService extends BaseService<EtrForwexDto> {
	
	/**
     * 自定义mappper
     */
    @Autowired
	private EtrForwexMapper etrForwexMapper;
    
    /**
     * 自定义mappper
     */
    @Autowired
    private EtrFundMapper etrFundMapper;

    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;
    
    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    
    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    
    /**
     * 平台日期
     */
    @Autowired(required=false)
    private PlatformDateImpl platformDateImpl;

    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    
    /**
     * 使用给定的检索分页数据。返回类型是Service的泛型。
     * 
     * @param length int
     * @param page int
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<EtrForwexDto> selectForPage(int page, int length,
                                                  Map<String, Object> pageParam)
        throws Exception {
        String assetType1 = (String)pageParam.get("assetType1");
        if(StringUtil.isNotEmpty(assetType1)){
            pageParam.put("assetType1List", StringUtil.splitToList(GlobalConstant.COMMA,assetType1));
        }
        String ps = (String)pageParam.get("ps");
        if(StringUtil.isNotEmpty(ps)){
            pageParam.put("psList", StringUtil.splitToList(GlobalConstant.COMMA,ps));
        }
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        pageParam.put("endDate", DateUtil.parse((String)pageParam.get("endDate")));
        pageParam.put("startDate", DateUtil.parse((String)pageParam.get("startDate")));
        return super.queryForPage(page, length, () -> etrForwexMapper.select(pageParam));
    }
    
    
    /**
     * 
     * 组合代码联想文本框 
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioId(Map<String, Object> param){
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return etrFundMapper.getPortfolioId(param);
    }
    
	 /**
     * 关闭、撤销、删除委托画面校验 
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrForwexDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getEntrustOperationType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
        traderList.add(dto.getTrader());
        return baseEtrInvestInfoService.checkDelegateOperation(entrustId, delegateOperationType,
            traderList, delegateCloseWay);
    }
    
    /**
     * 
     * 删除委托
     * 调用委托基础组件“删除委托”
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        if(baseEtrInvestInfoService != null){
            return  baseEtrInvestInfoService.deleteDelegate(entrustId);
        }
        else{
            return false;
        }
    }
    
    /**
     * 
     * 撤回委托
     * 调用基础组件“撤回委托”
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(String entrustId){
        //投资委托信息dto
        BaseEtrInvestInfoDto dto = new BaseEtrInvestInfoDto();
        //委托指令
        EtrEntrust etrEntrust = new EtrEntrust();
        etrEntrust.setEntrustId(entrustId);
        dto.setEtrEntrust(etrEntrust);
        if(baseEtrInvestInfoService != null){
            return baseEtrInvestInfoService.recallDelegate(dto);
        }
        else{
            return false;
        }
    }
    
    
    /**
     * 
     * 关闭委托
     * 调用基础组件“关闭委托”
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId,String closeWay){
        if(baseEtrInvestInfoService != null){
            return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
        }
        else{
            return false;
        }
    }
    
    
    /**
     * 新建页面组合代码联想下拉框 <功能详细描述>
     * 
     * @param map Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSelectPortfolioId(int page,int length,Map<String, Object> map) {
        map.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        map.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return this.queryForPageForSelectReturn(page, length,() -> etrFundMapper.getSelectPortfolioId(map));
    }
    
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity EtrForwexDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    public void doInsert(EtrForwexDto entity)
        throws IllegalAccessException,
        InvocationTargetException {
        //文件
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setFileList(entity.getFileArray());
        mstFileInputParameter.setBussinessId(entity.getWorkflowId());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity EtrForwexDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    public void doDelete(EtrForwexDto entity)
        throws IllegalAccessException,
        InvocationTargetException {
        //文件
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setFileList(entity.getFileArray());
        mstFileInputParameter.setBussinessId(entity.getEntrustId());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    
    /**
     * 实现doComplete方法
     * @param entity EtrForwexDto
     * @throws InvocationTargetException IllegalAccessException
     * @throws IllegalAccessException InvocationTargetException
     */
    public void doComplete(EtrForwexDto entity)
        throws IllegalAccessException,
        InvocationTargetException {
        // 投资委托信息dto
        BaseEtrInvestInfoDto baseEtrInvestInfoDto = new BaseEtrInvestInfoDto();
        String type = entity.getOperateType();
        if (StringUtil.equals(type, "NEW")) {
            // 委托指令Entity
            EtrEntrust etrEntrust = new EtrEntrust();
            BeanUtil.copyProperties(etrEntrust, entity);
            BeanUtil.copyProperties(etrEntrust, entity.getBussEtrInvestPersonInfoDto());
            //设置委托状态
            etrEntrust.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
            etrEntrust.setIsOutTrade(DictCodeList.是否_是.getValue());
            if(platformDateImpl != null){
                etrEntrust.setEntrustDate(platformDateImpl.getSysDate()); 
            }
            // 委托指令附属表Entity
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            BeanUtil.copyProperties(etrEntrustAdd, entity);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_外汇远期.getValue());
            //设置起息日到期日
            etrEntrustAdd.setVdate(entity.getSettleVdate());
            etrEntrustAdd.setMdate(entity.getSettleMdate());
            // List<委托指令回购质押品Entity>

            // List<委托指令分发Entity>
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,entity.getBussEtrInvestPersonInfoDto().getTrader());
            for(String trader:traderList){
                EtrDistribute etrDistribute = new EtrDistribute();
                //设置接收状态
                etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                //设置指定交易员指定交易员 = 指定交易员       
                etrDistribute.setTrader(trader);
                //设置委托代码
                etrDistribute.setEntrustId(entity.getEntrustId());
                //
                etrDistributeList.add(etrDistribute);
            }
            // List<委托完成状况表Entity>
            List<EtrFinishStatus> etrFinishStatusList = new ArrayList<>();
           /* EtrFinishStatus etrFinishStatus = new EtrFinishStatus();
            BeanUtil.copyProperties(etrFinishStatus, entity);
            etrFinishStatusList.add(etrFinishStatus);*/

            baseEtrInvestInfoDto.setEtrEntrust(etrEntrust);
            baseEtrInvestInfoDto.setEtrEntrustAdd(etrEntrustAdd);
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    baseEtrInvestInfoDto.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        baseEtrInvestInfoDto.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            baseEtrInvestInfoDto.setEtrFinishStatusList(etrFinishStatusList);

            // 新建委托
            if (baseEtrInvestInfoService != null) {
                baseEtrInvestInfoService.newDelegate(baseEtrInvestInfoDto);
            }
            //文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(entity.getFileArray());
            mstFileInputParameter.setBussinessId(entity.getEntrustId());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }

        }
        else {
            // 委托指令Entity
            EtrEntrust etrEntrust = new EtrEntrust();
            BeanUtil.copyProperties(etrEntrust, entity);
            etrEntrust.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
            etrEntrust.setIsOutTrade(DictCodeList.是否_是.getValue());
            if(platformDateImpl != null){
                etrEntrust.setEntrustDate(platformDateImpl.getSysDate()); 
            }
            // 委托指令附属表Entity
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            BeanUtil.copyProperties(etrEntrustAdd, entity);
//            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_基金.getValue());
            BeanUtil.copyProperties(etrEntrust, entity.getBussEtrInvestPersonInfoDto());
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_外汇远期.getValue());
            //设置起息日到期日
            etrEntrustAdd.setVdate(entity.getSettleVdate());
            etrEntrustAdd.setMdate(entity.getSettleMdate());
            // List<委托指令回购质押品Entity>

            // List<委托指令分发Entity>
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,entity.getBussEtrInvestPersonInfoDto().getTrader());
            for(String trader:traderList){
                EtrDistribute etrDistribute = new EtrDistribute();
                //设置接收状态
                etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                //设置指定交易员指定交易员 = 指定交易员       
                etrDistribute.setTrader(trader);
                //设置委托代码
                etrDistribute.setEntrustId(entity.getEntrustId());
                //
                etrDistributeList.add(etrDistribute);
            }
            
            // List<委托完成状况表Entity>
            List<EtrFinishStatus> etrFinishStatusList = new ArrayList<>();
            /*EtrFinishStatus etrFinishStatus = new EtrFinishStatus();
            BeanUtil.copyProperties(etrFinishStatus, entity);
            etrFinishStatusList.add(etrFinishStatus);*/

            baseEtrInvestInfoDto.setEtrEntrust(etrEntrust);
            baseEtrInvestInfoDto.setEtrEntrustAdd(etrEntrustAdd);
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    baseEtrInvestInfoDto.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        baseEtrInvestInfoDto.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            baseEtrInvestInfoDto.setEtrFinishStatusList(etrFinishStatusList);

            // 修改委托
            if (baseEtrInvestInfoService != null) {
                baseEtrInvestInfoService.updateDelegate(baseEtrInvestInfoDto);
            }
            
            //上传文件修改更新
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setBussinessId(entity.getEntrustId());
            mstFileInputParameter.setFileList(entity.getFileArray());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
    }
    
    
    /**
     * 编辑、双击查看页面详情 <功能详细描述>
     * 
     * @param entrustId String
     * @return EtrForwexDto
     * @see
     * @since 1.0
     */
    public EtrForwexDto selectByKey(String entrustId) {
        EtrForwexDto etrForwexDto = etrForwexMapper.selectByKey(entrustId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        etrForwexDto.getBussEtrInvestPersonInfoDto().setTrader(trader);
        return etrForwexDto;
    }
    
    
    
    
}
