/*
 * 文件名：EtrBrowDepoInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrbrowdepoinfo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 存款委托
 * 〈功能详细描述〉
 *
 * @author xiexinting
 * @version 1.0 2018年9月11日
 * @see EtrBrowDepoInfoService
 * @since 1.0
 */
@Service
public class EtrBrowDepoInfoService extends CommonExportService<EtrBrowDepoInfoDto> implements BaseProcessService<EtrBrowDepoInfoDto> {
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    /**
     *
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     *
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     *
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     *
     */
    @Autowired
    private EtrCommonService etrCommonService;

    /**
     * 存款委托查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrBrowDepoInfoDto> selectForPage(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        // type9-11 created by wangguiqi on 2019/7/19 -start
        if (StringUtil.isNotEmpty(param.get("type9"))) {
            param.put("type9", StringUtil.splitToList(null, param.get("type9").toString()));
        } else {
            param.remove("type9");
        }
        if (StringUtil.isNotEmpty(param.get("type10"))) {
            param.put("type10", StringUtil.splitToList(null, param.get("type10").toString()));
        } else {
            param.remove("type10");
        }
        if (StringUtil.isNotEmpty(param.get("type11"))) {
            param.put("type11", StringUtil.splitToList(null, param.get("type11").toString()));
        } else {
            param.remove("type11");
        }
        // type9-11 created by wangguiqi on 2019/7/19 -end
        return restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/findPage", param,
                EtrBrowDepoInfoDto.class);
    }

    /**
     * 组合代码联想文本框
     * <功能详细描述>
     *
     * @param portfolioId 组合代码
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioId(String portfolioId) {
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("portfolioId", portfolioId);
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/getPortfolioId",
                map, SuggestDto.class);
    }

    /**
     * 资产代码联想文本框
     * <功能详细描述>
     *
     * @param finprodId 资产代码
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketId(String finprodId) {

        return restTemplate.getForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/getFinprodMarketId?finprodId=" + finprodId,
                SuggestDto.class);
    }

    /**
     * 撤回委托 <功能详细描述>
     *
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrBrowDepoInfoDto dto) {
        Boolean res = restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/recall", dto,
                boolean.class);
        
        //处理投资指令授信 20200526 start
        dealCloseEtrIsNeedOccupyCre(dto.getEntrustId());
        //处理投资指令授信 20200526 end
        
        return res;
    }

    /**
     * 关闭委托
     *
     * @param entrustId String
     * @param closeWay  String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay, String entrustStatus) {
        boolean res = restTemplate.getForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/close?entrustId=" + entrustId + "&closeWay=" + closeWay,
                boolean.class);
        
        //处理投资指令授信 20200526 start
    	// 已撤回/已退回 的不做授信处理
    	if (! DictCodeList.委托状态_已撤回.getValue().equals(entrustStatus) && ! DictCodeList.委托状态_已退回.getValue().equals(entrustStatus)) {
    		dealCloseEtrIsNeedOccupyCre(entrustId);
    	}
        //处理投资指令授信 20200526 end
        return res;
    }

    /**
     * 删除投资委托
     * <功能详细描述>
     *
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/delete?entrustId=" + entrustId,
                boolean.class);
    }

    /**
     * 校验方法 <功能详细描述>
     *
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrBrowDepoInfoDto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/check", dto,
                ValidMessage.class);
    }

    /**
     * 获取新建页面组合代码的联想文本框 <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSelectPortfolioId(Map<String, Object> map) {
        User user = SecurityUtil.getLoginUser();
        map.put("loginUser", user.getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrBrowDepoInfo/getSelectPortfolioId", map,
                SelectOptionDto.class);
    }

    /**
     * 获取通道
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getChannelId(Map<String, Object> map) {
        User user = SecurityUtil.getLoginUser();
        map.put("loginUser", user.getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrBrowDepoInfo/getChannelId", map,
                SelectOptionDto.class);
    }

    /**
     * 获取通道可用现金
     * getChannelAvailableCash
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getChannelAvailableCash(Map<String, Object> param) {
        Date sysDate = DateUtil.platDateFromCache();
        param.put("sysDate", DateUtil.format(sysDate));
        String calId = (String) param.get("calId");
        if (StringUtil.isNotEmpty(calId)) {
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(sysDate)), DateUtil.addDay(DateUtil.parse(DateUtil.format(sysDate)), 15), calId);
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(sysDate)), 1, holidayList, "1");
            param.put("sysDateT1", DateUtil.format(tradeDay));
        }
        Map<String, Object> cash = etrCommonService.getProAvailableCash(param);
//        Map<String, Object> cash = restTemplate.postForObject(
//                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInvestBondInfo/getProAvailableCash",param,
//                Map.class);
        return new BigDecimal((String) cash.get("cashT2"));
    }

    /**
     * 获取新建页面资产代码的联想文本框 <功能详细描述>
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSelectFinprodMarketId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrBrowDepoInfo/getSelectFinprodMarketId", map,
                SelectOptionDto.class);

    }

    /**
     * 根据金融产品代码获取委托金额
     * <功能详细描述>
     *
     * @param finprodId String
     * @return long
     * @see
     * @since 1.0
     */
    public BigDecimal selectEntrustAmt(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/selectEntrustAmt?finprodId=" + finprodId,
                BigDecimal.class);
    }

    /**
     * 根据金融产品代码获取发行规模
     * <功能详细描述>
     *
     * @param finprodId String
     * @return long
     * @see
     * @since 1.0
     */
    public BigDecimal selectIssueAmt(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/selectIssueAmt?finprodId=" + finprodId,
                BigDecimal.class);
    }

    /**
     * 编辑、双击查看页面详情
     * 根据主键查询
     *
     * @param entrustId   String
     * @param finprodId   String
     * @param portfolioId String
     * @return EtrBrowDepoInfoDto
     * @see
     * @since 1.0
     */
    public EtrBrowDepoInfoDto selectByKey(String entrustId, String finprodId, String portfolioId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrBrowDepoInfo/selectByKey?entrustId=" + entrustId + "&finprodId=" + finprodId + "&portfolioId=" + portfolioId,
                EtrBrowDepoInfoDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(EtrBrowDepoInfoDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        entity.setEntrustDate(DateUtil.platDateFromCache());
        basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getEntrustId());
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/doComplete",
                entity, Integer.class);
        
        //处理投资指令授信 start
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && !entity.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
            dealEtrIsNeedOccupyCre(entity, null);
        }
        //处理投资指令授信 end

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doInsert(EtrBrowDepoInfoDto entity, OperationType type) {
        //entity.setOperateType(type.toString());
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/doInsert",
                entity, Integer.class);
        
        //处理投资指令授信 start
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && entity.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
            dealEtrIsNeedOccupyCre(entity, null);
        }
        //处理投资指令授信 end
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(EtrBrowDepoInfoDto entity) {
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/doInsert",
                entity, Integer.class);
        
        //处理投资指令授信 start
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && entity.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
            dealEtrIsNeedOccupyCre(entity, null);
        }
        //处理投资指令授信 end
    }

    /**
     * 导出
     *
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrbrowdepoinfo/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<EtrBrowDepoInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "transactioncom")) {
            cell.setCellValue(value + "%");
        }
    }

    /**
     * 组合可用现金查询
     * <功能详细描述>
     *
     * @param param PtlCashPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Date sysDate = DateUtil.platDateFromCache();
        param.put("vdate", DateUtil.format(sysDate));
        //dto.setCdate(DateUtil.parse(DateUtil.format(sysDate)));
        /*List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        dto.setAcctType(acctTypeList);*/
        if (StringUtil.isNotEmpty(param.get("finprodId"))) {
            FinProduct finProduct = finProductMapper.selectByPrimaryKey((String) param.get("finprodId"));
            List<String> holidayList = dateCalService.getDealCalendarHoliday(sysDate, DateUtil.addDay(sysDate, 15), finProduct.getCalendarId());
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(sysDate).toString()), 1, holidayList, "1");
            param.put("sysDateT1", DateUtil.format(tradeDay));
        }
        return etrCommonService.getProAvailableCash(param);
