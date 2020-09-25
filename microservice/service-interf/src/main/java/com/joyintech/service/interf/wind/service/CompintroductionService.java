/*
 * 文件名：CompintroductionService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.BusinessMapper;
import com.joyintech.service.interf.wind.dao.CompintroductionMapper;
import com.joyintech.tams.common.base.dao.InterfCompintroductionMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoAddMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoMapper;
import com.joyintech.tams.common.base.entity.InterfCompintroduction;
import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * WIND-机构信息数据接入
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月27日
 * @see CompintroductionService
 * @since 1.0
 */
@Service
public class CompintroductionService extends WindService<InterfCompintroduction> {

	/**
	 * WIND-机构信息查询Mapper
	 */
	@Autowired
	private CompintroductionMapper selectMapper;

	/**
	 * 接口表Mapper
	 */
	@Autowired
	private InterfCompintroductionMapper insertMapper;

	/**
	 * 客户信息表Mapper
	 */
	@Autowired
	private MstCustomerInfoMapper customerInfoMapper;

	/**
	 * 客户信息附属表Mapper
	 */
	@Autowired
	private MstCustomerInfoAddMapper customerInfoAddMapper;

	/**
	 * 公司ID查询
	 */
	@Autowired
	private BusinessMapper busiMapper;

	/**
	 * 构建公司ID
	 */
	@Autowired
	private BaseGetBussinessId busiIdService;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfCompintroduction> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfCompintroduction entity) throws Exception {
		// 删除重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 接口表登录
		insertMapper.insertSelective(entity);
		// 业务表登录
		return saveBusinessData(entity);
	}

	/**
	 * 登录业务表
	 * 
	 * @param entity
	 *            机构信息
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveBusinessData(InterfCompintroduction entity) {
		// 取得业务表公司ID
		String compId = busiMapper.selectComIdByName(entity.getCompName());
		if (StringUtil.isEmpty(compId)) {
			BussinessCodeParameter parameter = new BussinessCodeParameter();
			parameter.setCommonBusinessType(DictCodeList.共通业务类型_市场机构管理);
			compId = busiIdService.buildBussinessCode(parameter);
			// 登录客户信息表
			saveCompInfo(entity, compId);
		}
		// 登录客户信息附属表
		return saveCompInfoAdd(entity, compId);
	}

	/**
	 * 登录客户信息表
	 * 
	 * @param entity
	 *            机构信息
	 * @param compId
	 *            公司ID
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveCompInfo(InterfCompintroduction entity, String compId) {
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		MstCustomerInfo info = new MstCustomerInfo();
		// 公司ID
		info.setCustomerId(compId);
		// 公司名称
		info.setCustomerName(entity.getCompName());
		// 公司简称
		info.setCustomerAbbr(entity.getCompSname());
		// 公司类型
		info.setCustomerType(DictCodeList.客户类型_法人.getValue());
		// 更新人
		info.setUpdateUser(WIND_USER);
		// 更新时间
		info.setUpdateTime(dateTime);
		// 创建人
		info.setCreateUser(WIND_USER);
		// 创建时间
		info.setCreateTime(dateTime);
		return customerInfoMapper.insertSelective(info);
	}

	/**
	 * 登录客户信息附属表
	 * 
	 * @param entity
	 *            机构信息
	 * @param isInsert
	 *            是否登录
	 * @param compId
	 *            公司ID
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveCompInfoAdd(InterfCompintroduction entity, String compId) {
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		boolean isInsert = false;
		MstCustomerInfoAdd infoAdd = customerInfoAddMapper.selectByPrimaryKey(compId);
		// 不存在的情况下，登录
		if (infoAdd == null) {
			infoAdd = new MstCustomerInfoAdd();
			// 公司ID
			infoAdd.setCustomerId(compId);
			// 创建人
			infoAdd.setCreateUser(WIND_USER);
			// 创建时间
			infoAdd.setCreateTime(dateTime);
			isInsert = true;
		}
		// 已存在，且为CMS机构时，只更新wind代码
		if (!StringUtil.isEmpty(infoAdd.getLinkId2())) {
			// wind编码
			infoAdd.setLinkId1(entity.getCompId());
		}
		// 存在，不为CMS机构时，更新全部信息
		else {
			// 所属省份
			infoAdd.setProvince(getItemCode(SysDatadictListService.PROVINCE, entity.getProvince()));
			// 所属市
			infoAdd.setCity(getItemCode(SysDatadictListService.CITY, entity.getCity()));
			// wind编码
			infoAdd.setLinkId1(entity.getCompId());
			// 组织机构代码/统一社会信用代码
			infoAdd.setInsitituteCode(entity.getRegisternumber());
			// 注册资本（万元）
			infoAdd.setRegisterCapital(entity.getRegcapital());
			// 企业性质
			infoAdd.setCompanyNature(getItemCode(SysDatadictListService.COMP_PROP, entity.getCompProperty()));
			// // 经营范围
			// infoAdd.setBusiScope(entity.getBusinessscope());
			// // 主营业务
			// infoAdd.setMainBusi(entity.getMainBusiness());
			// 更新人
			infoAdd.setUpdateUser(WIND_USER);
			// 更新时间
			infoAdd.setUpdateTime(dateTime);
		}
		// 登录
		if (isInsert) {
			MstCustomerInfoAddWithBLOBs istEntity = new MstCustomerInfoAddWithBLOBs();
			BeanUtil.copyPropertiesIgnoreNull(istEntity, infoAdd);
			return customerInfoAddMapper.insertSelective(istEntity);
		}
		// 更新
		else {
			MstCustomerInfoAddWithBLOBs istEntity = new MstCustomerInfoAddWithBLOBs();
			BeanUtil.copyPropertiesIgnoreNull(istEntity, infoAdd);
			return customerInfoAddMapper.updateByPrimaryKeySelective(istEntity);
		}
	}
}
