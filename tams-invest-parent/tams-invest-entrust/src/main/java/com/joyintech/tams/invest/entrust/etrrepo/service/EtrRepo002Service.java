/*
 * 文件名：EtrRepo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrrepo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.trade.BussRepoSecService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;
import com.joyintech.tams.invest.entrust.etrwtspotex.dto.EtrInvestPersonInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 新建service
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月24日
 * @see EtrRepo002Service
 * @since 1.0
 */
@Service
public class EtrRepo002Service extends BaseService<EtrRepoDto> implements BaseProcessService<EtrRepoDto> {

    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;
    
    @Autowired(required = false)
    private BussRepoSecService bussRepoSecService;

    @Autowired
    private EtrCommonService etrCommonService;
    /**
     * 工作流完成
     */
    @Override
    public void doComplete(EtrRepoDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate = DateUtil.platDateFromCache();
        dto.setEntrustDate(sysDate);
        basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/doComplete", dto,
            Integer.class);
//        防止更新时删除
        if(bussRepoSecService != null){
            bussRepoSecService.insert(dto.getBussRepoSec(),dto.getEntrustId());
        }
    }
    /**
     * 
     * 流程发起时调用
     */
     @Override
     public void doInsert(EtrRepoDto dto, OperationType type){
         dto.setOperateType(type.toString());
         restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/doInsert",dto,
             Integer.class);
     }

     /**
      * 
      * 流程修改时调用
      */
     @Override
     public void doUpdate(EtrRepoDto dto){
         restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/doInsert",dto,
             Integer.class);
     }
     /**
      * 新建页面组合代码联想下拉框 <功能详细描述>
      * 
      * @return
      * @see
      * @since 1.0
      */
     public Page<SelectOptionDto> getentrustIdAdd(Map<String, Object> map) {
         map.put("userId", SecurityUtil.getLoginUser().getUserName());
         return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                                        + "/EtrRepo/getentrustIdAdd" , map,
             SelectOptionDto.class);
     }
     /**
      * 关闭委托 <功能详细描述>
      * 
      * @param entrustId
      * @param closeWay
      * @see
      * @since 1.0
      */
     public boolean closeDelegate(String entrustId, String closeWay) {
         return restTemplate.getForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/close?entrustId=" + entrustId
                                          + "&closeWay=" + closeWay,
             boolean.class);
     }
     /**
      * 撤回委托 <功能详细描述>
      * 
      * @see
      * @since 1.0
      */
     public boolean recallDelegate(EtrRepoDto dto) {
         return restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/recall", dto, boolean.class);
     }
     
     /**
      * 查询修改/查看返显数据 <功能详细描述>
      * 
      * @param entrustId
      * @return
      * @see
      * @since 1.0
      */
     public EtrRepoDto selectByKey(String entrustId) {
         return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                          + "/EtrRepo/selectByKey?entrustId=" + entrustId,
             EtrRepoDto.class);
     }
     /**
      * 查询证券托管户：联想框文本框
      *
      * @param parameters 画面参数
      * @return 证券托管户：联想框文本框
      * @see
      * @since 1.0
      */
     public List<SelectOptionDto> getSecManageAcctId(Map<String, Object> parameters) {
         List<SuggestDto> tempList = restTemplate.postForList(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/getSecManageAcctId",
             parameters, SuggestDto.class);

         List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
         if (!StringUtil.isEmptyList(tempList)) {
             for (SuggestDto suggestDto : tempList) {
                 SelectOptionDto selectOptionDto = new SelectOptionDto();
                 selectOptionDto.setId(suggestDto.getCode());
                 selectOptionDto.setText(suggestDto.getName());
                 if (StringUtil.isNotBlank((String) parameters.get("portfolioId"))) {
                     selectOptionDto.setParentId((String) parameters.get("portfolioId"));
                 }
                 resultList.add(selectOptionDto);
             }
         }

         return resultList;
     }
     /**
      * 组合可用现金查询
      * <功能详细描述>
      * @return
      * @see
      * @since 1.0
      */
     public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
         Date vdate = DateUtil.parse((String)param.get("vdate"));
         String calId = (String)param.get("calId");
         if(StringUtil.isNotEmpty(calId)){
             List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(vdate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15),calId);
             Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
             param.put("sysDateT1",DateUtil.format(tradeDay));
         }
         return etrCommonService.getProAvailableCash(param);
