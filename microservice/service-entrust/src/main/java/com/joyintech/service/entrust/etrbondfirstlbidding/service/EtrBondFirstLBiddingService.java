/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrbondfirstlbidding.service;

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
import com.joyintech.service.entrust.etrbondfirstlbidding.dao.EtrBondFirstLBiddingMapper;
import com.joyintech.service.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.common.base.dao.BidCloseStatusMapper;
import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.common.base.entity.BidCloseStatus;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.MstCounterParty;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.pagemenusplit.PageMenuSplitUtil;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 债券一级投标委托 后台处理
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年5月6日
 * @see EtrBondFirstLBiddingService
 * @since 1.0
 */
@Service
public class EtrBondFirstLBiddingService extends BaseService<EtrBondFirstLBiddingDto>{
    
    /**
     * 自定义债券一级投标委托mapper
     */
    @Autowired
    private EtrBondFirstLBiddingMapper mapper;
    
    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;

    @Autowired
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;


    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 组合可用持仓和实际持仓查询更新组件接口
     */
//    @Autowired(required = false)
//    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

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

    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;

    @Autowired
    private BidCloseStatusMapper bidCloseStatusMapper;
    /**
     * 债券一级投标委托 分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrBondFirstLBiddingDto> selectForPage(int page, int length,
                                                       Map<String, Object> pageParam) {
        pageParam.put("bond", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        pageParam.put("YES_OR_NO_Y", BussinessDictCodeList.是否_是.getValue());

        // add by etliu 20210501 银行间场内场外拆分 start
        pageParam.put("defaultTradeMarketList", PageMenuSplitUtil.getTradeMarketList((String) pageParam.get("pageId")));
        // add by etliu 20210501 银行间场内场外拆分 end

        return super.queryForPage(page, length, () -> mapper.selectForPage(pageParam));
    }
    
    /**
     * 获取组合代码的联想文本框 <功能详细描述>
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(Map<String, Object> map) {
        map.put("portType", BussinessDictCodeList.组合类型_投资类组合.getValue());
        return mapper.getentrustId(map);
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
        map.put("param", param.get("finprodId"));
        map.put("assestType", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());

        // add by etliu 20210501 银行间场内场外拆分 start
        map.put("defaultTradeMarketList", PageMenuSplitUtil.getTradeMarketList((String) param.get("pageId")));
        // add by etliu 20210501 银行间场内场外拆分 end

        return mapper.getfinprodId(map);

    }
    
    /**
     * 获取新建界面的资产代码联想下拉框框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length,Map<String, Object> param) {
        param.put("assestType", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());

        // add by etliu 20210501 银行间场内场外拆分 start
        param.put("defaultTradeMarketList", PageMenuSplitUtil.getTradeMarketList((String) param.get("parentPageId")));
        // add by etliu 20210501 银行间场内场外拆分 end

        return this.queryForPageForSelectReturn(page, length,()->mapper.getfinprodIdForAdd(param));
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
        param.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
        return this.queryForPageForSelectReturn(page, length,()->mapper.getentrustIdAdd(param));
    }
    /**
     * 画面校验 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrBondFirstLBiddingDto dto) {
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
    public boolean recallDelegate(EtrBondFirstLBiddingDto dto)
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
     * @param
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
//    public BigDecimal getAvailableNum(Map<String, Object> map) {
//        return mapper.getAvailableNum(map);
//    }

    /**
     * 根据主键查询修改/查看画面信息 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrBondFirstLBiddingDto selectByKey(String entrustId, String finprodId,
                                            String portfolioId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entrustId", entrustId);
        map.put("finprodId", finprodId);
        map.put("portfolioId", portfolioId);
        map.put("bond", BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
        map.put("YES_OR_NO_Y", BussinessDictCodeList.是否_是.getValue());
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrBondFirstLBiddingDto etrBondFirstLBiddingDto = mapper.selectByKey(map);
        etrBondFirstLBiddingDto.setBidPriceList(mapper.selectBidPriceByKey(map));
        String trader = "";
        for (int i = 0; i < traderList.size(); i++ ) {
            trader = trader + "," + traderList.get(i);
        }
        etrBondFirstLBiddingDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrBondFirstLBiddingDto;
    }

    /**
     * 提交
     * 
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(EtrBondFirstLBiddingDto dto, String operationType)
        throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        etrEntrustAdd.setPayType(dto.getEtrPayType());
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());
            dto.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
            dto.setEntrustStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustDate(dto.getEntrustDate());
            etrEntrust.setEntrustAmt(null);
            if (StringUtil.isNotEmpty(dto.getEntrustNum())) {
                etrEntrust.setEntrustNum(dto.getEntrustNum().divide(new BigDecimal(100)));
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(BussinessDictCodeList.平安资产类型_一级投标债券.getValue());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {

                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                    dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                 entity.setEtrDistributeList(etrDistributeList);
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

            entrustCommonDto.setEtrEntrust(etrEntrust);
            entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
            //委托投标标位
           entity.setBidPriceList(dto.getBidPriceList());
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
            dto.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_一级投标债券.getValue());//
            dto.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
            dto.setEntrustStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
//            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            etrEntrust.setEntrustAmt(null);
            if (StringUtil.isNotEmpty(dto.getEntrustNum())) {
                etrEntrust.setEntrustNum(dto.getEntrustNum().divide(new BigDecimal(100)));
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(BussinessDictCodeList.平安资产类型_一级投标债券.getValue());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                    dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
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

            entrustCommonDto.setEtrEntrust(etrEntrust);
            entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);

            //委托投标标位
            entity.setBidPriceList(dto.getBidPriceList());
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

//    /**
//     * 流程启动时为附件绑定工作流代码 <功能详细描述>
//     * 
//     * @param dto
//     * @param operateType
//     * @see
//     * @since 1.0
//     */
//    public void doInsert(EtrBondFirstLBiddingDto dto, String operateType) {
//        // 上传文件修改更新
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
//        mstFileInputParameter.setFileList(dto.getFileArray());
//        if (mstFileCommonService != null) {
//            mstFileCommonService.updateBusinessId(mstFileInputParameter);
//        }
//    }

    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse((String)param.get("sysDate"));
        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(BussinessDictCodeList.资金账户类型_托管账户.getValue());
        acctTypeList.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
        dto.setAcctType(acctTypeList);
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));
        Map<String, Object> result = new HashMap<>();
        if (basePtlCashPositionInfoService != null) {
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT", cashT);
            result.put("cashT1", cashT);
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
        return result;
    }

