/*
 * 文件名：EtrWtSpotexService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年6月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrwtspotex.service;

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
import com.joyintech.service.entrust.etrwtspotex.dao.EtrWtSpotexMapper;
import com.joyintech.service.entrust.etrwtspotex.dto.EtrWtSpotexDto;
import com.joyintech.tams.common.base.entity.EtrDistribute;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;

/**
 * 外汇即期委托微服务service 〈功能详细描述〉
 * 
 * @author wangguoxian
 * @version 1.0 2019年6月24日
 * @see EtrWtSpotexService
 * @since 1.0
 */
@Service("backEtrWtSpotexService")
public class EtrWtSpotexService extends BaseService<EtrWtSpotexDto> {

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
     * 外汇即期委托自定义mapper
     */
    @Autowired
    private EtrWtSpotexMapper wtSpotexMapper;

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
     * 外汇即期委托分页查询 <功能详细描述>
     * 
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<EtrWtSpotexDto> selectForPage(int page, int length, Map<String, Object> param)
        throws Exception {
        String portfolioId = (String)param.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioId)) {
            param.put("portfolioIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
        }
        param.put("FINPROD_TYPE_TG_F11", DictCodeListExt1.金融产品类型_投管用_即期.getValue());
        return queryForPage(page, length, () -> wtSpotexMapper.selectForPage(param));
    }

    /**
     * 校验 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrWtSpotexDto dto) {
        String entrustId = dto.getEntrustId();
        String delegateOperationType = dto.getOperateType();
        String delegateCloseWay = dto.getCloseWay();
        List<String> traderList = new ArrayList<>();
        if (dto.getEtrInvestPersonInfo() != null
            && StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {
            traderList.add(dto.getEtrInvestPersonInfo().getTrader());
        }
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
    public boolean recallDelegate(EtrWtSpotexDto dto)
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
    public EtrWtSpotexDto selectByKey(String entrustId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entrustId", entrustId);
        List<String> traderList = entrustCommonPersonSelectMapper.getTraderList(map);
        EtrWtSpotexDto EtrWtSpotexDto = wtSpotexMapper.selectByKey(entrustId);
        String trader = "";
        for (int i = 0; i < traderList.size(); i++ ) {
            trader = trader + "," + traderList.get(i);
        }
        EtrWtSpotexDto.getEtrInvestPersonInfo().setTrader(trader);
        return EtrWtSpotexDto;
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
    public void doComplete(EtrWtSpotexDto dto, String operationType)
        throws IllegalAccessException,
        InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        if (StringUtil.equals(operationType, "NEW")) {
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            etrEntrust.setIsOutTrade(DictCodeList.是否_是.getValue());
            etrEntrust.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_外汇即期.getValue());
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
            etrEntrust.setIsOutTrade(DictCodeList.是否_是.getValue());
            etrEntrust.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_外汇即期.getValue());
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
    public void doInsert(EtrWtSpotexDto dto, String operateType) {
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
