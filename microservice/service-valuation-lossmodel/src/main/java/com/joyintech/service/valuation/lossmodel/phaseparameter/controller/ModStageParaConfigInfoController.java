package com.joyintech.service.valuation.lossmodel.phaseparameter.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.phaseparameter.dto.ModStageParaConfigInfoDto;
import com.joyintech.service.valuation.lossmodel.phaseparameter.service.ModStageParaConfigInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
/**
 * 减值阶段参数管理Controller
 * 〈功能详细描述〉
 * @author zhangfei
 * @version 1.0 2021年6月28日
 * @see ModStageParaConfigInfoController
 * @since 1.0
 */
@RestController("ModStageParaConfigInfo_dtx")
@RequestMapping("/ModStageParaConfigInfo")
public class ModStageParaConfigInfoController {
	
	@Autowired
	private ModStageParaConfigInfoService modStageParaConfigInfoService;
	
	
	/**
     * 
     * 分页查询
     * <功能详细描述>
     * @param pageParam
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ModStageParaConfigInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return modStageParaConfigInfoService.selectForPage(page,length,pageParam);
    }
	
    
    /**
     * 通过主键查询
     * @param param seqno
     * @return dto
     * @since 1.0
     */
    @PostMapping("findByPrimaryKey")
    public ModStageParaConfigInfoDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
        return modStageParaConfigInfoService.selectByPrimaryKey(param);
    }
    
    /**
     * 根据减值阶段和要素查询参数值
     */
    @PostMapping("findByStageAndElements")
    public ModStageParaConfigInfoDto selectByStageAndElements(@RequestBody Map<String, Object> param) {
        return modStageParaConfigInfoService.selectByStageAndElements(param);
    }
    
    
    /**
	   * 更新数据
	  * @param dto
	  * @return
	  * @throws Exception
	  */
	 @Transactional
	 @DTXTransaction
	 @PostMapping("update")
	 public int update(@RequestBody ModStageParaConfigInfoDto dto) throws Exception {
	     return modStageParaConfigInfoService.update(dto);
	 }
    
	 
	 /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<ModStageParaConfigInfoDto> selectForExport(@RequestBody Map<String, Object> map) {
        return modStageParaConfigInfoService.selectForExport(map);
    }
}
