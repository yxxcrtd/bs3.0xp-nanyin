/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.service.risk.assetinfodetail.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.assetinfodetail.dao.AssetInfoDetailRiskMapper;
import com.joyintech.service.risk.assetinfodetail.dto.AssetInfoDetailRiskDto;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

/**
 * 资产要素信息维护Service
 */
@Service("backAssetInfoDetailRiskService")
public class AssetInfoDetailRiskService extends CommonExportService<AssetInfoDetailRiskDto> {

	/**
	 * 资产要素登记mapper
	 */
	@Autowired
	private AssetInfoDetailRiskMapper assetInfoDetailMapper;
	
	 /**
     * 自动生成标的类金融产品附属表mapper对象
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;

	@Autowired
	private FinProductTypeMapper finProductTypeMapper;



	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.6.0
	 */
    public Page<AssetInfoDetailRiskDto> selectForPage(int page, int length, Map<String, Object> param){
    	//获取当前登录用户的机构编码
    	param.put("orgCode", CommonUtil.getTreeInstCode());

    	param.put("assetCodes", StringUtil.isNotEmpty(param.get("assetCode"))?(param.get("assetCode").toString().split(",")):null);
    	param.put("assetTypes", StringUtil.isNotEmpty(param.get("assetType"))?(param.get("assetType").toString().split(",")):null);
    	param.put("assetType2s", StringUtil.isNotEmpty(param.get("assetType2"))?(param.get("assetType2").toString().split(",")):null);
    	param.put("orgAssetType1s", StringUtil.isNotEmpty(param.get("orgAssetType1"))?(param.get("orgAssetType1").toString().split(",")):null);
    	param.put("orgAssetType2s", StringUtil.isNotEmpty(param.get("orgAssetType2"))?(param.get("orgAssetType2").toString().split(",")):null);
    	return super.queryForPage(page, length, () -> assetInfoDetailMapper.selectChbInfoByParam(param));
    }

    /**
     * 查询对应的资产要素实体类 
     * @param assetUUid
     * @return
     */
    public RskAssetInfo BackRskAssetInfoMethod(String assetUUid){
    	RskAssetInfo infoDto = new RskAssetInfo();       
        infoDto=assetInfoDetailMapper.getChbAssetInfoByUUid(assetUUid);       
        return  infoDto;
    }

    /**
	 * 根据uuid获取资产信息
	 * @param UUid
	 * @return
	 */
    public RskAssetInfo getChbAssetInfoByUUid(String assetUUid){
    	RskAssetInfo  infoDto=new  RskAssetInfo();       
        infoDto=assetInfoDetailMapper.getChbAssetInfoByUUid(assetUUid);       
        return  infoDto;
    }

