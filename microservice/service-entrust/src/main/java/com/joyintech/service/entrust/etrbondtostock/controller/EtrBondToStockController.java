package com.joyintech.service.entrust.etrbondtostock.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.service.entrust.etrbondtostock.service.EtrBondToStockService;


/**
 * 可转债转股委托
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStockController
 * @since 1.0
 */
@RestController("EtrBondToStockCtr_dtx")
@RequestMapping("/EtrBondToStock")
public class EtrBondToStockController {
	
	@Autowired
    private EtrBondToStockService etrBondToStockService;
	
    /**
     * 
     * 可转债转股委托分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrBondToStockDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrBondToStockService.selectForPage(page,length,param);
    }
    
    /**
     * 
     * 持仓分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("findOtherPage/{page}/{length}")
    public Page<EtrBondToStockDto> selectOtherPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
    	Page<EtrBondToStockDto> pagelist= etrBondToStockService.selectOtherPage(page,length,param);
    	return pagelist;
    }
    
    /**
     * 撤回委托 
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recall")
    public boolean recallDelegate(@RequestBody EtrBondToStockDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrBondToStockService.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrBondToStockService.closeDelegate(entrustId, closeWay);
    }
    
    
    /**
     * 判断是否证券账户
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("yesOrNo")
    public boolean getYesOrNo(String secAcctId) {
        return etrBondToStockService.getYesOrNo(secAcctId);
    }
    
    
    /**
    *
    * 转债代码联想下拉框
    * <功能详细描述>
    * @param map 输入参数
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
   @PostMapping("/getFinprodId/{page}/{length}")
   public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> map,@PathVariable("page") int page,
                                                         @PathVariable("length") int length){
       return etrBondToStockService.getFinprodId(page,length,map);
   }
   
   /**
    * 
    * 持仓查询
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("getPositionInfo")
   public Map<String, Object> getPositionInfo(@RequestBody Map<String, Object> param){
       return  etrBondToStockService.getPositionInfo(param);
   }
   
   /**
   *
   * 正股代码联想下拉框
   * <功能详细描述>
   * @param map 输入参数
   * @return List<SelectOptionDto>
   * @see
   * @since 1.0
   */
  @PostMapping("/getShareId/{page}/{length}")
  public Page<SelectOptionDto> getShareId(@RequestBody Map<String, Object> map,@PathVariable("page") int page,
                                                        @PathVariable("length") int length){
      return etrBondToStockService.getShareId(page,length,map);
  }
    
    /**
    *
    * 新建页面转债代码联想下拉框
    * <功能详细描述>
    * @param map 输入参数
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
   @PostMapping("/getFinprodIdForAdd/{page}/{length}")
   public Page<SelectOptionDto> getFinprodIdForAdd(@RequestBody Map<String, Object> map,@PathVariable("page") int page,
                                                         @PathVariable("length") int length){
       return etrBondToStockService.getFinprodIdForAdd(page,length,map);
   }
    
    /**
     * 
     * 工作流完成方法
     * <功能详细描述>
     * @param map
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrBondToStockDto dto) throws IllegalAccessException, InvocationTargetException{
    	etrBondToStockService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrBondToStockDto dto){
    	etrBondToStockService.doInsert(dto, dto.getOperateType());
    }
    /**
     * 
     * 通过主键查询数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectByKey")
    public EtrBondToStockDto selectByKey(String entrustId,String sysDate) {
        return etrBondToStockService.selectByKey(entrustId,sysDate);
    }
    /**
     * 
     * 操作前校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrBondToStockDto dto){
        return etrBondToStockService.check(dto);
    }
    
    /**
     * 获取金融产品信息
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getCounterIdByFinprodId")
    public String getCounterIdByFinprodId(@RequestBody String finprodId) {
        return etrBondToStockService.getCounterIdByFinprodId(finprodId);
    }
    
}
