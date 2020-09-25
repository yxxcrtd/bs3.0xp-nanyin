
package com.joyintech.service.chb3.submitmaintenance.investorsholdinfo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfo;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo002Service;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;


/**
 * 鎶曡祫鑰呮寔鏈変俊鎭湇鍔�
 * 銆堝姛鑳借缁嗘弿杩般��
 * @author zhangzhongliang
 * @version 1.0 2019骞�4鏈�19鏃�
 * @see InvestorsHoldInfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/InvestorsHoldInfo")
public class InvestorsHoldInfoController {
    /**
     * 涓氬姟閫昏緫Service
     */
    @Autowired
    private InvestorsHoldInfo001Service investorsHoldInfo001Service;
    /**
     * 閫昏緫涓氬姟澶勭悊bean
     */
    @Autowired
    private InvestorsHoldInfo002Service investorsHoldInfo002Service;
    @Autowired
    private ProRegistCodeManage001Service proRegistCodeManage001Service;
    
    
    
    /**
     *
     * 涓氬姟鐢婚潰鏌ヨ浜嬩欢
     * <鍔熻兘璇︾粏鎻忚堪>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<InvestorsHoldInfo001Dto> selectForPage(@RequestBody Map<String, Object> param) {
        Page<InvestorsHoldInfo001Dto> page = investorsHoldInfo001Service.selectForPage(param);
        return page;
    }
    /**
     *
     * 涓氬姟鐢婚潰瀵煎嚭鏌ヨ
     * <鍔熻兘璇︾粏鎻忚堪>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectAllList")
    public List<InvestorsHoldInfo001Dto> selectAllList(@RequestBody Map<String, Object> param) {
          return investorsHoldInfo001Service.selectAllList(param);
    }


    /**
     *
     * 鍒犻櫎
     * <鍔熻兘璇︾粏鎻忚堪>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("delete")
    public void delete(@RequestBody InvestorsHoldInfo001Dto dto){
        investorsHoldInfo001Service.delete(dto);
    }

    /**
     * 瀵煎叆鏁版嵁鏂囦欢
     *
     * @param fileName 瀵煎叆鐨勬枃浠跺悕
     * @return 瀵煎叆缁撴灉锛屽寘鍚獙璇佸唴瀹癸紝濡傛灉楠岃瘉澶辫触锛岃繑鍥瀖essage
     */
    @SuppressWarnings("unchecked")
   	@PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) throws Exception {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String bankCode =  null!=param.get("bankCode")?param.get("bankCode").toString():"";
    	String deptCode =  null!=param.get("deptCode")?param.get("deptCode").toString():"";
    	String orgCode =  null!=param.get("orgCode")?param.get("orgCode").toString():"";
        return this.investorsHoldInfo001Service.importData(dataList,tempList,bankCode,deptCode,orgCode);
    }
    /**
     * 瀵煎叆鏁版嵁鏂囦欢涔嬪墠妫�鏌ユ暟鎹�
     *
     * @param fileName 瀵煎叆鐨勬枃浠跺悕
     * @return 澶勭悊缁撴灉
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param){
    	List<List<String>> dataList = (List<List<String>>) param.get("dataList");
    	String bankCode =  null!=param.get("bankCode")?param.get("bankCode").toString():"";
    	String deptCode =  null!=param.get("deptCode")?param.get("deptCode").toString():"";
    	String orgCode =  null!=param.get("orgCode")?param.get("orgCode").toString():"";
        return investorsHoldInfo001Service.checkConfirmMsgForImportData(dataList,bankCode,deptCode,orgCode);
    }
    
    
    
    /**
     * 瀵煎叆浠ｉ攢鏁版嵁鏂囦欢
     *
     * @param fileName 瀵煎叆鐨勬枃浠跺悕
     * @return 瀵煎叆缁撴灉锛屽寘鍚獙璇佸唴瀹癸紝濡傛灉楠岃瘉澶辫触锛岃繑鍥瀖essage
     */
    @SuppressWarnings("unchecked")
   	@PostMapping("importChbData")
    public List<ExcelValidDto> importChbData(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String bankCode =  null!=param.get("bankCode")?param.get("bankCode").toString():"";
    	String deptCode =  null!=param.get("deptCode")?param.get("deptCode").toString():"";
    	String orgCode =  null!=param.get("orgCode")?param.get("orgCode").toString():"";
        return this.investorsHoldInfo001Service.importChbData(dataList,tempList,bankCode,deptCode,orgCode);
    }
    
    
    /**
     *瀵煎叆浠ｉ攢鏁版嵁鏂囦欢涔嬪墠妫�鏌ユ暟鎹�
     *
     * @param fileName 瀵煎叆鐨勬枃浠跺悕
     * @return 澶勭悊缁撴灉
     */
    @SuppressWarnings("unchecked")
   	@PostMapping("checkConfirmMsgForChbImportData")
    public AjaxResult checkConfirmMsgForChbImportData(@RequestBody Map<String, Object> param) {
		List<List<String>> dataList = (List<List<String>>) param.get("dataList");
		List<List<String>> tempList = (List<List<String>>) param.get("tempList");
		String bankCode =  null!=param.get("bankCode")?param.get("bankCode").toString():"";
    	String deptCode =  null!=param.get("deptCode")?param.get("deptCode").toString():"";
    	String orgCode =  null!=param.get("orgCode")?param.get("orgCode").toString():"";
        return investorsHoldInfo001Service.checkConfirmMsgForChbImportData(dataList,tempList,bankCode,deptCode,orgCode);
    }
    /**
     * 
     * 淇濆瓨鎿嶄綔
     * <鍔熻兘璇︾粏鎻忚堪>
     * @param dto 鏁版嵁
     * @param result 妫�鏌ョ粨鏋�
     * @return 杩斿洖缁撴灉
     * @see
     * @since 1.0
     */
    @PostMapping("doSaveOrUpdate")
    public void doSaveOrUpdate(@RequestBody InvestorsHoldInfo001Dto dto) {
        investorsHoldInfo002Service.doSaveOrUpdate(dto);
    }
    /**
     * 
     * dealCnyAmt
     * <鍔熻兘璇︾粏鎻忚堪>
     * @param dto 鏁版嵁
     * @param result 妫�鏌ョ粨鏋�
     * @return 杩斿洖缁撴灉
     * @see
     * @since 1.0
     */
    @PostMapping("dealCnyAmt")
    public BigDecimal dealCnyAmt(@RequestBody Map<String, Object> param){
        return investorsHoldInfo002Service.dealCnyAmt(param);
    }
    
    
    /**
     * 鏍规嵁涓氬姟涓籯ey鑾峰彇entity
     * <鍔熻兘璇︾粏鎻忚堪>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getEntityByKey")
    public ChbInvestholdInfo getEntityByKey(@RequestBody Map<String, Object> map) {
    	return investorsHoldInfo002Service.getEntityByKey(map);
    }

    /**
     * 鏍规嵁涓氬姟涓籯ey鑾峰彇entity
     * <鍔熻兘璇︾粏鎻忚堪>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("queryAllRegistCode")
    public List<SelectOptionDto> queryAllRegistCode(){
    	return proRegistCodeManage001Service.queryAllRegistCode();
    }
    
    @PostMapping("selectAllData")
	public List<InvestorsHoldInfo001Dto> selectAllData(@RequestBody Map<String, Object> pageParam){
		return investorsHoldInfo001Service.selectAllData(pageParam);
	}
}
