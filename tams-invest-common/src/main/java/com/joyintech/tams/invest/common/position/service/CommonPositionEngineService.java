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
import com.joyintech.tams.constant.DictCodeListExt3;
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

}
