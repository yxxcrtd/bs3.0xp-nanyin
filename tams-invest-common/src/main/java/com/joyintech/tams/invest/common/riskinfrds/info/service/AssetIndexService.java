package com.joyintech.tams.invest.common.riskinfrds.info.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetIndex;

@Service
public class AssetIndexService extends RedisBaseService<IAssetIndex>{

	public IAssetIndex getAssetIndex(String market, String tradePlace){
		return getInfoFromRedis(RedisKeyEnum.资产代码索引.key(), market + GlobalConstant.COLON + tradePlace);
	}
}
