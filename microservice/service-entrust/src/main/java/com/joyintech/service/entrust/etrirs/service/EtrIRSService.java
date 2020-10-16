/*
 * 文件名：EtrIRSService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrirs.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.common.service.EntrustCommonPersonSelectService;
import com.joyintech.service.entrust.etrinvestallinfo.dto.EtrInvestAllInfoDto;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.service.entrust.etrirs.dao.EtrIRSMapper;
import com.joyintech.service.entrust.etrirs.dto.EtrIRSDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

/**
 * 利率互换委托微服务service 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年7月11日
 * @see EtIRSService
 * @since 1.0
 */
@Service("backEtrIRSService")
public class EtrIRSService extends BaseService<EtrIRSDto> {

    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;

    /**
     * 利率互换委托自定义mapper
     */
    @Autowired
    private EtrIRSMapper etrIRSMapper;

    /**
     * 投资委托基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 委托自动分发
     */
    @Autowired(required = false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    

    /**
     * 利率互换委托分页查询 <功能详细描述>
     * 
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<EtrIRSDto> selectForPage(int page, int length, Map<String, Object> param)
        throws Exception {
        param.put("FINPROD_TYPE_TG_F14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        Page<EtrIRSDto> pagelist =queryForPage(page, length, () -> etrIRSMapper.selectForPage(param));
        for(EtrIRSDto dto:pagelist.getRoot()){
        	//到期日处理  
            if(dto.getDueDate().equals(" - ")){
            	dto.setDueDate("");
            }
            //交易完成度
            if(dto.getTransactioncom()==null){
            	dto.setTransactioncom(BigDecimal.ZERO);
            }
        }
        for(EtrIRSDto dto:pagelist.getRoot()){
            String realName = entrustCommonPersonSelectMapper.getRealName(dto.getInvManagerId());
            dto.setInvManagerId(realName);
        }
        return pagelist;
    }

    /**
     * 校验 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrIRSDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getOperateType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
       /* if (dto.getEtrInvestPersonInfo() != null
            && StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
            traderList.add(dto.getEtrInvestPersonInfo().getTrader());
        }*/
        return baseEtrInvestInfoService.checkDelegateOperation(entrustId, delegateOperationType,
            traderList, delegateCloseWay);
    }

    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrIRSDto dto)
        throws IllegalAccessException,
        InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        BeanUtil.copyProperties(etrEntrust, dto);
        // BeanUtil.copyProperties(etrEntrust,dto.getEtrInvestPersonInfo());
        entity.setEtrEntrust(etrEntrust);
        return baseEtrInvestInfoService.recallDelegate(entity);
    }

    /**
     * 关闭委托 <功能详细描述>
     * 
     * @param entrustId
     * @param closeWay
     * @return
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
    }

    /**
     * 修改/查看信息查询 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrIRSDto selectByKey(String entrustId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrIRSDto etrIRSDto = etrIRSMapper.selectByKey(entrustId);
        String trader = "";
        for (int i = 0; i < traderList.size(); i++ ) {
            trader = trader + "," + traderList.get(i);
        }
        //到期日处理  
        if(etrIRSDto.getDueDate().equals(" - ")){
        	etrIRSDto.setDueDate("");
        }
        etrIRSDto.getEtrInvestPersonInfo().setTrader(trader);
        return etrIRSDto;
    }

    /**
     * 删除委托 <功能详细描述>
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
     * 工作流完成方法 <功能详细描述>
     * 
     * @param dto
     * @param operationType
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public void doComplete(EtrIRSDto dto, String operationType)
        throws IllegalAccessException,
        InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        if (StringUtil.equals(operationType, "NEW")) {
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            etrEntrust.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
            etrEntrust.setPs(DictCodeList.交易方向_买入.getValue());
            //设置金额 数量  乘以一万
            etrEntrust.setEntrustAmt(dto.getEntrustAmt().multiply(new BigDecimal(10000)));
            etrEntrust.setEntrustNum(dto.getEntrustAmt().multiply(new BigDecimal(10000)));
            //到期收益率处理（固定利率）
            if(StringUtil.isNotEmpty(dto.getMatureYield())){
            	//到期收益率 除以一百
            	BigDecimal matureYield =new BigDecimal(dto.getMatureYield()).divide(BigDecimal.valueOf(100));
            	dto.setMatureYield(String.valueOf(matureYield));
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            if(StringUtil.isNotEmpty(dto.getDueDate())){
            	List<String> DueDateList = StringUtil.splitToList(" ", dto.getDueDate());
            	etrEntrustAdd.setMdate(DateUtil.parse(DueDateList.get(0)));
            	etrEntrustAdd.setMdate2(DateUtil.parse(DueDateList.get(2)));
            }else{
            	//置空etrentrustadd mdate属性
            	etrEntrustAdd.setMdate(null);
            }
            // add by wangpeng 20190829 设置自动分发时  无资产类型会报错 
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_利率互换.getValue());
            //add end wangpeng
            // 委托指令分发
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
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_利率互换.getValue());
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
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            etrEntrust.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
          //设置金额 数量  乘以一万
            etrEntrust.setEntrustAmt(dto.getEntrustAmt().multiply(new BigDecimal(10000)));
            etrEntrust.setEntrustNum(dto.getEntrustAmt().multiply(new BigDecimal(10000)));
            // add by wangpeng 20190917 修改之后 到期收益率问题及资产类型
            //到期收益率处理（固定利率）
            if(StringUtil.isNotEmpty(dto.getMatureYield())){
            	//到期收益率 除以一百
            	BigDecimal matureYield =new BigDecimal(dto.getMatureYield()).divide(BigDecimal.valueOf(100));
            	dto.setMatureYield(String.valueOf(matureYield));
            }
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_利率互换.getValue());
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            //add end wangpeng
            
            if(StringUtil.isNotEmpty(dto.getDueDate())){
            	List<String> DueDateList = StringUtil.splitToList(" ", dto.getDueDate());
            	etrEntrustAdd.setMdate(DateUtil.parse(DueDateList.get(0)));
            	etrEntrustAdd.setMdate2(DateUtil.parse(DueDateList.get(2)));
            }else{
            	//置空etrentrustadd mdate属性
            	etrEntrustAdd.setMdate(null);
            }
            // 委托指令分发
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
            // 上传文件修改更新
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            mstFileInputParameter.setFileList(dto.getFileArray());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
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
        String result = etrIRSMapper.judgeCounterType(counterId);
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
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse((String)param.get("sysDate"));
        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setAcctType(acctTypeList);
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));
        BigDecimal cashTG = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
        result.put("cashTG", cashTG);

        return result;
    }

    /**
     * 查询上传文件 <功能详细描述>
     * 
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId) {
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(businessId);
        if (mstFileCommonService != null) {
            MstFileOutputParameter output = mstFileCommonService.getFileInfoByBusiId(businessId);
            return output.getUploadFileList();
        }
        else {
            return null;
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
    public void doInsert(EtrIRSDto dto, String operateType) {
        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
        // 是否需要插入业务流程表
    }
}
