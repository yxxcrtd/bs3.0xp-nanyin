/*
 * 文件名：EtrIbBoutRepo002Service.java
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etribboutrepo.service;

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
import com.joyintech.service.entrust.etribboutrepo.dao.EtrIbBoutRepo002Mapper;
import com.joyintech.service.entrust.etribboutrepo.dto.EtrIbBoutRepo002Dto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 买断式回购委托service
 * 〈功能详细描述〉
 * @see EtrIbBoutRepo002Service
 * @since 1.0
 */
@Service("backEtrIbBoutRepo002Service")
public class EtrIbBoutRepo002Service extends BaseService<EtrIbBoutRepo002Dto> {

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
    private EtrIbBoutRepo002Mapper etrIbBoutRepo002Mapper;
    
    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     * 校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrIbBoutRepo002Dto dto) {
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
     * 工作流完成方法 <功能详细描述>
     * 
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(EtrIbBoutRepo002Dto dto, String operationType)
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
            //页面单位为万元
            etrEntrust.setEntrustNum(etrEntrust.getEntrustAmt().multiply(new BigDecimal(100)));
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
            //页面单位是万元
            etrEntrust.setEntrustNum(etrEntrust.getEntrustAmt().multiply(new BigDecimal(100)));
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
               // etrEntrustAdd.setTermDays2(Long.valueOf(dto.getRepoTerm2()));
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
    public void doInsert(EtrIbBoutRepo002Dto dto, String operateType) {
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
    public EtrIbBoutRepo002Dto selectByKey(String entrustId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrIbBoutRepo002Dto etrProjectDto = etrIbBoutRepo002Mapper.selectByKey(entrustId);
        String trader = "";
        for(int i=0; i<traderList.size(); i++){
            trader = trader +","+ traderList.get(i);
        }
        etrProjectDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrProjectDto;
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
        String result = etrIbBoutRepo002Mapper.judgeCounterType(counterId);
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
        return etrIbBoutRepo002Mapper.selectWflAmt(map);
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
        return etrIbBoutRepo002Mapper.selectAmt(map);
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
        return etrIbBoutRepo002Mapper.selectStandardAmt(map);
    }
}
