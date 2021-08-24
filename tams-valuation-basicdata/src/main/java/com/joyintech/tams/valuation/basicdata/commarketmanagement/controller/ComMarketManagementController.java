package com.joyintech.tams.valuation.basicdata.commarketmanagement.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basicdata.commarketmanagement.dto.ComMarketManagementDto;
import com.joyintech.tams.valuation.basicdata.commarketmanagement.service.ComMarketManagement001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@PageId("ComMarketManagement")
@RequestMapping("/valuation/basicdata/commarketmanagement/ComMarketManagement")
public class ComMarketManagementController extends BaseController{
	@Autowired
    private ComMarketManagement001Service carketManagementService;
	
	/*@Autowired
    private BusiProductRatingService busiProductRatingService;*/
	
  	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {	     
        this.setSelectOptionToModel(model, DictCode.评级类型);
        this.setSelectOptionToModel(model, DictCode.长期短期);
        this.setSelectOptionToModel(model, DictCode.评级机构);	
        this.setSelectOptionToModel(model, DictCode.评级结果);	

        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType"))) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("remType", map.get("remType"));
            String mdate = (String)map.get("mdate");
            if(StringUtil.isNotEmpty(mdate)){
            	model.addAttribute("quaryDate", DateUtil.addDay(mdate,0)+" - "+DateUtil.addDay(mdate,0));
            }
        }
        return super.init(model);
    }
    /**
     * 分页查询
     * 
     * @return Page<MstMarketManagementDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ComMarketManagementDto> selectAllMstMarketManagement()
        throws Exception {
    	
            Page<ComMarketManagementDto> page = carketManagementService.selectForPage(RequestUtil.getPageParam());
            return page;
        
    }
    /**
     * 资产代码的联想文本框事件
     * 
     * @return 查询的资产代码和资产名称列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodName")
    public List<SuggestDto> getFinprodName()
        throws Exception {
    	  Map<String, Object> param = RequestUtil.getPageParam();
          // 获取根据输入值检索到的结果
          List<SuggestDto> temp = carketManagementService.selectFinprodName(param);
          return temp; 
    }
    
   
   /* *//**
     * 获取所有评级结果
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     *//*
    @ResponseBody
    @RequestMapping("/getGradeResult")
    public Page<SelectOptionDto> getGradeResult() {
        return mstMarketManagement001Service.getGradeResult(RequestUtil.getPageParam());
    }*/
    /**
     * 
     * 文件导出
     * <功能详细描述>
     * @return ResponseEntity
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return carketManagementService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
