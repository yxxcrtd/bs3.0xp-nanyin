/*
 * 文件名：PerformanceBenchmark001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
/**
 * 
 */
package com.joyintech.tams.invest.basicdata.mstperformancebenchmark.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.constant.BussinessDictCode;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service.ComponentInfoService;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service.MstPerformanceBenchMarkService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 复合基准管理的新建 显示业绩基准信息的新建页面
 * 
 * @author zhangjun
 * @version 1.0 2018年8月23日
 * @see MstPerformanceBenchMark002Controller
 * @since 1.0
 */
@Controller
@PageId("MstPerformanceBenchMark002")
@RequestMapping("/invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002")
public class MstPerformanceBenchMark002Controller extends BaseController {

    /**
     * 复合基准管理service注入
     */
    @Autowired
    private MstPerformanceBenchMarkService mstPerformanceBenchMarkService;

    /**
     * 基准成分service注入
     */
    @Autowired
    private ComponentInfoService componentInfoService;

    /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

    /**
     * 新建页面初始化
     */

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        setSelectOptionToModel(model,
                new BussinessDictCode[] { BussinessDictCode.基准类型,BussinessDictCode.行情类型});
        
        List<SelectOptionDto> listFloatRate = makeAllFloatRate();
        model.addAttribute("INDEX_ID_SOURCE", JsonUtils.objectArray2Json(listFloatRate.toArray()));

        String indexId = getParam("indexId");
        String workflowId = getParam("workflowId");
        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(indexId)) {
            // 根据主键获取业务实体
            MstPerformanceBenchMarkDto entity = mstPerformanceBenchMarkService.selectDetail(
                indexId);
            // 设置到model中(这种塞进去entity，indexId需要在html中写entity？indexId这种形式)
            model.addAttribute("entity", entity);
        }
        // 来自代办
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            MstPerformanceBenchMarkDto entity = mstPerformanceBenchMarkService.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }
    
    /**
     * @return List<SelectOptionDto>
     */
    private List<SelectOptionDto> makeAllFloatRate() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();

        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.利率行情, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            dto.setId(dto.getCol() + GlobalConstant.POINT + dto.getId());
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.指数行情, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            dto.setId(dto.getCol() + GlobalConstant.POINT + dto.getId());
            listFloatRate.add(dto);
        }
        SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.收益率曲线, null);
        for (SelectOptionDto dto : selectDto3.getOptions()) {
            dto.setId(dto.getCol() + GlobalConstant.POINT + dto.getId());
            listFloatRate.add(dto);
        }
        return listFloatRate;
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
        ValidateGroupEdit.class}) MstPerformanceBenchMarkDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存按钮点击
     *
     * @param dto dto
     * @param result BindingResult
     * @throws Exception Exception
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) MstPerformanceBenchMarkDto dto, BindingResult result)
        throws Exception {
        if (result.hasErrors()) {
            return new AjaxResult();
        }
        // 如果基准编号为空的情况下，需要自动生成一个基准编号，也就是新建，修改时是有这个基准编号的，唯一
        if (StringUtil.isEmpty(dto.getIndexId())) {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_复合基准);
            String code = bussGetBussinessIdService != null ? bussGetBussinessIdService.buildBussinessCode(
                param) : IDGenerator.generate();
            dto.setIndexId(code);
        }
        ProcessParameter<MstPerformanceBenchMarkDto> processParameter = new ProcessParameter<MstPerformanceBenchMarkDto>();
        WorkFlowParameter<MstPerformanceBenchMarkDto> params = new WorkFlowParameter<MstPerformanceBenchMarkDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());

        params.setBusinessCode(item -> item.getIndexId());
        params.setBusinessType(DictCodeList.共通业务类型_复合基准.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
            entity -> SpringContextUtil.getTextValue("MstPerformanceBenchMark002.indexName") + "("
                      + entity.getIndexName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstPerformanceBenchMarkService.start(processParameter, dto);
        return new AjaxResult(msg);

    }

    /**
     * 编辑修改页面表格查询 <功能详细描述>
     * 
     * @return Page<PaRskCustomerRatingDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<ComponentInfoDto> selectAll()
        throws Exception {
        List<ComponentInfoDto> page = componentInfoService.selectComponentInfoPage(
            RequestUtil.getPageParam());

        String preId = DictCode.行情类型.getValue() + GlobalConstant.POINT;
        List<SelectOptionDto> listFloatRate = makeAllFloatRate();
        page.forEach(item -> {
            listFloatRate.stream().filter(
                option -> StringUtil.equals(option.getParentId(), preId + item.getComponentType())
                          && StringUtil.equals(option.getId(),
                              option.getCol() + GlobalConstant.POINT + item.getComponentId())).forEach(
                                  option -> {
                                      item.setComponentId(option.getId());
                                  });
        });
        List<ComponentInfoDto> com =page;
        BigDecimal hunderd = new BigDecimal(100);
        for(ComponentInfoDto copy:com){
            copy.setWeight(copy.getWeight().multiply(hunderd));
        }
        if (page==null||page.size()<1){
            page=new ArrayList<>();
        }
        return page;
    }
    @ResponseBody
    @RequestMapping("editCheck")
    public List editCheck(@RequestBody MstPerformanceBenchMarkDto dto) {
        List li =new ArrayList();
        List<MstPerformanceBenchMarkDto> listDto = dto.getSelectedItem();
        dto.setIndexId(dto.getSelectedItem().get(0).getIndexId());
        String flag="N";
        if (listDto.size() > 1) {
            if (mstPerformanceBenchMarkService.checkAllCodeIsUse(listDto) > 0) {
                flag= "Y";
            }

        }else if (mstPerformanceBenchMarkService.checkCodeIsUse(dto) > 0) {
            flag= "Y";
        }
        li.add(flag);
        return li;
    }

//    /**
//     * 下拉框联动的行情类型 <功能详细描述>
//     * 
//     * @return Page<PaRskCustomerRatingDto>
//     * @throws Exception Exception
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("getComponentId")
//    public List<SelectOptionDto> selectByComponentType()
//        throws Exception {
//        Map<String, Object> map = RequestUtil.getParameters(getRequest());
//        if (StringUtil.equals(DictCodeList.行情类型_指数基准.getValue(),
//            map.get("componentType"))) {
//            return this.getSelectDataFromDataDic(DictCode.指数行情, null).getOptions();
//        }
//        if (StringUtil.equals(DictCodeList.行情类型_利率基准.getValue(),
//            map.get("componentType"))) {
//            return this.getSelectDataFromDataDic(DictCode.利率行情, null).getOptions();
//        }
//        if (StringUtil.equals(DictCodeList.行情类型_收益率曲线基准.getValue(),
//            map.get("componentType"))) {
//            return this.getSelectDataFromDataDic(DictCode.收益率曲线, null).getOptions();
//        }
//        return null;
//    }
}
