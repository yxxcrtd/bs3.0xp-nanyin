/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.assets.bondasset.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assets.bondasset.dto.ValBondassetquery001Dto;
import com.joyintech.tams.valuation.assets.bondasset.service.ValBondassetquery001Service;
import com.joyintech.tams.valuation.assets.bondasset.service.ValBondassetquery002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 模板示例<br>
 * 债券类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValBondassetquery001Controller
 * @since 2.0
 */
@Controller
@PageId("ValBondassetquery001")
@RequestMapping("/valuation/assets/bondasset/ValBondassetquery001")
public class ValBondassetquery001Controller extends BaseController {
    
    /**
     * ValBondassetquery001Service
     */
    @Autowired
    private ValBondassetquery001Service valBondassetquery001Service;
    
    @Autowired
    private ValBondassetquery002Service valBondassetquery002Service;
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.息票品种);
        this.setSelectOptionToModel(model, DictCode.债券类型);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
        	model.addAttribute(GlobalConstant.WORKFLOW_ID, workflowId);
        }
        return super.init(model);
    }

    /**
     * 债券获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBondId")
    @ResponseBody
    public List<SelectOptionDto> getBondId(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return valBondassetquery001Service.getBondId(param);
    }


    /**
     * 查询前校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("checkQuery")
//    public AjaxResult checkQuery(@Valid @RequestBody ValBondassetquery001SearchDto dto,
//            BindingResult result) {
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        return new AjaxResult();
//    }

    /**
     * 债券类资产数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValBondassetquery001Dto> findPage() throws Exception {
    	Map<String,Object> reqMap = RequestUtil.getPageParam();
        return valBondassetquery001Service.findPage(reqMap);
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
    public ResponseEntity<byte[]> export()throws Exception {
            String datas = this.getParam("datas");
            Map<String, Object> paraMap = RequestUtil.getPageParam();
            List<ValBondassetquery001Dto> listdto;
            if (datas == null) {
            listdto = (List<ValBondassetquery001Dto>)valBondassetquery001Service.findPageout(paraMap);
            }
            else {
                listdto = JsonUtils.json2List(datas, ValBondassetquery001Dto.class);
            }
            File outFile = valBondassetquery001Service.makeFile(
                "/com/joyintech/tams/valuation/assets/bondasset/config/export_Bondassetquery001.xml", listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
    
    /**
     * 删除check
     * 
     * @param dto 行数据
     * @param result check结果
     * @return WebResult
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("doDeleteCheck")
    @ResponseBody
    public AjaxResult doDeleteCheck(@RequestBody @Valid ValBondassetquery001Dto dto,
                                 BindingResult result) throws Exception {
    	// 有交易数据
        int count = valBondassetquery002Service.selectDealCount(dto.getBondid());
        if (count > 0) {
            return new AjaxResult("该资产已存在交易数据，不能删除！", MessageType.错误消息);
        }
        return valBondassetquery001Service.doDeleteCheck(result, dto);
    }

    /**
     * 删除分支节点
     * 
     * @param dto 行数据
     * @return WebResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult doDelete(@RequestBody @Valid ValBondassetquery001Dto dto, BindingResult br) {
        
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        
        ProcessParameter<ValBondassetquery001Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<ValBondassetquery001Dto> params = new WorkFlowParameter<>();
        // 业务代码
        params.setBusinessCode(dto.getFinprodId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产信息.getValue());
        // 页面id
        params.setPageId("ValBondassetquery003");
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
        // 流程业务说明
        params.setComment(entity -> WorkFlowUtil.concatComment("债券类资产信息删除：债券代码",
            entity.getBondid()//资产代码
        ));
        proParam.setWorkFlowParameter(params);
        
        String msg = getMessage("COMMON_I_0001");
        try {
            msg = valBondassetquery001Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }
        return new AjaxResult(msg);
    }

}
