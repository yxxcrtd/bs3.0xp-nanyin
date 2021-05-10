/*
 * 文件名：FinPlanAssetC002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.plan.plancur.controller;

import java.util.ArrayList;
import java.util.Map;

import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.basicdata.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.plan.plancur.dto.FinPlanAssetCDto;
import com.joyintech.tams.valuation.basicdata.asset.plan.plancur.service.FinPlanAssetCService;
import com.joyintech.tams.valuation.basicdata.business.dto.BussinessCodeParameter;
import com.joyintech.tams.valuation.basicdata.business.service.BaseGetBussinessIdService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 计划类资产（货币型）标的信息-新建
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月7日
 * @see FinPlanAssetC002Controller
 * @since 1.0
 */
@Controller
@PageId("FinPlanAssetC002")
@RequestMapping("/valuation/basicdata/asset/plan/plancur/FinPlanAssetC002")
public class FinPlanAssetC002Controller extends BaseController{
    @Autowired
    private FinPlanAssetCService finPlanAssetCService;
    /**
     * 各种业务共通组件
     */
    @Autowired(required=false)
    private CommonBusinessService commonBusinessService;

    @Autowired
    private ValAssetCommonService assetCommonService;
    /**
     * 平台日期组件接口
     */
//    @Autowired(required=false)
//    private IPlatformDate iPlatformDate;
    /**
     * 业务编号共通组件
     */
    @Autowired(required=false)
    private BaseGetBussinessIdService baseGetBussinessId;
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        /*添加行内资产五级分类的数据字典值 lishuping 2019/7/23 start*/
        this.setSelectOptionToModel(model,DictCode.计划类资产类型,
            DictCode.申购确认速度,DictCode.清算速度,DictCode.币种, DictCode.赎回确认速度,
            DictCode.本金收益特征,DictCode.是否,DictCode.投资性质,DictCode.资金实际投向, DictCode.购买结构,DictCode.交易日历,
            DictCode.境内外,DictCode.收益类型,DictCode.计划类资产核算分类,DictCode.增值税资产分类
        );
        /*添加行内资产五级分类的数据字典值 lishuping 2019/7/23 end*/
        model.addAttribute("FUND_ASSET_TYPE_F01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("PLAN_ASSET_TYPE_N02", DictCodeList.计划类资产一级分类_信托产品.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        String finprodId = getParam("finprodId");
//        add by zhuyongkang 20191012 start
        String finprodMarketId=getParam("finprodMarketId");
        if(StringUtil.isNotEmpty(finprodMarketId)&&StringUtil.isEmpty(finprodId)){
            finprodId=finPlanAssetCService.getFinprodId(finprodMarketId);
        }
        //        add by zhuyongkang 20191012 end
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
         // 根据工作流与留痕处理提供的共通方法获取业务实体
            FinPlanAssetCDto dto = finPlanAssetCService.getBussinessEntityByWorkflowId(
                    workflowId);
            // 设置到model中
            model.addAttribute(GlobalConstant.WORKFLOW_ID,workflowId);
            model.addAttribute("entity", dto);
        }
        else if(WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(finprodId)){
            FinPlanAssetCDto finPlanAssetCDto = finPlanAssetCService.getBasicInfoByFinprodId(finprodId);
            if(null!=finPlanAssetCDto){
                //点击修改保存提交会校验实际授信主体必填问题
                String a = CacheUtil.getSysParam(ValComponentConstant.CEMODEL);
                if(StringUtil.equals("01", a)){
                	finPlanAssetCDto.setCreditFlag("");
                }
                finPlanAssetCDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                finPlanAssetCDto.setFjId(finPlanAssetCDto.getFinprodId());
            }
            model.addAttribute("entity",finPlanAssetCDto);
        }
        else if(WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(finprodId)){
          //新建界面初始化
            FinPlanAssetCDto dto = new FinPlanAssetCDto();
            //默认平台日期
            dto.setVdate(DateUtil.parse(DateUtil.getSysDate()) != null ? DateUtil.parse(DateUtil.getSysDate()) : DateUtil.now());
//            dto.setMdate(iPlatformDate!=null?iPlatformDate.getSysDate():DateUtil.now());
            dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            dto.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
            dto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
            dto.setFjId("fj" + System.currentTimeMillis());
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("entity", dto);
        }
        //取系统参数
        model.addAttribute("CE_MODEL", CacheUtil.getSysParam(ValComponentConstant.CEMODEL));
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }
    
    /**
     * 发行人的联想下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getIssuerId")
    public Page<SelectOptionDto> getIssuerId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        //sql中参数不是search，页面控件传回是search，做转换 yjm 20190731
        map.put("param", map.get("search"));
        Page<SelectOptionDto> temp = finPlanAssetCService.getIssuerId(map);
        return temp;
    }
    
    /**
     * 管理人的联想下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getManagerId")
    public Page<SelectOptionDto> getManagerId(){
        Map<String,Object>map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = finPlanAssetCService.getManagerId(map);
        return temp;
    }
    /**
     * 托管人的联想下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getTrusteeId")
    public Page<SelectOptionDto> getTrusteeId(){
        Map<String,Object>map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = finPlanAssetCService.getTrusteeId(map);
        return temp;
    }
    /**
     * 标的到期日是否为节假日
     * @param finProduct
     * @return
     */
    @ResponseBody
    @RequestMapping("/isHoliday")
    public Boolean isHoliday(@RequestBody FinProduct finProduct){
        if(null==finProduct.getMdate()||StringUtil.isEmpty(finProduct.getCalendarId())){
            return false;
        }
        return finPlanAssetCService.isDealCalendarHoliday(finProduct.getMdate(), finProduct.getCalendarId());
    }
    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinPlanAssetCDto dto, BindingResult result) {
        AjaxResult resulterr = new AjaxResult();
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        // 判断是否存在资产代码一致的数据
        if (finPlanAssetCService.selectFinprodMarketId(dto.getFinprodMarketId()) > 0 && !StringUtil.equals("1",dto.getPageModel())){
            errorList.add(new ValidMessage("已经存在资产代码为："+dto.getFinprodMarketId()+"的数据！"));
            resulterr.setErrorList(errorList);
            return resulterr;
        }
        //	起息日必须小于终止日
        if (dto.getMdate()!=null&&dto.getVdate()!=null&&dto.getMdate().compareTo(dto.getVdate()) <= 0){
            errorList.add(new ValidMessage("成立日必须小于终止日"));
            resulterr.setErrorList(errorList);
            return resulterr;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存操作
     *
     * @param dto dto
     * @param br BindingResult
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinPlanAssetCDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            if (StringUtil.equals(dto.getProfitType(),DictCodeList.收益类型_净值型.getValue())){
                parameter.setCommonBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_信息);
            }
            if (StringUtil.equals(dto.getProfitType(),DictCodeList.收益类型_货币型.getValue())){
                parameter.setCommonBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_信息);
            }
            // 修改业务编号生成方式 如果页面传入市场代码则不需要生成
            if(!StringUtil.isNotEmpty(dto.getFinprodMarketId())){
                String code = baseGetBussinessId.buildBussinessCode(parameter);
                dto.setFinprodMarketId(code);
            }
        }
        if(!StringUtil.isNotEmpty(dto.getFinprodId())){
//            dto.setFinprodId(commonBusinessService.getFinProdId(dto.getFinprodMarketId(),
//                    DictCodeListExt1.金融产品类型_计划类资产.getValue(), null));
            dto.setFinprodId(DictCodeListExt1.金融产品类型_计划类资产.getValue()+"_"+dto.getFinprodMarketId());
        }
        ProcessParameter<FinPlanAssetCDto> processParameter = new ProcessParameter<FinPlanAssetCDto>();
        WorkFlowParameter<FinPlanAssetCDto> params = new WorkFlowParameter<FinPlanAssetCDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        if (StringUtil.equals(dto.getProfitType(),DictCodeList.收益类型_净值型.getValue())){
            params.setBusinessType(DictCodeList.共通业务类型_计划类资产_净值型_信息.getValue());
        }
        if (StringUtil.equals(dto.getProfitType(),DictCodeList.收益类型_货币型.getValue())){
            params.setBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_信息.getValue());
        }
        // 设置流程业务说明, entity即传入的dto
        // 业务说明：资产代码 + "(" + 资产代码 + ")" + 资产名称 + "(" + 资产名称 + ")"
        params.setComment(entity -> getMessage("FinPlanAssetC001.finProdMarketId") + "(" + entity.getFinprodMarketId() + ")"+"-"
                +getMessage("FinPlanAssetC001.finProdName") + "(" + entity.getFinprodName() + ")");
        processParameter.setWorkFlowParameter(params);
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        String msg = finPlanAssetCService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 获取核算分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFinanceProductClass")
    public FinPlanAssetCDto getFinanceProductClass(@RequestBody FinPlanAssetCDto dto){
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //资产类型
        fdto.setAssetType(dto.getAssetType());
        // 收益类型
        fdto.setProfitType(dto.getProfitType());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        dto.setAccountintType(assetCommonService.getFinanceProductClass(fdto));
        return dto;
    }

    /**
     * 获取税务分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFinanceProductTaxClass")
    public FinPlanAssetCDto getFinanceProductTaxClass(@RequestBody FinPlanAssetCDto dto){
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //资产类型
        fdto.setAssetType(dto.getAssetType());
        // 收益类型
        fdto.setProfitType(dto.getProfitType());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        dto.setTaxType(assetCommonService.getFinanceProductTaxClass(fdto));
        return dto;
    }
}
