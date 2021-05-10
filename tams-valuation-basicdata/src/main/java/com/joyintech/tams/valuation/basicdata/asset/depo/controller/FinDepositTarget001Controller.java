/*
 * 文件名：FinDepositTarget001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.depo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.depo.service.FinDepositTargetService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 存款类资产管理
 * @author zhangnian
 * @version 1.0 2018年9月5日
 * @see FinDepositTarget001Controller
 * @since 1.0
 */
@Controller
@PageId("FinDepositTarget001")
@RequestMapping("/valuation/basicdata/asset/depo/FinDepositTarget001")
public class FinDepositTarget001Controller extends BaseController {

    @Autowired
    private FinDepositTargetService finDepositTargetService;
    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.存款类资产类型_GZ,
            DictCode.存款类资产存款类型,DictCode.存款核算分类, DictCode.是否};

    @Override
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        return super.init(model);
    }
    
    
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinDepositTargetDto> selectAll()
        throws Exception {
        Page<FinDepositTargetDto> page = finDepositTargetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    
    /**
     * 删除check
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinDepositTargetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    

    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinDepositTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 设置留痕参数和工作流参数
        ProcessParameter<FinDepositTargetDto> processParameter = new ProcessParameter<FinDepositTargetDto>();
        WorkFlowParameter<FinDepositTargetDto> params = new WorkFlowParameter<FinDepositTargetDto>();
        params.setPageId("FinDepositTarget002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产信息.getValue());
        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinDepositTarget002.finprodId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+ GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("FinDepositTarget002.finprodName")+ GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodName()+ GlobalConstant.CLOSE_PARENTHESIS
                +this.getMessage("FinDepositTarget002.type6")+ GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodType2()+ GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finDepositTargetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinDepositTargetDto[dto.getSelectedItem().size()]));// 调用工作流事件
        return new AjaxResult(msg);// 返回工作流错误信息
    }
    

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        String datas = this.getParam("datas");
        FinDepositTargetDto dto = (FinDepositTargetDto)JsonUtils.map2entity(params, FinDepositTargetDto.class);
        List<FinDepositTargetDto> dtoList;
        if (datas == null) {
            dtoList = finDepositTargetService.selectAll(params);
        }
        else {
            dtoList = JsonUtils.json2List(datas, FinDepositTargetDto.class);
        }
        File  outFile = finDepositTargetService.makeFile(
                    "/com/joyintech/tams/valuation/basicdata/asset/depo/conf/export_conf.xml",
                    dtoList);

        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    /**
     * 获取所有资产id--联想文本框
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getAssetId")
    public List<SuggestDto> getAssetId(){
        String search = this.getParam("param")==null? StringUtil.EMPTY:this.getParam("param");
        String finprodType = this.getParam("finprodType")==null?StringUtil.EMPTY:this.getParam("finprodType");
        String profitType = this.getParam("profitType")==null?StringUtil.EMPTY:this.getParam("profitType");
        return finDepositTargetService.getAssetId(search,finprodType,profitType);
    }
}
