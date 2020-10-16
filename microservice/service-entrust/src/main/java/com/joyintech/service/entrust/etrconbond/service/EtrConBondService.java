/*
 * 文件名：EtrBondFirstLBiddingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrconbond.service;

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
import com.joyintech.service.entrust.common.service.EntrustCommonPersonSelectService;
import com.joyintech.service.entrust.etrbondfirstlbidding.dao.EtrBondFirstLBiddingMapper;
import com.joyintech.service.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.service.entrust.etrconbond.dao.EtrConBondMapper;
import com.joyintech.service.entrust.etrconbond.dto.EtrConBondDto;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 可转债委托 后台处理
 * 〈功能详细描述〉
 * @author gejiaao
 * @version 1.0 2019年5月6日
 * @see EtrConBondService
 * @since 1.0
 */
@Service("backEtrConBondService")
public class EtrConBondService extends BaseService{
    
    /**
     * 自定义可转债委托mapper
     */
    @Autowired
    private EtrConBondMapper mapper;
    
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
     * 委托指令共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 委托自动分发
     */
    @Autowired(required = false)
    private BusiAutoEtrDispService busiAutoEtrDispService;

    /**
     * 金融产品
     */
    @Autowired
    private FinProductMapper finProductMapper;
    /**
     * 组合
     */
    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
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
    public Page<EtrConBondDto> selectForPage(int page, int length,
                                                       Map<String, Object> pageParam) {
        pageParam.put("bond", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        //委托日期区间查询
        if (StringUtil.isNotEmpty((String)pageParam.get("entrustDate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)pageParam.get("entrustDate"));
            pageParam.put("startDate", dateList.get(0));
            pageParam.put("endDate", dateList.get(2));
        }
        pageParam.put("YES_OR_NO_Y", BussinessDictCodeList.是否_是.getValue());
        pageParam.put("type6_B14",DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        pageParam.put("type6_B15",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        pageParam.put("PS_T05",DictCodeList.交易方向_申购确认.getValue());
        pageParam.put("PS_T04",DictCodeList.交易方向_申购.getValue());
        pageParam.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        return super.queryForPage(page, length, () -> mapper.selectForPage(pageParam));
    }

    /**
     * 获取资产编号的联想文本框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> param) {
        param.put("assestType", BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
        param.put("type6_B14",DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("type6_B15",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());

        return this.queryForPage(page, length, () -> mapper.getFinprodId(param));
    }
    
    /**
     * 获取新建界面的资产代码联想下拉框框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(int page, int length,Map<String, Object> param) {
        param.put("assestType", BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
        param.put("type6_B14",DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("type6_B15",DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        return this.queryForPageForSelectReturn(page, length,()->mapper.getFinprodIdForAdd(param));
    }

    /**
     * 画面校验 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrConBondDto dto) {
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
    public boolean recallDelegate(EtrConBondDto dto)
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
     * 根据主键查询修改/查看画面信息 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrConBondDto selectByKey(String entrustId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entrustId", entrustId);
        map.put("bond", BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrConBondDto etrConBondDto = mapper.selectByKey(map);
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        dto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
        dto.setCcy(etrConBondDto.getCcy());
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        dto.setAcctType(acctTypeList);
        dto.setPortfolioId(etrConBondDto.getPortfolioId());
        dto.setCdate(etrConBondDto.getEntrustDate());
        BigDecimal cashT4 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
        etrConBondDto.setTradeMarketCash(cashT4 == null ? BigDecimal.ZERO : cashT4);

        if (StringUtil.isNotEmpty(etrConBondDto.getChlId())){
            dto.setChlFinprodId(etrConBondDto.getChlId());
            BigDecimal cashT2 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
            etrConBondDto.setChannelAvailableCash(cashT2 == null ? BigDecimal.ZERO : cashT2);
        }
        String trader = "";
        for (int i = 0; i < traderList.size(); i++ ) {
            trader = trader + "," + traderList.get(i);
        }
        etrConBondDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrConBondDto;
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
    public void doComplete(EtrConBondDto dto, String operationType)
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
            etrEntrust.setEntrustDate(dto.getEntrustDate());
            etrEntrust.setEntrustAmt(null);
            etrEntrust.setPs(DictCodeList.交易方向_申购.getValue());
            etrEntrust.setChlId(dto.getChlId());
            if (StringUtil.isNotEmpty(dto.getPurchBondTotal())) {
                etrEntrust.setEntrustNum(dto.getPurchBondTotal().divide(new BigDecimal(100)));
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setPurchModle(dto.getPurchModle());
            etrEntrustAdd.setPurchType(dto.getPurchType());
            etrEntrustAdd.setSecAcctId(dto.getSecAcctId());
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_债券.getValue());
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
            baseEtrInvestInfoService.newDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        } else {
            dto.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_债券.getValue());
            dto.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
            dto.setEntrustStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            dto.setRecStatus(BussinessDictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustAmt(null);
            etrEntrust.setChlId(dto.getChlId());
            if (StringUtil.isNotEmpty(dto.getPurchBondTotal())) {
                etrEntrust.setEntrustNum(dto.getPurchBondTotal().divide(new BigDecimal(100)));
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setPurchModle(dto.getPurchModle());
            etrEntrustAdd.setPurchType(dto.getPurchType());
            etrEntrustAdd.setSecAcctId(dto.getSecAcctId());
            etrEntrustAdd.setAssetType(BussinessDictCodeList.平安资产类型_债券.getValue());
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
                    etrDistributeList.add(etrDistribute);
                }
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
            //委托投标标位
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
     * 组合可用现金查询
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
        if (basePtlCashPositionInfoService != null) {
            List<String> acctTypes = new ArrayList<>();
            acctTypes.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
            dto.setAcctType(acctTypes);
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("tradeMarketCash", cashT);
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

    public void doInsert(EtrConBondDto dto, String operateType) {
        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }

    public Boolean checkChildPortfolio(EtrConBondDto etrConBondDto){
        boolean flag = false;
        PtlPortfolio ptlPortfolio = ptlPortfolioMapper.selectByPrimaryKey(etrConBondDto.getPortfolioId());
        if (StringUtil.equals(ptlPortfolio.getPortfolioType(),DictCodeListExt1.组合类型_子组合.getValue())){
            Map<String,Object> map = new HashMap<>();
            map.put("portfolioId",etrConBondDto.getPortfolioId());
            map.put("finprodId",etrConBondDto.getFinprodId());
            map.put("ps",DictCodeList.交易方向_申购.getValue());
            map.put("finprodType2",DictCodeList.平安资产类型_债券.getValue());
            List<String> allChildPortFolioId = mapper.getAllChildPortFolioId(map);
            if (allChildPortFolioId.size()>0){
                flag = true;
            }
        }
        return flag;
    }

    public String getCounterIdByFinprodId(String finprodId) {
        return mapper.getCounterId(finprodId);
    }
}
