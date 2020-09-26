/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.controller;

import java.util.*;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle002Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service.ValAccountHandle001Service;
import com.joyintech.tams.valuation.system.batchstart.service.ValBatchStart001Service;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValAccountHandle001Contoller
 * @since 1.0
 */

@Controller
@PageId("ValAccountHandle001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valaccounthandle/ValAccountHandle001")
public class ValAccountHandle001Contoller extends BaseController {
	
	@Autowired
	private ValAccountHandle001Service valAccountHandle001Service;
    @Autowired
    private ValBatchStart001Service sysBatchStart001Service;
    @Autowired
    private BaseCommonService baseCommonService;
	
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
        model.addAttribute("batchTaskNums", this.getBatchTaskid().size());
        this.setSelectOptionToModel(model, DictCode.批处理账套类型);
        // 是否显示查看数据字典明细按钮，多级法人改造 add by hxy 20200623
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));

        return super.init(model);
    }
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBatchTaskid")
    @ResponseBody
    public List<BatchTaskDto> getBatchTaskid() {
        Map<String,Object> map=new HashMap<String,Object>();
        return valAccountHandle001Service.getBatchTaskid(map);
    }

    
    /**
     * 套账下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBookSetId")
    @ResponseBody
    public Page<SelectOptionDto> getBookSetId() {
    	HashMap<String, Object> map = (HashMap<String, Object>) RequestUtil.getPageParam();
    	String booksetType = String.valueOf(map.get("booksets"));
    	String search = null;
    	if (map.containsKey("search")) {
    		search = String.valueOf(map.get("search"));
    	} else {
    		map.put("search", search);
    	}
    	if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(booksetType)) {
    		return valAccountHandle001Service.getBooksetIdByGrp(map);
    	} else {
    		/**add by wujie on 20190328 start**/
    		map.put("acctSubjectTypeNo", DictCodeListExt1.金融产品类型_通道.getValue());
    		/**add by wujie on 20190328 end**/
    		return valAccountHandle001Service.getBookSetId2(map);
    	}
    }


    @RequestMapping("/findAllTask")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public List<TreeNode> findAllTask(){
    	Map<String,Object> map = RequestUtil.getPageParam();
    	map.put("taskGrpType", "task");
    	List<TreeNode> treeList = valAccountHandle001Service.getTreeNodeList(map);
    	return treeList;
    }
    @RequestMapping("checkBokkSetDate")
    @ResponseBody
    public String checkBokkSetDate(){
        Map<String,Object> map = RequestUtil.getPageParam();
        String prodGroupType = String.valueOf(map.get("prodGroupType"));
        String booksetId = (String)map.get("booksetId");
        String[] booksetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)) {
        	if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(prodGroupType)) {
        		// 账套组
        		String[] prodGroupSeqNos = StringUtil.split(booksetId, ",");
        		booksetIdArr = valAccountHandle001Service.findAllProdbyGroup(prodGroupSeqNos).toArray(new String[0]);
        	} else {
        		// 账套
        		booksetIdArr = booksetId.split(",");
        	}
        }
        map.put("booksetIdArr", booksetIdArr);
        String msg=valAccountHandle001Service.checkBokkSetDate(map);
        return msg;
    }
    @RequestMapping("checkExecute")
    @ResponseBody
    public int checkExecute(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return valAccountHandle001Service.checkExecute(map);
    }

    /**
     * 获取账套到期日
     * @return
     */
    @RequestMapping("getMdateForBookSet")
    @ResponseBody
    public List<String> getMdateForBookSet(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return valAccountHandle001Service.getMdateForBookSet(map);
    }

    @RequestMapping("/findLogPage")
    @ResponseBody
    public List<ValAccountHandle001Dto> findLogPage() throws Exception{
    	Map<String,Object> map =RequestUtil.getPageParam();

        return valAccountHandle001Service.findLogPage(map);
    }
    @RequestMapping(value = "executeBatch")
    @ResponseBody
    public AjaxResult executeBatch(String selectedBatchGroup, String selectedProductGroup,
                                   String startDate, String endDate, String batchGroupType, String prodGroupType) {
        sysBatchStart001Service.executeBatch();
        return new AjaxResult("批处理启动成功，可通过监控页面查看");
    }
    
    @ResponseBody
    @RequestMapping("findPage")
    public List<ValAccountHandle002Dto> selectBalanceStatement()
        throws Exception {
    	List<ValAccountHandle002Dto> list = new ArrayList<ValAccountHandle002Dto>();
    	Map<String,Object> map = RequestUtil.getPageParam();
    	List<String> prodList=new ArrayList<String>();
    	//获取前台参数
    	String searchCheckLog = String.valueOf(map.get("searchCheckLog"));
    	String booksetId=(String)map.get("booksetId");
    	String prodGroupType = RequestUtil.getParam("prodGroupType");
    	String[] booksetIdArray = null;
    	
        if(StringUtil.isNotEmpty(booksetId)) {
        	if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)) {
        		booksetIdArray = booksetId.split(",");
        		if(booksetIdArray != null){
        			prodList = Arrays.asList(booksetIdArray);
                }
        	}
        	if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(prodGroupType)) {
        		String[] prodGroupSeqNos = StringUtil.split(booksetId, ",");
        		prodList = valAccountHandle001Service.findAllProdbyGroup(prodGroupSeqNos);
        	}
        }
        map.put("prodList", prodList);
        if (DictCodeList.是否_否.getValue().equals(searchCheckLog)) {
        	list = valAccountHandle001Service.checkBokPosition(map);
        } else {
        	list = valAccountHandle001Service.selectCheckLog(map);
        }
        return list;
    }
    @RequestMapping("isCheckBefore")
    @ResponseBody
    public int bokCheckBefore(){
    	Map<String,Object> map =RequestUtil.getPageParam();
        return valAccountHandle001Service.bokCheckBefore(map);
    }
    @RequestMapping("geyCheckSysValue")
    @ResponseBody
    public int geyCheckSysValue(){
    	int res=0;
    	//获取系统参数
    	String checkValue = CacheUtil.getSysParam("PRE_BATCH_CHECK");
    	if("OFF".equals(checkValue)){
    		res=0;
    	}else if("ON".equals(checkValue)){
    		res=1;
    	}
        return res;
    }
}