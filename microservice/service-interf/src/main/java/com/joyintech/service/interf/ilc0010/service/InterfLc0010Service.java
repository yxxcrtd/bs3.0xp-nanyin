/*
 * 文件名：InterfLc0010Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0010.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.ilc0010.dao.InterfLC0010Mapper;
import com.joyintech.service.interf.ilc0010.entity.InterfLc0010Entity;
import com.joyintech.service.interf.ilc0010.entity.InterfLc0010GradeLvEntity;
import com.joyintech.service.interf.wind.dao.BusinessMapper;
import com.joyintech.service.interf.wind.service.SysDatadictListService;
import com.joyintech.tams.common.base.dao.InterfCmsInstitutionGradeMapper;
import com.joyintech.tams.common.base.dao.InterfCmsInstitutionMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoAddMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoMapper;
import com.joyintech.tams.common.base.dao.PaRskCustomerRatingMapper;
import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
import com.joyintech.tams.common.base.entity.PaRskCustomerRating;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;

/**
 * Lc0010cms机构信息评级信息service
 * 
 * @author changwenbin
 * @version 1.0 2019年1月11日
 * @see InterfLc0010Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0010)
public class InterfLc0010Service extends ReceivedEsbService<InterfLc0010Entity, InterfLc0010Entity> {
	/**
	 * cms机构信息mapper
	 */
	@Autowired
	private InterfCmsInstitutionMapper interfCmsInstitutionMapper;

	/**
	 * cms机构评级mapper
	 */
	@Autowired
	private InterfCmsInstitutionGradeMapper interfCmsInstitutionGradeMapper;

	/**
	 * cms机构信息机构评级删除mapper
	 */
	@Autowired
	private InterfLC0010Mapper deleteMapper;

	/**
	 * 公司ID查询
	 */
	@Autowired
	private BusinessMapper busiMapper;

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
	 * 构建公司ID
	 */
	@Autowired
	private BaseGetBussinessId busiIdService;

	/**
	 * 机构评级业务表Mapper
	 */
	@Autowired
	private PaRskCustomerRatingMapper paCompRatingMapper;

	/**
	 * 数据字典项Service
	 */
	@Autowired
	private SysDatadictListService dictService;

	/**
	 * 登录、更新业务表用户名
	 */
	public static final String CMS_USER = "cms";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InterfLc0010Entity selectResponseData(InterfLc0010Entity entity) throws Exception {
		return new InterfLc0010Entity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int saveInterfData(InterfLc0010Entity entity) throws Exception {
		int result = 0;
		// 当机构信息存在，登录CMS_机构信息表
		if (StringUtil.isNotEmpty(entity.getCustomerId())) {
			// 删除重复数据
			deleteMapper.deleteByCusId(entity.getCustomerId());
			// cms机构信息表登录
			result = interfCmsInstitutionMapper.insert(entity);
		}
		// 当评级信息存在，登录CMS_机构评级表
		if (entity.getGradeList() != null) {
			for (InterfLc0010GradeLvEntity gradeEntity : entity.getGradeList()) {
				// 删除重复数据
				deleteMapper.deleteByGradeOrgId(gradeEntity.getGradeOrgId());
				// cms机构评级表登录
				result += interfCmsInstitutionGradeMapper.insert(gradeEntity);
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int saveBusinessData(InterfLc0010Entity entity) throws Exception {
		boolean isInsert = false;
		String compId = busiMapper.selectComIdByName(entity.getInstitutionName());
		// 不存在时，生成新的id登录业务表
		if (StringUtil.isEmpty(compId)) {
			BussinessCodeParameter parameter = new BussinessCodeParameter();
			parameter.setCommonBusinessType(DictCodeList.共通业务类型_市场机构管理);
			compId = busiIdService.buildBussinessCode(parameter);
			isInsert = true;
			saveCompInfo(entity, compId);
		}
		// 附属表操作
		int result = saveCompInfoAdd(entity, isInsert, compId);
		List<InterfLc0010GradeLvEntity> gradeList = entity.getGradeList();
		// 当评级信息存在，登录客户评级表
		if (!StringUtil.isEmptyList(gradeList)) {
			saveGradeInfo(gradeList, compId);
		}
		return result;
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
	private int saveCompInfo(InterfLc0010Entity entity, String compId) {
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		MstCustomerInfo info = new MstCustomerInfo();
		// 公司ID
		info.setCustomerId(compId);
		// 公司名称
		info.setCustomerName(entity.getInstitutionName());
		// 公司类型
		info.setCustomerType(DictCodeList.客户类型_法人.getValue());
		// 更新人
		info.setUpdateUser(CMS_USER);
		// 更新时间
		info.setUpdateTime(dateTime);
		// 创建人
		info.setCreateUser(CMS_USER);
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
	private int saveCompInfoAdd(InterfLc0010Entity entity, boolean isInsert, String compId) {
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		MstCustomerInfoAdd infoAdd = new MstCustomerInfoAdd();
		// 公司ID
		infoAdd.setCustomerId(compId);
		// 机构类型（规模）
		infoAdd.setPartyType1(dictService.getItemCode(DictCode.发行机构类型_按规模划分.getValue(), entity.getPartyType1()));
		// 机构类型（经济类型）
		infoAdd.setPartyType2(
				dictService.getItemCode(DictCode.发行机构类型_按经济类型划分.getValue(), entity.getPartyType2()));
		// 机构类型（技术领域）
		infoAdd.setPartyType3(
				dictService.getItemCode(DictCode.发行机构类型_按技术领域划分.getValue(), entity.getPartyType3()));
		// 国标一级分类
		infoAdd.setIndustry(dictService.getItemCode(DictCode.机构所属行业一级分类.getValue(), entity.getRepIndustry()));
		// 国标二级分类
		infoAdd.setIndustry2(
				dictService.getItemCode(DictCode.机构所属行业二级分类.getValue(), entity.getRepIndustryLevel2()));
		// 组织机构代码/统一社会信用代码
		infoAdd.setInsitituteCode(entity.getInstitutionCode());
		// 是否政府融资平台
		infoAdd.setIsGov(entity.getIsGov());
		// 核心资本（万元）
		infoAdd.setMainCapital(entity.getActualCapital());
		// 核心资本（万元）
		infoAdd.setRegisterCapital(entity.getRegistCapital());
		// 所属省份
		infoAdd.setProvince(dictService.getItemCode(SysDatadictListService.PROVINCE, entity.getProvince()));
		// 所属市
		infoAdd.setCity(dictService.getItemCode(SysDatadictListService.CITY, entity.getCity()));
		// 所属县
		infoAdd.setCounty(dictService.getItemCode(SysDatadictListService.COUNTY, entity.getCounty()));
		// BECIF
		infoAdd.setLinkId2(entity.getBecif());
		// 控股类型
		infoAdd.setHoldingType(dictService.getItemCode(DictCode.控股类型.getValue(), entity.getHoldingType()));
		// 客户主体类型
		infoAdd.setCustomerObjType(
				dictService.getItemCode(DictCode.客户主体类型.getValue(), entity.getCustomerObjType()));
		// 是否上市公司
		infoAdd.setIsListedCompany(entity.getIsListedCompany());
		// 是否集团客户
		infoAdd.setIsGroupCustomer(entity.getIsGroupCustomer());
		// 所属集团客户编号
		infoAdd.setParentCustomerId(entity.getParentCustomerId());
		// 所属集团客户名称
		infoAdd.setParentCustomerName(entity.getParentCustomerName());
		// 是否民营企业
		infoAdd.setIsPrivate(entity.getIsPrivate());
		// 是否为逃废债企业
		infoAdd.setIsEscapeDept(entity.getIsEscapeDept());
		// 授信策略
		infoAdd.setCreditType(dictService.getItemCode(DictCode.授信策略.getValue(), entity.getCreditType()));
		// 是否授信类关联交易
		infoAdd.setIsCreditRelatedTrd(entity.getIsCreditRelatedTrd());
		// 产业结构调整类型
		infoAdd.setStructAdjustType(
				dictService.getItemCode(DictCode.产业结构调整类型.getValue(), entity.getStructAdjustType()));
		// 工业转型升级标识
		infoAdd.setIndusUpgrateType(
				dictService.getItemCode(DictCode.工业转型升级标识.getValue(), entity.getIndusUpgrateType()));
		// // 经营范围
		// infoAdd.setBusiScope(entity.getBusiScope());
		// // 主营业务
		// infoAdd.setMainBusi(entity.getMainBusi());
		// 企业性质
		infoAdd.setCompanyNature(dictService.getItemCode(DictCode.客户性质.getValue(), entity.getIsTopCompany()));
		// 更新人
		infoAdd.setUpdateUser(CMS_USER);
		// 更新时间
		infoAdd.setUpdateTime(dateTime);
		// 登录
		if (isInsert) {
			// 创建人
			infoAdd.setCreateUser(CMS_USER);
			// 创建时间
			infoAdd.setCreateTime(dateTime);
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

	/**
	 * 保存评级信息
	 * 
	 * @param gradeList
	 *            评级信息
	 * @param compId
	 *            客户代码
	 * @return 保存行数
	 */
	private int saveGradeInfo(List<InterfLc0010GradeLvEntity> gradeList, String compId) {
		int result = 0;
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		for (InterfLc0010GradeLvEntity gradeEntity : gradeList) {
			PaRskCustomerRating gradeInfo = new PaRskCustomerRating();
			// 客户代码
			gradeInfo.setCustomerId(compId);
			// 评级类型
			gradeInfo.setRatingtype(
					dictService.getItemCode(DictCode.客户评级类型.getValue(), gradeEntity.getGradeType()));
			// 评级生效日
			gradeInfo.setStartDate(gradeEntity.getGradeEffDate());
			// 评级到期日
			gradeInfo.setEndDate(gradeEntity.getGradeEndDate());
			// 评级认定结果
			gradeInfo.setRating(
					dictService.getItemCode(DictCode.客户评级认定结果.getValue(), gradeEntity.getGradeResult()));
			// 评级报告年度
			gradeInfo.setRatingYear(Long.parseLong(gradeEntity.getGradeRptYear()));
			// 使用模型类别
			gradeInfo.setRatingMode(
					dictService.getItemCode(DictCode.客户评级使用模型类别.getValue(), gradeEntity.getGradeModelType()));
			// 更新人
			gradeInfo.setUpdateUser(CMS_USER);
			// 更新时间
			gradeInfo.setUpdateTime(dateTime);
			// 创建人
			gradeInfo.setCreateUser(CMS_USER);
			// 创建时间
			gradeInfo.setCreateTime(dateTime);
			// 删除重复数据
			paCompRatingMapper.deleteByPrimaryKey(gradeInfo);
			// cms机构评级表登录
			result += paCompRatingMapper.insertSelective(gradeInfo);
		}
		return result;
	}
}
