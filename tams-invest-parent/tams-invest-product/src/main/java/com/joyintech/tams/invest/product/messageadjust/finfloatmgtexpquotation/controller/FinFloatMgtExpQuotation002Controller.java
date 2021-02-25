/*
 * 文件名：FinFloatMgtExpQuotation002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
//import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finfloatmgtexpquotation.service.FinFloatMgtExpQuotation001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 浮动管理费报价管理-查询controller
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月19日
 * @see FinFloatMgtExpQuotation002Controller
 * @since 1.0
 */
@Controller
@PageId("FinFloatMgtExpQuotation002")
@RequestMapping("/invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002")
public class FinFloatMgtExpQuotation002Controller extends BaseController {
    /**
     * 浮动管理报价service
     */
    @Autowired
    private FinFloatMgtExpQuotation001Service finFloatMgtExpQuotation001Service;
    /**
     * 工作流共通service
     */
//    @Autowired(required =false)
//    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 调用组件生成业务编号
     */
    @Autowired(required =false)
    private BaseGetBusinessId baseGetBusinessId;
    
    /**
     * 判断交易日历共通
     */
    @Autowired(required= false )
    private DateCalService dateCalService;
    
    @Override
    public String init(Model model) {
		// TODO Auto-generated method stub
    	model.addAttribute("FLOAT_CALC_RULE_01", DictCodeListExt1.业绩报酬计算规则_业绩基准.getValue());
        String cashId=getParam("cashId");
        String finprodId=getParam("finprodId");
        String effDate=getParam("effDateStr");
        String lev=getParam("lev");
        //工作流代码
        String workflowId = getParam("workflowId");
        //工作流待办跳转
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.getBussinessEntityByWorkflowId(workflowId);
        	//add by menghongwei 支持分档产品 20190626 start
        	if(StringUtil.isNotEmpty(dto.getpFinprodId())) {
        		if(StringUtil.equals(DictCodeList.处理模式_分档.getValue(), dto.getDealMode())){
            		dto.setProdStyle(dto.getFinprodId());
        		}
        		else if(StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), dto.getDealMode())){
            		dto.setInvestmentCycle(dto.getFinprodId());
        		}
        		dto.setFinprodId(dto.getpFinprodId());
        	}
        	//add by menghongwei 支持分档产品 20190626 end
            model.addAttribute("entity", dto);
            model.addAttribute("entity_benchMark", dto.getBussBenchMarkInfoDto());
            if(StringUtil.isNotEmpty(dto.getEffDateOld())){
                model.addAttribute("effDateOld", dto.getEffDateOld());
            }
            else{
                model.addAttribute("effDateOld", dto.getEffDate());
            }
            model.addAttribute("workflowId", workflowId);
        }//查询画面双击查看或修改
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)) {
        	FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.selectDetail(finprodId, effDate,cashId);
        	if(StringUtil.isNotEmpty(dto)){
        		//添加业绩基准信息
        		BussBenchMarkInfoDto bussBenchMarkInfoDto = finFloatMgtExpQuotation001Service.selectBussBenchMarkInfo(finprodId, effDate);
                model.addAttribute("entity_benchMark", bussBenchMarkInfoDto);
        		if(StringUtil.isNotEmpty(lev)){
        			dto.setLev(Long.parseLong(lev));
        		}
        		//add by menghongwei 支持分档产品 20190626 start
        		if(StringUtil.isNotEmpty(dto.getpFinprodId())) {
        			if(StringUtil.equals(DictCodeList.处理模式_分档.getValue(), dto.getDealMode())){
                		dto.setProdStyle(dto.getFinprodId());
            		}
            		else if(StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), dto.getDealMode())){
                		dto.setInvestmentCycle(dto.getFinprodId());
            		}
            		dto.setFinprodId(dto.getpFinprodId());
            	}
        		//add by menghongwei 支持分档产品 20190626 end
        		model.addAttribute("entity", dto);
        		if(StringUtil.isNotEmpty(dto) && StringUtil.isNotEmpty(dto.getEffDateOld())){
        			model.addAttribute("effDateOld", dto.getEffDateOld());
        		}
        		else if(StringUtil.isNotEmpty(dto)){
        			model.addAttribute("effDateOld", dto.getEffDate());
        		}
        	}
        }//新建按钮
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(finprodId)) {
            
        }
		model.addAttribute("entityName", "bussBenchMarkInfo");
        //add by wm 2019-09-25 浮动管理调整（增加周期型修改） start
        model.addAttribute("DEAL_MODE_10", DictCodeList.处理模式_周期型.getValue());
        model.addAttribute("DEAL_MODE_11", DictCodeList.处理模式_分档.getValue());
        model.addAttribute("INT_TYPE_01", DictCodeList.利率类型_固定利率.getValue());
        //add by wm 2019-09-25 浮动管理调整（增加周期型修改） end
     // 一体化对应etliu start
