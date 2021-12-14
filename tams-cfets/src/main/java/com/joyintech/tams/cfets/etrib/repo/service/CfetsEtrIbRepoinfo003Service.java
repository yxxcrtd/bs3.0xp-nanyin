/*
 * 文件名：EtrIbRepoinfo003Service.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月29日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.etrib.repo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.common.service.WFlowCommonService;
import com.joyintech.service.entrust.etrcommanddispatcher.service.EtrCommandDispatcherService;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.cfets.etrib.bond.service.CfetsEtribBondInfo003Service;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoPosPortfolioCashRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoPosPortfolioSecRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoPositionRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtribRepoInfo003Dto;
import com.joyintech.tams.common.base.dao.WflEtrEntrustAddMapper;
import com.joyintech.tams.common.base.dao.WflEtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.EtrPledge;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2021年01月29日
 * @see CfetsEtrIbRepoinfo003Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CfetsEtrIbRepoinfo003Service extends CommonExportService<CfetsEtribRepoInfo003Dto> implements BaseProcessService<CfetsEtribRepoInfo003Dto> {

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
    private CfetsEtribBondInfo003Service cfetsEtribBondInfo003Service;

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
     * @param type 操作类型
     */
    @Override
    public void doComplete(CfetsEtribRepoInfo003Dto dto, OperationType type) {
        try {
			doCompleteInsert(dto, type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} 
    }

	private void doCompleteInsert(CfetsEtribRepoInfo003Dto dto, OperationType type)
			throws IllegalAccessException, InvocationTargetException {
		BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        //add by tanxuan on 20200514 指令分发推送MQ  参数定义
        String isPushMsg = DictCodeList.是否_否.getValue();
        if (StringUtil.equals(type.toString(), "NEW")) {
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
//            由于dto.getEtrInvestPersonInfo()中InvManagerId为null，因此审批后的业务表中无项目经理，故隐藏掉这行代码
//            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
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
        } else {
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
//            由于dto.getEtrInvestPersonInfo()中InvManagerId为null，因此审批后的业务表中无项目经理，故隐藏掉这行代码
//            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
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
     * @param entity
     * @param operateType
     * @see
     * @since 1.0
     */
    @Override
    public void doInsert(CfetsEtribRepoInfo003Dto entity, OperationType operateType) {
    	if ("submit".equals(entity.getOperateType())) {
    		if (CollectionUtils.isNotEmpty(entity.getEtrInvestRepoInfoDtos())) {
                for (CfetsEtribRepoInfo003Dto dto : entity.getEtrInvestRepoInfoDtos()) {
                    doInsertFlow(dto);
                }
                //发送头寸
                sendPositionMethod(entity);
            }
        }
    }
     
    private  void sendPositionMethod(CfetsEtribRepoInfo003Dto entity){
        List<CfetsEtrIbRepoPosPortfolioCashRequest> ptlCashList=new ArrayList<>();
        List<CfetsEtrIbRepoPosPortfolioSecRequest> ptlSecList=new ArrayList<>();
        for (CfetsEtribRepoInfo003Dto dto : entity.getEtrInvestRepoInfoDtos()) {
            //调用头寸
            CfetsEtrIbRepoPosPortfolioCashRequest posport=new CfetsEtrIbRepoPosPortfolioCashRequest();
            posport.setPortfolioId(dto.getPortfolioId());
            posport.setEtrId(dto.getEntrustId());
            if(StringUtil.isNotEmpty(dto.getPortfolioId())){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("portfolioId", dto.getPortfolioId());
                map.put("acctType", BussinessDictCodeList.资金账户类型_托管账户.getValue());
                String cashAcctId =cfetsEtribBondInfo003Service.qryPortfolioCashAcctId(map);
                if (StringUtil.isNotEmpty(cashAcctId)) {
                    posport.setCashAcctId(cashAcctId); // 托管户
                }
            }
            posport.setCashAmount(dto.getEntrustAmt());
            ptlCashList.add(posport);
            if (StringUtil.isNotEmpty(dto.getBussRepoSec()) && !StringUtil.isEmptyList(dto.getBussRepoSec().getPledgeBondList())) {
                for(PledgeBondInfoDto pledgeBondInfoDto:dto.getBussRepoSec().getPledgeBondList()){
                    CfetsEtrIbRepoPosPortfolioSecRequest secport=new CfetsEtrIbRepoPosPortfolioSecRequest();
                    secport.setPortfolioId(dto.getPortfolioId());
                    secport.setEtrId(dto.getEntrustId());
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("portfolioId", dto.getPortfolioId());
                    map.put("finprodId", entity.getFinprodId());
                    String secAcctId = cfetsEtribBondInfo003Service.qryPortfolioSecAcctId(map);
                    if (StringUtil.isNotEmpty(secAcctId)) {
                        secport.setSecAcctId(secAcctId); // 证券账户
                    }
                    secport.setSecAmount(pledgeBondInfoDto.getPledgePrin());
                    secport.setOwnSecAmount(pledgeBondInfoDto.getPledgePrin());
                    secport.setIntegrateSecAmount(BigDecimal.ZERO);
                    ptlSecList.add(secport);
                }
            }
        }
        //调用头寸
        CfetsEtrIbRepoPositionRequest req=new CfetsEtrIbRepoPositionRequest();
        req.setInterfaceType("REPO001");
        req.setUserId(SecurityUtil.getLoginUser().getUserName());
        req.setTotalTradeId(entity.getTotalEtrustId());
        req.setFlowStatus(DictCodeListExt3.流程节点_流程提交.getValue());
        req.setModelType(DictCodeListExt1.子系统代码_委托.getValue());
        String balanceCheck="Y";
        if(StringUtil.isNotEmpty(entity.getSoftSkipMode()) && StringUtil.equals(entity.getSoftSkipMode(),"Y")){
            balanceCheck="N";
        }
        req.setBalanceCheck(balanceCheck);
        req.setBusiType(DictCodeListExt3.头寸业务类型_质押式回购首期.getValue());
        req.setTradePlace(BussinessDictCodeList.交易场所_银行间.getValue());
        req.setSettleSpeed(entity.getSettleSpreed());
        req.setBusiAct(DictCodeListExt3.业务动作_投资指令.getValue());
        req.setPs(entity.getPs());
        req.setSystemAct(DictCodeListExt3.系统动作_新建.getValue());
        req.setCdate(entity.getEntrustDate());
        req.setCcy(entity.getCcy());
        req.setPtlCashList(ptlCashList);
        req.setPtlSecList(ptlSecList);
        PositionHttpRequest.send(req);
    }

	public void doInsertFlow(CfetsEtribRepoInfo003Dto dto) {
    	try {
            dto.setOperateType(dto.getOperateType());
            BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
            EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
            EtrEntrust etrEntrust = new EtrEntrust();
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            entrustCommonDto.setEtrEntrust(etrEntrust);
            entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
            entrustCommonDto.setBussRepoSecDto(dto.getBussRepoSec());
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeListExt3.委托状态_流程中.getValue());
            dto.setRecStatus(DictCodeListExt3.委托状态_流程中.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            //BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(dto.getEntrustAmt());
            etrEntrust.setInvManagerId(dto.getInvManagerId());
            etrEntrust.setTradeMarket(dto.getTradeMarket());
            etrEntrust.setTradePlat(dto.getTradePlat());
            if(StringUtil.isNotEmpty(etrEntrust.getPrice())){
            	etrEntrust.setPrice(etrEntrust.getPrice().divide(new BigDecimal(100)));
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
            etrEntrustAdd.setVdate(dto.getVdate());
            etrEntrustAdd.setMdate(dto.getMdate());
            etrEntrustAdd.setPledgeStockId(dto.getZyqk());
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                    DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)1);
            }
            if(StringUtil.isNotEmpty(dto.getTermType())){
                etrEntrustAdd.setTermType(dto.getTermType());
            }
            if(dto.getRepoTerm()!=null){
                etrEntrustAdd.setTermDays(dto.getRepoTerm().longValue());
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            entity.setEtrPledgeList(getEtrPledgeListByBussRepoSecDto(dto.getBussRepoSec(),dto.getEntrustId()));
            entity.setWorkFlowId(dto.getWorkflowId());
            WFlowCommonService.insertFlow(entity);
//            if(baseEtrCashPositionCommService!=null){
//                baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
//            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
	
	private List<EtrPledge> getEtrPledgeListByBussRepoSecDto(BussRepoSecDto bussRepoSec,String bizCode){
	   	 if(StringUtil.isNotEmpty(bussRepoSec)){
	   		 List<EtrPledge> etrPledgeList = new ArrayList<>();
	   		 for (PledgeBondInfoDto pledgeBond : bussRepoSec.getPledgeBondList()) {
	                // 回购委托新建
	                EtrPledge etrPledge = new EtrPledge();
	                // 委托代码 = 委托代码
	                etrPledge.setEntrustId(bizCode);
	                // 金融产品代码 = 输出质押品DTO.金融产品代码
	                etrPledge.setFinprodId(pledgeBond.getFinprodId());
	                // 质押面值 = 输出质押品DTO.质押面值
	                etrPledge.setPledgeFaceValue(new BigDecimal("100"));
	                // 质押数量 = 输出质押品DTO.质押数量
	                etrPledge.setPledgeNumber(ArithmeticUtil.divide(pledgeBond.getPledgePrin(), new BigDecimal("100"), 4));
	                // 质押率 = 输出质押品DTO.质押率
	                etrPledge.setPledgeRatio(ArithmeticUtil.divide(pledgeBond.getPledgeRatio(), new BigDecimal("100"), 4));
	                // 质押金额 = 输出质押品DTO.质押金额
	                etrPledge.setPledgeAmt(ArithmeticUtil.multiply(pledgeBond.getPledgePrin(), etrPledge.getPledgeRatio()));
	                etrPledgeList.add(etrPledge);
	                return etrPledgeList;
	       	 }
	   	 }
	   	 return null;
	}




}
