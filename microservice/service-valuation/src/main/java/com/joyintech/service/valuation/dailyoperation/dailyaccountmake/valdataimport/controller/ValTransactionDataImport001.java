/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandleService;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valdataimport.service.ValPublicDataImport001Service;
import com.joyintech.service.valuation.interf.inertfStartUp.service.ValInertfStartUp001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.dto.ClassForStringLIst;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValTransactionDataImport001
 * @since 1.0
 */

@RestController(value="serviceValTransactionDataImport001")
@RequestMapping("/ValTransactionDataImport001")
public class ValTransactionDataImport001 extends BaseController {
	
	//代表交易查询
	private static String InterfFlag = "InterfDeal";
	
	@Autowired
	private ValPublicDataImport001Service valPublicDataImport001Service;
	@Autowired
	private ValInertfStartUp001Service valInertfStartUp001Service;
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    @Autowired
    private BaseCommonService baseCommonService;
    
    @Autowired
    private InterfCommonService interfCommonService;
    
	@Autowired
	private ValAccountHandleService valAccountHandleService;
    
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
        String sysDate=baseCommonService.getSysDateString();
        if(sysDate==null||"".equals(sysDate)){
            sysDate=DateUtil.format(new Date());
        }
        model.addAttribute("sysDate", sysDate);
        this.setSelectOptionToModel(model, DictCode.批处理账套类型);
        return super.init(model);
    }
    
    
    @PostMapping("/findBooksetDate")
    public List<ValBooksetDateSwitch001Dto> findBooksetDate(@RequestBody Map<String, Object> paramMap) {
    	return valAccountHandleService.findBooksetDate(paramMap);
    	
    }
    
//    @RequestMapping("/getInterGroup")
//    @ResponseBody
//    public List<TreeNode> getInterGroup(){
//    	Map<String,Object> map = RequestUtil.getPageParam();
//    	map.put("batchName", getParam("TmpCName"));
//    	map.put("taskGrpType", InterfFlag);
//    	List<TreeNode> treeList = valAccountHandle001Service.getTreeNodeList(map);
//    	return treeList;
//    }
    
//    @RequestMapping("/getInterGroupCode")
//    @ResponseBody
//    public List<SelectOptionDto> getInterGroupCode(){
//    	Map<String,Object> map = new HashMap<String,Object>();
//    	map.put("TmpCName", getParam("TmpCName"));
//    	List<SelectOptionDto>  list = valPublicDataImport001Service.getInterGroupCode(map);
//    	return list;
//    }
    
    
//    @RequestMapping("/findLogPage")
//    @ResponseBody
//    public List<ValPublicDataImport001LogDto> findLogPage(){
//    	List<ValPublicDataImport001LogDto>  list = new ArrayList<>();
//    	Map<String,Object> map = RequestUtil.getPageParam();
//    	//map.put("createDate", DateUtil.parse("20180815"));
//    	map.put("status", this.getParam("status"));
//    	map.put("execTaskId", this.getParam("execTaskId"));
//    	if(StringUtil.isNotEmpty(map.get("exDate"))){
//        	map.put("createDate", new Date(Long.valueOf(map.get("exDate").toString())));
//    		list = valPublicDataImport001Service.getInterActionLog(map);
//    	}
//    	return list;
//    }
    
    
//    @RequestMapping("/stopInterf")
//    @ResponseBody
//    public int stopInterf(){
//    	Map<String, Object> map = new HashMap<>();
//    	return valPublicDataImport001Service.stopInterLog(map);
//    }
    
    
    /**
     * 接口启动
     * 
     * @throws IOException
     * @see
     * @since 1.0
     */
    @PostMapping("/findAllProduct")
    public List<SysBatchGrpPrdlink> findAllProduct(@RequestBody Map<String, Object> params) {
            	return  valAccountHandle001Service.findAllProduct(params);
    }
  
    @PostMapping("/getBooksetIdbyLayeringInfo")
    public ClassForStringLIst getBooksetIdbyLayeringInfo(@RequestBody List<String> bookSetIds) {
    	ClassForStringLIst dto = new ClassForStringLIst();
    	dto.setPara(valAccountHandle001Service.getBooksetIdbyLayeringInfo(bookSetIds));
        return dto;
    }
    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getProdId/{page}/{length}")
    public Page<SelectOptionDto> getProdId(@RequestBody Map<String, Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) {
        return valAccountHandle001Service.getProdId(page,length,map);
    }

    /**
     * 查询账套组
     * @return 套账日期
     * @throws Exception
     * @see
     */
    @PostMapping("/findBooksetGroup/{page}/{length}")
    public Page<SelectOptionDto> findBooksetGroup(@RequestBody Map<String, Object> map,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valAccountHandle001Service.findBooksetGroup(page,length,map);
    }
    
}