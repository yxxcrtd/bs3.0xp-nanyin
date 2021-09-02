package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.dto.ModPdFactorKpmgConfigInfoDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorkpmgconfig.service.ModPdFactorKpmgConfigInfoService;
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
 * @see ModPdFactorKpmgConfigInfoController
 * @since 1.0
 */
@RestController("ModPdFactorKpmgConfigInfo_dtx")
@RequestMapping("/ModPdFactorKpmgConfigInfo")
public class ModPdFactorKpmgConfigInfoController {
	
	@Autowired
	private ModPdFactorKpmgConfigInfoService modPdFactorKpmgConfigInfoService;
	
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
    public Page<ModPdFactorKpmgConfigInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return modPdFactorKpmgConfigInfoService.selectForPage(page,length,pageParam);
    }
	
    /**
    *
    * @param map
    * @return
    */
   @PostMapping("selectByYear")
   public List<ModPdFactorKpmgConfigInfoDto> selectByYear(@RequestBody Map<String, Object> map) {
       return modPdFactorKpmgConfigInfoService.selectByYear(map);
   }
	
   
   /**
    * 通过主键查询
    * @param param seqno
    * @return dto
    * @since 1.0
    */
   @PostMapping("findByPrimaryKey")
   public ModPdFactorKpmgConfigInfoDto selectByPrimaryKey(@RequestBody Map<String, Object> param) {
       return modPdFactorKpmgConfigInfoService.selectByPrimaryKey(param);
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
   public int insert(@RequestBody ModPdFactorKpmgConfigInfoDto dto) throws Exception {
       return modPdFactorKpmgConfigInfoService.insert(dto);
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
	  public int update(@RequestBody ModPdFactorKpmgConfigInfoDto dto) throws Exception {
	      return modPdFactorKpmgConfigInfoService.update(dto);
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
	 public int delete(@RequestBody ModPdFactorKpmgConfigInfoDto dto) throws Exception {
	     return modPdFactorKpmgConfigInfoService.delete(dto);
	 }
   
	 

	 
	 
	 
	 /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<ModPdFactorKpmgConfigInfoDto> selectForExport(@RequestBody Map<String, Object> map) {
        return modPdFactorKpmgConfigInfoService.selectForExport(map);
    }
	 
    /**
     * 校验前瞻因子是否被使用过
     * @param map
     * @return
     */
    @PostMapping("checkBySeqNo")
    public int checkBySeqNo(@RequestBody Map<String, Object> map) {
    	return modPdFactorKpmgConfigInfoService.checkBySeqNo(map);
    }
   
}
