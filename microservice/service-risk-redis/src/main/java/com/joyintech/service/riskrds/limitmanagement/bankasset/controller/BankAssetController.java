package com.joyintech.service.riskrds.limitmanagement.bankasset.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.riskrds.limitmanagement.bankasset.dto.BankAssetDto;
import com.joyintech.service.riskrds.limitmanagement.bankasset.service.BankAssetService;


/**
 * 商业银行净资产维护
 * @author libaisong
 * @version 1.0 2019年11月19日
 * @see BankAssetController
 * @since 1.0
 */
@RestController("backBankAssetController")
@RequestMapping("/BankAsset")
public class BankAssetController {

	@Autowired
	private BankAssetService bankAssetService;
	
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<BankAssetDto> selectAll(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return bankAssetService.selectForPage(page,length,param);
    }
    
    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectCustomerId/{page}/{length}")
    public Page<SelectOptionDto> selectCustomerId(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
        throws Exception {
        return bankAssetService.selectCustomerId(page, length, param);
    }
    
    @PostMapping("selectBankAsset")
    public BankAssetDto selectBankAsset(@RequestBody Map<String, Object> map){
    	BankAssetDto dto = bankAssetService.selectBankAsset(map);
    	return dto;
    }
    
    @PostMapping("selectMaxBankAsset")
    public Date selectMaxBankAsset(@RequestBody Map<String, Object> map){
    	Date maxEffDate = bankAssetService.selectMaxBankAsset(map);
    	return maxEffDate;
    }
    
    /**
    * @param map 查询参数
    * @return 资产名称和资产代码列表
    * @see
    * @since 1.0
    */
   @PostMapping("getCustomerIdList")
   public List<SuggestDto> getCustomerIdList(@RequestBody Map<String, Object> map) {
       return bankAssetService.getCustomerIdList(map);
   }
   
   /**
   *
   * 导出数据查询
   *
   * @param map
   * @return
   */
  @PostMapping("selectForExport")
  public List<BankAssetDto> selectForExport(@RequestBody Map<String, Object> map) {
      return bankAssetService.selectForExport(map);
  }
    
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     */

    @PostMapping("insert")
    public int insert(@RequestBody BankAssetDto dto) throws Exception {
        return bankAssetService.insert(dto);
    }
    
    /**
     * 更新
     * @param dto
     * @return
     * @throws Exception
     */

    @PostMapping("update")
    public int update(@RequestBody BankAssetDto dto) throws Exception {
        return bankAssetService.update(dto);
    }
    
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */

    @PostMapping("delete")
    public int delete(@RequestBody BankAssetDto dto) throws Exception {
        return bankAssetService.delete(dto);
    }
    
    @PostMapping("selectBankAssetByInsitituteCode")
    public List<BankAssetDto> selectBankAssetByInsitituteCode(@RequestBody String insitituteCode){
    	return bankAssetService.selectBankAssetByInsitituteCode(insitituteCode);
    }
}