//        return restTemplate.postForObject(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/getProAvailableCash",param,
//            Map.class);
    }

    /**
     * 组合T+1可用现金查询
     * <功能详细描述>
     *
     * @param dto PtlCashPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    public BigDecimal getProAvailableCash1(PtlCashPositionFlowDto dto) {
        Date sysDate = DateUtil.platDateFromCache();
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(dto.getFinprodId());
        List<String> holidayList = dateCalService.getDealCalendarHoliday(sysDate, DateUtil.addDay(sysDate, 15), finProduct.getCalendarId());
        Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(sysDate).toString()), 1, holidayList, "1");
        dto.setCdate(tradeDay);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
        dto.setAcctType(acctTypeList);
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/getProAvailableCash", dto,
                BigDecimal.class);
    }


    /**
     * 组合可用持仓查询
     * <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getAssetAvailablePosition(EtrSecPositionDto dto) {
        Date sysDate = DateUtil.platDateFromCache();
        dto.setCdate(sysDate);
        return etrCommonService.getAssetAvailablePosition(dto);
//        return restTemplate.postForObject(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/getAssetAvailablePosition",dto,
//            BigDecimal.class);
    }

    /**
     * 查询文件上传详情
     * <功能详细描述>
     *
     * @param businessId String
     * @return List<SysUploadFileInfo>
     * @see
     * @since 1.0
     */
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId) {
        return restTemplate.getForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBrowDepoInfo/selectFileByBusinessId?businessId=" + businessId,
                SysUploadFileInfo.class);
    }

    /**
     * 查询授权额度
     * <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectLimitAmt(EtrBrowDepoInfoDto dto) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", SecurityUtil.getLoginUser().getUserName());
        map.put("assetType", DictCodeList.平安资产类型_存款.getValue());
        map.put("assetSecType", dto.getFinprodType2());
        map.put("tradeMarket", dto.getTradeMarket());
        map.put("ps", dto.getPs());
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/SelectLimitAmt/selectLimitAmt", map,
                BigDecimal.class);
    }

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        EtrBrowDepoInfoDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            try {
                BigDecimal amount = dto.getEntrustAmt();
                if (amount == null) {
                    amount = dto.getEntrustNum();
                }
                Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), amount, LoginUserUtil.getUserName(),
                        BussinessDictCodeList.平安资产类型_存款.getValue(), dto.getFinprodType2(), dto.getTradeMarket(), dto.getPs()
                        , dto.getFinprodId(), null
                );
                return commonBusinessService.是否需要上级审批_委托用(params);
            }catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        } else {
            return null;
        }
    }
 // add by gaopeng 20191219 多组合多资产导入 start
    /**
     * 获取委托列表数据
     *
     * @return
     */
    public EtrBrowDepoInfoDto getEntrustListData(String tempList) {
        EtrBrowDepoInfoDto dto = new EtrBrowDepoInfoDto();
        List<EtrBrowDepoInfoDto> etrEntrustDtos = new ArrayList<>();
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++) {
            EtrBrowDepoInfoDto etrBrowDepoInfoDto = new EtrBrowDepoInfoDto();
            JSONArray data = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            //根据模板中固定的下标来赋值
            etrBrowDepoInfoDto.setIndexId(data.get(0).toString());
            //委托开始日
            etrBrowDepoInfoDto.setVdate(DateUtil.parse(data.get(1).toString()));
            //委托结束日
            etrBrowDepoInfoDto.setMdate(DateUtil.parse(data.get(2).toString()));
            //组合代码
            etrBrowDepoInfoDto.setPortfolioId(data.get(3).toString().trim());
            //市场代码
            etrBrowDepoInfoDto.setFinprodMarketId(data.get(4).toString().trim());
            //根据资产代码查出联动值
            Map<String, Object> map = new HashMap<>();
            map.put("initValue", data.get(4).toString());
            map.put("page", 1);
            map.put("length", 20);
            Page<SelectOptionDto> finprodIds = getSelectFinprodMarketId(map);
            SelectOptionDto selectOptionDto = finprodIds.getRoot().get(0);
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(selectOptionDto.getObject()));
            //资产代码
            etrBrowDepoInfoDto.setFinprodId(jsonObject.get("finprodId").toString());
            //finprodType2资产类型
            etrBrowDepoInfoDto.setFinprodType2(jsonObject.get("finprodType2").toString());
            //二级资产类型
            etrBrowDepoInfoDto.setAssetType1(jsonObject.get("type9").toString());
            //三级资产类型
            etrBrowDepoInfoDto.setAssetType10(jsonObject.get("type10").toString());
            //四级资产类型
            etrBrowDepoInfoDto.setAssetType11(jsonObject.get("type11").toString());
            //资产代码与名称拼接
            etrBrowDepoInfoDto.setFinprodAbbr(etrBrowDepoInfoDto.getFinprodMarketId()
                + ":" + selectOptionDto.getText());
            //交易方向
            etrBrowDepoInfoDto.setPs(data.get(5).toString().split(":")[0]);
            //币种
            etrBrowDepoInfoDto.setCcy(data.get(6).toString().split(":")[0]);
            //本金金额
            etrBrowDepoInfoDto.setEntrustAmt(new BigDecimal((data.get(7).toString())));
            //借款利率
            etrBrowDepoInfoDto.setPrice(new BigDecimal((data.get(8).toString())).divide(new BigDecimal(100)));
            //起息日
            etrBrowDepoInfoDto.setDateStart(DateUtil.parse(data.get(9).toString()));
            //到息日
            etrBrowDepoInfoDto.setDateEnd(DateUtil.parse(data.get(10).toString()));
            //期限
            if(StringUtil.isNotBlank(data.get(11).toString())) {
                etrBrowDepoInfoDto.setTermDays(new Long(data.get(11).toString())); 
            }
            Map<String, Object> param = new HashMap<>();
            // 通道
            if (StringUtil.isNotBlank(data.get(12).toString())) {
                etrBrowDepoInfoDto.setChlId(data.get(12).toString());
                param = new HashMap<>();
                param.put("initValue", data.get(12).toString());
                param.put("page", 1);
                param.put("length", 20);
                Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                    ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelId",
                    param, SelectOptionDto.class);
                if (!StringUtil.isEmptyList(selectOptionDtoPage.getRoot())) {
                    etrBrowDepoInfoDto.setChlIdName(selectOptionDtoPage.getRoot().get(0).getText());
                }
            }
            etrBrowDepoInfoDto.setBussEtrInvestPersonInfoDto(new BussEtrInvestPersonInfoDto());
            // 投资经理
            etrBrowDepoInfoDto.getBussEtrInvestPersonInfoDto().setInvManagerId(etrBrowDepoInfoDto.getCreateUser());
            // 投资处室
            etrBrowDepoInfoDto.getBussEtrInvestPersonInfoDto().setRoom(etrBrowDepoInfoDto.getCreateDept());
            // 备注
            etrBrowDepoInfoDto.setRemark(data.get(13).toString());
            param.put("portfolioId", etrBrowDepoInfoDto.getPortfolioId());
            param.put("ccy", etrBrowDepoInfoDto.getCcy());
            param.put("vdate", etrBrowDepoInfoDto.getVdate());
            param.put("calId", etrBrowDepoInfoDto.getCalendarId());
            param.put("chlId", etrBrowDepoInfoDto.getChlId());
            Map<String, Object> cashMap = this.getProAvailableCash(param);
            // 指令可用现金
            if (cashMap != null && cashMap.get("cashT") != null) {
                etrBrowDepoInfoDto.setProAvailableCash(
                    new BigDecimal(cashMap.get("cashT").toString()));
            }
            else {
                etrBrowDepoInfoDto.setProAvailableCash(BigDecimal.ZERO);
            }
            //指令可用持仓查询
            map.put("finprodId", etrBrowDepoInfoDto.getFinprodId());
            EtrSecPositionDto etrSecPositionDto=new EtrSecPositionDto();
            etrSecPositionDto.setCcy(etrBrowDepoInfoDto.getCcy());
            etrSecPositionDto.setFinprodId(etrBrowDepoInfoDto.getFinprodId());
            etrSecPositionDto.setPortfolioId(etrBrowDepoInfoDto.getPortfolioId());
            if(StringUtil.isNotEmpty(getAssetAvailablePosition(etrSecPositionDto))){
                etrBrowDepoInfoDto.setAssetAvailablePosition(getAssetAvailablePosition(etrSecPositionDto));
            }
            etrEntrustDtos.add(etrBrowDepoInfoDto);
        }
        dto.setSelectedItem(etrEntrustDtos);
        return dto;
    }

    /**
     * 验证导入的数据
     * @param tempList 导入的数据
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 100) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", getMessage("ETR_E_0021"));
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }

        String totalMsg = "";
        JSONArray jsonArrays = new JSONArray();
        // 失败条数
        int errorNum = 0;
        for (int i = 1; i < objects.size(); i++ ) {
            String confirmMsg = "";
            // excel的行号
            String lineNo = String.valueOf(i + 1);
            List<String> rowData = objects.get(i);
                // 委托开始日日期非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(1).toString(), "EtrBrowDepoInfo001.entrustVDate", lineNo));
                // 委托开始日日期格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(1).toString(), "EtrBrowDepoInfo001.entrustVDate", lineNo));
                // 委托结束日日期非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(2).toString(), "EtrBrowDepoInfo001.entrustMDate", lineNo));
                // 委托结束日日期格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(2).toString(), "EtrBrowDepoInfo001.entrustMDate", lineNo));
                // 组合代码非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(3).toString(), "EtrBrowDepoInfo001.portfolioId", lineNo));
                // 组合代码check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkPortfolioIdExist(rowData.get(3).toString(), "EtrBrowDepoInfo001.portfolioId", lineNo));
                //资产代码
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(4).toString(), "EtrBrowDepoInfo001.finprodId", lineNo));
                if (StringUtil.isNotEmpty(rowData.get(4).toString())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("initValue", rowData.get(4).toString());
                    map.put("page", 1);
                    map.put("length", 20);
                    Page<SelectOptionDto> finprodIds = getSelectFinprodMarketId(map);
                    if (finprodIds.getRoot().isEmpty()) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, getMessage("ETR_E_0017",getMessage("EtrBrowDepoInfo001.finprodId"), lineNo));
                    }
                }
                // 交易方向非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(5).toString(), "EtrBrowDepoInfo001.ps", lineNo));
                // 币种非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(6).toString(), "EtrBrowDepoInfo001.ccy", lineNo));
                // 本金金额(元)非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(7).toString(), "EtrBrowDepoInfo003.entrustAmt", lineNo));
                // 本金金额(元)是数值
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsNumber(rowData.get(7).toString(), "EtrBrowDepoInfo003.entrustAmt", lineNo));
                // 本金金额(元)是大于0
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsGtZero(rowData.get(7).toString(), "EtrBrowDepoInfo003.entrustAmt", lineNo));
                //借款利率（%）是数值
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsNumber(rowData.get(8).toString(), "EtrBrowDepoInfo003.price", lineNo));
                // 借款利率（%）是大于0
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsGtZero(rowData.get(8).toString(), "EtrBrowDepoInfo003.price", lineNo));
                // 起息日日期格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(9).toString(), "EtrBrowDepoInfo001.vdate", lineNo));
                // 到息日日期格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(10).toString(), "EtrBrowDepoInfo001.mdate", lineNo));
                // 通道存在
                if (StringUtil.isNotBlank(rowData.get(12).toString())) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("initValue", rowData.get(12).toString());
                    param.put("page", 1);
                    param.put("length", 20);
                    Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                        ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelId",
                        param, SelectOptionDto.class);
                    if (selectOptionDtoPage.getRoot().isEmpty()) {
                        confirmMsg =  etrCommonService.StringAddByBr(confirmMsg,
                            getMessage("ETR_E_0017", getMessage("invest.channelId"), lineNo));
                    }
                }
                if (StringUtil.isBlank(confirmMsg)) {
                    jsonArrays.add(rowData);
                } else {
                    totalMsg = etrCommonService.StringAddByBr(totalMsg, confirmMsg);
                        errorNum++ ;
                }
        }
        totalMsg = etrCommonService.StringAddByBr(totalMsg,
            getMessage("ETR_C_0001", String.valueOf(errorNum)));
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JSONArray.toJSONString(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }
    // add by gaopeng 20191219 多组合多资产导入 end
    
    // 指令授信 start
    public TrdProductDeal prepareDeal(EtrBrowDepoInfoDto entity) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDeal,entity);
        trdProductDeal.setTradeId(entity.getEntrustId());
        trdProductDeal.setpTradeId(entity.getEntrustId());
        trdProductDeal.setCounterId(entity.getBussEtrInvestPersonInfoDto().getCounterId());
        trdProductDeal.setTradeAmt(entity.getPrinAmt());
        trdProductDeal.setPrinAmt(entity.getPrinAmt());
        return trdProductDeal;
    }
    
    
    /**
     * 交易授信处理
     * @param entrustId
     */
    public void dealEtrIsNeedOccupyCre(EtrBrowDepoInfoDto entity, String operationType) {
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setProdid(entity.getPortfolioId());
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        TrdProductDeal trdProductDeal = prepareDeal(entity);
        inputDto.setTrdProductDeal(trdProductDeal);

        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getrTradeId());
        trdProductDealAdd.setPortfolioId(entity.getPortfolioId());
