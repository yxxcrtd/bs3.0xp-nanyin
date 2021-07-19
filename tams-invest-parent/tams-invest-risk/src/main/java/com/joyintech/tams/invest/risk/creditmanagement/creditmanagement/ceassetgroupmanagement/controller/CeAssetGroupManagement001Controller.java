/**
 * 
 */
package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement001Service;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service.CeAssetGroupManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 资产系列管理
 * 
 * @author dongzhiling
 * @version 1.0 2019年1月7日
 * @see CeAssetGroupManagement001Controller
 * @since 1.0
 */

@Controller
@PageId("CeAssetGroupManagement001")
@RequestMapping("/invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement001")
public class CeAssetGroupManagement001Controller extends BaseController {
    
    /**
     * CeAssetGroupManagement001Service
     */
    @Autowired(required = false)
    private CeAssetGroupManagement001Service ceAssetGroupManagement001Service;
    /**
     * CeAssetGroupManagement002Service
     */
    @Autowired(required = false)
    private CeAssetGroupManagement002Service ceAssetGroupManagement002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param model
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssetName")
    public String getAssetName(Model model) {
        Map<String, Object> param = RequestUtil.getPageParam();
        model.addAttribute("list", ceAssetGroupManagement001Service.getAssetName(param));
        return "invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement004";
    }
    
    /**
     * 
     * <一句话功能简述>
     * 页面查询
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CeAssetGroupManagement001Dto> findPage() {
        if (StringUtil.isNotEmpty(this.getParam("seqno"))) {
            return ceAssetGroupManagement001Service.getAssetDetailBySeqno(RequestUtil.getParameters(getRequest()));
        }
        return new Page<CeAssetGroupManagement001Dto>();
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  CeAssetGroupManagement001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //查询系列名称
        CeAssetGroupManagement001Dto businessInfoBySeqno = ceAssetGroupManagement002Service.getBusinessInfoBySeqno(dto.getSeqno());
        dto.setAssetpoolName(businessInfoBySeqno.getAssetpoolName());
        ProcessParameter<CeAssetGroupManagement001Dto> processParameter = new ProcessParameter<CeAssetGroupManagement001Dto>();
        WorkFlowParameter<CeAssetGroupManagement001Dto> params = new WorkFlowParameter<CeAssetGroupManagement001Dto>();
        params.setPageId("CeAssetGroupManagement002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_资产系列授信.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("CeAssetGroupManagement001.seqno"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getSeqno(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("CeAssetGroupManagement002.name"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getAssetpoolName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = ceAssetGroupManagement001Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 导出
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ceAssetGroupManagement001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
