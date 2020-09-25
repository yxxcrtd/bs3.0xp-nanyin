package com.joyintech.tams.invest.common.riskinfrds.info.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IExcharge;

/**
 * 汇率行情信息Redis读写Service
 * 
 * @author zy_zhangrui
 *
 */
@Service
public class ExchargeInfoService extends RedisBaseService<IExcharge> {

	/**
	 * 取得 汇率行情信息
	 * 
	 * @param basisCcy 基础币种
	 * @param quotCcy 报价币种
	 * @param type 来源
	 * @return 汇率行情信息
	 */
	public IExcharge getExchargeInfo(String basisCcy, String quotCcy, String type){
		return this.getInfoFromRedis(RedisKeyEnum.汇率行情信息.key(), basisCcy + GlobalConstant.COLON + quotCcy + GlobalConstant.COLON + type);
	}

	/**
	 * 取得 汇率值
	 * 
	 * @param basisCcy 基础币种
	 * @param quotCcy 报价币种
	 * @param type 来源
	 * @return 汇率值
	 */
	public BigDecimal getExchargeVal(String basisCcy, String quotCcy, String type){
		IExcharge info = getExchargeInfo(basisCcy, quotCcy, type);
		if (info != null) {
			return info.getExchargeVal();
		}
		return BigDecimal.ONE;
	}

	/**
	 * 取得 外币兑人名币汇率行情信息
	 * 采用核心汇率
	 * 
	 * @param basisCcy 外币币种
	 * @return 汇率行情信息
	 */
	public IExcharge getDefaultExchargeInfo(String basisCcy){
		return this.getInfoFromRedis(RedisKeyEnum.汇率行情信息.key(), 
				basisCcy + GlobalConstant.COLON + BussinessDictCodeListRds.币种_人民币_CNY.getValue());
	}

	/**
	 * 取得 外币兑人名币汇率值
	 * 采用核心汇率
	 * 
	 * @param basisCcy 基础币种
	 * @return 汇率值
	 */
	public BigDecimal getDefaultExchargeVal(String basisCcy){
		IExcharge info = getDefaultExchargeInfo(basisCcy);
		if (info != null) {
			return info.getExchargeVal();
		}
		return BigDecimal.ONE;
	}
}