//         return restTemplate.postForObject(
//             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/getProAvailableCash",param,
//             Map.class);
     }
     /**
      * 
      * 判断是否为节假日
      * <功能详细描述>
      * @param param
      * @return
      * @see
      * @since 1.0
      */
     public Map<String, Object> judgeIsHday(Map<String, Object> param){
         String mdateAdd = (String)param.get("mdateAdd");
         String calId = (String)param.get("calId");
         boolean result = dateCalService.isDealCalendarHoliday(mdateAdd,calId);
//         AjaxResult msg =new AjaxResult();
//         msg.setMessage("到期日为节假日");
//         msg.setMessageType(MessageType.错误消息);
         if(result == true){
             List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(mdateAdd),DateUtil.addDay(DateUtil.parse(mdateAdd), 15),calId);
             Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(mdateAdd), 1, holidayList, "1");
             param.put("mdateAdd2",DateUtil.format(tradeDay));
             param.put("msg", "到期日为节假日");
         }
         return param;
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
     public BigDecimal selectStandardAmt(Map<String, Object> map){
         map.put("sysDate", DateUtil.getSysDate());
//         BigDecimal standardAmt = new BigDecimal(0);
//         Map<String, BigDecimal> amtMap = restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/selectAmt",map,
//             Map.class);
//         BigDecimal wflamt = restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/selectWflAmt",map,
//             BigDecimal.class);
         BigDecimal prinAmt = restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/selectStandardAmt",map,
                 BigDecimal.class);
//         BigDecimal amt = amtMap.get("amt") == null ? null : new BigDecimal(String.valueOf(amtMap.get("amt")));
//         BigDecimal wflAmt = amtMap.get("wflAmt") == null ? null : new BigDecimal(String.valueOf(amtMap.get("wflAmt")));
//         BigDecimal prinAmt = amtMap.get("prinAmt") == null ? null : new BigDecimal(String.valueOf(amtMap.get("prinAmt")));
//         if(amt!=null){
//             standardAmt = standardAmt.add(amt);
//         }
//         if(wflAmt!=null){
//             standardAmt = standardAmt.add(wflAmt);
//         }
//         if(prinAmt!=null){
//        	 standardAmt = prinAmt.subtract(standardAmt);
//         }
         return prinAmt;
     }


    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        EtrRepoDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            try {
            	Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), dto.getEntrustAmt(), LoginUserUtil.getUserName(),
                        BussinessDictCodeList.平安资产类型_回购.getValue(), dto.getAssetType1(), dto.getTradeMarket(), dto.getPs()
                        , null,entity.getWorkflowId());
            	return commonBusinessService.是否需要上级审批_委托用(params);
            }
            catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        }
        else {
            return null;
        }
