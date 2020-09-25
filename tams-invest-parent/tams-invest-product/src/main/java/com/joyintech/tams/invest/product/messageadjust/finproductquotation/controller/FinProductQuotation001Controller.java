package com.joyintech.tams.invest.product.messageadjust.finproductquotation.controller;
/**
 * 产品报价管理-查询
 * @author wangkang
 * @date 2018-10-9
 */

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto.FinProductQuotation001Dto;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.service.FinProductQuotation001Service;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年12月1日
 * @see FinProductQuotation001Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductQuotation001")
@RequestMapping("/invest/product/messageadjust/finproductquotation/FinProductQuotation001")
public class FinProductQuotation001Controller extends BaseController {

    /**
     * 产品报价管理查询service
     */
    @Autowired
    private FinProductQuotation001Service finProductQuotation001Service;

    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        setSelectOptionToModel(model, DictCode.销售部门, DictCode.录入方式,DictCode.发送状态);
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);
        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }
    
    /**
     * 产品代码联想下拉框
     * 
     * @return 产品代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryProdIdInfoIssueSuc")
    public List<SelectOptionDto> queryProdIdInfoIssueSuc() {
        return finProductQuotation001Service.queryProdIdInfoIssueSuc(RequestUtil.getPageParam());
    }

    /**
     * 产品报价管理查询
     * 
     * @return Page<FinProductQuotation001Dto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductQuotation001Dto> selectForPage()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return finProductQuotation001Service.selectForPage(param);
    }

    /**
     * 产品代码联想下拉框 <功能详细描述>
     * 
     * @param search
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getProdId(String search) {
        return finProductQuotation001Service.getFinprodIds(search);
    }

    
    /**
     * 产品款型联想下拉框 <功能详细描述>
     * 
     * @param search
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdType")
    public List<SelectOptionDto> getProdType(String search) {
        return finProductQuotation001Service.getProdType(search);
    }
    /**
     * 修改或删除的check
     * 
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinProductQuotation001Dto dto, BindingResult br) {
        return commonCheck(br, dto.getOperateType());
    }

    /**
     * 启动工作流执行删除 <功能详细描述>
     * 
     * @param dto
     * @param br
     * @return AjaxResult
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody FinProductQuotation001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinProductQuotation001Dto> processParameter = new ProcessParameter<FinProductQuotation001Dto>();
        WorkFlowParameter<FinProductQuotation001Dto> params = new WorkFlowParameter<FinProductQuotation001Dto>();
        params.setPageId("FinProductQuotation002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> {
            return item.getFinprodMarketId();
        });
        params.setBusinessType(DictCodeList.共通业务类型_产品报价调整.getValue());
        processParameter.setOperationType(OperationType.DELETE);

        // 设置流程业务说明
        // 业务说明："产品代码" + "(" + 产品代码 + ")" + "-" + "报价生效日期" + "(" + 画面.生效日 + ")"
        params.setComment(entity -> this.getMessage("FinProductQuotation001.finprodMarketId") + "("
                                    + entity.getFinprodMarketId() + ")"
                                    + this.getMessage("FinProductQuotation001.effDate") + "("
                                    + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductQuotation001Service.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinProductQuotation001Dto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }

    /**
     * 导出信息
     * 
     * @return ResponseEntity
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finProductQuotation001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 报价调整-发送销售接口状态
     *
     * @param FinNetProductBonusSchemeDto
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("sendProductQuotationToSale")
    public AjaxResult sendProductQuotationInterf(@RequestBody FinProductQuotation001Dto dto) {
        //或许选择推送的数据，循环调用推送销售系统方法
        List<FinProductQuotation001Dto> dtoList = dto.getSelectedItem() ;
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
            boolean sendFlag = true ;// 默认状态为推送销售系统
            for (FinProductQuotation001Dto finProductQuotation001Dto : dtoList) {
                // 查询是否已经推送销售系统
                if(DictCodeList.发送状态_发送成功.getValue().equals(finProductQuotation001Dto.getSendStatus())){
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032","未发送和发送失败")));
                    sendFlag = false ;
                    return ar ;
                }
            }
			if (sendFlag) {
				List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
				for (FinProductQuotation001Dto model : dtoList) {
					Map<String,String> mapInfo =new HashMap<>();
					mapInfo.put("cashId", model.getCashId());
					mapInfo.put("effDate", model.getEffDateStr());
					listMap.add(mapInfo);
				}
				// 推送报价调整至销售系统
				Map<String, String> map = finProductQuotation001Service.sendProductQuotationToSale(listMap);
				errorList.add(new ValidMessage(map.get("msg")));
			}
        }
        return ar;
    }
}