//    /**
//     * 组合可用持仓查询 <功能详细描述>
//     * 
//     * @param dto
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public Map<String, Object> getAssetAvailablePosition(Map<String, Object> param) {
//        PtlSecPositionFlowDto dto = new PtlSecPositionFlowDto();
//        Date sysdate = DateUtil.parse((String)param.get("sysDate"));
//        dto.setCdate(sysdate);
//        List<String> acctTypeList = new ArrayList<>();
//        acctTypeList.add(BussinessDictCodeList.资金账户类型_托管账户.getValue());
//        acctTypeList.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
//        dto.setAcctType(acctTypeList);
//        dto.setFinprodId((String)param.get("finprodId"));
//        dto.setCcy((String)param.get("ccy"));
//        dto.setHodingType(BussinessDictCodeList.持仓类型_可用.getValue());
//        dto.setPortfolioId((String)param.get("portfolioId"));
//        
//        // 一体化对应etliu start
//        dto.setInvAim((String)param.get("invAim"));
//        // 一体化对应etliu end
//        
//        Map<String, Object> result = new HashMap<>();
//        if (basePtlSecPositionInfoService != null) {
//            BigDecimal position = basePtlSecPositionInfoService.queryUsableSecPosition(dto);
//            result.put("position", position);
//        }
//        if (StringUtil.isNotEmpty(param.get("sysDateT1"))) {
//            PtlSecPositionFlowDto dto2 = new PtlSecPositionFlowDto();
//            Date sysdateT1 = DateUtil.parse((String)param.get("sysDateT1"));
//            dto2.setCdate(sysdateT1);
//            dto2.setAcctType(acctTypeList);
//            dto2.setFinprodId((String)param.get("finprodId"));
//            dto.setCcy((String)param.get("ccy"));
//            dto2.setHodingType(BussinessDictCodeList.持仓类型_可用.getValue());
//            dto2.setPortfolioId((String)param.get("portfolioId"));
//            if (basePtlSecPositionInfoService != null) {
//                BigDecimal positionT1 = basePtlSecPositionInfoService.queryUsableSecPosition(dto2);
//                result.put("positionT1", positionT1);
//            }
//        }
//        return result;
//    }
    
    /**
     *  
     * 
     * @param dto
     * @param operateType
     * @see
     * @since 1.0
     */
    public void doInsert(EtrBondFirstLBiddingDto dto, String operateType) {
        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }

    public String getCounterName(String counterId){
        MstCounterParty mstCounterParty = mstCounterPartyMapper.selectByPrimaryKey(counterId);
        return mstCounterParty==null?"":mstCounterParty.getCounterName();
    }

    /**
     * 验证债券是否截标
     * @param dto
     * @return
     */
    public Integer checkIsBidClose(EtrBondFirstLBiddingDto dto){
        BidCloseStatus bidCloseStatus = bidCloseStatusMapper.selectByPrimaryKey(dto.getFinprodId());
        if (null != bidCloseStatus && StringUtil.equals(bidCloseStatus.getCloseStatus(),
                DictCodeListExt3.截标状态_已截标.getValue())){
            return 1;
        }
        return 0;
    }
    /**
     * 获取新建界面的主承销商下拉框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getMainConsignee(int page, int length,Map<String, Object> param) {
        param.put("YESORNO_YES", DictCodeList.是否_是.getValue());
        return this.queryForPageForSelectReturn(page, length,()->mapper.getMainConsignee(param));
    }
}