    /**
	 * 数据赋值 marge模式
	 * @param dto
	 * @return
	 */
	public Integer doSave(AssetInfoDetailRiskDto dto){
		int  flag = 0;
		List<RskAssetInfo> assetInfo = new ArrayList<>();
		//数据保存.使用marge模式.避免出现老数据仍然出现的问题
		if(StringUtil.isNotEmpty(dto)){
			assetInfo.add(dto);
			flag = assetInfoDetailMapper.mergeInfoListEx(assetInfo);
		}
		//在金融产品附属表FinProductAdd存值,股票还要在FIN_PRODUCT_TYPE表存值
			 if (dto.getFinProductAdd() != null) {
				 String finprodId = dto.getFinProductAdd().getFinprodId();
				 FinProductAdd finProductAdd = assetInfoDetailMapper.selectByFinprodId(finprodId);
				 if(finProductAdd == null) {
                 finProductAddMapper.insert(dto.getFinProductAdd());
				 }else {
				 finProductAddMapper.updateByPrimaryKeySelective(dto.getFinProductAdd());
				 }
             }
		if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())
				||StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_二级市场.getValue())){
			if (dto.getFinProductType() != null) {
				String finprodId = dto.getFinProductType().getFinprodId();
				FinProductType finProductType = assetInfoDetailMapper.selectByFinprodIdType(finprodId);
				if(finProductType == null) {
					finProductTypeMapper.insert(dto.getFinProductType());
				}else {
					finProductTypeMapper.updateByPrimaryKeySelective(dto.getFinProductType());
				}
			}
		}

		return flag;
	}


	/**
	 * 数据删除
	 */
	public Integer doDelete(AssetInfoDetailRiskDto dto){
		int  flag = 0;

		/***如果是资产基本信息,则修改状态为D*/
		//if(DictCodeListExt3.中债_处理状态_已处理.getValue().equals(dto.getDataStatus())){
			assetInfoDetailMapper.updateChbAssetType(dto.getAssetUuid());
		//}
//		/**
//		 * 如果是待处理数据,则将错误信息表里面的数据删除
//		 */
//		if(DictCodeListExt3.中债_处理状态_待处理.getValue().equals(dto.getDataStatus())){
//			assetInfoDetailMapper.deleteError(dto.getAssetUuid());
//		}
			
		//删除FinProductAdd、FIN_PRODUCT_TYPE表的值
		   String finprodId = dto.getFinProductAdd().getFinprodId();
		   finProductAddMapper.deleteByPrimaryKey(finprodId);
		if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())
				||StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_二级市场.getValue())){
		   assetInfoDetailMapper.deleteFinProductType(finprodId);
				
		}

		return flag;
	}

	/**
	 * 根据uuid获取资产信息
	 * @param UUid
	 * @return
	 */
	public AssetInfoDetailRiskDto getAssetInfoByUUid(String UUid){
		return assetInfoDetailMapper.getAssetInfoDetail(UUid);
	}
	
	  /**
     * 资产代码下拉框
     * @param param
     * @return
     */
    public List<SelectOptionDto> selectAssetCodeList(Map<String, Object> param) {
        List<SelectOptionDto> resultList = assetInfoDetailMapper.selectAssetCodeList(param);
        return resultList;
    }
    /**
	 * 获取资产及二级类型的关系
	 * @return
	 */
    public List<SysDatadictListLink> getDataListLinkData(Map<String,Object> map) {
        return assetInfoDetailMapper.getDataListLinkData(map);
    }
    
    /**
     * 查询回购交易的金额
     * <功能详细描述>
     * @param param String assetuuid
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    public BigDecimal getTargetAmt(String assetUUid) {
        return assetInfoDetailMapper.getTargetAmt(assetUUid);
    }
    
    /**
  	 * 根据界面传入数据获取二级资产类型
  	 * @param resultMap
  	 * @return
  	 */
    public List<SelectOptionDto> getAssetTye2(Map<String, Object> param) {
        List<SelectOptionDto> resultList = assetInfoDetailMapper.getAssetTye2(param);
        return resultList;
    }
    
    /**
   	 * 查询资产信息
   	 * @return
   	 */
       public List<AssetInfoDetailRiskDto> selectChbInfoByParam(Map<String,Object> map) {
           return assetInfoDetailMapper.selectChbInfoByParam(map);
       }
       
     /**
   	 * 检查是否存在对应资产
   	 * @param UUid
   	 * @return
   	 */
   	public AssetInfoDetailRiskDto queryAssetInfo(String assetCode){
   		return assetInfoDetailMapper.queryAssetInfo(assetCode);
   	}
   	
   	public Integer selectDataCount(List<AssetInfoDetailRiskDto> dto){
		return assetInfoDetailMapper.selectDataCount(dto);
	}
   	
  
	
	public List<ExcelValidDto> importData(List<AssetInfoDetailRiskDto> dataList){	
		List<ExcelValidDto> checkResult = new ArrayList<>();
		if(!StringUtil.isEmptyList(dataList)){
			List<RskAssetInfo> assetInfo = new ArrayList<>();
			dataList.forEach(assetInfoDetailDto -> {
				assetInfo.add(assetInfoDetailDto);				
			});
			assetInfoDetailMapper.mergeInfoListEx(assetInfo);
			//在金融产品附属表FinProductAdd存值,股票还要在FIN_PRODUCT_TYPE表存值
			dataList.forEach(dto -> {
			if (dto.getFinProductAdd() != null) {
				String finprodId = dto.getFinProductAdd().getFinprodId();
				FinProductAdd finProductAdd = assetInfoDetailMapper.selectByFinprodId(finprodId);
				if(finProductAdd == null) {
					finProductAddMapper.insert(dto.getFinProductAdd());
				}else {
					finProductAddMapper.updateByPrimaryKeySelective(dto.getFinProductAdd());
				}
			}
			if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())
					||StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_二级市场.getValue())){
				if (dto.getFinProductType() != null) {
					String finprodId = dto.getFinProductType().getFinprodId();
					FinProductType finProductType = assetInfoDetailMapper.selectByFinprodIdType(finprodId);
					if(finProductType == null) {
						finProductTypeMapper.insert(dto.getFinProductType());
					}else {
						finProductTypeMapper.updateByPrimaryKeySelective(dto.getFinProductType());
					}
				}
			}
			});
			return checkResult;
		}
		checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0),
				this.getMessage("REPORT_COMMON_FILE_E_0016")));
		return  checkResult;
	}

}
