package com.joyintech.tams.valuation.batch.repassetindex.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.RepAssetIndex;
import com.joyintech.tams.valuation.batch.repassetindex.dao.RepAssetIndexTaskMapper;
import com.joyintech.tams.valuation.batch.repassetindex.dto.BokBookset;
import com.joyintech.tams.valuation.batch.repassetindex.dto.RepAssetIndexDtoResult;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.utils.DBUtil;


/**
 * 资产指标计算Processor逻辑Service
 * 
 * @author dongxiangyang
 * @version 1.0 2018年12月21日
 * @see RepAssetIndexDtoReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepAssetIndexDtoReaderService {

	/**
	 * 资产指标计算mapper
	 */
	@Autowired
	private RepAssetIndexTaskMapper repAssetIndexTaskMapper;

	@Autowired
	private DBUtil dBUtil;

	public RepAssetIndexDtoResult execute(Map<String, Object> param) {
		RepAssetIndexDtoResult result = new RepAssetIndexDtoResult();
		// 定义资产指标表信息
		List<RepAssetIndex> listRepAssetIndexDto = new ArrayList<>();
		// 获取reader返回值
		BokBookset acctEntity = (BokBookset) param.get(GlobalConstant.READER_RESULT);

		// 若reader传递的参数为空，不进行后续处理
		if (acctEntity == null) {
			return null;
		}

		// 取统计分析明细(账务明细)
		BokStatDetPosiiton bokStatDetPosiiton = new BokStatDetPosiiton();
		// 账套代码=DTO.账套代码
		bokStatDetPosiiton.setBooksetId(acctEntity.getBooksetId());
		// 发生日=DTO.入账日
		bokStatDetPosiiton.setHappenDate(acctEntity.getBookDate());
		// 入账日=DTO.入账日
		bokStatDetPosiiton.setBookDate(acctEntity.getBookDate());
		//modify by wm 2019-06-13 修改查询明细方法 start
//		List<BokStatDetPosiiton> resutBokStatDetPosiiton = repAssetIndexTaskMapper
//				.selectBokStatDetPosiiton(bokStatDetPosiiton);
		List<BokStatDetPosiiton> resutBokStatDetPosiiton = repAssetIndexTaskMapper
				.selectBokStatDetPosiitonNew(bokStatDetPosiiton);
		//modify by wm 2019-06-13 修改查询明细方法 end
		// 非空执行
		if (resutBokStatDetPosiiton != null && !resutBokStatDetPosiiton.isEmpty()) {
			// 循环账务明细处理结果
			for (BokStatDetPosiiton bokDto : resutBokStatDetPosiiton) {
				// 初始化资产指标表信息
				RepAssetIndex repAssetIndexDto = new RepAssetIndex();
                //有效状态=E
				repAssetIndexDto.setStatus("E");
				// 资产指标表.流水号 <- 自动生成，规则为BOOK + "YYYYMMDD"+20位序列号
				repAssetIndexDto.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
				// 资产指标表.账套代码 = DTO.账套代码
				repAssetIndexDto.setBooksetId(acctEntity.getBooksetId());
				// 资产指标表.估值日 = DTO.入账日
				repAssetIndexDto.setValueDate(acctEntity.getBookDate());
				// 资产指标表.业务明细代码 = 账务明细.业务明细代码
				repAssetIndexDto.setBusiId(bokDto.getBusiId());
				// 资产指标表.业务明细类型 = 账务明细.金融产品类型
//				repAssetIndexDto.setBusiType(bokDto.getFinprodType());
				// 资产指标表.产品代码 = 产品基本信息产品代码
			    String prodId=repAssetIndexTaskMapper.getprodId(repAssetIndexDto.getBooksetId());
			    
				repAssetIndexDto.setProdId(null!=prodId?prodId:repAssetIndexDto.getBooksetId());
				// 取金融产品数据
				FinProduct finProduct = new FinProduct();
//				// 金融产品代码 = 资产指标表.业务明细代码 and 金融产品类型 = 资产指标表.业务明细类型
//				finProduct.setFinprodId(repAssetIndexDto.getBusiId());
//				finProduct.setFinprodType(repAssetIndexDto.getBusiType());
//				FinProduct finProductResult = repAssetIndexTaskMapper.selectFinProduct(finProduct);
//				if (finProductResult != null) {
////					// 资产指标表.业务明细简称 = 标的类金融产品.金融产品简称
////					repAssetIndexDto.setBusiAbbr(finProductResult.getFinprodAbbr());
////					// 资产指标表.业务明细名称 = 标的类金融产品.金融产品名称
////					repAssetIndexDto.setBusiName(finProductResult.getFinprodName());
//					// 资产指标表.产品代码 = 标的类金融产品.市场代码
////					repAssetIndexDto.setProdId(finProductResult.getFinprodMarketId());
//				}
////				else{
////					// 资产指标表.产品代码 = 标的类金融产品
////					repAssetIndexDto.setProdId(acctEntity.getProdId());
////				}

				// 取金融产品分类数据
//				FinProductType finProductType = new FinProductType();
//				// 金融产品代码 = 资产指标表.业务明细代码 and 金融产品类型 = 资产指标表.业务明细类型
//				finProductType.setFinprodId(repAssetIndexDto.getBusiId());
////				finProductType.setFinprodType(repAssetIndexDto.getBusiType());
//
//				FinProductType finProductTypeResult = repAssetIndexTaskMapper.selectFinProductType(finProductType);

				// 与设计确认过，只有【业务类型】为【债券】才塞入值
//				if (finProductTypeResult != null
//						&& DictCodeListExt1.金融产品类型_债券.getValue().equals(repAssetIndexDto.getBusiType())) {
//					// 资产指标表.资产细分类 = 金融产品分类表.分类1
//					repAssetIndexDto.setBondType(finProductTypeResult.getType1());
//					// 资产指标表.资产细分类(汉字) - 此处对应枚举类为债券类型
//					repAssetIndexDto.setBondTypeZ(SysDataUtil.getNameFromDataDict(DictCode.债券类型, finProductTypeResult.getType1()));
//					
//				}

				// 资产指标表.投资目的 = 账务明细.投资目的
//				repAssetIndexDto.setInvAim(bokDto.getInvAim());
				// 资产指标表.当前持仓 = 账务明细.当前持仓
				repAssetIndexDto.setPosAmt(bokDto.getPosAmt());
				// 资产指标表.计提利率 = 账务明细.计提利率
//				repAssetIndexDto.setIntRate(bokDto.getIntRate());
//				// 资产指标表.日摊销收益率 = 账务明细.日摊销收益率
//				repAssetIndexDto.setDailydscYield(bokDto.getDailydscYield());
//				// 资产指标表.当日发生应计利息 = 账务明细.当日发生应计利息
//				repAssetIndexDto.setTdyIntincexpAdd(bokDto.getTdyIntincexpAdd());
				// 资产指标表.当日累计应计利息余额 = 账务明细.当日累计应计利息余额
				repAssetIndexDto.setTdyIntincexp(bokDto.getTdyIntincexp());
				// 资产指标表.起息日 = 账务明细.起息日
//				repAssetIndexDto.setVdate(bokDto.getValDate());
				// 资产指标表.到期日 = 账务明细.到期日
				repAssetIndexDto.setMdate(bokDto.getMdate());
				// 资产指标表.当日份额 = 账务明细.当日份额
				repAssetIndexDto.setTdyPosition(bokDto.getTdyPosition());
				// 资产指标表.当日成本 = 账务明细.剩余期限用成本
//				if (DictCodeListExt1.金融产品类型_债券.getValue().equals(repAssetIndexDto.getBusiType())) {
//					
//				}
//				else {
//					repAssetIndexDto.setTdyCostAmt(bokDto.getTdyCostAmt());
//				}
				repAssetIndexDto.setTdyCostAmt	(bokDto.getTdyCostAmt());				
				// 资产指标表.当日公允价值变动 = 账务明细.当日公允价值变动
				repAssetIndexDto.setTdyFloatIngpl(bokDto.getTdyFloatIngpl());
				// 资产指标表.剩余期限 = 账务明细.剩余期限
				repAssetIndexDto.setEndDays1(bokDto.getEndDays1());
				// 资产指标表.剩余存续期限 = 账务明细.剩余存续期限
				repAssetIndexDto.setEndDays2(bokDto.getEndDays2());
				// 资产指标表.剩余期限用成本 = 账务明细.剩余期限用成本
//				repAssetIndexDto.setEndDaysCostAmt(bokDto.getEndDaysCostAmt());
				// 资产指标表.资产负债标识
				String busiTypeNow = bokDto.getFinprodType();
				if (
						DictCodeListExt1.金融产品类型_固定管理费.getValue().equals(busiTypeNow)|| 
						DictCodeListExt1.金融产品类型_浮动管理费.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_托管费.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_销售费.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_税额.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_外包服务费.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_代销费.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_交易费用.getValue().equals(busiTypeNow)) {

					repAssetIndexDto.setAlFlag("02");
				} else if (
						DictCodeListExt1.金融产品类型_拆入.getValue().equals(busiTypeNow)||
						 DictCodeListExt1.金融产品类型_债券质押式正回购.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_债券买断式正回购.getValue().equals(busiTypeNow)
						|| DictCodeListExt1.金融产品类型_债券借贷融入.getValue().equals(busiTypeNow)) {

					repAssetIndexDto.setAlFlag("02");
				} else {

					repAssetIndexDto.setAlFlag("01");
				}

//				// 资产指标表.剩余存续期 = 账务明细.剩余存续期
//				if (bokDto.getEndDays2() != null) {
//					repAssetIndexDto.setSurplusTerm(bokDto.getEndDays2().intValue());
//				}

//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("booksetdate", acctEntity.getBookDate());
//				map.put("booksetID", acctEntity.getBooksetId());
//				map.put("busiId", repAssetIndexDto.getBusiId());
//				// 取估值增值
//				BigDecimal valueIncrement = repAssetIndexTaskMapper.getValueIncrement(map);
//				if (valueIncrement == null) {
//					// 偏离金额=0
//					repAssetIndexDto.setDeviationAmt(BigDecimal.ZERO);
//				} else {
//					// 偏离金额
//					repAssetIndexDto.setDeviationAmt(valueIncrement);
//				}

				listRepAssetIndexDto.add(repAssetIndexDto);
			}
		}
		// 查询其他应收款 清算款
		// 取统计分析明细(账务明细)
		BokBalance bokBalance = new BokBalance();
		// 账套代码=DTO.账套代码
		bokBalance.setBooksetId(acctEntity.getBooksetId());
		// 发生日=DTO.入账日
		bokBalance.setBalanceDate(acctEntity.getBookDate());
		List<BokBalance> resutBokBalance = repAssetIndexTaskMapper.selectBokBalance(bokBalance);

		// 非空执行
		if (resutBokBalance != null && !resutBokBalance.isEmpty()) {
			// 循环账务明细处理结果
			for (BokBalance bokDto : resutBokBalance) {
				// 初始化资产指标表信息
				RepAssetIndex repAssetIndexDto = new RepAssetIndex();

				// 资产指标表.流水号 <- 自动生成，规则为BOOK + "YYYYMMDD"+20位序列号
				repAssetIndexDto.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
				// 资产指标表.账套代码 = DTO.账套代码
				repAssetIndexDto.setBooksetId(acctEntity.getBooksetId());
				
				// 资产指标表.产品代码 = 产品基本信息产品代码
			    String prodId=repAssetIndexTaskMapper.getprodId(repAssetIndexDto.getBooksetId());
			    
				repAssetIndexDto.setProdId(null!=prodId?prodId:repAssetIndexDto.getBooksetId());

				// 资产指标表.估值日 = DTO.入账日
				repAssetIndexDto.setValueDate(acctEntity.getBookDate());

				// 资产指标表.业务明细代码 = 账务明细.业务明细代码
				repAssetIndexDto.setBusiId(bokDto.getSubjectNo());
				// 资产指标表.业务明细类型 = 账务明细.金融产品类型
//				repAssetIndexDto.setBusiType(DictCodeListExt1.金融产品类型_其他.getValue());
				
				repAssetIndexDto.setTdyCostAmt(bokDto.getoAmt());
				repAssetIndexDto.setAlFlag("01");
				
				repAssetIndexDto.setEndDays1(BigDecimal.ONE);
				
				listRepAssetIndexDto.add(repAssetIndexDto);
			}
		}
		result.setListRepAssetIndex(listRepAssetIndexDto);

		return result;
	}

}
