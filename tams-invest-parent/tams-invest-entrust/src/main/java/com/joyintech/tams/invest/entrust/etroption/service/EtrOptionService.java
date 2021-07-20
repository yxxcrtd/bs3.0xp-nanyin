/*
 * 文件名：EtrOption001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etroption.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.entrust.etroption.dto.EtrOptionDto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 期权委托画面端service 〈功能详细描述〉
 *
 * @author likai
 * @version 1.0 2019年12月25日
 * @see EtrOptionService
 * @since 1.0
 */
@Service
public class EtrOptionService extends CommonExportService<EtrOptionDto> implements BaseProcessService<EtrOptionDto> {

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

    /**
     *
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 拆借委托分页查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrOptionDto> selectForPage(Map<String, Object> param) {
        if(StringUtil.isNotEmpty((String)param.get("entrustDate"))){
            List<String> list = StringUtil.splitToList(" ",(String)param.get("entrustDate"));
            param.put("startDate", list.get(0));
            param.put("endDate", list.get(2));
        }
        if(StringUtil.isNotEmpty(param.get("assetType11"))){
            List<String> contractTypeList = StringUtil.splitToList(",",(String)param.get("assetType11"));
            param.put("contractTypeList", contractTypeList);
        }
        if(StringUtil.isNotEmpty(param.get("assetType10"))){
            List<String> contractSubMatterList = StringUtil.splitToList(",",(String)param.get("assetType10"));
            param.put("contractSubMatterList", contractSubMatterList);
        }
        if(StringUtil.isNotEmpty(param.get("portfolioId"))){
            List<String> portfolioIdList = StringUtil.splitToList(",",(String)param.get("portfolioId"));
            param.put("portfolioIdList", portfolioIdList);
        }
        if(StringUtil.isNotEmpty(param.get("entrustStatus"))){
            List<String> entrustStatusList = StringUtil.splitToList(",",(String)param.get("entrustStatus"));
            // 去除空字符串值
            entrustStatusList = entrustStatusList.stream().filter(i->!i.isEmpty()).collect(Collectors.toList());
            param.put("entrustStatusList", entrustStatusList);
        }
        param.put("option", DictCodeListExt3.平安资产类型_期权.getValue());
        param.put("loginUser", SecurityUtil.getLoginUser().getUserName());
         Page<EtrOptionDto> page1 =restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/findPage", param,
                 EtrOptionDto.class);
        return page1;
    }

    /**
     * 查询页面组合代码联想文本框 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(String entrustId) {
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("param", entrustId);
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrOption/getentrustId" , map,
                                       SuggestDto.class);
    }

    public List<SuggestDto> getOptionTargetId(String entrustId) {
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("param", entrustId);
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrOption/getOptionTargetId" , map,
                SuggestDto.class);
    }

    /**
     * 新建页面组合代码联想下拉框 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getentrustIdAdd(Map<String, Object> map) {
        String loginUser = SecurityUtil.getLoginUser().getUserName();
        map.put("loginUser", loginUser);
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
            + "/EtrOption/getentrustIdAdd" , map,
            SelectOptionDto.class);
    }

    /**
     * 新建页面组合代码联想下拉框 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getOptionTargetIdadd(Map<String, Object> map) {
        String loginUser = SecurityUtil.getLoginUser().getUserName();
        map.put("loginUser", loginUser);
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrOption/getOptionTargetIdadd" , map,
                SelectOptionDto.class);
    }


    /**
     * 撤回委托
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrOptionDto entity) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/recall",entity,
            boolean.class);
    }

    /**
     * 关闭委托
     * <功能详细描述>
     * @param entrustId
     * @param closeWay
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/close?entrustId="+entrustId+"&closeWay?="+closeWay,
            boolean.class);
    }
//
//    /**
//     * 删除委托
//     * <功能详细描述>
//     * @param entrustId
//     * @see
//     * @since 1.0
//     */
//    public boolean deleteDelegate(String entrustId) {
//        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrLendSpecInfo/delete?entrustId="+entrustId,
//            boolean.class);
//    }
//
    /**
     * 校验方法
     *
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrOptionDto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/check", dto,
            ValidMessage.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(EtrOptionDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        
        if(doDeleteFlow(dto.getWorkflowId())){
            Date sysDate = DateUtil.platDateFromCache();
            dto.setEntrustDate(sysDate);
            basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());
            restTemplate.postForObject(
                    ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/doComplete",dto,
                    Integer.class); 
        }
    }
   /**
    *
    * 流程发起时调用
    */
    @Override
    public void doInsert(EtrOptionDto dto, OperationType type){
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/doInsert",dto,
                Integer.class);
        
        if ("submit".equals(dto.getOperateType())) {
            doInsertFlow(dto);
        }
    }

    /**
     *
     * 流程修改时调用
     */
    @Override
    public void doUpdate(EtrOptionDto dto){
        boolean flag = restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/doUpdate",dto,
                Boolean.class);
        
        if (!flag){
            return;
        }
        
        if (!StringUtil.equals("revoke", dto.getOperateType()) 
                && !StringUtil.equals("delete", dto.getOperateType())) {
            
            // 执行通用方法删除并重新插入流程指令数据
            boolean isSuccess = doDeleteFlow(dto.getWorkflowId()) && doInsertFlow(dto);
            
            if(isSuccess){
                ProcessMark newEntity;
                try {
                    newEntity = WorkFlowUtil.getBusinessObjectFromRequest(EtrOptionDto.class, true);
                    newEntity.setWorkflowId(dto.getWorkflowId());
                    newEntity.setStatus(dto.getStatus());
                    newEntity.setBusinessData(JsonUtils.object2Json(dto));
                    // 操作留痕表
                    WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
                } catch (Exception e){
                    throw new RuntimeException("数据留痕失败", e);
                }
            }
        }
    }

    @Override
    public void doBack(EtrOptionDto dto) {
        if (doDeleteFlow(dto.getWorkflowId())){
            restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/doBack",dto,
                    Boolean.class);
        }
    }

    /**
     *
     * 流程作废时调用
     */
