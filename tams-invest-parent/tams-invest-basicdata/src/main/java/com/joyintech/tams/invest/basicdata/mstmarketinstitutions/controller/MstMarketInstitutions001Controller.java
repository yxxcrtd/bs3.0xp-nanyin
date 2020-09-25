/*
 * 文件名：MstMarketInstitutions002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstmarketinstitutions.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.basicdata.mstmarketinstitutions.dto.MstMarketInstitutionsDto;
import com.joyintech.tams.invest.basicdata.mstmarketinstitutions.service.MstBondCreditRatingService;
import com.joyintech.tams.invest.basicdata.mstmarketinstitutions.service.MstMarketInstitutionsService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 市场机构的基本信息管理
 * 显示市场机构信息的基本页面
 * @author Wangzehui
 * @version 1.0 2018年7月23日
 * @see MstMarketInstitutions001Controller
 * @since 1.0
 * 
 */
@Controller
@PageId("MstMarketInstitutions001")
@RequestMapping("/invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001")
public class MstMarketInstitutions001Controller extends BaseController{
    
    /**
     * 市场机构管理service注入
     */
    @Autowired
    private MstMarketInstitutionsService mstMarketInstitutionsService;
    /**
     * 
     * {@inheritDoc}
     */
    @Autowired
    private MstBondCreditRatingService mstBondCreditRatingService;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        /*添加'是否持有金融牌照'数据字典值 add by lishuping 20190823start*/
        this.setSelectOptionToModel(model, DictCode.是否 );
        /*添加'是否持有金融牌照'数据字典值 add by lishuping 20190823 end*/
        return super.init(model);
    }
    
    /**
     * 是否持有金融牌照 扩充空值 版
     */
    @ResponseBody
    @RequestMapping("/getOrgType2")
    public List<SelectOptionDto> getOrgType2() {
    	
    	List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeListExt1.是否_是.getValue());
        dto1.setText(DictCodeListExt1.是否_是.getCodeName());
        
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeListExt1.是否_否.getValue());
        dto2.setText(DictCodeListExt1.是否_否.getCodeName());
        
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setId(DictCodeListExt1.是否_空.getValue());
        dto3.setText(DictCodeListExt1.是否_空.getCodeName());
        
        listtype.add(dto1);
        listtype.add(dto2);
        listtype.add(dto3);
        
    	return listtype;
    }
    
    /**
     * 
     * 市场机构页面模糊查询
     * <功能详细描述>
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstMarketInstitutionsDto> selectAllMstCaptialAccount()
        throws Exception {
        Page<MstMarketInstitutionsDto> page = mstMarketInstitutionsService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
    
    /**
     * 
     * 客户代码联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCustomerId")
    public List<SuggestDto> getCustomerId()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        //List<SuggestDto> list = new ArrayList<SuggestDto>();
        String param = super.getRequest().getParameter("param");
        map.put("customerId", param);
        List<SuggestDto> temp = mstMarketInstitutionsService.getCustomerId(map);
        return temp;
    }
    
    /**
     * 
     * 客户名称联想文本框
     * <功能详细描述>
     * @return List<SuggestDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCustomerName")
    public List<SuggestDto> getCustomerName()
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String param = super.getRequest().getParameter("param");
        map.put("customerType", DictCodeList.客户类型_法人.getValue());
        map.put("customerName", param);
        map.put("customerId", RequestUtil.getPageParam().get("customerId"));
        List<SuggestDto> temp = mstMarketInstitutionsService.getCustomerName(map);
        return temp;
    }
    
    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) MstMarketInstitutionsDto dto, BindingResult result) {
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
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) MstMarketInstitutionsDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        for(int i=0; i<dto.getSelectedItem().size(); i++){
            List<MstBondCreditRating> mstBondCreditRating = mstBondCreditRatingService
                .selectMstBondCreditRatingByPrimaryKey(dto.getSelectedItem().get(i).getCustomerId());
            dto.getSelectedItem().get(i).setMstBondCreditRating(mstBondCreditRating);
        }
        ProcessParameter<MstMarketInstitutionsDto> processParameter = new ProcessParameter<MstMarketInstitutionsDto>();
        WorkFlowParameter<MstMarketInstitutionsDto> params = new WorkFlowParameter<MstMarketInstitutionsDto>();
        params.setPageId("MstMarketInstitutions002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getCustomerId());
        params.setBusinessType(DictCodeList.共通业务类型_市场机构管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        //客户代码 + "(" + 客户代码 + ")" + "-"  + "客户名称" + "-"  + "客户名称"
        params.setComment(
            entity -> "客户代码"+ "(" + entity.getCustomerId()+")" +  "-" + "客户名称"+ "(" + entity.getCustomerName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstMarketInstitutionsService.start(processParameter,
            dto.getSelectedItem().toArray(new MstMarketInstitutionsDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 文件导出
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return mstMarketInstitutionsService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
