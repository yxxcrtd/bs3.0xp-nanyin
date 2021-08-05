/*
 * 文件名：FinFundTargetService.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.service.asset.futures.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.asset.futures.dao.FinFuturesMapper;
import com.joyintech.service.asset.futures.dto.FinFuturesDto;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.component.base.core.BaseFinProductBaseInfoService;
import com.joyintech.tams.component.base.core.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

/**
 * 期货标的信息的微服务service 〈功能详细描述〉
 *
 * @author 余飞
 * @version 2020年11月09日
 * @see FinFuturesService
 * @since 1.0
 */
@Service("backFinFuturesService")
public class FinFuturesService extends BaseService<FinFuturesDto> {
	
	@Autowired
	private FinFuturesMapper finFuturesMapper;

	/**
	 * 附件信息共通Service
	 */
	@Autowired(required = false)
	private MstFileCommonService mstFileCommonService;

	// 标的类金融产品基本信息组件
	@Autowired(required = false)
	private BaseFinProductBaseInfoService baseFinProductBaseInfoService;

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<FinFuturesDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception {
		param.put("FINPROD_TYPE_TG_F36", DictCodeListExt3.金融产品类型_投管用_期货.getValue());
		if(param.get("status").equals("02")){
			return super.queryForPage(page, length, () -> finFuturesMapper.selectForPage(param));
		}else {
			Date cDate = DateUtil.parse(DateUtil.getSysDate());
			param.put("cDate", cDate);
			return super.queryForPage(page, length, () -> finFuturesMapper.selectForPageInit(param));
		}
	}

