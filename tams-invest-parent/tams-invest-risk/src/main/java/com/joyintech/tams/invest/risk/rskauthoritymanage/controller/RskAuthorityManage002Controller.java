/*
 * 文件名：RskAuthorityManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.rskauthoritymanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.risk.rskauthoritymanage.dto.RskAuthorityManageDto;
import com.joyintech.tams.invest.risk.rskauthoritymanage.service.RskAuthorityManageService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 风险授权管理新建
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月1日
 * @see RskAuthorityManage002Controller
 * @since 1.0
 */
@Controller
@PageId("RskAuthorityManage002")
@RequestMapping("/invest/risk/rskauthoritymanage/RskAuthorityManage002")
public class RskAuthorityManage002Controller extends BaseController {
    /**
     * rskAuthorityManageService
     */
    @Autowired
    private RskAuthorityManageService rskAuthorityManageService;
    /**
     * 业务标号生成共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 初始化的查询条件
     */
   private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.角色岗位,
            DictCode.平安资产类型, DictCode.交易场所,DictCode.授权类型
            // 资产类型改为授权类型 add by renping 2019-08-06  start
            , DictCode.授权类型一级, DictCode.授权类型二级
            // 资产类型改为授权类型 add by renping 2019-08-06  end
           };
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        List<SelectOptionDto> assetSecTypeList = makeAllAssetSecType();
        model.addAttribute("ASSET_SEC_TYPE", JsonUtils.objectArray2Json(assetSecTypeList.toArray()));
        List<SelectOptionDto> psList = makeAllPs();
        model.addAttribute("PS", JsonUtils.objectArray2Json(psList.toArray()));
        String seqNo = getParam("seqNo");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (workflowId != null && !StringUtil.equals(workflowId, "")) {
            RskAuthorityManageDto dto = rskAuthorityManageService.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setWorkflowId(workflowId);
            
            RskAuthorityManageDto e = new RskAuthorityManageDto();
            e.setSeqNo(dto.getSeqNo());
            //根据金融产品代码和生效日获得页面展示元素
        	RskAuthorityManageDto dtore = rskAuthorityManageService.selectBySecNo(e); 
            if(dtore != null) {
                dtore.setWorkflowId(workflowId);
                model.addAttribute(GlobalConstant.ENTITY, dtore);
            }else {
            	model.addAttribute(GlobalConstant.ENTITY, dto);
            }
        }
        else if (StringUtil.isNotEmpty(seqNo)){
            RskAuthorityManageDto e = new RskAuthorityManageDto();
            e.setSeqNo(seqNo);
            //根据金融产品代码和生效日获得页面展示元素
            RskAuthorityManageDto dto = rskAuthorityManageService.selectBySecNo(e); 
            model.addAttribute("entity", dto);
        }
        model.addAttribute("RSK02", DictCodeList.共通业务类型_授权管理.getValue());
        model.addAttribute("AUTHORIZE_TYPE_02", DictCodeList.授权类型_授权.getValue());
        return super.init(model);
    }
    /**
     * 生成交易方向的枚举值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private List<SelectOptionDto> makeAllPs() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.回购拆借方向, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.互换方式, null);
        for (SelectOptionDto dto : selectDto3.getOptions()) {
            dto.setId(dto.getCol()+dto.getId());
            listFloatRate.add(dto);
        }
        return listFloatRate;
    }

    /**
     * 生成资产二级类型的枚举值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private List<SelectOptionDto> makeAllAssetSecType() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.WIND一级分类_含债券一级分类等, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.存款类资产类型, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.项目类一级分类, null);
        for (SelectOptionDto dto : selectDto3.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto4 = this.getSelectDataFromDataDic(DictCode.基金类资产类型, null);
        for (SelectOptionDto dto : selectDto4.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto5 = this.getSelectDataFromDataDic(DictCode.计划类资产一级分类, null);
        for (SelectOptionDto dto : selectDto5.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto6 = this.getSelectDataFromDataDic(DictCode.拆借类型, null);
        for (SelectOptionDto dto : selectDto6.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto7 = this.getSelectDataFromDataDic(DictCode.回购类型, null);
        for (SelectOptionDto dto : selectDto7.getOptions()) {
            listFloatRate.add(dto);
        }
        return listFloatRate;
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
    public Page<RskAuthorityManageDto> selectAll()
        throws Exception {
        Page<RskAuthorityManageDto> page = rskAuthorityManageService.select(
            RequestUtil.getPageParam());
        if (StringUtil.isNotEmpty(page.getRoot())) {
           for (RskAuthorityManageDto manageDto : page.getRoot()) {
               if(StringUtil.equals(DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue(),manageDto.getAssetType())) {
                   manageDto.setTradeDirection(DictCode.互换方式.getValue()+manageDto.getTradeDirection()); 
               } 
           }
        }
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskAuthorityManageDto dto, BindingResult result) {
        if(result.getAllErrors().isEmpty() 
            && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)
            && !StringUtil.equals(dto.getWflOperType(), "null") 
            && !StringUtil.equals(dto.getWflOperType(), "NEW")) {
            AjaxResult ar = commonCheck(result, dto.getOperateType());
            String ms = getMessage("RSK_W_0029");
            if(rskAuthorityManageService.checkEdit(dto) ){
                ar.setData(ms);
            }
            return ar;
        }
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskAuthorityManageDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setAllData(dto.getAllData().replace(DictCode.互换方式.getValue(), ""));
        for (RskAuthorityManageDto dto2 : dto.getSelectedItem()) {
            dto2.setTradeDirection(dto2.getTradeDirection().replace(DictCode.互换方式.getValue(), ""));
        }
        ProcessParameter<RskAuthorityManageDto> processParameter = new ProcessParameter<RskAuthorityManageDto>();
        WorkFlowParameter<RskAuthorityManageDto> params = new WorkFlowParameter<RskAuthorityManageDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
      //修改时为画面所选记录的金融产品代码。新建时调用各种业务共通组件.获取金融产品代码
        params.setBusinessCode(entity -> {
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_授权管理);
            String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
                param) : IDGenerator.generate();
            return code;
        });
        params.setBusinessType(DictCodeList.共通业务类型_授权管理.getValue());
     // 设置流程业务说明, entity即传入的dto
        // 业务说明：“用户：”+画面.用户+“授权额度：”+画面.授权额度
        params.setComment(entity -> "角色岗位:" + "(" + entity.getRolePosStr() + ")" + "-" + "用户:" + "("
                                    + entity.getUserName() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = rskAuthorityManageService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 判断编辑时是否覆盖的check
     *
     * @param dto dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkEdit")
    public AjaxResult checkEdit(@RequestBody RskAuthorityManageDto dto){
        AjaxResult ar = new AjaxResult();
        String ms = getMessage("RSK_W_0029");
        if(rskAuthorityManageService.checkEdit(dto)){
            ar.setData(ms);
        }
        return ar;
    }
    
}