//    @Override
//    public void doDelete(String workflowId){
//        restTemplate.getForObject(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrLendSpecInfo/doDelete?workflowId="+workflowId,
//            Integer.class);
//    }
    /**
     * 修改/查看反显查询
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrOptionDto selectByKey(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrOption/selectByKey?entrustId="+entrustId,
                EtrOptionDto.class);
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
        exportParam.config("/com/joyintech/tams/invest/entrust/etroption/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<EtrOptionDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
//    @Override
//    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
//        if(StringUtil.equals(propName,"transactioncom")){
//            cell.setCellValue(value+"%");
//        }
//    }
    /**
     *
     * 查询文件上传详情
     * <功能详细描述>
     * @param businessId String
     * @return List<SysUploadFileInfo>
     * @see
     * @since 1.0
     */
//    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId){
//        return restTemplate.getForList(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrLendSpecInfo/selectFileByBusinessId?businessId="+businessId,
//            SysUploadFileInfo.class);
//    }
    /**
     *
     * 判断是否为节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult judgeIsHday(Map<String, Object> param){
        String mdateAdd = (String)param.get("mdateAdd");
        String calId = (String)param.get("calId");
        boolean result = dateCalService.isDealCalendarHoliday(mdateAdd,calId);
        AjaxResult msg =new AjaxResult();
        msg.setMessage("到期日为节假日");
        msg.setMessageType(MessageType.错误消息);
        return result == true?msg:null;
    }
    /**
     *
     * 查询授权额度
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
//    public BigDecimal selectLimitAmt(EtrLendSpecInfoDto dto){
//        Map<String, Object> map = new HashMap<>();
//        map.put("userName", SecurityUtil.getLoginUser().getUserName());
//        map.put("assetType", DictCodeList.平安资产类型_拆借.getValue());
//        map.put("assetSecType", dto.getAssetType1());
//        map.put("tradeMarket", dto.getTradeMarket());
//        map.put("ps",dto.getPs());
//        return restTemplate.postForObject(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/SelectLimitAmt/selectLimitAmt", map,
//            BigDecimal.class);
//    }
//
//    @Override
//    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
//        Map<String, Object> variables = new HashMap<>();
//        EtrLendSpecInfoDto dto = this.getBussinessEntity(entity);
//        if (taskId != null) {
//            try {
//    Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), amount, LoginUserUtil.getUserName(),
//            DictCodeListExt3.平安资产类型_期权.getValue(), dto.getAssetType1(), dto.getTradeMarket(), dto.getPs()
//            // 资产类型改为授权类型 add by renping 2019-08-06  start
//            , dto.getAssetType10(), dto.getWorkflowId());
//    return commonBusinessService.是否需要上级审批_委托用(params);
//            }
//            catch (WorkFlowException e) {
//                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
//                throw new WorkFlowException(e);
//            }
//        }
//        else {
//            return null;
//        }
////        return null;
//    }

    private boolean doInsertFlow(EtrOptionDto dto){
        boolean isSuccess = false;
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        
        try {
            // EtrEntrust
            EtrEntrust etrEntrust = new EtrEntrust();
            BeanUtil.copyProperties(etrEntrust,dto);
            BeanUtil.copyProperties(etrEntrust,dto.getEtrInvestPersonInfo());
            etrEntrust.setEntrustStatus(DictCodeListExt3.委托状态_流程中.getValue());
            etrEntrust.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_期权.getValue());
            entity.setEtrEntrust(etrEntrust);

            // EtrEntrustAdd
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            BeanUtil.copyProperties(etrEntrustAdd,dto);
            etrEntrustAdd.setBusiPs(dto.getPs());
            etrEntrustAdd.setVdate(dto.getVdateAdd());
            etrEntrustAdd.setMdate(dto.getMdateAdd());
            etrEntrustAdd.setAssetType(DictCodeListExt3.平安资产类型_期权.getValue());
            try {etrEntrustAdd.setOptFeePayType2((Double.parseDouble(dto.getOptFeePayType2())/100)+"");} catch (Exception e) {}
            entity.setEtrEntrustAdd(etrEntrustAdd);

            // 附件 MstFileInputParameter
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            entity.setMstFileInputParameter(mstFileInputParameter);

            // WorkflowId
            entity.setWorkFlowId(dto.getWorkflowId());

            isSuccess = restTemplate.postForObject(
                    ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/doInsertEtrFlow", entity,
                    Boolean.class);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // 对象转换赋值失败
            throw new RuntimeException("对象转换赋值失败", e);
        } 
        return isSuccess;
    }

    private boolean doDeleteFlow(String workflowId) {
        Map<String, Object> param = new HashMap<>();
        param.put("workflowId", workflowId);
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/doDeleteEtrFlow", param,
                Boolean.class);

    }
}
