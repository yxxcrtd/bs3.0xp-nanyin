package com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.dto.CreditRightsManagementDto;
import com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.service.CreditRightsManagementService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 授信权数管理
 * @author huwenbin
 *
 */

@Controller
@PageId("CreditRightsManagement001")
@RequestMapping("/invest/risk/creditmanagement/creditrightsmanagement/CreditRightsManagement001")
public class CreditRightsManagement001Controller extends BaseController{
	
	@Autowired
	private CreditRightsManagementService creditRightsManagementService;
	
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, DictCode.平安资产类型);
		List<SelectOptionDto> assetSecTypeList = makeAllAssetSecType();
        model.addAttribute("ASSET_SEC_TYPE", JsonUtils.objectArray2Json(assetSecTypeList.toArray()));
        return super.init(model);
    }
	
	
	
	
	/**
	 * 
	 * 页面查询
	 * <功能详细描述>
	 * @return Page<CreditRightsManagementDto>
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
    @RequestMapping("findPage")
    public Page<CreditRightsManagementDto> selectAllMstCaptialAccount()
        throws Exception {
        Page<CreditRightsManagementDto> page = creditRightsManagementService.selectForPage(RequestUtil.getPageParam());
        return page;
    }
	
	/**
     * 删除按钮点击时的check
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) CreditRightsManagementDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 行删除与删除按钮
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br br
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({ValidateGroupDelete.class}) CreditRightsManagementDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<CreditRightsManagementDto> processParameter = new ProcessParameter<CreditRightsManagementDto>();
        WorkFlowParameter<CreditRightsManagementDto> params = new WorkFlowParameter<CreditRightsManagementDto>();
        params.setPageId("CreditRightsManagement002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item->item.getSeqno());
        params.setBusinessType(DictCodeList.共通业务类型_风险权数管理.getValue());
        String twoStr =StringUtil.isEmpty(dto.getAssetTypeTwoStr())?"":"-" + "资产二级分类"+ "(" + dto.getAssetTypeTwoStr()+ ")";
        // 设置流程业务说明, entity即传入的dto
        params.setComment(
                entity -> "资产一级分类"+ "(" + entity.getAssetTypeOneStr()+ ")" 
                		+  "-" + twoStr
                		+ "生效日"+"(" + entity.getEffDateStr() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = creditRightsManagementService.start(processParameter, 
            dto.getSelectedItem().toArray(new CreditRightsManagementDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    /**
     * 文件导出
     * <一句话功能简述>
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return creditRightsManagementService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 
     * 获取资产二级分类
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
}
