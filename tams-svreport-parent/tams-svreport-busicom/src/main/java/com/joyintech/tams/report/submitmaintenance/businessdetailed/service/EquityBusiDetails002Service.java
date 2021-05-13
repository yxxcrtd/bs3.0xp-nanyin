/*
 * 文件名：SelectOptionService.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：何阳阳
 * 修改时间：2017年9月15日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */

package com.joyintech.tams.report.submitmaintenance.businessdetailed.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.IBDDataCheckUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dao.EquityBusiDetails001Mapper;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 权益基期画面用的下拉框内容查询
 * 
 * @author 何阳阳
 * @version 1.0 2017年9月15日
 * @see EquityBusiDetails002Service
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class EquityBusiDetails002Service extends BaseService{

	@Autowired
    private EquityBusiDetails001Mapper equityBusiDetails001Mapper;
	
	@Autowired
	EquityBusiDetails001Service equityBusiDetails001Service;
	
    /**
     * 查询管理处室
     * @param paraMap 查询参数
     * @return 管理处室下拉框
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public Page<SelectOptionDto> queryPrdNameList(Map<String, Object> paraMap) {
        return super.queryForPage(EquityBusiDetails001Mapper.class, "queryPrdName", paraMap);
    }
    
    /**
     * 根据主键查询投资者明细信息-双击查看
     * @param map
     * @return
     */
	public EquiteBusiDetails001Dto getEntityByKey(Map<String, Object> map) {
		EquiteBusiDetails001Dto key = new EquiteBusiDetails001Dto();
        key.setBusiSeqno(map.get("busiSeqno").toString());
//        key.setRegCode(map.get("regCode").toString());
        key.setAccount(map.get("account").toString()); //修改主键为产品代码
        key.setRegistBankCode(map.get("registBankCode").toString());
        return equityBusiDetails001Mapper.selectByPrimaryKey(key);
	}
	
	/**
	 * 更新数据
	 * @param dto
	 */
	@SuppressWarnings("unchecked")
	public void doSaveOrUpdate(Map<String, Object> param){
		String bankCode = (String)param.get("bankCode");
    	String deptCode = (String)param.get("deptCode");
    	String orgCode = (String)param.get("orgCode");
    	String userName = (String) param.get("userName");
		EquiteBusiDetails001Dto dto=null;
		if(param.containsKey("dto")){
			Map<String, Object> dtomap=(Map<String, Object>)param.get("dto");
			try {
				dto = (EquiteBusiDetails001Dto)JsonUtils.map2entity(dtomap,EquiteBusiDetails001Dto.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			dto=(EquiteBusiDetails001Dto)param.get("objdto");
		}
		 // 将targetObject转化为Map<String, String>
        Map<String, Object> resultObjMap = BeanUtil.object2Map(dto);
        Map<String, String> resultStrMap = new HashMap<String, String>();
        for (String key : resultObjMap.keySet()) {
            Object value = resultObjMap.get(key);
            if (value != null
                && value instanceof Date) {
                resultStrMap.put(key, DateUtil.format((Date) value, DateUtil.DEFAULT_OTHER_FORMAT));
            }
            else if (value != null) {
                resultStrMap.put(key, value.toString());
            }
            else {
                resultStrMap.put(key, null);
            }
        }
        List<Map<String, String>> checkList = new ArrayList<Map<String, String>>();
        checkList.add(resultStrMap);
        List<EquiteBusiDetails001Dto> entitiesList = new ArrayList<>();
        // 删除导入用的临时的数据
        equityBusiDetails001Service.deleteImportTmpData();
        // 插入导入用的临时数据
        List<Map<String, String>> insertList = new ArrayList<Map<String, String>>();
        for(Map<String, String> map:checkList){
            Map<String, String> upmap=new HashMap<String, String>();
            upmap.put("REGIST_BANK_CODE", map.get("registBankCode"));
            upmap.put("BUSI_SEQNO", map.get("busiSeqno"));
            upmap.put("ACCOUNT", map.get("account"));
            upmap.put("INDENTIFY_MARK", map.get("indentifyMark"));
            insertList.add(upmap);
           
        }
       
        equityBusiDetails001Mapper.insertIntoImportTable(insertList);
        if(dto != null){
			bankCode=dto.getRegistBankCode();
		}
        equityBusiDetails001Service.checkDataInfo(checkList, entitiesList,bankCode,deptCode,orgCode,userName);
        if(!entitiesList.isEmpty()){
            if(entitiesList.get(0).getTempMap()!=null){
                dto.setTempMap(entitiesList.get(0).getTempMap());
            }
            if(entitiesList.get(0).getAmountCny()!=null){
                dto.setAmountCny(entitiesList.get(0).getAmountCny());
            }
        }


		//状态
		dto.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
		// 产品代码
        if (StringUtil.isEmpty(dto.getAccount())) {
            String account = equityBusiDetails001Mapper.queryProdidByRegCode(dto.getRegCode());
            dto.setAccount(account);
        }
		if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
//			dto.setRegistBankCode(CommonUtil.getUserBankCode());
//            dto.setOrgCode(CommonUtil.getOrgCode());
//            dto.setDeptCode(CommonUtil.getDeptCode());
//            dto.setCreateDept(dto.getDeptCode()!=null?dto.getDeptCode():dto.getOrgCode());
//            // 数据来源-系统新建
//            dto.setDataSource(DictCodeListExt2.数据来源_系统新建.getValue());
            
            //TODO 插入数据库
		}else if(StringUtil.equals(dto.getPageModel(),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
			if(StringUtil.isEmpty(dto.getUpdateUser())) {
				dto.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
			}
			dto.setUpdateTime(DateUtil.now());
			
			//判断识别标识是否在 权益人信息检查表中-查询合并后的识别标识
			ChbEquHoldCheckDto queryCheckDto = new ChbEquHoldCheckDto();
			queryCheckDto.setIndentifyMark(dto.getIndentifyMark());
			ChbEquHoldCheckDto checkResult = equityBusiDetails001Mapper.selectChbEquHoldCheckByPrimaryKey(queryCheckDto);
	        if(StringUtil.isNotEmpty(checkResult)){
	        	dto.setOrgIndentifyMark(dto.getIndentifyMark());
	        	dto.setIndentifyMark(checkResult.getMergerindentifymark());
	        }
			//更新投资者明细信息
			 List<EquityBusiInfoRegist001Dto> listOA = new ArrayList<>();
             EquityBusiInfoRegist001Dto dtoSub = new EquityBusiInfoRegist001Dto();
             BeanUtil.copyPropertiesIgnoreNull(dtoSub, dto);
             listOA.add(dtoSub);
             EquiteBusiDetails001Dto result= equityBusiDetails001Mapper.selectByPrimaryKey(dto);
             equityBusiDetails001Service.insertEquityBusiHisByPart(listOA);
             equityBusiDetails001Mapper.updateByPrimaryKeySelective(dto);
			//更新投资者明细信息登记表
			equityBusiDetails001Service.doUpdateSub(dto,result);
		}
		 // 删除导入用的临时的数据
        equityBusiDetails001Service.deleteImportTmpData();
        BaseCheckLib.objFiledsMap=null;
	}
	
	
	public void doEquityBusiDetailsRefresh(){
		BaseCheckLib.objFiledsMap=null;
    	List<EquiteBusiDetails001Dto> equiteBusiDetailsList=equityBusiDetails001Mapper.selectEquiteBusiDetailsForTrans();
    	if(!StringUtil.isEmptyList(equiteBusiDetailsList)){
    		for(EquiteBusiDetails001Dto dto:equiteBusiDetailsList){
    			//先检查
    			dto.setPageModel("1");
    			dto.setOperateMode("submit");
    	        List<Map<String, String>> regList = equityBusiDetails001Mapper.queryAllRegCode();
    	        Map<String, String> allProdIdMap = regList.stream().collect(
    	            JoyinCollectors.toMap(x -> x.get("REG_CODE"), x -> x.get("OPERATE_MODE")));
    		    dto.setOperateMode(allProdIdMap.get(dto.getRegCode()));
    			List<ValidMessage> errs = IBDDataCheckUtil.checkDataForPageValid(dto, ReportGlobalConstant.COMMON_CHECK_DATA_TYPE_EQUITY_DETAILS);
    			if(!StringUtil.isEmptyList(errs)){
    				//有错误
    				continue;
    			}
    			if(StringUtil.isNotEmpty(dto.getIndentifyMark())){
    				//判断识别标识是否在投资者身份信息表中
    				EquityInfo001Dto querydto = new EquityInfo001Dto();
    				querydto.setBankCode(dto.getRegistBankCode());
    				querydto.setIndentifyMark(dto.getIndentifyMark());
    				EquityInfo001Dto infoResult = equityBusiDetails001Mapper.selectEquityInfoByPrimaryKey(querydto);

    		        //判断识别标识是否在 权益人信息检查表中
    				ChbEquHoldCheckDto queryCheckDto = new ChbEquHoldCheckDto();
    				queryCheckDto.setRegistBankCode(dto.getRegistBankCode());
    				queryCheckDto.setIndentifyMark(dto.getIndentifyMark());
    				ChbEquHoldCheckDto checkResult = equityBusiDetails001Mapper.selectChbEquHoldCheckByPrimaryKey(queryCheckDto);
    		        if(!StringUtil.isNotEmpty(infoResult) && !StringUtil.isNotEmpty(checkResult)){
    		        	continue;
    		        }
    			}
    			Map<String, Object> param = new HashMap<String, Object>();
    			param.put("objdto", dto);
    			//保存
    			doSaveOrUpdate(param);
    		}
    	}
	}
}