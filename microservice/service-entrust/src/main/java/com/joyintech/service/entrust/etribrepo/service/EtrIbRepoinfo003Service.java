/*
 * 文件名：EtrIbRepoinfo003Service.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月29日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.service.entrust.etribrepo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etrcommanddispatcher.service.EtrCommandDispatcherService;
import com.joyintech.service.entrust.etribrepo.dao.EtrIbRepoinfo002Mapper;
import com.joyintech.service.entrust.etribrepo.dto.EtribRepoInfo003Dto;
import com.joyintech.tams.common.base.dao.WflEtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.WflEtrEntrustMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2021年01月29日
 * @see EtrIbRepoinfo003Service
 * @since 1.0
 */
@Service("backEtrIbRepoinfo003Service")
@Transactional(rollbackFor = Exception.class)
public class EtrIbRepoinfo003Service {

    @Autowired
    private EtrIbRepoinfo002Mapper etrIbRepoinfo002Mapper;
    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    /**
     * 委托自动分发
     */
    @Autowired(required = false)
    private BusiAutoEtrDispService busiAutoEtrDispService;

    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    @Autowired
    private EtrCommandDispatcherService etrCommandDispatcherService;

    /**
     * 委托指令流程中Mapper
     */
    @Autowired
    private WflEtrEntrustMapper wflEtrEntrustMapper;

    /**
     * 委托指令附属表流程中Mapper
     */
    @Autowired
    private WflEtrEntrustAddMapper wflEtrEntrustAddMapper;

    /**
     * 流程结束后操作
     *
     * @param dto           实体类
     * @param operationType 操作类型
     */
    public void doComplete(EtribRepoInfo003Dto dto, String operationType) throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        //add by tanxuan on 20200514 指令分发推送MQ  参数定义
        String isPushMsg = DictCodeList.是否_否.getValue();
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(dto.getEntrustAmt());
            //做交易录单时交易日历丢失 交易日历默认银行间 add by libaisong 2021-03-01 start
            etrEntrust.setCalendarId("03");
            //做交易录单时交易日历丢失 交易日历默认银行间 add by libaisong 2021-03-01 end
            etrEntrust.setTradeMarket( DictCodeListExt1.交易场所_银行间.getValue());
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
            //指令接收查询交易方向丢失 add by libaisong 2021-03-01 start
            etrEntrustAdd.setBusiPs(dto.getPs());
            //指令接收查询交易方向丢失 add by libaisong 2021-03-01 end
            etrEntrustAdd.setVdate(dto.getVdate());
            etrEntrustAdd.setMdate(dto.getMdate());
            etrEntrustAdd.setPledgeStockId(dto.getZyqk());
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long) 0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long) 1);
            }
            if(StringUtil.isNotEmpty(dto.getTermType())){
                etrEntrustAdd.setTermType(dto.getTermType());
            }
            if(dto.getRepoTerm()!=null){
                etrEntrustAdd.setTermDays(dto.getRepoTerm().longValue());
            }
            if(StringUtil.isNotEmpty(etrEntrust.getPrice())){
            	etrEntrust.setPrice(etrEntrust.getPrice().divide(new BigDecimal(100)));
            }
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {

                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                        dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                // entity.setEtrDistributeList(etrDistributeList);
            }
            // 自动分发
            if (busiAutoEtrDispService != null) {
                // 检测是否设置交易员
                if (!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)) {
                    // 跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                    isPushMsg = DictCodeList.是否_是.getValue();
                } else {
                    // 查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if (busiAutoEtrDispService.checkEntrustTime(etrEntrust)) {
                        // 自动分发
                        entity.setEtrDistributeList(
                                busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                        isPushMsg = DictCodeList.是否_是.getValue();
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.newDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        } else {
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(dto.getEntrustAmt());
            if(StringUtil.isNotEmpty(etrEntrust.getPrice())){
                etrEntrust.setPrice(etrEntrust.getPrice().divide(new BigDecimal(100)));
            }
            //做交易录单时交易日历丢失 交易日历默认银行间 add by libaisong 2021-03-01 start
            etrEntrust.setCalendarId("03");
            //做交易录单时交易日历丢失 交易日历默认银行间 add by libaisong 2021-03-01 end
            etrEntrust.setTradeMarket( DictCodeListExt1.交易场所_银行间.getValue());
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
            //指令接收查询交易方向丢失 add by libaisong 2021-03-01 start
            etrEntrustAdd.setBusiPs(dto.getPs());
            //指令接收查询交易方向丢失 add by libaisong 2021-03-01 end
            etrEntrustAdd.setVdate(dto.getVdate());
            etrEntrustAdd.setMdate(dto.getMdate());
            etrEntrustAdd.setPledgeStockId(dto.getZyqk());
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long) 0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long) 1);
            }
            if(StringUtil.isNotEmpty(dto.getTermType())){
                etrEntrustAdd.setTermType(dto.getTermType());
            }
            if(dto.getRepoTerm()!=null){
                etrEntrustAdd.setTermDays(dto.getRepoTerm().longValue());
            }
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                        dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                // entity.setEtrDistributeList(etrDistributeList);
            }
            // 自动分发
            if (busiAutoEtrDispService != null) {
                // 检测是否设置交易员
                if (!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)) {
                    // 跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                    isPushMsg = DictCodeList.是否_是.getValue();
                } else {
                    // 查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if (busiAutoEtrDispService.checkEntrustTime(etrEntrust)) {
                        // 自动分发
                        entity.setEtrDistributeList(
                                busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                        isPushMsg = DictCodeList.是否_是.getValue();
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.updateDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        if (baseEtrCashPositionCommService != null) {
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }
        //add by tanxuan on 20200514 指令分发推送信息到交易系统
        if (DictCodeList.是否_是.getValue().equals(isPushMsg)) {
            Map<String, Object> param = new HashMap<>();
            param.put("entrustId", dto.getEntrustId());
            etrCommandDispatcherService.pushCommandMsg(param);
        }

    }

    /**
     * 流程启动时为附件绑定工作流代码 <功能详细描述>
     *
     * @param dto
     * @param operateType
     * @see
     * @since 1.0
     */
    public void doInsert(EtribRepoInfo003Dto dto, String operateType) {
        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }

    public boolean doDeleteEtrFlow(Map<String,Object> param){
        String entrustId = (String)param.get("entrustId");
        WflEtrEntrustKey wflEtrEntrustKey = new WflEtrEntrustKey();
        wflEtrEntrustKey.setEntrustId(entrustId);
        wflEtrEntrustKey.setWorkflowStatus(DictCodeListExt3.委托状态_流程中.getValue());

        WflEtrEntrustAddKey wflEtrEntrustAddKey = new WflEtrEntrustAddKey();
        wflEtrEntrustAddKey.setEntrustId(entrustId);
        wflEtrEntrustAddKey.setWorkflowStatus(DictCodeListExt3.委托状态_流程中.getValue());

        etrIbRepoinfo002Mapper.deleteWflEtrEntrustAddByEntrustId(entrustId);
        etrIbRepoinfo002Mapper.deleteWflEtrEntrustByEntrustId(entrustId);
//            wflEtrEntrustMapper.deleteByPrimaryKey(wflEtrEntrustKey);
//            // 删除委托指令附属
//            wflEtrEntrustAddMapper.deleteByPrimaryKey(wflEtrEntrustAddKey);

        return true;
    }

}
