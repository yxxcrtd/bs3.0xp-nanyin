package com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpDto;
import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.service.QuotaImpService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

@RestController("backQuotaImp001Controller")
@RequestMapping("/quotaimp")
public class QuotaImp001Controller {
	
    @Autowired
    private QuotaImpService quotaImpService;
    
    
    /**
     * 画面查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<QuotaImpDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<QuotaImpDto> pageList = quotaImpService.selectAll(page, length, param);
        return pageList;
    }
    
    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
    	List<ExcelValidDto> checkResult = new ArrayList<>();
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String seqId = param.get("seqId")==null?null:param.get("seqId").toString();
		String impType = param.get("impType")==null?null:param.get("impType").toString();
		if("01".equals(impType)){
			return this.quotaImpService.importTYData(dataList,tempList,seqId,impType);
		}else if("02".equals(impType)){
			return this.quotaImpService.importQYData(dataList,tempList,seqId,impType);
		}else if("03".equals(impType)){
			return this.quotaImpService.importXMData(dataList,tempList,seqId,impType);
		}else if("04".equals(impType)){
			return this.quotaImpService.importABSData(dataList,tempList,seqId,impType);
		}
		checkResult.add(new ExcelValidDto("导入类型", String.valueOf(1),
                "导入类型不存在"));
	    return checkResult;
		
    }

}
