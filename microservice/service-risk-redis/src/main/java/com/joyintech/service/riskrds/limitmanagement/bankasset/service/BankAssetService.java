package com.joyintech.service.riskrds.limitmanagement.bankasset.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.bankasset.dao.BankAssetMapper;
import com.joyintech.service.riskrds.limitmanagement.bankasset.dto.BankAssetDto;
import com.joyintech.tams.common.base.dao.MstBankNetvalueMapper;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;


/**
 * 商业银行净资产维护
 * @author libasiong
 * @version 1.0 2020年12月29日
 * @see BankAssetService
 * @since 1.0
 */
@Service
public class BankAssetService  extends BaseService<BankAssetDto>{

	@Autowired
	private BankAssetMapper bankAssetMapper;
	
	@Autowired
	private MstBankNetvalueMapper mstBankNetvalueMapper;
	
    /**
     * 分页查询
     * 
     * @param length 长度
     * @param page 页数
     * @param map 分页查询参数
     * @return 商业银行净资产维护
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<BankAssetDto> selectForPage(int page, int length, Map<String, Object> map)
        throws Exception {
    	if(map.get("insitituteCode")!=null){
    		List<String> insitituteCodes=Arrays.asList(map.get("insitituteCode").toString().split(","));
    		map.put("insitituteCodes", insitituteCodes);
    	}
        return super.queryForPage(page, length,
            () -> bankAssetMapper.selectForPage(map));
    }
    
    /**
     * 使用给定的sqlID为联想框检索分页数据。返回类型是Page<SuggestDto>。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<SelectOptionDto> selectCustomerId(int page, int length,
                                                        Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPageForSelectReturn(page, length,
            () -> bankAssetMapper.selectCustomerId(pageParam));
    }
    
    public BankAssetDto selectBankAsset(Map<String, Object> map){
    	BankAssetDto dto = bankAssetMapper.selectBankAsset(map);
    	return dto;
    }
    
    public Date selectMaxBankAsset(Map<String, Object> map){
    	Date maxEffDate = bankAssetMapper.selectMaxBankAsset(map);
    	return maxEffDate;
    }
    
    public List<SuggestDto> getCustomerIdList(Map<String, Object> map) {
        return bankAssetMapper.getCustomerIdList(map);
    }
    
    public List<BankAssetDto> selectForExport(Map<String, Object> param) {
        return bankAssetMapper.selectForPage(param);
    }
    
    public int insert(BankAssetDto entity) {
    	entity.setValStatus(BussinessDictCodeListRds.商业银行净资产维护_有效状态_有效.getValue());
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("customerId", entity.getCustomerId());
    	map.put("effDate", DateUtil.format(entity.getEffDate(), "yyyy-MM-dd"));
    	map.put("valStatus", entity.getValStatus());
    	BankAssetDto dto = bankAssetMapper.selectBankAsset(map);
    	//如果存在有效 将原来改为失效 
    	if(StringUtil.isNotEmpty(dto)){
    		dto.setValStatus(BussinessDictCodeListRds.商业银行净资产维护_有效状态_失效.getValue());
    		mstBankNetvalueMapper.updateByPrimaryKeySelective(dto);
    	}
    	BigDecimal seqno = new BigDecimal(bankAssetMapper.selectSeqno());
    	entity.setSeqno(seqno);
    	return mstBankNetvalueMapper.insertSelective(entity);
    }
	
    public int update(BankAssetDto entity){
    	entity.setValStatus(BussinessDictCodeListRds.商业银行净资产维护_有效状态_有效.getValue());
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("customerId", entity.getCustomerId());
    	map.put("effDate", DateUtil.format(entity.getEffDate(), "yyyy-MM-dd"));
    	map.put("valStatus", entity.getValStatus());
    	BankAssetDto dto = bankAssetMapper.selectBankAsset(map);
    	//如果存在有效 将原来改为失效 
    	if(StringUtil.isNotEmpty(dto)){
    		dto.setValStatus(BussinessDictCodeListRds.商业银行净资产维护_有效状态_失效.getValue());
    		mstBankNetvalueMapper.updateByPrimaryKeySelective(dto);
    	}
    	BigDecimal seqno = new BigDecimal(bankAssetMapper.selectSeqno());
    	entity.setSeqno(seqno);
    	return mstBankNetvalueMapper.insertSelective(entity);
    }
    
    public int delete(BankAssetDto entity){
    	entity.setValStatus(BussinessDictCodeListRds.商业银行净资产维护_有效状态_失效.getValue());
    	return mstBankNetvalueMapper.updateByPrimaryKeySelective(entity);
    }
    
    public List<BankAssetDto> selectBankAssetByInsitituteCode(String insitituteCode){
    	List<BankAssetDto> list=bankAssetMapper.selectBankAssetByInsitituteCode(insitituteCode);
    	  if(StringUtil.isEmptyList(list)){
    		  if(StringUtil.isNotEmpty(insitituteCode)&&insitituteCode.length()==18){
    			  insitituteCode=insitituteCode.substring(8, 17);
    			  list=bankAssetMapper.selectBankAssetByInsitituteCode(insitituteCode);
    		  }else  if(StringUtil.isNotEmpty(insitituteCode)&&insitituteCode.length()==9){
    			  list=bankAssetMapper.selectBankAssetByInsitituteCodeTmp(insitituteCode);
    		  }else{
    			  list=new ArrayList<BankAssetDto>();
    		  }
    	  }
    	return list;
    }
	
}
