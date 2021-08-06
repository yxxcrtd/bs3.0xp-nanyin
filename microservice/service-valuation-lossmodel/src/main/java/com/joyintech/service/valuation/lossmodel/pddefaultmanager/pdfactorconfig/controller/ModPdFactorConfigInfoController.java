package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorConfigInfoDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.ModPdFactorWeightConfigDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.service.ModPdFactorConfigInfoService;
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
 * PD前瞻因子维护Controller
 * 〈功能详细描述〉
 * @author zhangfei
 * @version 1.0 2021年6月24日
 * @see ModPdFactorConfigInfoController
 * @since 1.0
 */
@RestController("ModPdFactorConfigInfo_dtx")
@RequestMapping("/ModPdFactorConfigInfo")
public class ModPdFactorConfigInfoController {
	
	@Autowired
	private ModPdFactorConfigInfoService modPdFactorConfigInfoService;
	
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
    public Page<ModPdFactorConfigInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return modPdFactorConfigInfoService.selectForPage(page,length,pageParam);
    }
	
    /**
    *
    * @param map
    * @return
    */
   @PostMapping("selectByYearAndQuarter")
   public List<ModPdFactorConfigInfoDto> selectByYearAndQuarter(@RequestBody Map<String, Object> map) {
       return modPdFactorConfigInfoService.selectByYearAndQuarter(map);
   }
	
   
   /**
    * 通过主键查询
    * @param param seqno
    * @return dto
    * @since 1.0
    */
   @PostMapping("findByPrimaryKey")
   public ModPdFactorConfigInfoDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
       return modPdFactorConfigInfoService.selectByPrimaryKey(param);
   }
   
   
   
   /**
    * 查询最新权重信息数据
    * @param param seqno
    * @return dto
    * @since 1.0
    */
   @PostMapping("findLastWeightInfo")
   public ModPdFactorWeightConfigDto findLastWeightInfo(@RequestBody Map<String, Object> param) {
       return modPdFactorConfigInfoService.findLastWeightInfo(param);
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
   public int insert(@RequestBody ModPdFactorConfigInfoDto dto) throws Exception {
       return modPdFactorConfigInfoService.insert(dto);
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
	  public int update(@RequestBody ModPdFactorConfigInfoDto dto) throws Exception {
	      return modPdFactorConfigInfoService.update(dto);
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
	 public int delete(@RequestBody ModPdFactorConfigInfoDto dto) throws Exception {
	     return modPdFactorConfigInfoService.delete(dto);
	 }
   
	 
	 /**
	    * 插入权重配置数据
	    * @param dto
	    * @return
	    * @throws Exception
	    */
	   @Transactional
	   @DTXTransaction
	   @PostMapping("insertWeight")
	   public int insertWeight(@RequestBody ModPdFactorWeightConfigDto dto) throws Exception {
	       return modPdFactorConfigInfoService.insertWeight(dto);
	   }
	 
	 
	 
	 /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<ModPdFactorConfigInfoDto> selectForExport(@RequestBody Map<String, Object> map) {
        return modPdFactorConfigInfoService.selectForExport(map);
    }
	 
    /**
     * 校验前瞻因子是否被使用过
     * @param map
     * @return
     */
    @PostMapping("checkBySeqNo")
    public int checkBySeqNo(@RequestBody Map<String, Object> map) {
    	return modPdFactorConfigInfoService.checkBySeqNo(map);
    }
   
}
