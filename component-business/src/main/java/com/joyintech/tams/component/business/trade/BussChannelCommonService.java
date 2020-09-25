package com.joyintech.tams.component.business.trade;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

import java.util.List;
import java.util.Map;

/**
 * 通道相关共通
 *
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.component.business.period.service
 * @Description: 金融产品交易共通
 * @date Date : 2019年03月26日 15:31
 */
public interface BussChannelCommonService {
    Page<SelectOptionDto> getChannelId(Map<String, Object> pageParam);
    Page<SelectOptionDto> getYcxhkChannelId(Map<String, Object> pageParam);
    List<SelectOptionDto> getChannelIdForAsset(Map<String, Object> pageParam);
	Page<SelectOptionDto> getChannelIdAndCollectType(Map<String, Object> pageParam);
	List<SelectOptionDto> getChannelIdAndCollectTypeForAsset(Map<String, Object> pageParam);
    Page<SelectOptionDto> getSecManageAcctId(Map<String, Object> pageParam);
}
