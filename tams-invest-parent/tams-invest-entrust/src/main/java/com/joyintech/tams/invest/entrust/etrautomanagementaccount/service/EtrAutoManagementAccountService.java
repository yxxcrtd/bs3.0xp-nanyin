package com.joyintech.tams.invest.entrust.etrautomanagementaccount.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.EtrEntrustWw;
import com.joyintech.tams.common.base.entity.EtrEntrustWwDetail;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.common.service.AuthProcessService;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrautomanagementaccount.dto.EtrAutoManagementAccountDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EtrAutoManagementAccountService extends CommonExportService<EtrAutoManagementAccountDto> implements AuthProcessService<EtrAutoManagementAccountDto> {
    /**
     * 平台日期
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    
    @Autowired
    private EtrCommonService etrCommonService;


    @Override
	public String checkForSubmitMulti(List<FlowEntity> entityList, boolean isMutiApproval) {
    	 // 单笔指令 不需要校验
        if (entityList.size() == 1){
            return null;
        }
        if (isMutiApproval){
            for (FlowEntity flowEntity : entityList){
                String message = checkAuthUser(this.getBussinessEntityByWorkflowId(flowEntity.getWorkflowId()),flowEntity);
                if(StringUtil.isNotEmpty(message)){
                    // 校验不通过 清除缓存
                    removeCheckAuthUser(flowEntity);
                    return message;
                }
            }
            // 校验通过 清除缓存
            removeCheckAuthUser(entityList.get(0));
        }
        return null;
	}

	@Override
    public void doComplete(EtrAutoManagementAccountDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate = DateUtil.platDateFromCache();
        dto.setEntrustDate(sysDate);

        //风险相关
        basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());

        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/doComplete",dto,
                Integer.class);

        doDeleteFlow(dto.getWorkflowId());
    }

    public Page<EtrAutoManagementAccountDto> selectForPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());

        if (StringUtil.isNotEmpty((String)param.get("entrustDate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)param.get("entrustDate"));
            param.put("startDate", dateList.get(0));
            param.put("endDate", dateList.get(2));
        }
//        if (StringUtil.isNotEmpty((String)param.get("portfolioId"))) {
//            param.put ("portfolioId","F17_".concat ((String)param.get ("portfolioId")));
//        }


        Page<EtrAutoManagementAccountDto> result = restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/findPage", param,
                EtrAutoManagementAccountDto.class);

        return result;
    }




    public Page<SelectOptionDto> getSelectPortfolioId(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("loginUser", user.getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/getSelectPortfolioId" , param,
                SelectOptionDto.class);
    }

    public List<SuggestDto> getFinprodMarketId(String finprodId){

        return restTemplate.getForList(ServiceApplication.ENTRUST_SERVICE_NAME+"/EtrAutoManagementAccount/getFinprodMarketId?finprodId="+finprodId,
                SuggestDto.class);
    }

    public Page<SelectOptionDto> getSelectFinprodMarketId(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/getSelectFinprodMarketId" ,param,
                SelectOptionDto.class);

    }

    public List<SelectOptionDto> finprodMarketIdChange(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/finprodMarketIdChange" ,param,
                SelectOptionDto.class);

    }

    public List<SelectOptionDto> finprodMarketIdType7Change(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/finprodMarketIdType7Change" ,param,
                SelectOptionDto.class);

    }

    @Override
    public void doBack(EtrAutoManagementAccountDto entity){
        //just for aspectupdate
        // ----------add by yushilong 20200721 预占功能 start----------
        if (StringUtil.equals(BussinessConstant.SUBMIT, entity.getOperateType())||StringUtil.equals("update", entity.getOperateType())) {
            try {
                EntrustRdsCommonAop.doListPreOccupy(entity.getSelectedItem(),entity.getAssetType(),
                        BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue(),
                        false, null);
                
                //redis 重新按照原数据进行占用 add by zhangpei 20210128
                rePreemptReidsAndCredit(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ----------add by yushilong 20200721 预占功能 end----------
    }

    @Override
    public void doInsert(EtrAutoManagementAccountDto entity, OperationType type){
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/doInsert",
                entity, Integer.class);
        if ("save".equals(entity.getOperateType())) {
            return;
        }
        doInsertFlow(entity);
    }
    private void doInsertFlow(EtrAutoManagementAccountDto dto) {

            dto.setOperateType(dto.getOperateType());
            BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
            EtrEntrustWw etrEntrustWw = new EtrEntrustWw();

            dto.setPortfolioId (dto.getPortfolioId ());
            dto.setFinprodType2(dto.getFinprodType2 ());
            dto.setEntrustStatus(BussinessDictCodeList.委托状态_流程中.getValue());
            BeanUtil.copyPropertiesIgnoreNull(etrEntrustWw, dto);

            if(iPlatformDate!= null){
                etrEntrustWw.setEntrustDate(iPlatformDate.getSysDate());
            }


            List<EtrEntrustWwDetail> list = new ArrayList<> ();
            if(dto.getSelectedItem () != null){
                for(int i = 0; i < dto.getSelectedItem ().size (); i++){
                    EtrEntrustWwDetail etrEntrustWwDetail = new EtrEntrustWwDetail();
                    BeanUtil.copyPropertiesIgnoreNull(etrEntrustWwDetail, dto);
                    etrEntrustWwDetail.setSeqNo (IDGenerator.generate());
                    etrEntrustWwDetail.setPortfolioId (dto.getSelectedItem ().get (i).getPortfolioId ());
                    etrEntrustWwDetail.setPortfolioAmt (dto.getSelectedItem ().get (i).getPortfolioAmt ());
                    etrEntrustWwDetail.setEntrustAmt (dto.getSelectedItem ().get (i).getSubjectNum ());
                    list.add (etrEntrustWwDetail);
                }
            }
            entity.setEtrEntrustWwDetailList (list);

            entity.setEtrEntrustWw(etrEntrustWw);

            entity.setWorkFlowId(dto.getWorkflowId());

            restTemplate.postForObject(
                    ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustwwcommonbase/doInsertEtrFlow", entity,
                    Boolean.class);
    }

    @Override
    public void doDelete(String workflowId) {
        doDeleteFlow(workflowId);
    }
    private void doDeleteFlow(String workflowId) {
        Map<String,Object> param = new HashMap<> ();
        param.put("workflowId",workflowId);
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustwwcommonbase/doDeleteEtrFlow", param,
                Boolean.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(EtrAutoManagementAccountDto entity){
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/doInsert",
                entity, Integer.class);
        if (!StringUtil.equals("revoke", entity.getOperateType()) && !StringUtil.equals("delete", entity.getOperateType())) {
            // 先删除流程中数据
            doDeleteFlow(entity.getWorkflowId());
            // 重新insert
            doInsertFlow(entity);
            ProcessMark newEntity;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(EtrAutoManagementAccountDto.class, true);
                // 工作流ID
                newEntity.setWorkflowId(entity.getWorkflowId());
                newEntity.setStatus(entity.getStatus());
                newEntity.setBusinessData(JsonUtils.object2Json(entity));
                // 更新业务表数据
                // 操作留痕表
                WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ----------add by yushilong 20200721 预占功能 start----------
        //update zhangweiqi SNSTZJY-2553  自主专户指令，指令生效后，修改生效指令，审批人无法审批 begin
        if (StringUtil.equals("update", entity.getOperateType())) {
            try {
            	for(EtrAutoManagementAccountDto dto:entity.getSelectedItem()){
            		if(!StringUtil.isNotEmpty(dto.getEntrustId())){
            			dto.setEntrustId(entity.getEntrustId()+dto.getPortfolioId());
            		}
            	}
            	entity.setOperateType("submit");
            	  //update zhangweiqi SNSTZJY-2553  自主专户指令，指令生效后，修改生效指令，审批人无法审批 end
                AjaxResult ar = EntrustRdsCommonAop.doListPreOccupy(entity.getSelectedItem(),
                        entity.getAssetType(),
                        BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
                        false, null);
                //释放原数据 add by zhangpei 20210128
                rePreemptCredit(entity,DictCodeListExt1.交易状态_撤销.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // ----------add by yushilong 20200721 预占功能 end----------
    }


    /**
     *
     * 编辑、双击查看页面详情
     * 根据主键查询
     * @param entrustId String
     * @return EtrBrowDepoInfoDto
     * @see
     * @since 1.0
     */
    public EtrAutoManagementAccountDto selectByKey(String entrustId){
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/selectByKey?entrustId=" + entrustId,
                EtrAutoManagementAccountDto.class);
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
        exportParam.config("/com/joyintech/tams/invest/entrust/etrautomanagementaccount/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<EtrAutoManagementAccountDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("entrustDate", propName)) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }



    public List<EtrAutoManagementAccountDto> portfolioIdChange(Map<String, Object> map) {
        Date sysDate = DateUtil.platDateFromCache();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        String sd = DateUtil.format (sysDate);
        map.put ("entrustDate",sd);
        return restTemplate.postForList (ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/portfolioIdChange", map,
                EtrAutoManagementAccountDto.class);
    }

    public Page<EtrAutoManagementAccountDto> selectNewForPage(Map<String, Object> param) {
        Date sysDate = DateUtil.platDateFromCache();
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        String sd = DateUtil.format (sysDate);
        if(!StringUtil.equals ("0",param.get ("modelStatus")))//update zhangweiqi 2021-1-12 SNSTZJY-2549 自主专户投资指令，AAsfpjccs组合持仓在新建和修改页面显示不一致
        param.put ("entrustDate",sd);

        Page<EtrAutoManagementAccountDto> result = restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/selectNewForPage", param,
                EtrAutoManagementAccountDto.class);

        return result;
    }

    /**
     * 关闭委托
     * <功能详细描述>
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId,String closeWay) {
        return restTemplate.getForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/close?entrustId="+entrustId+"&closeWay="+closeWay,
                boolean.class);
    }

    public List<EtrAutoManagementAccountDto> selectAllData(List<String> entrustIdList) {
        Map<String,Object> map = new HashMap<>();
        map.put ("entrustIdList",entrustIdList);
        return restTemplate.postForList (ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrAutoManagementAccount/selectAllData", map,
                EtrAutoManagementAccountDto.class);
    }
    
    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
    	EtrAutoManagementAccountDto dto = this.getBussinessEntity(entity);
        CommonBusinessService commonBusinessService = SpringContextUtil.getBean(CommonBusinessService.class);
        if (taskId != null) {
            try {
                BigDecimal amount = dto.getEntrustNum();
                if (amount == null) {
                    amount = dto.getEntrustAmt();
                }
                Map<String, Object> params = CommonBusinessService.setParams(null, amount, LoginUserUtil.getUserName(),
                		dto.getFinprodType2(), dto.getType6(), null, dto.getPs()
                        , dto.getFinprodId(), entity.getWorkflowId()
                        );
                // add by zhangrui1 风险审批修改 start
                params.put("riskTrialRetDto", dto.getRiskTrialRetDto());
                // add by zhangrui1 风险审批修改 end
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
    }

//
//    List<> finprodMarketIdChange(Map<String, Object> map){
//
//    }
    /**
     * 组装原数据预占数据
     * add by zhangpei
     * 20210128
     * @param entity
     * @return
     */
    private EtrAutoManagementAccountDto buildOldCreaditAndReidsList(EtrAutoManagementAccountDto entity){
    	EtrAutoManagementAccountDto entityOld=new EtrAutoManagementAccountDto();
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("modelStatus", "0");
    	param.put("entrustId", entity.getEntrustId());
    	param.put("portfolioIdChange", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue()+GlobalConstant.UNDER_LINE+ entity.getPortfolioId ());
    	param.put("portfolioId", entity.getPortfolioId());
    	param.put("workflowId", "");
    	//根据委托代码查询原数据的明细数据
    	List<EtrAutoManagementAccountDto> list = restTemplate.postForList(
    		                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAutoManagementAccount/getSelectPortfolioIdModelForOld", param,
    		                EtrAutoManagementAccountDto.class);
    	//根据委托代码查询原数据的汇总数据
    	EtrAutoManagementAccountDto dto = restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                + "/EtrAutoManagementAccount/selectByKeyForOld?entrustId=" + entity.getEntrustId(),
        EtrAutoManagementAccountDto.class);
    	List<EtrAutoManagementAccountDto> selectedItem=new ArrayList<EtrAutoManagementAccountDto>();
    	if(!StringUtil.isEmptyList(list)){
    		for(EtrAutoManagementAccountDto rows:list){
    			EtrAutoManagementAccountDto item=new EtrAutoManagementAccountDto();
    			BeanUtil.copyPropertiesIgnoreNull(item, dto);
    			BeanUtil.copyPropertiesIgnoreNull(item, rows);
    			//各个明细数据还是取原明细数据的值
    			item.setEntrustAmt(rows.getPortfolioAmt());
    			item.setEntrustNum(rows.getPortfolioAmt());
    			item.setEntrustId(entity.getEntrustId()+rows.getPortfolioId());
    			selectedItem.add(item);
    		}
    	}
    	entityOld.setSelectedItem(selectedItem);
    	return entityOld;
    }
    /**
     * redis
     * add by zhangpei
     * 20210128
     * @param entityNew
     */
    public void rePreemptReidsAndCredit(EtrAutoManagementAccountDto entityNew){
    	EtrAutoManagementAccountDto entity=buildOldCreaditAndReidsList(entityNew);
        try {
        	if(!StringUtil.isEmptyList(entity.getSelectedItem())){
            	entity.setOperateType("submit");
            	//Reids预占
                EntrustRdsCommonAop.doListPreOccupy(entity.getSelectedItem(),
                        entity.getAssetType(),
                        BussinessDictCodeListRds.接口系统ID_预占批量接口.getValue(),
                        false, null);
                //Credit
                etrCommonService.executeOccupyCreditLines(entity.getSelectedItem(), DictCodeListExt1.交易状态_初始.getValue(),"doUpdate");
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 授信
     * @param entityNew
     */
    public void rePreemptCredit(EtrAutoManagementAccountDto entityNew,String status){
    	EtrAutoManagementAccountDto entity=buildOldCreaditAndReidsList(entityNew);
        try {
        	if(StringUtil.isNotEmpty(entity)){
        		if(StringUtil.isNotEmpty(entity.getSelectedItem())){
        			entity.setOperateType("submit");
                    //Credit
                    etrCommonService.executeOccupyCreditLines(entity.getSelectedItem(), status,"doUpdate");
        		}
        	}
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}