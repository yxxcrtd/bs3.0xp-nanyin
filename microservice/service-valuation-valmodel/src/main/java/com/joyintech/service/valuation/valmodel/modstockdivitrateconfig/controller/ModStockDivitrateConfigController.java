package com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.dto.ModStockDivitrateConfigInfoDto;
import com.joyintech.service.valuation.valmodel.modstockdivitrateconfig.service.ModStockDivitrateConfigService;
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
 * 预期股息增长率Controller
 * 〈功能详细描述〉
 * @author zhangfei
 * @version 1.0 2021年7月1日18:49:15
 * @see ModStockDivitrateConfigController
 * @since 1.0
 */
@RestController("ModStockDivitrateConfigInfo_dtx")
@RequestMapping("/ModStockDivitrateConfigInfo")
public class ModStockDivitrateConfigController {
	
	@Autowired
	private ModStockDivitrateConfigService modStockDivitrateConfigService;
	
	
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
    public Page<ModStockDivitrateConfigInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return modStockDivitrateConfigService.selectForPage(page,length,pageParam);
    }
    
    /**
     * 通过主键查询
     * @param param seqno
     * @return dto
     * @since 1.0
     */
    @PostMapping("findByPrimaryKey")
    public ModStockDivitrateConfigInfoDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
        return modStockDivitrateConfigService.selectByPrimaryKey(param);
    }
    
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody ModStockDivitrateConfigInfoDto dto) throws Exception {
        return modStockDivitrateConfigService.insert(dto);
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
 	  public int update(@RequestBody ModStockDivitrateConfigInfoDto dto) throws Exception {
 	      return modStockDivitrateConfigService.update(dto);
 	  }
    
 	  /**
 	  *
 	  * 删除
 	  * @param dto
 	  * @return
 	  * @throws Exception
 	  */
 	 @Transactional
 	 @DTXTransaction
 	 @PostMapping("delete")
 	 public int delete(@RequestBody ModStockDivitrateConfigInfoDto dto) throws Exception {
 	     return modStockDivitrateConfigService.delete(dto);
 	 }
	
 	 /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<ModStockDivitrateConfigInfoDto> selectForExport(@RequestBody Map<String, Object> map) {
        return modStockDivitrateConfigService.selectForExport(map);
    }
    
    
    /**
     * 校验资产代码是否已维护
     * @param map
     * @return
     */
    @PostMapping("checkByFinprodId")
    public int checkByFinprodId(@RequestBody Map<String, Object> map) {
    	return modStockDivitrateConfigService.checkByFinprodId(map);
    }
}
