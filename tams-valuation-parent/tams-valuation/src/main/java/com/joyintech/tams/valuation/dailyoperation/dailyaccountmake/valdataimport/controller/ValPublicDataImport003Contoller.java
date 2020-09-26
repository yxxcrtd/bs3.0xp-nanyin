/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto.ValInterflogExt;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.service.ValPublicDataImport001Service;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValPublicDataImport003Contoller
 * @since 1.0
 */

@Controller
@PageId("ValPublicDataImport003")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport003")
public class ValPublicDataImport003Contoller extends BaseController {
	
	
	@Autowired
	private ValPublicDataImport001Service valPublicDataImport001Service;
    
    
    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        model.addAttribute("parentSeqno", getParam("parentSeqno"));
        model.addAttribute("tmpName", getParam("tmpName"));
        /**add by wujie on 20190114 start**/
        String logStatus = getParam("logStatus");
        if("09".equals(logStatus)){
        	logStatus = "N";
        }
        else if("02".equals(logStatus)){
        	logStatus = "Y";
        }
        model.addAttribute("logStatus", logStatus);
        /**add by wujie on 20190114 end**/
        return super.init(model);
    }

    /**
     * 获取日志详情
     *
     * @return 记账手工凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("searchList")
    @ResponseBody
    public List<ValInterflogExt> searchList() {
        // 日志seqno
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("parentSeqno", getParam("parentSeqno"));
    	map.put("tmpName", getParam("tmpName"));
    	/**add by wujie on 20190114 start**/
    	map.put("logStatus", getParam("logStatus"));
    	/**add by wujie on 20190114 end**/
    	List<ValInterflogExt> result = valPublicDataImport001Service.getValInterflogDetail(map);
        return result;
    }
    
  
}