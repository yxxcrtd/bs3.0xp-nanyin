package com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto.RskPortAutoRelatePoint002Dto;
import com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto.RskPortAutoRelatePointDeleteDto;
import com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto.RskPortAutoRelatePointDto;
import com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.service.RskPortAutoRelatePointService;
import com.joyintech.tams.common.base.entity.RskRuleIndicator;
import com.joyintech.tams.common.base.entity.RskRuleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("backRskPortAutoRelatePointController_dtx")
@RequestMapping("/RskPortAutoRelatePoint")
public class RskPortAutoRelatePointController {
	@Autowired
	private RskPortAutoRelatePointService rskPortAutoRelatePointService;
	/**
	 * 查询指标名称
	 * @param param
	 * @return
	 */
	@PostMapping("/getRuleName")
	public List<RskPortAutoRelatePointDto> getRuleName(@RequestBody Map<String,Object> param){
		return rskPortAutoRelatePointService.getRuleName(param);
	}
	/**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskPortAutoRelatePointDto> selectForPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskPortAutoRelatePointService.selectForPage(page, length, param);
    }
    @PostMapping("/selectAllRule/{page}/{length}")
    public Page<RskPortAutoRelatePoint002Dto> selectAllRule(@RequestBody Map<String, Object> param,
                                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskPortAutoRelatePointService.selectAllRule(page, length, param);
    }
    @PostMapping("/findAllInt/{page}/{length}")
    public Page<RskPortAutoRelatePointDto> findAllInt(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskPortAutoRelatePointService.findAllInt(page, length, param);
    }
    /**
     * 插入规则
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertRule")
    public int insertRule(@RequestBody RskPortAutoRelatePoint002Dto dto){
    	return rskPortAutoRelatePointService.insertRule(dto);
    }
    /**
     * 插入规则
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/updateRule")
    public int updateRule(@RequestBody RskPortAutoRelatePoint002Dto dto){
    	return rskPortAutoRelatePointService.updateRule(dto);
    }
    /**
     * 插入规则
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteRule")
    public int deleteRule(@RequestBody RskPortAutoRelatePoint002Dto dto){
    	return rskPortAutoRelatePointService.deleteRule(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public int delete(@RequestBody RskPortAutoRelatePointDeleteDto dto){
    	return rskPortAutoRelatePointService.delete(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/insert")
    public int insert(@RequestBody RskPortAutoRelatePointDto dto){
    	return rskPortAutoRelatePointService.insert(dto);
    }
    @PostMapping("/isExist")
    public boolean isExist(@RequestBody RskPortAutoRelatePoint002Dto dto){
    	return rskPortAutoRelatePointService.isExist(dto);
    }
    
    @GetMapping("/isAreadyHaveInd")
    public boolean isAreadyHaveInd(String ruleId){
    	return rskPortAutoRelatePointService.isAreadyHaveInd(ruleId);
    }

    /**
     * 规则信息获取
     * @param map 规则代码
     */
    @PostMapping("getBussinessEntityByRuleId")
    public RskRuleInfo getBussinessEntityByRuleId(@RequestBody Map<String, Object> map) {
        return rskPortAutoRelatePointService.getBussinessEntityByRuleId(map);
    }

    /**
     * 规则指标信息获取
     * @param map 规则代码与指标代码
     */
    @PostMapping("getRelInfoByRuleId")
    public RskRuleIndicator getRelInfoByRuleId(@RequestBody Map<String, Object> map) {
        return rskPortAutoRelatePointService.getRelInfoByRuleId(map);
    }
}
