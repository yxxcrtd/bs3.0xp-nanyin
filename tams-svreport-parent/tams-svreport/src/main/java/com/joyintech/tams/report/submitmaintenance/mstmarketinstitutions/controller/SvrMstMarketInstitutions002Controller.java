/*
 * 文件名：MstMarketInstitutions002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemDictCode;
import com.joyintech.tams.common.base.entity.InterfCmsInstitution;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrPaRskCustomerRatingDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrMstMarketInstitutionsMService;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrPaRskCustomerRatingMService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 市场机构信息新建
 * 显示市场机构信息的新建页面
 * @author huwenbin
 * @version 1.0 2018年7月23日
 * @see SvrMstMarketInstitutions002Controller
 * @since 1.0
 */
@Controller
@PageId("MstMarketInstitutions002")
@RequestMapping("/submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002")
public class SvrMstMarketInstitutions002Controller extends BaseController{
    /**
     * 客户评级service注入
     */
    @Autowired
    private SvrPaRskCustomerRatingMService paRskCustomerRatingMService;
    
    /**
     * 市场机构管理service注入
     */
    @Autowired
    private SvrMstMarketInstitutionsMService mstMarketInstitutionsMService;
    /**
     * 
     * 新建页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        //REPORT_YEAR
        List<SelectOptionDto> listYear = new ArrayList<SelectOptionDto>();
        for(int i = 2017; i<=2025 ; i++){
            SelectOptionDto year = new SelectOptionDto();
            year.setId(i+"");
            year.setText(i+"");
            listYear.add(year);
        }
        model.addAttribute("REPORT_YEAR", JsonUtils.objectArray2Json(listYear.toArray()));
        this.setSelectOptionToModel(model,DictCode.机构所属行业一级分类 );
        this.setSelectOptionToModel(model,DictCode.机构所属行业二级分类 );
        
        //公司性质
        this.setSelectOptionToModel(model,DictCode.企业性质);
        //是否
        this.setSelectOptionToModel(model,DictCode.是否 );
        this.setSelectOptionToModel(model,SystemDictCode.行政区划_省 );
        //控股类型
        this.setSelectOptionToModel(model,DictCode.控股类型 );
        //客户主体类型
        this.setSelectOptionToModel(model,DictCode.客户主体类型 );
        //产业结构调整类型
        this.setSelectOptionToModel(model,DictCode.产业结构调整类型);
        //工业转型升级标识
        this.setSelectOptionToModel(model,DictCode.工业转型升级标识);
        //机构类型_按经济类型划分
        this.setSelectOptionToModel(model,DictCode.发行机构类型_按经济类型划分 );
        //机构类型_按技术领域划分
        this.setSelectOptionToModel(model,DictCode.发行机构类型_按技术领域划分 );
        //机构类型_按规模划分
        this.setSelectOptionToModel(model,DictCode.发行机构类型_按规模划分 );
        //授信策略
        this.setSelectOptionToModel(model,DictCode.授信策略 );
        //表格数据字典
        this.setSelectOptionToModel(model,DictCode.客户评级类型, DictCode.客户评级认定结果 ,DictCode.客户评级使用模型类别);
        //核算分类
        this.setSelectOptionToModel(model,DictCode.核算分类 );
        //客户机构代码
        String customerId = getParam("customerId");
        
        if (StringUtil.isNotEmpty(customerId)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("customerId", customerId);
            SvrMstMarketInstitutionsDto mstMarketInstitutionsDto = mstMarketInstitutionsMService.selectDetail(map);
            model.addAttribute("entity", mstMarketInstitutionsDto);
        }
        return super.init(model);
    }
    
    /**
     * 
     * 编辑修改页面表格查询
     * <功能详细描述>
     * @return Page<PaRskCustomerRatingDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<SvrPaRskCustomerRatingDto> selectAllMstCaptialAccount()
        throws Exception {
        Page<SvrPaRskCustomerRatingDto> page = paRskCustomerRatingMService.selectPaRskCustomerPage(RequestUtil.getPageParam());
        return page;
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) SvrMstMarketInstitutionsDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto MstMarketInstitutionsDto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(SvrMstMarketInstitutionsDto dto) throws Exception{
    	String msg = this.getMessage("REPORT_COMMON_I_0006");
    	if(dto.getPaRskCustomerStr()!=null){
    		dto.setPaRskCustomer(JSON.parseArray(dto.getPaRskCustomerStr(), SvrPaRskCustomerRatingDto.class));
    	}
        if("0".equals(dto.getPageModel())){
        	mstMarketInstitutionsMService.doComplete(dto, OperationType.NEW);
        }else if("1".equals(dto.getPageModel())){
        	mstMarketInstitutionsMService.doComplete(dto,OperationType.MODIFY);
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCity")
    public List<SelectOptionDto> getCity()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dict_code_parent",SystemDictCode.行政区划_省.getValue());
        param.put("item_code_child", param.get("province"));
        return mstMarketInstitutionsMService.getAddressSelectOption(param);
    }
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCounties")
    public List<SelectOptionDto> getCounties()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("dict_code_parent",SystemDictCode.行政区划_市.getValue());
        param.put("item_code_child", param.get("city"));
        return mstMarketInstitutionsMService.getAddressSelectOption(param);
    }
    
    /**
     * 
     * 根据becif查询CMS_机构信息
     * <功能详细描述>
     * @return InterfCmsInstitution
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/selectInterfCmsInstitution")
    public InterfCmsInstitution selectInterfCmsInstitution(){
        Map<String, Object> param = RequestUtil.getPageParam();
        return mstMarketInstitutionsMService.selectInterfCmsInstitution((String)param.get("becif"));
    }
    
    /**
     * 
     * 根据wind编码查询wind_公司简介
     * <功能详细描述>
     * @return InterfCompintroduction
     * @see
     * @since 1.0
     */
//    TODO
//    @ResponseBody
//    @RequestMapping("/selectInterfCompintroduction")
//    public InterfCompintroduction selectInterfCompintroduction(){
//        Map<String, Object> param = RequestUtil.getPageParam();
//        return mstMarketInstitutionsMService.selectInterfCompintroduction((String)param.get("objectId"));
//    }
    
}

