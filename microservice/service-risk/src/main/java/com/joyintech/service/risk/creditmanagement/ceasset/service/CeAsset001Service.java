package com.joyintech.service.risk.creditmanagement.ceasset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.risk.creditmanagement.ceasset.dao.CeAssetMapper;
import com.joyintech.service.risk.creditmanagement.ceasset.dto.CreditLinesAstDto;
import com.joyintech.tams.constant.DictCode;


/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAsset001Service
 * @since 1.0
 */
@Service("backCeAsset001Service")
public class CeAsset001Service extends BaseService {
	
	@Autowired
	private CeAssetMapper ceAssetMapper;
	
    /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<CreditLinesAstDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
        return super.queryForPage(page, length, () -> ceAssetMapper.selectForPage(param));
    }

    /**
     * 资产代码联想
      *
      * @param map 检索参数
      * @return SelectOptionDto
      */
    public Page<SelectOptionDto> getFinprodMarketIds(int page, int length, Map<String, Object> map) {
//        过滤带出的通道信息 add by wangpeng 20190826 start
    	List<? extends DataDictItem> dictlist =SysDataUtil.getDataDictItem(DictCode.平安资产类型.getValue());
    	List<String> PinAn_Asset_Type = new ArrayList<>();
    	if(!dictlist.isEmpty()){
    		for( DataDictItem d:dictlist){
    			PinAn_Asset_Type.add(d.getItemCode());
    		}
    	}
    	map.put("Asset", PinAn_Asset_Type);
//      过滤带出的通道信息 add by wangpeng 20190826 end
		return this.queryForPage(page, length,() -> ceAssetMapper.getFinprodMarketIds(map));
    }
    
}
