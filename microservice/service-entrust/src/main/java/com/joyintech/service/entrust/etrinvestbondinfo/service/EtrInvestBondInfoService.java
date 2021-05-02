/*
 * 文件名：EtrInvestBondInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrinvestbondinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.common.service.EntrustCommonPersonSelectService;
import com.joyintech.service.entrust.etrinvestbondinfo.dao.EtrInvestBondInfoMapper;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.pagemenusplit.PageMenuSplitUtil;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 债券投资委托微服务端service 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年9月11日
 * @see EtrInvestBondInfoService
 * @since 1.0
 */
@Service("backEtrInvestBondInfoService")
public class EtrInvestBondInfoService extends BaseService<EtrInvestBondInfoDto> {

    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;

    @Autowired
    private EntrustCommonPersonSelectService entrustCommonPersonSelectService;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 自定义债券投资委托mapper
     */
    @Autowired(required = false)
    private EtrInvestBondInfoMapper etrInvestBondInfoMapper;

    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 组合可用持仓和实际持仓查询更新组件接口
     */
    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

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
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    /**
     * 获取组合代码的联想文本框 <功能详细描述>
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(Map<String, Object> map) {
        map.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        map.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return etrInvestBondInfoMapper.getentrustId(map);
    }

    /**
     * 获取资产编号的联想文本框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getfinprodId(Map<String, Object> param) {
        Map<String, Object> map = new HashMap<>();
        param.put("param", param.get("finprodId"));
        map.put("assestType", DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        // add by etliu 20210501 银行间场内场外拆分 start
        map.put("defaultTradeMarketList", PageMenuSplitUtil.getTradeMarketList((String) param.get("pageId")));
        // add by etliu 20210501 银行间场内场外拆分 end

        return etrInvestBondInfoMapper.getfinprodId(map);

    }

    /**
     * 债券委托投资分页查询 <功能详细描述>
     * 
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<EtrInvestBondInfoDto> selectForPage(int page, int length,
                                                    Map<String, Object> param)
        throws Exception {
        param.put("bond", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        //子组合查询改造 王贵奇 2019/8/20
        param.put("portfolioType", DictCodeListExt1.组合类型_子组合.getValue());
        param.put("portfolioType1", DictCodeListExt1.组合类型_投资类组合.getValue());
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        //排除可转债委托 add by gejiaao 20191126
        param.put("PS_T04",DictCodeList.交易方向_申购.getValue());

        // add by etliu 20210501 银行间场内场外拆分 start
        param.put("defaultTradeMarketList", PageMenuSplitUtil.getTradeMarketList((String) param.get("pageId")));
        param.put("defaultTradePlatFormList", PageMenuSplitUtil.getTradePlatFormList((String) param.get("pageId")));
        // add by etliu 20210501 银行间场内场外拆分 end

        Page<EtrInvestBondInfoDto> pageDto = queryForPage(page, length,
            () -> etrInvestBondInfoMapper.selectForPage(param));

//        List<EtrInvestBondInfoDto> dtoList = pageDto.getRoot();
//        dtoList = entrustCommonPersonSelectService.getRealName(dtoList);
//        pageDto.setRoot(dtoList);
        return pageDto;
    }

    /**
     * 查询用户信息 <功能详细描述>
     * 
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    // @DataSource(DataSourceType.OTHER)
    // @Transactional(propagation=Propagation.REQUIRES_NEW)
    // public List<EtrInvestBondInfoDto> getUser(List<EtrInvestBondInfoDto> dtoList){
    // for(EtrInvestBondInfoDto dto:dtoList){
    // String realName = etrInvestBondInfoMapper.getUser(dto.getInvManagerId());
    // dto.setInvManagerId(realName);
    // }
    // return dtoList;
    // }
    /**
     * 获取新建界面的资产代码联想下拉框框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length,Map<String, Object> param) {
        param.put("assestType", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("isautobond", CacheUtil.getSysParam("IS_AUTOBOND"));

        // add by etliu 20210501 银行间场内场外拆分 start
        param.put("defaultTradeMarketList", PageMenuSplitUtil.getTradeMarketList((String) param.get("parentPageId")));
        // add by etliu 20210501 银行间场内场外拆分 end

        return this.queryForPageForSelectReturn(page, length,()->etrInvestBondInfoMapper.getfinprodIdForAdd(param));
    }

    /**
     * 获取新建界面的组合代码联想下拉框框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getentrustIdAdd(int page, int length,Map<String, Object> param) {
    	String types = DictCodeListExt1.组合类型_子组合.getValue()+","+DictCodeListExt1.组合类型_投资类组合.getValue();
        param.put("portTypes", StringUtil.splitToList(GlobalConstant.COMMA,types));
        return this.queryForPageForSelectReturn(page, length,()->etrInvestBondInfoMapper.getentrustIdAdd(param));
    }

    /**
     * 画面校验 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrInvestBondInfoDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getOperateType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
        traderList.add(dto.getTrader());
        return baseEtrInvestInfoService.checkDelegateOperation(entrustId, delegateOperationType,
            traderList, delegateCloseWay);
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
    public boolean recallDelegate(EtrInvestBondInfoDto dto)
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
     * 关闭委托 <一句话功能简述> <功能详细描述>
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
     * 删除投资委托 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return baseEtrInvestInfoService.deleteDelegate(entrustId);
    }

    /**
     * 可用面值计算 <功能详细描述>
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getAvailableNum(Map<String, Object> map) {
        BigDecimal result = etrInvestBondInfoMapper.getAvailableNum(map);
        return result == null ? new BigDecimal(100) : result;
    }

    /**
     * 根据主键查询修改/查看画面信息 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrInvestBondInfoDto selectByKey(String entrustId, String finprodId,
                                            String portfolioId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entrustId", entrustId);
        map.put("finprodId", finprodId);
        map.put("portfolioId", portfolioId);
        map.put("bond", DictCodeListExt1.金融产品类型_债券.getValue());
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrInvestBondInfoDto etrInvestBondInfoDto = etrInvestBondInfoMapper.selectByKey(map);
        etrInvestBondInfoDto.setSettleSpreed(String.valueOf(etrInvestBondInfoDto.getSettleSpeed()));
        String trader = "";
        for (int i = 0; i < traderList.size(); i++ ) {
            trader = trader + "," + traderList.get(i);
        }
        etrInvestBondInfoDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrInvestBondInfoDto;
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
    public void doComplete(EtrInvestBondInfoDto dto, String operationType)
        throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(null);
            etrEntrust.setEntrustNum(dto.getEntrustNum().divide(new BigDecimal(100)));
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_债券.getValue());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            etrEntrustAdd.setOurEntNum(ArithmeticUtil.divide(dto.getEntrustOwnNum(), 100, 4));
            etrEntrustAdd.setLendinEntNum(ArithmeticUtil.divide(dto.getEntrustIntegrateNum(), 100, 4));
            if (dto.getExerYield() != null) {
                etrEntrustAdd.setExerYield(dto.getExerYield().divide(new BigDecimal(100)));
            }
            if (dto.getMatureYield() != null) {
                etrEntrustAdd.setMatureYield(dto.getMatureYield().divide(new BigDecimal(100)));
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)1);
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
                }
                else {
                    // 查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if (busiAutoEtrDispService.checkEntrustTime(etrEntrust)) {
                        // 自动分发
                        entity.setEtrDistributeList(
                            busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
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
        }
        else {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            dto.setIsOutTrade(DictCodeList.是否_是.getValue());
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(null);
            etrEntrust.setEntrustNum(dto.getEntrustNum().divide(new BigDecimal(100)));
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_债券.getValue());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            etrEntrustAdd.setOurEntNum(ArithmeticUtil.divide(dto.getEntrustOwnNum(), 100, 4));
            etrEntrustAdd.setLendinEntNum(ArithmeticUtil.divide(dto.getEntrustIntegrateNum(), 100, 4));
            if (dto.getExerYield() != null) {
                etrEntrustAdd.setExerYield(dto.getExerYield().divide(new BigDecimal(100)));
            }
            if (dto.getMatureYield() != null) {
                etrEntrustAdd.setMatureYield(dto.getMatureYield().divide(new BigDecimal(100)));
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)1);
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
                }
                else {
                    // 查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if (busiAutoEtrDispService.checkEntrustTime(etrEntrust)) {
                        // 自动分发
                        entity.setEtrDistributeList(
                            busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
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
        if(baseEtrCashPositionCommService!=null){
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
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
    public void doInsert(EtrInvestBondInfoDto dto, String operateType) {
        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
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
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse((String)param.get("sysDate"));
        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));
        Map<String, Object> result = new HashMap<>();
        dto.setAcctType(acctTypeList);

        if (basePtlCashPositionInfoService != null) {
            BigDecimal cashT3 = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT3", cashT3);

            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT", cashT);
        }
        if (StringUtil.isNotEmpty(param.get("calId"))) {
            PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
            Date sysdateT1 = DateUtil.parse((String)param.get("sysDateT1"));
            dto2.setCdate(sysdateT1);
            dto2.setAcctType(acctTypeList);
            dto2.setPortfolioId((String)param.get("portfolioId"));
            dto2.setCcy((String)param.get("ccy"));
            if (basePtlCashPositionInfoService != null) {
                BigDecimal cashT1 = basePtlCashPositionInfoService.queryUsableCashPosition(dto2);
                result.put("cashT1", cashT1);
            }
        }

        if (StringUtil.isNotEmpty(param.get("chlFinprodId"))) {
//            acctTypeList = new ArrayList<>();
//            acctTypeList.add(DictCodeListExt1.资金账户类型_通道账户.getValue());
//            dto.setAcctType(acctTypeList);
            dto.setAcctType(null);
            dto.setChlFinprodId((String) param.get("chlFinprodId"));
            if (basePtlCashPositionInfoService != null) {
                BigDecimal cashT2 = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
                result.put("cashT2", cashT2);
            }
        }

        return result;
    }

    /**
     * 组合可用持仓查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getAssetAvailablePosition(Map<String, Object> param) {
        PtlSecPositionFlowDto dto = new PtlSecPositionFlowDto();
        dto.setEntrust(true);
        Date sysdate = DateUtil.parse((String)param.get("sysDate"));
        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setAcctType(acctTypeList);
        dto.setFinprodId((String)param.get("finprodId"));
        dto.setCcy((String)param.get("ccy"));
        dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
        dto.setPortfolioId((String)param.get("portfolioId"));

        // 一体化对应etliu start
        dto.setInvAim((String)param.get("invAim"));
        // 一体化对应etliu end

        if (StringUtil.isNotEmpty((String) param.get("chlId"))) {
            dto.setChannel(true);
            dto.setSecAcctId((String) param.get("chlId"));
        } else {
            dto.setSecAcctId((String) param.get("secAcctId"));
        }

        Map<String, Object> result = new HashMap<>();
        if (basePtlSecPositionInfoService != null) {
            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal position = basePtlSecPositionInfoService.queryUsableSecPosition(dto);
            result.put("position", ArithmeticUtil.multiply(position, new BigDecimal(100)));
        }
        if (StringUtil.isNotEmpty(param.get("sysDateT1"))) {
            PtlSecPositionFlowDto dto2 = new PtlSecPositionFlowDto();
            Date sysdateT1 = DateUtil.parse((String)param.get("sysDateT1"));
            dto2.setCdate(sysdateT1);
            dto2.setAcctType(acctTypeList);
            dto2.setFinprodId((String)param.get("finprodId"));
            dto.setCcy((String)param.get("ccy"));
            dto2.setHodingType(DictCodeList.持仓类型_可用.getValue());
            dto2.setPortfolioId((String)param.get("portfolioId"));
            if (basePtlSecPositionInfoService != null) {
                BigDecimal positionT1 = basePtlSecPositionInfoService.queryUsableSecPosition(dto2);
                result.put("positionT1", ArithmeticUtil.multiply(positionT1, new BigDecimal(100)));
            }
        }
        return result;
    }
    
    /**
     * 查询估值净价
     * @author wangguiqi
     */
	public BigDecimal getClosePriceByPara(Map<String, Object> param) {
		return etrInvestBondInfoMapper.getClosePriceByPara(param);
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
        return this.queryForSuggest(() -> etrInvestBondInfoMapper.getSecManageAcctId(param),
            null);
    }
}
