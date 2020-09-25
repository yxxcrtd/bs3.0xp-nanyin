/*
 * 文件名：EtrBoutRepoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrboutrepo.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrboutrepo.dao.EtrBoutRepoMapper;
import com.joyintech.service.entrust.etrboutrepo.dto.EtrBoutRepoDto;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;

/**
 * 买断式回购委托service
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月10日
 * @see EtrBoutRepoService
 * @since 1.0
 */
@Service
public class EtrBoutRepoService extends BaseService<EtrBoutRepoDto> {

    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required=false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 投资委托基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;
    
    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    /**
     * 自定义mapper
     */
    @Autowired
    private EtrBoutRepoMapper etrBoutRepoMapper;
    
    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    
    /**
     * 买断式回购委托分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<EtrBoutRepoDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
        param.put("FINPROD_TYPE2_Z", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        param.put("FINPROD_TYPE2_N", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        return queryForPage(page, length, () -> etrBoutRepoMapper.selectForPage(param));
    }
    /**
     * 获取组合代码的联想文本框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(Map<String, Object> map) {
        return etrBoutRepoMapper.getentrustId(map);
    }

    /**
     * 获取新建界面的组合代码联想下拉框框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getentrustIdAdd(int page, int length,Map<String, Object> map) {
        return this.queryForPageForSelectReturn(page, length,()->etrBoutRepoMapper.getentrustIdAdd(map));
    }
    /**
     * 校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrBoutRepoDto dto) {
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
     * 撤回委托
     * 
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrBoutRepoDto dto)
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
     * 工作流完成方法 <功能详细描述>
     * 
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(EtrBoutRepoDto dto, String operationType)
        throws IllegalAccessException, InvocationTargetException {

        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
            dto.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            etrEntrust.setEntrustNum(etrEntrust.getEntrustAmt().divide(new BigDecimal(100)));
            etrEntrust.setEntrustAmt(null);
            if(StringUtil.isNotEmpty(etrEntrust.getPrice())){
            	etrEntrust.setPrice(etrEntrust.getPrice().divide(new BigDecimal(100)));
            }
            etrEntrustAdd.setEntrustId(dto.getEntrustId());
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)1);
            }
            etrEntrustAdd.setVdate(dto.getVdateAdd());
            etrEntrustAdd.setMdate(dto.getMdateAdd());
            if(StringUtil.isNotEmpty(dto.getRepoTerm())){
//                List<String> termlist = StringUtil.splitToList("-", dto.getRepoTerm());
//                etrEntrustAdd.setTermDays(Long.valueOf(termlist.get(0)));
//                if(termlist.size()>1){
//                    etrEntrustAdd.setTermDays2(Long.valueOf(termlist.get(1)));
//                }
                etrEntrustAdd.setTermDays(Long.valueOf(dto.getRepoTerm()));
            }
            if(StringUtil.isNotEmpty(dto.getRepoTerm2())) {
            	etrEntrustAdd.setTermDays2(Long.valueOf(dto.getRepoTerm2()));
            }
            etrEntrustAdd.setTermType(dto.getTermType());
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_买断式回购.getValue());
            etrEntrustAdd.setAssetType1(dto.getAssetType1());
            etrEntrustAdd.setBusiPs(dto.getPs());
            etrEntrustAdd.setCashUsedate(dto.getAvailableDay());
            etrEntrustAdd.setSecAcctId(dto.getSecuritiesCust());
            etrEntrustAdd.setFirstUnitCprice(dto.getFirstprice());
            etrEntrustAdd.setFirstUnitCprice2(dto.getFirstprice2());
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
            dto.setFinprodType2(StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())?DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue():DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
            dto.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            etrEntrust.setEntrustNum(etrEntrust.getEntrustAmt().divide(new BigDecimal(100)));
            etrEntrust.setEntrustAmt(null);
            if (StringUtil.isNotEmpty(etrEntrust.getPrice())){
                etrEntrust.setPrice(etrEntrust.getPrice().divide(new BigDecimal(100)));
            }
            etrEntrustAdd.setEntrustId(dto.getEntrustId());
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_0.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)0);
            }
            if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_1.getValue())) {
                etrEntrustAdd.setSettleSpreed((long)1);
            }
            etrEntrustAdd.setVdate(dto.getVdateAdd());
            etrEntrustAdd.setMdate(dto.getMdateAdd());
            if(dto.getRepoTerm()!=null){
//                List<String> termlist = StringUtil.splitToList("-", dto.getRepoTerm());
//                etrEntrustAdd.setTermDays(Long.valueOf(termlist.get(0)));
//                if(termlist.size()>1){
//                    etrEntrustAdd.setTermDays2(Long.valueOf(termlist.get(1)));
//                }
                etrEntrustAdd.setTermDays(Long.valueOf(dto.getRepoTerm()));
                etrEntrustAdd.setTermDays2(Long.valueOf(dto.getRepoTerm2()));
            }
            etrEntrustAdd.setTermType(dto.getTermType());
            etrEntrustAdd.setAssetType(DictCodeList.平安资产类型_买断式回购.getValue());
            etrEntrustAdd.setAssetType1(dto.getAssetType1());
            etrEntrustAdd.setBusiPs(dto.getPs());
            etrEntrustAdd.setCashUsedate(dto.getAvailableDay());
            etrEntrustAdd.setSecAcctId(dto.getSecuritiesCust());
            etrEntrustAdd.setFirstUnitCprice(dto.getFirstprice());
            etrEntrustAdd.setFirstUnitCprice2(dto.getFirstprice2());
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
        if (baseEtrCashPositionCommService != null ) {
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
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
    public void doInsert(EtrBoutRepoDto dto, String operateType) {
      //上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }
    /**
     * 查询修改/查看反显数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrBoutRepoDto selectByKey(String entrustId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrBoutRepoDto etrProjectDto = etrBoutRepoMapper.selectByKey(entrustId);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        etrProjectDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrProjectDto;
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
        return this.queryForSuggest(() -> etrBoutRepoMapper.getSecManageAcctId(param),
            null);
    }
    /**
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
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
            Map<String, Object> result = new HashMap<>();
            result.put("cashTG", cashTG);
            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal cashT = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
            result.put("cashT", cashT);
            if (StringUtil.isNotEmpty(param.get("calId"))) {
                PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
                Date vdate1 = DateUtil.parse((String)param.get("vdate1"));
                dto2.setCdate(vdate1);
                dto2.setAcctType(acctTypeList);
                dto2.setPortfolioId((String)param.get("portfolioId"));
                dto2.setCcy((String)param.get("ccy"));
                BigDecimal cashT1 = basePtlCashPositionInfoService.queryUsableCashPosition(dto2);
                result.put("cashT1", cashT1);
            }
            return result;
        }
        else {
            return null;
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
     * 
     * 查询流程中已质押额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectWflAmt(Map<String, Object> map){
        map.put("repoType", DictCodeList.回购类型_质押式竞价回购.getValue());
        if(iPlatformDate != null){
            map.put("sysDate",iPlatformDate.getSysDate());
        }
        return etrBoutRepoMapper.selectWflAmt(map);
    }
    /**
     * 
     * 查询交易中已质押额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectAmt(Map<String, Object> map){
        map.put("repoType", DictCodeList.回购类型_质押式竞价回购.getValue());
        if(iPlatformDate != null){
            map.put("sysDate",iPlatformDate.getSysDate());
        }
        return etrBoutRepoMapper.selectAmt(map);
    }
    /**
     * 
     * 查询标准券额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> selectStandardAmt(Map<String, Object> map){
        map.put("pin", DictCodeList.出入库方向_入库.getValue());
        map.put("pout", DictCodeList.出入库方向_出库.getValue());
        if(iPlatformDate != null){
            map.put("sysDate",iPlatformDate.getSysDate());
        }
        return etrBoutRepoMapper.selectStandardAmt(map);
    }
}
