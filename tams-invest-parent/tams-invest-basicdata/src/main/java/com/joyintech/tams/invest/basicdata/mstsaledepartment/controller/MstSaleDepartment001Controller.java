package com.joyintech.tams.invest.basicdata.mstsaledepartment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.service.MstSaleDepartmentService;

@Controller
@PageId("MstSaleDepartment001")
@RequestMapping("/invest/basicdata/mstsaledepartment/MstSaleDepartment001")
public class MstSaleDepartment001Controller extends BaseController{
    /**
     * 营业部管理service注入
     */
    @Autowired
    private MstSaleDepartmentService mstSaleDepartmentService;
    
    /**
     * 页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[] {DictCode.申报单位,DictCode.特殊处理业务,DictCode.柜台接口类型,DictCode.是或否});
        List<SelectOptionDto> brokerIds = new ArrayList<SelectOptionDto>();
		try {
			brokerIds = getMstCustomerInfo();
			model.addAttribute("brokerIds", JsonUtils.objectArray2Json(brokerIds.toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
    public Page<MstSaleDepartmentDto> selectAll()
        throws Exception {
        Map<String, Object> param = RequestUtil.getPageParam();
        //柜台接口类型值在js中就已经是数组了，直接取出来
        String countinterType = (String)param.get("intertype");
        if (StringUtil.isNotEmpty(countinterType)) {
            param.put("countinterType",StringUtil.splitToList(GlobalConstant.COMMA, countinterType));
        }
        Page<MstSaleDepartmentDto> page = mstSaleDepartmentService.selectForPage(param);
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
    @RequestMapping("/getMstSaleDepartmentName")
    public List<SuggestDto> getMstSaleDepartmentName()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, String> map = new HashMap<String, String>();
        map.put("param", param);
        List<SuggestDto> temp = mstSaleDepartmentService.getMstSaleDepartmentName(map);
        return temp;
    }
    
    /**
     * 券商下拉框
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getMstCustomerInfo()
        throws Exception {
        String param = super.getRequest().getParameter("param");
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(param)){
            map.put("brokerId", param);	
        }
        List<SelectOptionDto> temp = mstSaleDepartmentService.getMstCustomerInfo(map);
        return temp;
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
        return mstSaleDepartmentService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
