package com.joyintech.tams.invest.basicdata.mstseatinfo.controller;

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
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.basicdata.mstseatinfo.dto.MstSeatInfoDto;
import com.joyintech.tams.invest.basicdata.mstseatinfo.service.MstSeatInfoService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

@Controller
@PageId("MstSeatInfo001")
@RequestMapping("/invest/basicdata/mstseatinfo/MstSeatInfo001")
public class MstSeatInfo001Controller extends BaseController {
	/**
	 * 席位信息管理service注入
	 */
	@Autowired
	private MstSeatInfoService mstSeatInfoService;
	
	 /**
     * 业务编号组件
     */
    @Autowired(required = false)
    private BaseGetBusinessId bussGetBussinessIdService;

	/**
	 * 页面初始化
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, new DictCode[] { DictCode.交易场所 });
		return super.init(model);
	}
	
	 /**
     * 查询 <功能详细描述>
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<MstSeatInfoDto> selectAll()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        Page<MstSeatInfoDto> page = mstSeatInfoService.selectForPage(param);
        return page;
    }
    
    /**
     * 营业部名称联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/fuzzySearchSeatId")
    public List<SuggestDto> getMstSaleDepartmentName()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, String> map = new HashMap<String, String>();
        map.put("param", param);
        List<SuggestDto> temp = mstSeatInfoService.fuzzySearchSeatId(map);
        return temp;
    }
    
    /**
     * 营业部名称联想文本框 <功能详细描述>
     * 
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/selectCounterSeat")
    public List<SuggestDto> selectCounterSeat()
        throws Exception {
        Map<String,Object> maps = RequestUtil.getPageParam();
        maps.put("counterSeatId",maps.get("param"));
        return mstSeatInfoService.selectCounterSeat(maps);
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
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstSeatInfoDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) MstSeatInfoDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<MstSeatInfoDto> processParameter = new ProcessParameter<MstSeatInfoDto>();
        WorkFlowParameter<MstSeatInfoDto> params = new WorkFlowParameter<MstSeatInfoDto>();
        params.setPageId("MstSeatInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getSeatId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_席位信息管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity ->SpringContextUtil.getTextValue("席位号") + "(" + entity.getSeatId() + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = mstSeatInfoService.start(processParameter,
            dto.getSelectedItem().toArray(new MstSeatInfoDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 文件导出 <功能详细描述>
     * 
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return mstSeatInfoService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
