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
import java.util.Map;

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
//        List<SelectOptionDto> assetSecTypeList = makeAllAssetSecType();
//        model.addAttribute("ASSET_SEC_TYPE", JsonUtils.objectArray2Json(assetSecTypeList.toArray()));
        String seqNo = getParam("seqNo");
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (workflowId != null && !StringUtil.equals(workflowId, "")) {
            RskAuthorityManageDto dto = rskAuthorityManageService.getBussinessEntityByWorkflowId(
                workflowId);
            dto.setWorkflowId(workflowId);
            model.addAttribute(GlobalConstant.ENTITY, dto);
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
    @ResponseBody
    @RequestMapping("makeAllPs")
    public List<SelectOptionDto> makeAllPs() {

        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.回购拆借方向, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }

        List<SelectOptionDto> listRate = new ArrayList<SelectOptionDto>();
        String key = getParam("assetType");
        if(key.equals ("F03") || key.equals ("F05") || key.equals ("F06")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
        }else if(key.equals ("F02")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (3));
            listRate.add (listFloatRate.get (4));
            listRate.add (listFloatRate.get (10));
        }else if(key.equals ("F01") || key.equals ("F17")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
        }else if(key.equals ("F07") || key.equals ("F10")){
            listRate.add (listFloatRate.get (13));
            listRate.add (listFloatRate.get (14));
        }else if(key.equals ("F11")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (7));
            listRate.add (listFloatRate.get (8));
            listRate.add (listFloatRate.get (9));
            listRate.add (listFloatRate.get (3));
        }else if(key.equals ("F04") || key.equals ("F09")){
            listRate.add (listFloatRate.get (4));
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (3));
        }else if(key.equals ("F08") ){
            listRate.add (listFloatRate.get (11));
            listRate.add (listFloatRate.get (12));
        }else if (key.equals ("F19") ){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (3));
            listRate.add (listFloatRate.get (4));
        }
        return listRate;
    }



    /**
     * 生成交易场所的枚举值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("makeAlltradeMarket")
    public List<SelectOptionDto> makeAlltradeMarket() {

        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易场所, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }

        List<SelectOptionDto> listRate = new ArrayList<SelectOptionDto>();
        String key = getParam("assetType");
        if(key.equals ("F02")){
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
            listRate.add (listFloatRate.get (4));
        }else if(key.equals ("F01")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
            listRate.add (listFloatRate.get (3));
            listRate.add (listFloatRate.get (5));
        }else if(key.equals ("F07") || key.equals ("F10")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
        }else if(key.equals ("F11")){
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
        }else if(key.equals ("F17")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
        }else if(key.equals ("F08")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (4));
        }
        return listRate;
    }

    /**
     * 生成资产二级类型的枚举值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("makeAllAssetSecType")
    public Page<SelectOptionDto> makeAllAssetSecType() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.授权类型二级, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }

        List<SelectOptionDto> listRate = new ArrayList<SelectOptionDto>();
        String key = getParam("assetType");
        if(key.equals ("F01") || key.equals ("F17")){
            listRate.add (listFloatRate.get (1));
            listRate.add (listFloatRate.get (2));
            listRate.add (listFloatRate.get (3));
            listRate.add (listFloatRate.get (4));
            listRate.add (listFloatRate.get (5));
            listRate.add (listFloatRate.get (6));
            listRate.add (listFloatRate.get (7));
            listRate.add (listFloatRate.get (8));
            listRate.add (listFloatRate.get (9));
            listRate.add (listFloatRate.get (10));
            listRate.add (listFloatRate.get (11));
            listRate.add (listFloatRate.get (12));
            listRate.add (listFloatRate.get (13));
            listRate.add (listFloatRate.get (14));
            listRate.add (listFloatRate.get (15));
            listRate.add (listFloatRate.get (16));
            listRate.add (listFloatRate.get (17));
            listRate.add (listFloatRate.get (18));
            listRate.add (listFloatRate.get (39));
            listRate.add (listFloatRate.get (40));
            listRate.add (listFloatRate.get (41));
            listRate.add (listFloatRate.get (42));
            listRate.add (listFloatRate.get (43));
            listRate.add (listFloatRate.get (44));
            listRate.add (listFloatRate.get (45));
            listRate.add (listFloatRate.get (46));
            listRate.add (listFloatRate.get (47));
            listRate.add (listFloatRate.get (48));
            listRate.add (listFloatRate.get (49));
        }else if(key.equals ("F02")){
            listRate.add (listFloatRate.get (21));
            listRate.add (listFloatRate.get (22));
            listRate.add (listFloatRate.get (23));
            listRate.add (listFloatRate.get (24));
            listRate.add (listFloatRate.get (25));
            listRate.add (listFloatRate.get (51));
        }else if(key.equals ("F03") || key.equals ("F04") || key.equals ("F09") ){
            listRate.add (listFloatRate.get (37));
            listRate.add (listFloatRate.get (38));
        }else if(key.equals ("F05")){
            listRate.add (listFloatRate.get (0));
            listRate.add (listFloatRate.get (19));
            listRate.add (listFloatRate.get (20));
            listRate.add (listFloatRate.get (50));
        }else if(key.equals ("F06")){
            listRate.add (listFloatRate.get (26));
            listRate.add (listFloatRate.get (27));
            listRate.add (listFloatRate.get (28));
            listRate.add (listFloatRate.get (29));
            listRate.add (listFloatRate.get (30));
            listRate.add (listFloatRate.get (31));
            listRate.add (listFloatRate.get (32));
            listRate.add (listFloatRate.get (33));
            listRate.add (listFloatRate.get (34));
            listRate.add (listFloatRate.get (35));
            listRate.add (listFloatRate.get (36));
            listRate.add (listFloatRate.get (52));
        }else if (key.equals("F19")){
            listRate.add (listFloatRate.get (53));
            listRate.add (listFloatRate.get (54));
        }
        Page<SelectOptionDto> page =new Page<SelectOptionDto>();
        page.setRoot (listRate);
        return page;
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
