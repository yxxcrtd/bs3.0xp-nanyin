package com.joyintech.service.risk.creditmanagement.ceassetgroup.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.dao.CeAssetGroupMapper;
import com.joyintech.service.risk.creditmanagement.ceassetgroup.dto.CreditLinesAstGrpDto;


/**
 * 资产授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeAssetGroup001Service
 * @since 1.0
 */
@Service("backCeAssetGroup001Service")
public class CeAssetGroup001Service extends BaseService<CreditLinesAstGrpDto> {
	
	@Autowired
	private CeAssetGroupMapper ceAssetGroupMapper;
	
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
    public Page<CreditLinesAstGrpDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
        return super.queryForPage(page, length, () -> ceAssetGroupMapper.selectForPage(param));
    }

    /**
     * 资产代码联想
      *
      * @param map 检索参数
      * @return SelectOptionDto
      */
    public List<SelectOptionDto> getAssetGroupAliasIds(Map<String, Object> map) {
        return ceAssetGroupMapper.getAssetGroupAliasIds(map);
    }
    
}