//        return null;
    }

    /**
     * 查询质押券的到期日
     * @return
      */
    public Date selectRepoMdate(Map<String,Object> param){
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/selectRepoMdate",param,
            Date.class);
    }


    /**
     * 查询 通道现金
     *
     * @param map
     * @return
     */
    public BigDecimal queryChlCash(Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/queryChlCash", map,BigDecimal.class);
    }


    /**
     * 查询 划款方式
     * @param map
     * @return
     */
    public String queryPayType(Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/queryPayType", map,String.class);
    }

    /**
     * 获取委托列表数据
     *
     * @return
     */
    public EtrRepoDto getEntrustListData(String tempList) {
        EtrRepoDto dto = new EtrRepoDto();
        // 质押券信息列表
        List<PledgeBondInfoDto> pledgeBondList = new ArrayList<>();
        dto.setBussRepoSec(new BussRepoSecDto());
        dto.getBussRepoSec().setPageFrom("EtrRepo002");
        dto.getBussRepoSec().setPledgeBondList(pledgeBondList);
        // 委托列表
        List<EtrRepoDto> etrRepoDtoList = new ArrayList<>();
        dto.setSelectedItem(etrRepoDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++ ) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray)objects.get(i)).toJSONString());
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                EtrRepoDto etrRepoDto = new EtrRepoDto();
                etrRepoDtoList.add(etrRepoDto);
                etrRepoDto.setBussRepoSec(new BussRepoSecDto());
                etrRepoDto.getBussRepoSec().setPageFrom("EtrRepo002");
                etrRepoDto.getBussRepoSec().setPledgeBondList(new ArrayList<PledgeBondInfoDto>());
                // 根据模板中固定的下标来赋值
                etrRepoDto.setIndexId(rowData.get(0).toString());
                // 委托开始日
                etrRepoDto.setVdate(DateUtil.parse(rowData.get(1).toString()));
                // 委托终止日
                etrRepoDto.setMdate(DateUtil.parse(rowData.get(2).toString()));
                // 组合代码
                etrRepoDto.setPortfolioId(rowData.get(3).toString());
                // 交易方向
                etrRepoDto.setPs(rowData.get(4).toString().split(":")[0]);
                // 币种
                etrRepoDto.setCcy(rowData.get(5).toString().split(":")[0]);
                // 清算速度
                etrRepoDto.setSettleSpreed(rowData.get(6).toString().split(":")[0]);
                // 回购类型
                if (StringUtil.isNotEmpty(rowData.get(7))) {
                    etrRepoDto.setAssetType1(rowData.get(7).toString().split(":")[0]);
                }
                // 委托金额
                etrRepoDto.setEntrustAmt(new BigDecimal(rowData.get(8).toString()));
                // 价格模式
                etrRepoDto.setPriceModel(rowData.get(9).toString().split(":")[0]);
                // 利率（%）
                if (StringUtil.isNotBlank(rowData.get(10).toString())) {
                    etrRepoDto.setPrice(new BigDecimal(rowData.get(10).toString()));
                }
                // 最低成交金额
                if (StringUtil.isNotBlank(rowData.get(11).toString())) {
                    etrRepoDto.setMinAmt(new BigDecimal(rowData.get(11).toString()));
                }
                // 交易场所
                etrRepoDto.setTradeMarket(rowData.get(12).toString().split(":")[0]);
                // 期限
                etrRepoDto.setRepoTerm(new BigDecimal(rowData.get(13).toString()));
                //add by yujiaping 20210222 TISHKBS-1817 导入模板中增加“期限品种”字段 start
                //期限品种
                etrRepoDto.setTermType(rowData.get(14).toString());
                //add by yujiaping 20210222 TISHKBS-1817 导入模板中增加“期限品种”字段 end
                // 交易日历
                etrRepoDto.setCalendarId(rowData.get(15).toString());
                Map<String, Object> param = new HashMap<>();
                param.put("initValue", rowData.get(15).toString());
                param.put("page", 1);
                param.put("length", 20);
                Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                    ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectCalendarId",
                    param, SelectOptionDto.class);
                if (!selectOptionDtoPage.getRoot().isEmpty()) {
                    etrRepoDto.setCalendarIdName(selectOptionDtoPage.getRoot().get(0).getText());
                }
                // 证券账户
                if (StringUtil.isNotBlank(rowData.get(16).toString())) {
                    etrRepoDto.setSecuritiesCust(rowData.get(16).toString());
                    param = new HashMap<>();
                    param.put("initValue", rowData.get(16).toString());
                    param.put("portfolioId", rowData.get(3).toString());
                    param.put("tradeMarket", rowData.get(12).toString().split(":")[0]);
                    param.put("page", 1);
                    param.put("length", 20);
                    List<SelectOptionDto> selectOptionDtoList = this.getSecManageAcctId(param);
                    if (!StringUtil.isEmptyList(selectOptionDtoList)) {
                        etrRepoDto.setSecuritiesCustName(selectOptionDtoList.get(0).getText());
                    }
                }
                // 通道
                if (StringUtil.isNotBlank(rowData.get(17).toString())) {
                    etrRepoDto.setChlId(rowData.get(17).toString());
                    param = new HashMap<>();
                    param.put("initValue", rowData.get(17).toString());
                    param.put("page", 1);
                    param.put("length", 20);
                    selectOptionDtoPage = restTemplate.postForPage(
                        ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelId",
                        param, SelectOptionDto.class);
                    if (!StringUtil.isEmptyList(selectOptionDtoPage.getRoot())) {
                        etrRepoDto.setChlIdName(selectOptionDtoPage.getRoot().get(0).getText());
                    }
                }
                // 备注
                etrRepoDto.setRemark(rowData.get(18).toString());
                param = new HashMap<>();
                param.put("portfolioId", etrRepoDto.getPortfolioId());
                param.put("ccy", etrRepoDto.getCcy());
                param.put("vdate", etrRepoDto.getVdateText());
                param.put("calId", etrRepoDto.getCalendarId());
                param.put("manageId", etrRepoDto.getSecuritiesCust());
                param.put("chlId", etrRepoDto.getChlId());
                Map<String, Object> cashMap = this.getProAvailableCash(param);
                // 指令可用现金
                if (cashMap != null && cashMap.get("cashT") != null) {
                    etrRepoDto.setProAvailableCash(
                        new BigDecimal(cashMap.get("cashT").toString()));
                }
                else {
                    etrRepoDto.setProAvailableCash(BigDecimal.ZERO);
                }
                // // 指令可用持仓
                // etrRepoDto.setAssetAvailablePosition(rowData.get(9).toString());
                etrRepoDto.setEtrInvestPersonInfo(new EtrInvestPersonInfoDto());
                // 投资经理
                etrRepoDto.getEtrInvestPersonInfo().setInvManagerId(dto.getCreateUser());
                // 投资处室
                etrRepoDto.getEtrInvestPersonInfo().setRoom(dto.getCreateDept());
            }
            // 质押券信息
            PledgeBondInfoDto pledgeBondInfoDto = new PledgeBondInfoDto();
            // 债券代码
            pledgeBondInfoDto.setBondCode(rowData.get(19).toString());
            EtrRepoDto etrRepoDto = etrRepoDtoList.get(etrRepoDtoList.size() - 1);
            List<PledgeBondInfoDto> list = selectAvaPledgeBondDetailList(
                pledgeBondInfoDto.getBondCode(), etrRepoDto);
            if(!StringUtil.isEmptyList(list)) {
                // 债券简称
                // 可质押券面总额
                // 首次净价
                // 债券类型
                // 登记托管机构
                BeanUtil.copyPropertiesIgnoreNull(pledgeBondInfoDto, list.get(0));
            }
            // 债券代码
            pledgeBondInfoDto.setBondCode(rowData.get(19).toString());
            // 质押券面总额
            pledgeBondInfoDto.setPledgePrin(new BigDecimal(rowData.get(20).toString()));
            // 折算比例%
            pledgeBondInfoDto.setPledgeRatio(new BigDecimal(rowData.get(21).toString()));
            pledgeBondList.add(pledgeBondInfoDto);
            etrRepoDto.getBussRepoSec().setTotalPrinAmt(
                ArithmeticUtil.doAdd(etrRepoDto.getBussRepoSec().getTotalPrinAmt(),
                    ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(pledgeBondInfoDto.getPledgePrin(),
                            pledgeBondInfoDto.getPledgeRatio()),
                        new BigDecimal(100))));
            etrRepoDto.getBussRepoSec().getPledgeBondList().add(pledgeBondInfoDto);
        }
        dto.setSelectedItem(etrRepoDtoList);
        return dto;
    }
    /**
     * 查询可添加质押券
     *
     * @param bondCode
     * @param etrRepoDto
     * @return
     * @see
     * @since 1.0
     */
    private List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(String bondCode,
                                               EtrRepoDto etrRepoDto) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("portfolioId", etrRepoDto.getPortfolioId());
        params.put("ps", etrRepoDto.getPs());
        params.put("chlId", etrRepoDto.getChlId());
        params.put("secAcctId", etrRepoDto.getSecuritiesCust());
        params.put("bondCode", bondCode);
        List<PledgeBondInfoDto> list = bussRepoSecService.selectAvaPledgeBondDetailList(params);
        return list;
    }

    /**
     * 验证导入的数据
     * @param fileName 文件名
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName, 0, 1);

        int totalData = 0;
        for (int i = 2; i < objects.size(); i++ ) {
            List<String> rowData = objects.get(i);
            // 1~17均为空时认为所在行仅质押券信息
            for (int j = 0; j <= 17; j++ ) {
                if (StringUtil.isNotEmpty(rowData.get(j))) {
                    totalData++ ;
                    break;
                }
            }
        }
        if (totalData > 100) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", getMessage("ETR_E_0021"));
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }

        String totalMsg = "";
        JSONArray jsonArrays = new JSONArray();
        EtrRepoDto etrRepoDto = new EtrRepoDto();
        // 失败条数
        int errorNum = 0;
        // 循环中用的，一个委托对应的全部Arrays
        JSONArray itemJsonArrays = new JSONArray();
        // 错误message（一个委托一条，委托开始时初始化）
        String confirmMsg = "";
        for (int i = 2; i < objects.size(); i++ ) {
            // excel的行号
            String lineNo = String.valueOf(i + 1);
            List<String> rowData = objects.get(i);
            // 1~16均为空时认为所在行仅质押券信息
            boolean isEntrust = false;
            for (int j = 0; j <= 16; j++ ) {
                if (StringUtil.isNotEmpty(rowData.get(j))) {
                    isEntrust = true;
                    break;
                }
            }
            // 如果是委托所在行，check 0~17
            if (isEntrust) {
                if (StringUtil.isBlank(confirmMsg)) {
                    jsonArrays.addAll(itemJsonArrays);
                }
                else {
                    totalMsg = etrCommonService.StringAddByBr(totalMsg, confirmMsg);
                    errorNum++ ;
                }
                confirmMsg = "";
                itemJsonArrays = new JSONArray();
                // 委托开始日非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(1).toString(), "EtrRepo003.vdate",
                        lineNo));
                // 委托开始日格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(1).toString(), "EtrRepo003.vdate",
                        lineNo));
                // 委托终止日非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(2).toString(), "EtrRepo003.mdate",
                        lineNo));
                // 委托终止日格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(2).toString(), "EtrRepo003.mdate",
                        lineNo));
                // 组合代码非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(3).toString(),
                        "EtrRepo003.portfolioId", lineNo));
                // 组合代码check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkPortfolioIdExist(rowData.get(3).toString(),
                        "EtrRepo003.portfolioId", lineNo));
                // 交易方向非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(4).toString(), "EtrRepo003.ps",
                        lineNo));
                // 币种非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(5).toString(), "EtrRepo003.ccy",
                        lineNo));
                // 清算速度非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(6).toString(),
                        "EtrRepo003.settleSpreed", lineNo));
                // 回购类型非空(交易所时)
                if ((rowData.get(12).toString().contains(DictCodeListExt1.交易场所_上海证券交易所.getValue())
                     || rowData.get(12).toString().contains(
                         DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                    && StringUtil.isBlank(rowData.get(7).toString())) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        getMessage("ETR_E_0023", lineNo));
                }
                // 委托金额
                String entrustAmt = rowData.get(8).toString();
                // 委托金额非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(entrustAmt, "EtrRepo003.entrustAmt", lineNo));
                // 委托金额是数值
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsNumber(entrustAmt, "EtrRepo003.entrustAmt", lineNo));
                // 委托金额是大于0
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsGtZero(entrustAmt, "EtrRepo003.entrustAmt", lineNo));
                // 价格模式非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(9).toString(),
                        "EtrRepo003.priceModel", lineNo));
                // 利率
                String price = rowData.get(10).toString();
                // 利率非空
                if (rowData.get(9).toString().contains(DictCodeListExt2.价格模式_指定价格.getValue())
                    || rowData.get(9).toString().contains(DictCodeListExt2.价格模式_限价指令.getValue())) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        etrCommonService.checkIsBlank(price, "EtrRepo003.rate", lineNo));
                }
                // 利率是数值
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsNumber(price, "EtrRepo003.rate", lineNo));
                // 利率是大于0
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsGtZero(price, "EtrRepo003.rate", lineNo));
                // 最低成交金额
                String minAmt = rowData.get(11).toString();
                // 最低成交金额是数值
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsNumber(minAmt, "EtrRepo003.minAmt", lineNo));
                // 最低成交金额是大于0
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsGtZero(minAmt, "EtrRepo003.minAmt", lineNo));
                // 交易场所非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(12).toString(),
                        "EtrRepo003.tradeMarket", lineNo));
                // 交易场所选择银行间时，回购类型默认为质押式回购，置灰，不可改
                if (rowData.get(12).toString().contains(DictCodeListExt1.交易场所_银行间.getValue())) {
                    if (StringUtil.isNotEmpty(rowData.get(7).toString())
                        && !rowData.get(7).toString().contains(
                            DictCodeList.回购类型_质押式协议回购.getValue())) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                            getMessage("ETR_E_0022", lineNo));
                    }
                }
                // 期限
                String repoTerm = rowData.get(13).toString();
                // 期限非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(repoTerm, "EtrRepo003.repoTerm", lineNo));
                // 期限是数值
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsNumber(repoTerm, "EtrRepo003.repoTerm", lineNo));
                // 期限是大于0
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsGtZero(repoTerm, "EtrRepo003.repoTerm", lineNo));
             // 期限品种非空(交易所时)
                if ((rowData.get(12).toString().contains(DictCodeListExt1.交易场所_上海证券交易所.getValue())
                     || rowData.get(12).toString().contains(
                         DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                    && StringUtil.isBlank(rowData.get(14).toString())) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        getMessage("ETR_E_0033", lineNo));
                }
                // 交易日历非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(15).toString(),
                        "EtrRepo003.calendarId", lineNo));
                // 交易日历check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkCalendarIdExist(rowData.get(15).toString(),
                        "EtrRepo003.calendarId", lineNo));
                // 证券账户
                // 通道
                if ((StringUtil.isBlank(rowData.get(16).toString())
                     && StringUtil.isBlank(rowData.get(17).toString()))
                    || (StringUtil.isNotBlank(rowData.get(16).toString())
                        && StringUtil.isNotBlank(rowData.get(17).toString()))) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        getMessage("ETR_E_0020", getMessage("EtrRepo003.securitiesCust"),
                            getMessage("invest.channelId"), lineNo));
                }
                // 只有银行间时才能选择通道
                if (!rowData.get(12).toString().contains(DictCodeListExt1.交易场所_银行间.getValue())
                    && StringUtil.isNotBlank(rowData.get(17).toString())) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        getMessage("ETR_E_0028", getMessage("EtrRepo003.tradeMarket"),
                            getMessage("invest.channelId"), lineNo));
                }
                // 证券账户存在
                if (StringUtil.isNotBlank(rowData.get(16).toString())
                    && StringUtil.isNotBlank(rowData.get(3).toString())
                    && StringUtil.isNotBlank(rowData.get(12).toString())) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("initValue", rowData.get(16).toString());
                    param.put("portfolioId", rowData.get(3).toString());
                    param.put("tradeMarket", rowData.get(12).toString().split(":")[0]);
                    param.put("page", 1);
                    param.put("length", 20);
                    List<SelectOptionDto> selectOptionDtoList = this.getSecManageAcctId(param);
                    if (StringUtil.isEmptyList(selectOptionDtoList)) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, getMessage(
                            "ETR_E_0017", getMessage("EtrRepo003.securitiesCust"), lineNo));
                    }
                }
                // 通道存在
                if (StringUtil.isNotBlank(rowData.get(17).toString())) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("initValue", rowData.get(17).toString());
                    param.put("page", 1);
                    param.put("length", 20);
                    Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                        ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelId",
                        param, SelectOptionDto.class);
                    if (selectOptionDtoPage.getRoot().isEmpty()) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                            getMessage("ETR_E_0017", getMessage("invest.channelId"), lineNo));
                    }
                }
                // 用于可添加质押券check的参数
                etrRepoDto = new EtrRepoDto();
                // 组合代码
                etrRepoDto.setPortfolioId(rowData.get(3).toString());
                // 交易方向
                String[] ps = rowData.get(4).toString().split(":");
                if (ps.length > 0) {
                    etrRepoDto.setPs(rowData.get(4).toString().split(":")[0]);
                }
                // 证券账户
                etrRepoDto.setSecuritiesCust(rowData.get(16).toString());
                // 通道
                etrRepoDto.setChlId(rowData.get(17).toString());
            }
            // check18~20
            String bondCode = rowData.get(19).toString();
            // 债券代码非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(bondCode, "EtrRepo003.bondCode", lineNo));
            // 债券代码存在且可添加
            if (StringUtil.isNotEmpty(bondCode) && StringUtil.isNotEmpty(etrRepoDto.getPs())
                && StringUtil.isNotEmpty(etrRepoDto.getPortfolioId())) {
                List<PledgeBondInfoDto> list = selectAvaPledgeBondDetailList(bondCode, etrRepoDto);
                if (StringUtil.isEmptyList(list)) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        getMessage("ETR_E_0018", getMessage("EtrRepo003.bondCode"), lineNo));
                }
            }
            String pledgePrin = rowData.get(20).toString();
            // 质押券面总额(元)非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(pledgePrin, "EtrRepo003.pledgePrin", lineNo));
            // 质押券面总额(元)是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(pledgePrin, "EtrRepo003.pledgePrin", lineNo));
            // 质押券面总额(元)是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(pledgePrin, "EtrRepo003.pledgePrin", lineNo));
            String pledgeRatio = rowData.get(21).toString();
            // 折算比例非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(pledgeRatio, "EtrRepo003.pledgeRatio", lineNo));
            // 折算比例是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(pledgeRatio, "EtrRepo003.pledgeRatio", lineNo));
            // 折算比例是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(pledgeRatio, "EtrRepo003.pledgeRatio", lineNo));
            if (StringUtil.isBlank(confirmMsg)) {
                itemJsonArrays.add(rowData);
            }
        }
        // 最后一条特殊处理
        if (StringUtil.isBlank(confirmMsg)) {
            jsonArrays.addAll(itemJsonArrays);
        }
        else {
            totalMsg = etrCommonService.StringAddByBr(totalMsg, confirmMsg);
            errorNum++ ;
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
}