//        trdProductDealAdd.setCreditSource(entity.getCreditSource());
        inputDto.setTrdProductDealAdd(trdProductDealAdd);

        trdProductDeal.setTradeStatus(operationType);
        AllTradeCommonUtil.do投资指令授信(inputDto);
    }
    
    /**
     * 关闭/撤回指令授信处理
     * @param entrustId
     */
    public void dealCloseEtrIsNeedOccupyCre(String entrustId) {
		Map<String, Object> map = commonBusinessService.selectByEntrustId(entrustId);
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setProdid((String)map.get("portfolioId"));
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        TrdProductDeal trdProductDeal = (TrdProductDeal) BeanUtil.mapToObject(map,TrdProductDeal.class);
        trdProductDeal.setpTradeId((String)map.get("entrustId"));
        trdProductDeal.setTradeAmt((BigDecimal)map.get("entrustAmt"));
        trdProductDeal.setPrinAmt((BigDecimal)map.get("entrustAmt")); 
        trdProductDeal.setTradeDate((Date)map.get("entrustDate"));
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        inputDto.setTrdProductDeal(trdProductDeal);
        
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getrTradeId());
        trdProductDealAdd.setPortfolioId((String)map.get("portfolioId"));
//        trdProductDealAdd.setCreditSource((String)map.get("creditSource"));
        inputDto.setTrdProductDealAdd(trdProductDealAdd);

        AllTradeCommonUtil.do投资指令授信(inputDto);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doBack(EtrBrowDepoInfoDto entity) {
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && entity.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
            dealEtrIsNeedOccupyCre(entity, DictCodeListExt1.交易状态_撤销.getValue());
        } 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doRevoke(EtrBrowDepoInfoDto entity) {
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && entity.getPs().equals(DictCodeList.交易方向_买入.getValue())) {
            dealEtrIsNeedOccupyCre(entity, DictCodeListExt1.交易状态_撤销.getValue());
        }
    }
    // 指令授信 end
}