	/**
	 * 查询资产代码 <功能详细描述>
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SuggestDto> getFinprodMarketIdList(String finprodMarketId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("FINPROD_TYPE_TG_F36", DictCodeListExt3.金融产品类型_投管用_期货.getValue());
		param.put("finProdMarketId", finprodMarketId);
		return finFuturesMapper.getFinProdMarketIdList(param);
	}

	/**
	 * 根据金融产品代码查询dto <功能详细描述>
	 *
	 * @param finprodId
	 * @return
	 * @see
	 * @since 1.0
	 */
	public FinFuturesDto selectByFinprodId(String finprodId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("finprodId", finprodId);
		param.put("FINPROD_TYPE_TG_F36", DictCodeListExt3.金融产品类型_投管用_期货.getValue());
		return finFuturesMapper.selectByFinprodId(param);
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public FinFuturesDto findEntity(Map<String, Object> param) {
		param.put("FINPROD_TYPE_TG_F36", DictCodeListExt3.金融产品类型_投管用_期货.getValue());
		return finFuturesMapper.findEntity(param);
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int insert(FinFuturesDto dto) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = getDto(dto);
		// 定义标的类金融产品dto
		BaseFinProductBaseInfoDto entity = new BaseFinProductBaseInfoDto();
		entity.setFinProduct((FinProduct) param.get("finProduct"));
		entity.setFinProductAdd((FinProductAdd) param.get("finProductAdd"));
		entity.setFinProductType((FinProductType) param.get("finProductType"));
		// 新建 标的类金融产品基本信息dto list
		List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
		list.add(entity);
		baseFinProductBaseInfoService.insert(list);
		// 文件
		MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
		mstFileInputParameter.setFileList(dto.getFileArray());
		mstFileInputParameter.setBussinessId(dto.getFinprodId());
		if (mstFileCommonService != null) {
			mstFileCommonService.updateBusinessId(mstFileInputParameter);
		}
		return 1;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	public int update(FinFuturesDto dto) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = getDto(dto);
		List<String> finprodIdList = (List<String>) param.get("finprodIdList");
		// 定义标的类金融产品dto
		BaseFinProductBaseInfoDto entity = new BaseFinProductBaseInfoDto();
		entity.setFinProduct((FinProduct) param.get("finProduct"));
		entity.setFinProductAdd((FinProductAdd) param.get("finProductAdd"));
		entity.setFinProductType((FinProductType) param.get("finProductType"));

		entity.setFinCashInfoList((List<FinCashInfo>) param.get("finCashInfoList"));
		entity.setFinCashPayRuleList((List<FinCashPayRule>) param.get("finCashPayRuleList"));
		// 修改标的类金融产品基本信息dto list
		List<BaseFinProductBaseInfoDto> list = new ArrayList<BaseFinProductBaseInfoDto>();
		list.add(entity);
		baseFinProductBaseInfoService.update(finprodIdList, list);
		// 文件
		MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
		mstFileInputParameter.setFileList(dto.getFileArray());
		mstFileInputParameter.setBussinessId(dto.getFinprodId());
		if (mstFileCommonService != null) {
			mstFileCommonService.updateBusinessId(mstFileInputParameter);
		}
		return 1;
	}

	/**
	 * 流程结束后，构建数据结构 将画面传入的数据分配到各dto中
	 *
	 * @param dto
	 * @see
	 * @since 1.0
	 */
	private Map<String, Object> getDto(FinFuturesDto dto) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> param = new HashMap<String, Object>();
		// 标的类金融产品dto
		FinProduct finProduct = new FinProduct();
		BeanUtil.copyProperties(finProduct, dto);
		finProduct.setFinprodId(dto.getFinprodId());
		if (StringUtil.isNotEmpty(dto.getFuturesContractId())) {
			String FuturesName = SysDataUtil.getNameFromDataDict(DictCode.合约标的, dto.getFuturesContractId());
			finProduct.setFinprodName(FuturesName);
			finProduct.setFinprodAbbr(FuturesName);
		}
		finProduct.setBlnArea(DictCodeList.境内外_境内.getValue());
		finProduct.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_期货.getValue());
		// 标的类金融产品附属表dto
		FinProductAdd finProductAdd = new FinProductAdd();
		BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
		finProductAdd.setFinprodId(finProduct.getFinprodId());
//		if (!StringUtil.isNotEmpty(finProductAdd.getContractMultiplier())) {
//			if (StringUtil.equals(dto.getFuturesType(), DictCodeListExt3.期货类型_国债期货.getValue())) {
//				if (StringUtil.equals(dto.getFuturesContractId(), DictCodeListExt3.合约标的_2年期国债.getValue())) {
//					finProductAdd.setContractMultiplier(new BigDecimal(2000000));
//				} else if (StringUtil.equals(dto.getFuturesContractId(), DictCodeListExt3.合约标的_5年期国债.getValue())
//						|| StringUtil.equals(dto.getFuturesContractId(), DictCodeListExt3.合约标的_10年期国债.getValue())) {
//					finProductAdd.setContractMultiplier(new BigDecimal(1000000));
//				}
//			} else if (StringUtil.equals(dto.getFuturesType(), DictCodeListExt3.期货类型_股指期货.getValue())) {
//				if (StringUtil.equals(dto.getFuturesContractId(), DictCodeListExt3.合约标的_中证500指数.getValue())) {
//					finProductAdd.setContractMultiplier(new BigDecimal(200));
//				} else if (StringUtil.equals(dto.getFuturesContractId(), DictCodeListExt3.合约标的_沪深300指数.getValue())
//						|| StringUtil.equals(dto.getFuturesContractId(), DictCodeListExt3.合约标的_上证50指数.getValue())) {
//					finProductAdd.setContractMultiplier(new BigDecimal(300));
//				}
//			}
//		}
		// 金融产品分类表dto
		FinProductType finProductType = new FinProductType();
		finProductType.setFinprodId(finProduct.getFinprodId());
		long i = 0;
		finProductType.setBranch(i);
		finProductType.setType6(dto.getFuturesType());
		finProductType.setType8(DictCodeListExt3.平安资产类型_期货.getValue());
		finProductType.setType9(dto.getFuturesType());

		// 获取要修改的金融产品代码集合(修改时用)
		List<String> finprodIdList = new ArrayList<String>();
		finprodIdList.add(finProduct.getFinprodId());
		param.put("finProduct", finProduct);
		param.put("finProductAdd", finProductAdd);
		param.put("finProductType", finProductType);
		param.put("finprodIdList", finprodIdList);
		return param;
	}

	/**
	 * 调用组件删除 <功能详细描述>
	 *
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int delete(FinFuturesDto dto) {
		List<String> finprodIdList = new ArrayList<String>();
		finprodIdList.add(dto.getFinprodId());
		baseFinProductBaseInfoService.delete(finprodIdList);
		return 1;
	}
}
