/*
 * 文件名：FileCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.service.LossGivenDefaultMaintain001Service;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.service.LossGivenDefaultMaintain002Service;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 附件信息--业务组件
 * @author zhangnian
 * @version 1.0 2018年9月10日
 * @see LossGivenDefaultMaintain001Controller
 * @since 1.0
 */
@Controller
@PageId("LossGivenDefaultMaintain001")
@RequestMapping("/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001")
public class LossGivenDefaultMaintain001Controller extends BaseController{

    @Autowired
    private LossGivenDefaultMaintain001Service lossGivenDefaultMaintain001Service;
    @Autowired
	private LossGivenDefaultMaintain002Service lossGivenDefaultMaintain002Service;
    
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 画面参数设置
//    	super.setSelectOptionToModel(model, DictCode.资产类型);
        super.setSelectOptionToModel(model, DictCode.证券品种);
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), "fileCommonDto"));
        return super.init(model);
    }
    @ResponseBody
    @RequestMapping("/searchPage")
    public Page<ModLgdConfigDto> findPage()
            throws Exception {
        return lossGivenDefaultMaintain001Service.findPage(
                RequestUtil.getParameters(getRequest()));
    }
    @SuppressWarnings("deprecation")
	@ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) ModLgdConfigDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ModLgdConfigDto> processParameter = new ProcessParameter<ModLgdConfigDto>();
        WorkFlowParameter<ModLgdConfigDto> params = new WorkFlowParameter<ModLgdConfigDto>();
        params.setPageId("Specialassets002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getAssetType()+item.getAssetDetailType()+item.getIsContainPledge());
        params.setBusinessType(DictCodeListExt3.共通业务类型_LGD违约损失率维护.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("资产类型"+
                GlobalConstant.OPEN_PARENTHESIS+entity.getAssetType()+GlobalConstant.CLOSE_PARENTHESIS+
                "债券类型"+GlobalConstant.OPEN_PARENTHESIS+
                entity.getAssetDetailType()+GlobalConstant.CLOSE_PARENTHESIS+
                "是否含有抵质押物"+GlobalConstant.OPEN_PARENTHESIS+
                entity.getIsContainPledge()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = lossGivenDefaultMaintain002Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new ModLgdConfigDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
//    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
//    	param.put("pageId", this.getPageId());
//        return lossGivenDefaultMaintain001Service.export(this.getPageId(),param);
    	 String datas = this.getParam("datas");
         Map<String, Object> paraMap = RequestUtil.getParameters(getRequest());
         List<ModLgdConfigDto> listdto = new ArrayList<ModLgdConfigDto>();
         if (datas == null) {
             listdto = lossGivenDefaultMaintain001Service.selectForExport(paraMap);
         } else {
             listdto = JsonUtils.json2List(datas, ModLgdConfigDto.class);
         }
         File outFile = lossGivenDefaultMaintain001Service.makeFile(
                 "/com/joyintech/tams/valuation/lossmodel/lossgivendefaultmanage/lossgivendefaultmaintain/conf/export_conf.xml",
                 listdto);
         return ExportUtil.export(
                 this.getMessage(DateUtil.format(new Date())+"_LGD违约损失率维护")
                         + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                 outFile, true);
    }



    /**
     * 联动：资产类别
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getSecTypeOption")
    public List<SelectOptionDto> getSecTypeOption() throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        //type 01 毕马威  02 普华永道
        //当为01时，资产类型显示回购09 和存款07
//        param.put("type","01");
        List<SelectOptionDto> linkSelectOption = lossGivenDefaultMaintain001Service.getLinkSelectOption(param);
        return linkSelectOption;
    }
    
}
