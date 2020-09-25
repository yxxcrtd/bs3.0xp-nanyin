/*
 * 文件名：FinMdateAdjustment002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.controller;

import java.util.*;
import javax.validation.Valid;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.dto.FinMdateAdjustment001Dto;
import com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.service.FinMdateAdjustment002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 到期日调整
 * 
 * @author zhangliang
 * @version 1.0 2018年11月1日
 * @see FinMdateAdjustment002Controller
 * @since 1.0
 */
@Controller
@PageId("FinMdateAdjustment002")
@RequestMapping("/invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002")
public class FinMdateAdjustment002Controller extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinMdateAdjustment002Service finMdateAdjustment002Service;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	setSelectOptionToModel(model, DictCode.调整类型);
        //新增时调整类型默认为提前终止
        if("0".equals(this.getParam("pageModel"))){
        	model.addAttribute("ADJUSTTYPE_01", DictCodeListExt2.调整类型_提前终止.getValue());
        }
        if (!WorkFlowUtil.isNotFromToDoList()) {
            ProcessMark pro = finMdateAdjustment002Service.getBusinessInfo(
                this.getParam("workflowId"));
            if (pro != null) {
                FinMdateAdjustment001Dto entity = (FinMdateAdjustment001Dto)JsonUtils.json2Object(
                    pro.getBusinessData(), FinMdateAdjustment001Dto.class);
                model.addAttribute(GlobalConstant.ENTITY, entity);
            }
            model.addAttribute( GlobalConstant.WORKFLOW_ID,this.getParam("workflowId"));
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))) {
            String finprodId = this.getParam("finprodId");
            String adjustNum = this.getParam("adjustNum");
            if (!"undefined".equals(adjustNum)&&StringUtil.isNotEmpty(adjustNum)) {
                model.addAttribute(GlobalConstant.ENTITY,
                    finMdateAdjustment002Service.queryAdjustInfoByPrimaryKey(finprodId,
                        adjustNum));
            }
            else {
                model.addAttribute(GlobalConstant.ENTITY,
                    finMdateAdjustment002Service.queryFinProductByPrimaryKey(finprodId));
            }
        }
        model.addAttribute("platDate", platformDate.getSysDateStr());

        return super.init(model);
    }

    /**
     * 产品到期日新建管理产品代码下拉框查询
     * 
     * @return 产品代码下拉框查询
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryAdjustProdIdList")
    public List<SelectOptionDto> queryAdjustProdIdList() {
    	Map<String, Object> param = RequestUtil.getPageParam();
    	//新建页面下拉框设置到期日>平台日的校验，只查询大于平台日的产品
    	if(param.get("isCreate") != null&& StringUtil.equals(param.get("isCreate"), "Y")) {
            param.put("platDate", platformDate.getSysDateStr());
    	}

        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return finMdateAdjustment002Service.queryAdjustProdIdList(param);
    }

    /**
     * 产品到期日新建关联影响列表
     * 
     * @return 关联影响列表查询
     */
    @ResponseBody
    @RequestMapping("queryRelateInfoList")
    public List<FinMdateAdjustment001Dto> queryRelateInfoList() {
    	//modify by wm 流程中WorkFlowUtil.isNotFromToDoList()判断不准确，改为判断流程id是否为空 2019-11-07
//   	 if (!WorkFlowUtil.isNotFromToDoList()) {
   	 if (StringUtil.isNotEmpty(this.getParam("workflowId"))) {
             ProcessMark pro = finMdateAdjustment002Service.getBusinessInfo(
                 this.getParam("workflowId"));
             if (pro != null) {
                 FinMdateAdjustment001Dto entity = (FinMdateAdjustment001Dto)JsonUtils.json2Object(
                     pro.getBusinessData(), FinMdateAdjustment001Dto.class);
                List<FinMdateAdjustment001Dto> list=new ArrayList<FinMdateAdjustment001Dto>();
                 if(entity!=null){
                	 List<FinMdateAdjustment001Dto> list1 = entity.getSelectedItem1();
                	 if(!StringUtil.isEmptyList(list1)&&StringUtil.equals(list1.get(0).getIsFeeLayer(), DictCodeList.是否_是.getValue())){
                		 list = entity.getSelectedItem1();
                	 }
                	 else{
                		 for(PrdMdateAdjust p:entity.getSelectedItem()){
                     		FinMdateAdjustment001Dto dto=new FinMdateAdjustment001Dto();
                     		//返回值赋值不全 yzg 20200115 start
                     		BeanUtil.copyPropertiesIgnoreNull(dto, p);
                     		//dto.setFeeName(p.getFeeName());
                     		//dto.seteAccrDate(p.geteAccrDate());
                     		//返回值赋值不全 yzg 20200115 end
                     		list.add(dto);
                     	}
                	 }
                }
                	return list;
             }
         }
    	 else if (StringUtil.isNotEmpty(this.getParam("finprodId"))){
            return finMdateAdjustment002Service.queryRelateInfoList(this.getParam("finprodId"));
        }
        
        return Collections.emptyList();
    }
    
    /**
     * 产品到期日新建管理产品代码下拉框查询
     * 
     * @return 产品代码下拉框查询
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryAdjustProdIdListDetail")
    public List<SelectOptionDto> queryAdjustProdIdListDetail() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("platDate", platformDate.getSysDateStr());// 新建下拉框筛选产品到期日>平台时间
        return finMdateAdjustment002Service.queryAdjustProdIdList(param);
    }

    /**
     * 根据金融产品代码查询到期日调整信息
     * 
     * @param finprodId 金融产品代码
     * @return 到期日调整信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryAdjustInfoByFinprodId")
    public FinMdateAdjustment001Dto queryAdjustInfoByFinprodId(String finprodId) {
        return finMdateAdjustment002Service.queryAdjustInfoByFinprodId(finprodId);
    }

    /**
     * 保存验证
     * 
     * @param finMdateAdjustment001Dto 金融产品
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Valid @RequestBody FinMdateAdjustment001Dto finMdateAdjustment001Dto) {
    	AjaxResult ar = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        
        //add by yzg 流程中校验 20200427 start 
        WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
        wflValidInputParameterIssue.setPageId(this.getPageId());
        wflValidInputParameterIssue.setWorkflowId(finMdateAdjustment001Dto.getWorkflowId());
        wflValidInputParameterIssue.setBusinessDataPk1(finMdateAdjustment001Dto.getFinprodId());
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
        	errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0077",
                "")));
        	ar.setErrorList(errorList);
        	return ar;
        }
        //add by yzg 流程中校验 20200427 end
        
        //校验必填项
        //产品代码不为空
        if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getFinprodId())) {
            errorList.add(new ValidMessage("finprodId", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinMdateAdjustment002.prodId"))));
        }
        //调整前到期日不为空
        if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdateUnadjust())) {
            errorList.add(new ValidMessage("mdateUnadjust", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinMdateAdjustment002.adjustBeforeMdate"))));
        }
        //调整前清盘日不为空
        if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getLdateUnadjust())) {
            errorList.add(new ValidMessage("ldateUnadjust", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinMdateAdjustment002.adjustBeforeLdate"))));
        }
        //调整后清盘日不为空
        if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getLdate())) {
            errorList.add(new ValidMessage("ldate", this.getMessage("FIN_PROD_E_0001",
                this.getMessage("FinMdateAdjustment002.adjustAfterLdate"))));
        }
        //
        //在html文件处理>300 不允许输入 edit by xueying 20200728 start
        //备注字段大于300
        /*if (StringUtil.isNotEmpty(finMdateAdjustment001Dto.getRemark())) {
            if (finMdateAdjustment001Dto.getRemark().length() > 300) {
                errorList.add(new ValidMessage("remark", this.getMessage("BUSS_COM_E_0044",
                    this.getMessage("FinMdateAdjustment002.remark"))));
            }
        }*/
        //在html文件处理>300 不允许输入 edit by xueying 20200728 start
        //调整类型不为空
        if(StringUtil.isNotEmpty(finMdateAdjustment001Dto.getAdjustType())){
        	if(finMdateAdjustment001Dto.getAdjustType().equals(DictCodeListExt2.调整类型_提前终止.getValue())){
        		if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdate())) {
                    errorList.add(new ValidMessage("mdate", this.getMessage("FIN_PROD_E_0001",
                        this.getMessage("提前终止日"))));
                }
        	}else if(finMdateAdjustment001Dto.getAdjustType().equals(DictCodeListExt2.调整类型_产品展期.getValue())){
        		if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdate())) {
                    errorList.add(new ValidMessage("mdate", this.getMessage("FIN_PROD_E_0001",
                        this.getMessage("展期日"))));
                }
        	}else{
        		if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdate())) {
            		errorList.add(new ValidMessage("mdate", this.getMessage("FIN_PROD_E_0001",
            				this.getMessage("FinMdateAdjustment002.adjustAfterMdate"))));
            	}
        	}
            	if (StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdate())
            			&&StringUtil.isNotEmpty(finMdateAdjustment001Dto.getFinprodId())) {
            	//提前终止日大于调整前到期日
            	if(finMdateAdjustment001Dto.getAdjustType().equals(DictCodeListExt2.调整类型_提前终止.getValue())){
            		if(finMdateAdjustment001Dto.getMdate().compareTo(finMdateAdjustment001Dto.getMdateUnadjust())>=0){
            			errorList.add(new ValidMessage("mdate",
            					this.getMessage("BUSS_COM_E_0035",
            							this.getMessage("FinMdateAdjustment002.beforeEnddate"),
            							this.getMessage("FinMdateAdjustment002.adjustBeforeMdate"))));
            		}
            		if(finMdateAdjustment001Dto.getVdate().compareTo(finMdateAdjustment001Dto.getMdate())>=0){
            			errorList.add(new ValidMessage("mdate",
                                this.getMessage("BUSS_COM_E_0036",
                                    this.getMessage("FinMdateAdjustment002.adjustAfterMdate"),
                                    "起息日")));
            		}
            	}else if(finMdateAdjustment001Dto.getAdjustType().equals(DictCodeListExt2.调整类型_产品展期.getValue())){
            		//产品展望日大于调整前到期日
            		if(finMdateAdjustment001Dto.getMdate().compareTo(finMdateAdjustment001Dto.getMdateUnadjust())<=0){
            			errorList.add(new ValidMessage("mdate",
            					this.getMessage("BUSS_COM_E_0036",
            							this.getMessage("FinMdateAdjustment001.table.RolloverDate"),
            							this.getMessage("FinMdateAdjustment002.adjustBeforeMdate"))));
            		}
            	}
            }
        }else{
        	errorList.add(new ValidMessage("adjustType",
                    this.getMessage("FIN_PROD_E_0090",
                        this.getMessage("FinMdateAdjustment002.adjustType"))));
        }
        if(!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getAdjustType())){
        	//调整后到期日不为空
        	if (!StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdate())) {
        		errorList.add(new ValidMessage("mdate", this.getMessage("FIN_PROD_E_0001",
        				this.getMessage("FinMdateAdjustment002.adjustAfterMdate"))));
        	}
        }
        	if (StringUtil.isNotEmpty(finMdateAdjustment001Dto.getMdate())) {
        	//调整到期日必须大于平台日
        		if(StringUtil.isNotEmpty(finMdateAdjustment001Dto.getAdjustType())){
        			if(finMdateAdjustment001Dto.getAdjustType().equals(DictCodeListExt2.调整类型_提前终止.getValue())){
        				if (finMdateAdjustment001Dto.getMdate().compareTo(platformDate.getSysDate()) <= 0) {
        	                errorList.add(new ValidMessage("mdate",
        	                    this.getMessage("BUSS_COM_E_0036",
        	                        this.getMessage("FinMdateAdjustment002.beforeEnddate"),
        	                        this.getMessage("SysCommon.platDate"))));
        	            }
                	}
        			else if(finMdateAdjustment001Dto.getAdjustType().equals(DictCodeListExt2.调整类型_产品展期.getValue())){
        				if (finMdateAdjustment001Dto.getMdate().compareTo(platformDate.getSysDate()) <= 0) {
        	                errorList.add(new ValidMessage("mdate",
        	                    this.getMessage("BUSS_COM_E_0036",
        	                        this.getMessage("FinMdateAdjustment001.table.RolloverDate"),
        	                        this.getMessage("SysCommon.platDate"))));
        	            }
        			}else{
        				if (finMdateAdjustment001Dto.getMdate().compareTo(platformDate.getSysDate()) <= 0) {
        	                errorList.add(new ValidMessage("mdate",
        	                    this.getMessage("BUSS_COM_E_0036",
        	                        this.getMessage("FinMdateAdjustment002.adjustAfterMdate"),
        	                        this.getMessage("SysCommon.platDate"))));
        	            }
        			}
        		}

            if (StringUtil.isNotEmpty(finMdateAdjustment001Dto.getFinprodId())) {
            	//add by zlp 20190318 start 不需要校验组合到期日，组合到期日应该同步更新
//                String portMdate = finMdateAdjustment002Service.queryPortFolioMdateByFinprodId(
//                    finMdateAdjustment001Dto.getFinprodId());
//                //调整后到期日不能大于组合到期日
//                if (portMdate != null && DateUtil.compareDate(finMdateAdjustment001Dto.getMdate(),
//                    DateUtil.parse(portMdate)) > 0) {
//                    errorList.add(new ValidMessage("mdate",
//                        this.getMessage("BUSS_COM_E_0003",
//                            this.getMessage("FinMdateAdjustment002.adjustAfterMdate"),
//                            this.getMessage("FinMdateAdjustment002.portFolioMdate"))));
//                }
                //调整后到期日（含当天）后不能有交易！
                if (finMdateAdjustment002Service.queryDealCountByFinprodId(
                    finMdateAdjustment001Dto.getFinprodId(),
                    DateUtil.format(finMdateAdjustment001Dto.getMdate())) > 0) {
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0052")));
                }
            }
        	}
        
        // add by lizhi 20200713 费用计息结束日必须大于平台日 start
        if(null != finMdateAdjustment001Dto.getSelectedItem1() && finMdateAdjustment001Dto.getSelectedItem1().size() > 0){
        	for(FinMdateAdjustment001Dto adjdto : finMdateAdjustment001Dto.getSelectedItem1()){
        		if(adjdto.geteAccrDate().compareTo(DateUtil.parse(DateUtil.getSysDate())) < 0){
        			errorList.add(new ValidMessage(adjdto.getFeeName() + "计提结束日不得小于平台日!"));
        		}
        	}
        };
        // add by lizhi 20200713 费用计息结束日必须大于平台日 end
        
        	
        if(commonBusinessService.isBackCheckNode(finMdateAdjustment001Dto.getFinprodMarketId())) {
        	errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0042")));
        };

        // add by  wj  20200819   -----  BS-1656
        //【到期日调整】若为“普通 ”“普通分档”封闭式产品，则为产品到期日或者到期日-1自然日
        if (StringUtil.equals(finMdateAdjustment001Dto.getDealMode(),DictCodeList.处理模式_普通.getValue() ) || StringUtil.equals(finMdateAdjustment001Dto.getDealMode(),DictCodeList.处理模式_分档.getValue() )){
            List<PrdMdateAdjust> prdMdateAdjustList = finMdateAdjustment001Dto.getSelectedItem();

            Date accrDate = null;
            if(!StringUtil.isEmptyList(prdMdateAdjustList)){
                for (PrdMdateAdjust temp:prdMdateAdjustList) {
                    if(StringUtil.equals(temp.getFeeType(),"12") ){
                        accrDate = temp.geteAccrDate();
                        break;
                    }
                }
            }

            // 存在浮动管理费的情况下
            if(accrDate!=null){

                if(StringUtil.isNotEmpty(finMdateAdjustment001Dto.getAdjustType())){
                    Date mdate  = finMdateAdjustment001Dto.getMdate();

                    // 浮动管理费 计提结束日 只能是 mdate  或者 mdate -1
                    if(mdate != null){
                        if (DateUtil.getBetweenDate(DateUtil.format(accrDate),DateUtil.format(mdate), DateUtil.DurationType.天) >1 ||DateUtil.getBetweenDate(DateUtil.format(accrDate),DateUtil.format(mdate), DateUtil.DurationType.天) <0 ){
                            errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0300")));
                        }
                    }
                }
            }
        }

        
        if (StringUtil.isEmptyList(errorList)) {
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SAVE,
                finMdateAdjustment001Dto.getOperateType())) {
                btnName = "SysCommon.save";
            }
            else if (StringUtil.equalsIgnoreCase(OPERATE_TYPE_SUBMIT,
                finMdateAdjustment001Dto.getOperateType())) {
                btnName = "SysCommon.submit";
            }
            ar.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        }
        else {
            ar.setErrorList(errorList);
        }

        return ar;
    }

    public static void main(String[] args) {

        String vdate = "2018-09-06";
        String mdate = "2018-11-11";
        long date = DateUtil.getBetweenDate(vdate, mdate, DateUtil.DurationType.天);
        System.out.println("sdfsdfsdf:"+date);

    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinMdateAdjustment001Dto dto)
        throws Exception {
        ProcessParameter<FinMdateAdjustment001Dto> processParameter = new ProcessParameter<FinMdateAdjustment001Dto>();
        WorkFlowParameter<FinMdateAdjustment001Dto> params = new WorkFlowParameter<FinMdateAdjustment001Dto>();
        params.setElementId(dto.getElementId());//元素id
        params.setPageId(this.getPageId());//画面id
        params.setBusinessCode(dto.getFinprodMarketId());//业务编码
        params.setBusinessType(DictCodeList.共通业务类型_到期日调整.getValue());//业务类型
        // 设置工作流注解
        params.setComment(entity -> this.getMessage("FinMdateAdjustment002.prodId") + "("
                                    + entity.getFinprodMarketId() + ")"
                                    + this.getMessage("FinMdateAdjustment002.prodAbbr") + "("
                                    + entity.getFinprodAbbr() + ")"
                                    + this.getMessage("FinMdateAdjustment002.adjustBeforeMdate")
                                    + "(" + DateUtil.format(entity.getMdateUnadjust()) + ")"
                                    + this.getMessage("FinMdateAdjustment002.adjustAfterMdate")
                                    + "(" + DateUtil.format(entity.getMdate()) + ")");
        processParameter.setWorkFlowParameter(params);
        // 调用工作流事件
        String msg = finMdateAdjustment002Service.start(processParameter, dto);
        // 返回工作流错误信息
        return new AjaxResult(msg);
    }
    
    /**
     * 获取下一个工作日
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getNextWorkDay")
    public String getNextWorkDay(){
        return finMdateAdjustment002Service.getNextWorkDay(this.getParam("mdate"), this.getParam("calendarId"));
    }
}
