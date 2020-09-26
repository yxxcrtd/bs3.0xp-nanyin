package com.joyintech.tams.svreport.loaninfomanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbLoanDetailInfoMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 贷款明细信息维护 服务Service
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalLoanInfo001Service extends BaseService<OperationalLoanInfo001Dto>{

	@Autowired
	private OperationalLoanInfo001Mapper operationalLoanInfo001Mapper;
	
	@Autowired
	private ChbLoanDetailInfoMapper chbLoanDetailInfoMapper;
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 */
	public void doDelete(Map<String, Object> param){
		String assetUuid = param.get("ASSET_UUID").toString();
		OperationalLoanInfo001Dto dto = new OperationalLoanInfo001Dto();
		dto.setAssetUuid(assetUuid);
		String status =DictCodeListExt1.维护数据状态_删除.getValue();
		dto.setStatus(status);
		operationalLoanInfo001Mapper.updateStatus(dto);
	}
	
	/**
	 * 根据主键查询资产收益权贷款明细基本信息表数据
	 * @param param
	 * @return
	 */
	public OperationalLoanInfo001Dto selectByPrimaryKey(Map<String, Object> param){
		String assetUuid = param.get("ASSET_UUID").toString();
		return operationalLoanInfo001Mapper.selectByPrimaryKey(assetUuid);
	}
	
	/**
	 * 查询产品资产代码
	 * @param param
	 * @return
	 */
	public List<SelectOptionDto> queryPbcAssetCode(Map<String, Object> param){
		return operationalLoanInfo001Mapper.queryPbcAssetCode(param);
	}
	public String queryPbcAssetType(Map<String, Object> param){
		String assetCode = param.get("assetCode").toString();
		return operationalLoanInfo001Mapper.queryPbcAssetType(assetCode);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	public void doSave(OperationalLoanInfo001Dto dto){
		String pageModel = dto.getPageModel();
		String status =DictCodeListExt1.维护数据状态_有效.getValue();
		dto.setStatus(status);
		dto.setUpdateUser(CommonUtil.getUserName());
		dto.setUpdateTime(DateUtil.now());
		if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			dto.setAssetUuid(IDGenerator.generate(36));
			dto.setCreateUser(CommonUtil.getUserName());
			dto.setCreateTime(DateUtil.now());
			dto.setCreateDept(CommonUtil.getDeptCode());
			dto.setOrgCode(CommonUtil.getOrgCode());
			dto.setDeptCode(CommonUtil.getDeptCode());
			dto.setDataSource(DictCodeListExt2.数据来源_系统新建.getValue());
			
			//插入资产收益权贷款明细基本信息表
			operationalLoanInfo001Mapper.insertLoanInfo(dto);
		}else if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
			//更新资产收益权贷款明细基本信息表
			chbLoanDetailInfoMapper.updateByPrimaryKeySelective(dto);
		}
	}
	
	public int checkAssetCodeExist(String assetCode){
		return operationalLoanInfo001Mapper.checkAssetCodeExist(assetCode);
	}
	
	/**
	 * 根据计划名称查询计划id以及待维护表ID
	 * @param pid
	 * @return
	 */
	public OperationalLoanInfo001Dto queryPlanInfo(String pid){
		return operationalLoanInfo001Mapper.queryPlanInfo(pid);
	}
	
	/**
     * 
     * 省市县联动
     * @param param
     * @return
     */
    @Transactional
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getAddressSelectOption(Map<String, Object> param){
        return operationalLoanInfo001Mapper.getAddressSelectOption(param);
    }
	
}
