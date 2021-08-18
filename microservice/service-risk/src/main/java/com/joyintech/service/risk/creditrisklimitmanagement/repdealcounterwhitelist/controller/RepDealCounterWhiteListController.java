package com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.dto.RepDealCounterWhiteListDto;
import com.joyintech.service.risk.creditrisklimitmanagement.repdealcounterwhitelist.service.RepDealCounterWhiteListService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;


@RestController
@RequestMapping("/repdealcounterwhitelist")
public class RepDealCounterWhiteListController {
	
    @Autowired
    private RepDealCounterWhiteListService repDealCounterWhiteListService;
    
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
    public Page<RepDealCounterWhiteListDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<RepDealCounterWhiteListDto> pageList = repDealCounterWhiteListService.selectAll(page, length, param);
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
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String seqId = param.get("seqId")==null?null:param.get("seqId").toString();
        return this.repDealCounterWhiteListService.importData(dataList,tempList,seqId);
    }

}
