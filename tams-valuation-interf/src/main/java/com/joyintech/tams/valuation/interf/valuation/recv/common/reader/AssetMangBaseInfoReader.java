/*
 * 文件名：资管接口批次信息、单文件check基础信息读取.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.common.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.AssetMangBaseInfoService;
import com.joyintech.tams.valuation.interf.core.InterfBaseReader;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;

@Service
@Scope("prototype")
public class AssetMangBaseInfoReader extends InterfBaseReader<TemplateOrderInfoReaderResult> {


    // @Autowired
    // private ProfitAndLossAccountingMapper mapper;
    @Autowired
    private AssetMangBaseInfoService service;


    @SuppressWarnings("unchecked")
	@Override
    public List<TemplateOrderInfoReaderResult> createList() {

    	InterfExecuteInfo executeInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
    	List<String> tempList = (List<String>)getParams().get(InterfCommonConstant.接口_运行接口列表);
    	
    	Map<String, Object> paramForService = new HashMap<>();
        paramForService.put("orderNo", executeInfo.getOrderNo());
        paramForService.put(InterfCommonConstant.接口_运行接口列表, tempList);
        
        // 获取reader返回值
        List<TemplateOrderInfoReaderResult> list = service.execute(paramForService);
        List<TemplateOrderInfoReaderResult> result = new ArrayList<>();
        // 设置文件完整目录
        for (TemplateOrderInfoReaderResult temp : list) {
            String filePath = "";
            if (StringUtil.isNotEmpty(temp.getFilePath())) {
                filePath = executeInfo.getLocalRootPath() + File.separator
                           + DateUtil.format(executeInfo.getInterfDate(),
                               DateUtil.DEFAULT_OTHER_FORMAT)
                           + File.separator + temp.getFilePath();
            }
            else {
                filePath = executeInfo.getLocalRootPath()  + File.separator
                    + executeInfo.getSystemId() + File.separator + DateUtil.format(
                    executeInfo.getInterfDate(), DateUtil.DEFAULT_OTHER_FORMAT)+
                        (StringUtil.isNotEmpty(this.getParams().get("INSTITUTION_CODE"))?File.separator+(String)this.getParams().get("INSTITUTION_CODE"):"");
            }
            temp.setFilePath(filePath);
            // 返回模板添加接口日
            temp.setResovleDate(executeInfo.getInterfDate());
            //设置日志编号
            temp.setParentSeqno(executeInfo.getActionLog().getSeqno());
            result.add(temp);
            
        }
        return result;
    }
}