//        setSelectOptionToModel(model, DictCode.计息基础,DictCode.计费基数_费用品种,DictCode.资产净值日类型,DictCode.业绩基准规则);
        //setSelectOptionToModel(model, DictCode.计息基础,DictCode.计费基数_费用品种,DictCode.基数日期类型,DictCode.业绩报酬计算规则);
     // 一体化对应etliu end
      //付费规则--不规则
        model.addAttribute("FREQUENCY_UNIT_08", DictCodeList.频率_不规则.getValue());
        setSelectOptionToModel(model, 
        DictCode.计息基础,
        DictCode.计费基数_费用品种,
        DictCode.基数日期类型,
        DictCode.业绩报酬计算规则,
        DictCode.频率,
        DictCode.营业日准则,
        DictCode.计提类型,
        DictCode.重置规则,DictCode.利率类型);
     // 浮动利率下拉框（含利率行情、复合基准四个部分）
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        // 利率行情
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        for (SelectOptionDto dto : selectDto.getOptions()) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_利率);
            listFloatRate.add(dto1);
        }
        // 复合基准
        List<SelectOptionDto> finBaseRuleInfoList = finFloatMgtExpQuotation001Service.getFinBaseRuleInfo();
        for (SelectOptionDto dto : finBaseRuleInfoList) {
            SelectOptionDto dto1 = new SelectOptionDto();
            dto1.setId(dto.getId());
            dto1.setText(dto.getText());
            dto1.setObject(DictCodeListExt1.基准类型_2_复合);
            listFloatRate.add(dto1);
        }
        model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));
        return super.init(model);
    }
    /**
     * 产品代码联想框
     * <功能详细描述>
     * @param search String
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodid(){
        return finFloatMgtExpQuotation001Service.getFinprodIdsByDate(RequestUtil.getPageParam());
    }
    /**
     * 联动费用名称等
     * <功能详细描述>
     * @return FinFloatMgtExpQuotation001Dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFeeinfos")
    public FinFloatMgtExpQuotation001Dto getFeeinfos(){
        String finprodId = RequestUtil.getParam("finprodId");
        String workflowId = RequestUtil.getParam("workflowId");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
        	return finFloatMgtExpQuotation001Service.getBussinessEntityByWorkflowId(workflowId);
        } else {
        	return finFloatMgtExpQuotation001Service.getFeeinfos(finprodId);
        }
       
    }
    /**
     * 费用名称联想框
     * <功能详细描述>
     * @param search String
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFeeInfo")
    public List<SelectOptionDto> getFeeInfo(String search){
        return finFloatMgtExpQuotation001Service.getFeeInfo(search);
    } 
    
	/**
	 * 市场代码和生效日联动利差等
	 * <功能详细描述>
	 * @return FinFloatMgtExpQuotation001Dto
	 * @see
	 * @since 1.0
	 */
    @ResponseBody
    @RequestMapping("getRuleDetail")
    public List<FinFloatMgtExpQuotation001Dto> getRuleDetail(){
        String finprodId =  this.getParam("finprodId");
        String effDate =  this.getParam("effDate");
        String lev =  this.getParam("lev");
        String workflowId =  this.getParam("workflowId");
        String pageModel = this.getParam("pageModel");
        List<FinFloatMgtExpQuotation001Dto> list=new ArrayList<FinFloatMgtExpQuotation001Dto>();
//        List<FinFloatMgtExpQuotation001Dto> listTemp=new ArrayList<FinFloatMgtExpQuotation001Dto>();
//        if(StringUtil.isEmpty(workflowId) && "false".equals( this.getParam("flag"))){
//        	list = finFloatMgtExpQuotation001Service.getRuleDetail(finprodId,effDate, lev,pageModel);
//        }
//        else 
        if (StringUtil.isNotEmpty(workflowId)) {
//            FinFloatMgtExpQuotation001Dto dto = finFloatMgtExpQuotation001Service.ruleDetailQueryByWflId(workflowId, RequestUtil.getParam("entityName"));
            FinFloatMgtExpQuotation001Dto dto=finFloatMgtExpQuotation001Service.getBussinessEntityByWorkflowId(workflowId);
            if(dto!=null){
                //删除一条基准利率时table中的显示
//                if(StringUtil.isNotEmpty(dto.getLev())){
//                    listTemp=finFloatMgtExpQuotation001Service.getRuleDetail(finprodId,effDate, lev);
//                    for(int i=0; i<listTemp.size(); i++){
//                        if(listTemp.get(i).getLev().equals(dto.getLev())){
//                            list.add(listTemp.get(i));
//                        }
//                    }
//                    //list=dto.getSelectedItem1();
//                }
//                else{
//                    list=dto.getSelectedItem1();
//                }
                    list=dto.getSelectedItem1();
                }
            }
        else if(StringUtil.isNotEmpty(finprodId) && StringUtil.isEmpty(workflowId)){
            list=finFloatMgtExpQuotation001Service.getRuleDetail(finprodId,effDate, lev,pageModel);
//            //通过产品代码和生效日得到基准利率
//            FinFloatMgtExpQuotation001Dto finFloatDto=finFloatMgtExpQuotation001Service.getIntRateById(finprodId, effDate);
////            if( !list.isEmpty() && !StringUtil.isEmpty(finFloatDto.getBaseRuleId())){
////                FinFloatMgtExpQuotation001Dto dto=new FinFloatMgtExpQuotation001Dto();
////                dto.setIntRate(finFloatDto.getIntRate());
////                dto.setBaseRuleId(finFloatDto.getBaseRuleId());
////                list.add(dto);
////            }else{
////                for(FinFloatMgtExpQuotation001Dto entity:list){
////                    entity.setIntRate(finFloatDto.getIntRate());
////                    entity.setBaseRuleId(finFloatDto.getBaseRuleId());
////                }
////            }
//            if(!list.isEmpty() && StringUtil.isNotEmpty(finFloatDto.getBaseRuleId())){
//                for(FinFloatMgtExpQuotation001Dto entity:list){
//                  entity.setBaseRuleId(finFloatDto.getBaseRuleId());
//              }
//            }
        }
        return list;
    }
   
    /**
     * 删除，修改时的check
     * <功能详细描述>
     * @param dto FinFloatMgtExpQuotation001Dto
     * @param result BindingResult
     * @return AjaxResult 
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinFloatMgtExpQuotation001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 保存提交
     * <功能详细描述>
     * @param dto FinFloatMgtExpQuotation001Dto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinFloatMgtExpQuotation001Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        BussinessCodeParameter parameter=new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_浮动管理费报价调整);
        String code=baseGetBusinessId.buildBussinessCode(parameter);
        ProcessParameter<FinFloatMgtExpQuotation001Dto> processParameter = new ProcessParameter<FinFloatMgtExpQuotation001Dto>();
        WorkFlowParameter<FinFloatMgtExpQuotation001Dto> params = new WorkFlowParameter<FinFloatMgtExpQuotation001Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(code);
        params.setBusinessType(DictCodeList.共通业务类型_浮动管理费报价调整.getValue());
        //业务说明："产品代码" + "(" + 画面.产品代码 + ")" + "-" + "浮动管理费报价生效日期" + "(" + 画面.生效日 + ")"
        params.setComment(entity -> this.getMessage("FinFloatMgtExpQuotation001.finprodId") + "("
            + entity.getFinprodMarketId() +")" +this.getMessage("FinFloatMgtExpQuotation001.effDate") + "("
            + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finFloatMgtExpQuotation001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * check是否是交易历节假日
     * <功能详细描述>
     * @param dto FinFloatMgtExpQuotation001Dto
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/checkIsHoliday")
    public boolean checkIsHoliday(@RequestBody FinFloatMgtExpQuotation001Dto dto){
    	String finprodId = StringUtil.isNotEmpty(dto.getpFinprodId())?dto.getpFinprodId():dto.getFinprodId();//支持分层
        FinProduct finProduct = finFloatMgtExpQuotation001Service.getFinProduct(finprodId);
        boolean flag = dateCalService.isDealCalendarHoliday(dto.getfAccrDate(),finProduct.getCalendarId());
        String msg = null;
        if(!flag){
            msg = getMessage("BUSS_COM_E_0039",getMessage("FinFloatMgtExpQuotation002.fAccrDate")+dto.getfAccrDate());    
        }
        return flag;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return "FinFloatMgtExpQuotation002";
    }
    /**
     * 获取分层产品下拉菜单
     * @param finprodId
     * @return
     */
    @ResponseBody
    @RequestMapping("queryLayerProdInfoByFinprodId")
    public List<SelectOptionDto> queryLayerProdInfoByFinprodId(String finprodId) {
        List<SelectOptionDto> result = finFloatMgtExpQuotation001Service.queryLayerProdInfoByFinprodId(finprodId);
        Collections.sort(result, (o1, o2) -> {
            if (o1.getText() != null) {
                return o1.getText().compareTo(o2.getText());
            }
            return 0;
        });
        return result;
    }
    /**
     * 新建选中产品代码联动业绩基准信息
     * @return 业绩基准
     */
    @ResponseBody
    @RequestMapping("getMarkInfo")
    public BussBenchMarkInfoDto getMarkInfo(){
        String finprodId=RequestUtil.getParam("finprodId");
        String effDate=RequestUtil.getParam("effDate");
        //添加业绩基准信息
        BussBenchMarkInfoDto bussBenchMarkInfoDto = finFloatMgtExpQuotation001Service.selectBussBenchMarkInfo(finprodId, effDate);
        return bussBenchMarkInfoDto;
    }
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
    @ResponseBody
    @RequestMapping("/checkeffDate")
    public boolean checkeffDate(@RequestBody FinFloatMgtExpQuotation001Dto dto){
    	String dateStr = DateUtil.format(dto.getEffDate());
        boolean flag = finFloatMgtExpQuotation001Service.checkeffDate(dateStr, dto.getFinprodId());
        return flag;
    }
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
    
    /**
     * 获取开放模式
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getopenMode")
    public String getopenMode(){
        return finFloatMgtExpQuotation001Service.getopenMode(this.getParam("finprodId"));
    }
    
    /**
     * 获取发行方式
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getopenType")
    public String getopenType(){
        return finFloatMgtExpQuotation001Service.getopenType(this.getParam("finprodId"));
    }
}
