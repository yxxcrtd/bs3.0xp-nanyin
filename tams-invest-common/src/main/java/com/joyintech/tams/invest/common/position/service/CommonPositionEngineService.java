package com.joyintech.tams.invest.common.position.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductTypeKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.position.dao.InterfPositionConfigMapper;
import com.joyintech.tams.invest.common.position.dto.InterfPositionConfigDto;

@Service
public class CommonPositionEngineService {
	
	private static final Map<String, InterfPositionConfigDto> CONFIG_MAP = new ConcurrentHashMap<String, InterfPositionConfigDto>();

    /**
     * 获取mapper对象
     */
    @Autowired
    private InterfPositionConfigMapper interfPositionConfigMapper;
	@Autowired
	private FinProductAddMapper finProductAddMapper;
	@Autowired
	private FinProductTypeMapper finProductTypeMapper;
	
	/**
	 * 获取结算方式
	 * @param finprodId
	 * @return
	 */
	public String getDeliveryType(String finprodId) {
		String deliveryType = StringUtil.EMPTY;
		FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finprodId);
		String tradePlat = finProductAdd.getTradePlat();
		if(StringUtil.isNotEmpty(tradePlat) && tradePlat.contains(DictCodeList.交易平台_竞价撮合平台.getValue())) {
			deliveryType= DictCodeListExt3.结算方式_头寸引擎_净额结算.getValue();
		}else {
			deliveryType= DictCodeListExt3.结算方式_头寸引擎_RTGS.getValue();
		}
		return deliveryType;
	}
	
	/**
	 * 获取type6
	 * @param finprodId
	 * @return
	 */
	public String getType6(String finprodId) {
		FinProductTypeKey key = new FinProductTypeKey();
        key.setFinprodId(finprodId);
        key.setBranch((long)0);
		String type6 = finProductTypeMapper.selectByPrimaryKey(key).getType6();
		return type6;
	}
	
    /**
     * 查询 头寸接口配置
     * @return
     */
	public List<InterfPositionConfigDto> searchInterfPositionConfig(){
		return interfPositionConfigMapper.searchInterfPositionConfig();
	}
	
	/**
	 * 获取头寸接口类型
	 * @param positionType
	 * @param TradePlat
	 * @param FinprodType
	 * @param ps
	 * @param busiAct
	 * @param entity
	 * @return
	 */
	public String getPositionInterType(String positionType,String TradePlat,String FinprodType,String ps,String busiAct,String entity) {
		String key = positionType+TradePlat+FinprodType+ps+busiAct+entity;
		if(CONFIG_MAP.get(key)==null) {
			List<InterfPositionConfigDto> list = this.searchInterfPositionConfig();
			for(InterfPositionConfigDto dto:list) {
				String k = dto.getPositionType()+dto.getTradePlat()+dto.getFinprodType()+dto.getPs()+dto.getBusiAct()+dto.getEntity();
				CONFIG_MAP.put(k, dto);
			}
		}
		InterfPositionConfigDto configDto = CONFIG_MAP.get(key);
		if(configDto!=null) {
			return configDto.getInterfType();
		}
		return null;
	}
	
	/**
	 * 获取头寸业务类型--日间使用
	 * @param FinprodType
	 * @param ps
	 * @return
	 */
	public String getPositionBusiType(String FinprodType2,String ps,String assetType2) {
		String busiType = "";
		if(DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(FinprodType2)) {
			if (BussinessDictCodeList.交易方向_行权回售.getValue().equals(ps)) {
				busiType = DictCodeListExt3.头寸业务类型_债券行权交易.getValue();
	        } else if (BussinessDictCodeList.交易方向_买入.getValue().equals(ps) || BussinessDictCodeList.交易方向_卖出.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_现券交易.getValue();
	        } else if (DictCodeList.交易方向_申购.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_网上申购.getValue();
	        } else if (DictCodeListExt3.交易方向_配债.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_配债.getValue();
	        } else if (DictCodeListExt3.交易方向_债转股.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_债转股.getValue();
	        } else if (DictCodeListExt3.交易方向_质押券入库.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_通用回购债券入库.getValue();
	        } else if (DictCodeListExt3.交易方向_质押券出库.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_通用回购债券出库.getValue();
	        }
		}
		if(DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(FinprodType2)) {
			if (DictCodeList.交易方向_申购.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_网上申购.getValue();
	        } else if (BussinessDictCodeList.交易方向_买入.getValue().equals(ps) || BussinessDictCodeList.交易方向_卖出.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_股票交易.getValue();
	        } else if (DictCodeListExt2.交易方向_配股.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_配股.getValue();
	        }
		}
		if(DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(FinprodType2)) {
			busiType = DictCodeListExt3.头寸业务类型_基金交易.getValue();
		}
		if((DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(FinprodType2) || DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(FinprodType2))
				&& DictCodeList.回购类型_质押式竞价回购.getValue().equals(assetType2)) {
			busiType = DictCodeListExt3.头寸业务类型_通用回购首期.getValue();
		}
		if((DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue().equals(FinprodType2) || DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue().equals(FinprodType2))
				&& DictCodeList.回购类型_质押式协议回购.getValue().equals(assetType2)) {
			if (DictCodeListExt3.交易方向_首期.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_协议回购首期.getValue();
	        } else if (DictCodeListExt3.交易方向_提前购回.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_协议回购提前购回.getValue();
	        } else if (DictCodeListExt3.交易方向_到期购回.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_协议回购到期购回.getValue();
	        } else if (DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_协议回购到期续作.getValue();
	        } else if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_协议回购解除质押.getValue();
	        } else if (DictCodeListExt3.交易方向_中途换券.getValue().equals(ps)) {
	        	busiType = DictCodeListExt3.头寸业务类型_协议回购中途换券.getValue();
	        }
		}
		return busiType;
	}

}
