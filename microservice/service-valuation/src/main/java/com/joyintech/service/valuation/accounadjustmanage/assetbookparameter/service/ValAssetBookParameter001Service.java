/*
 * 文件名：ValAssetBookParameter001Service.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dao.ValAssetBookParameter001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dao.ValAssetBookParameter002Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.tams.constant.DictCodeList;

@Service(value="serviceValAssetBookParameter001Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssetBookParameter001Service extends CommonExportService<ValAssetBookParameter002Dto>{

    /**
     * ValAssetBookParameter001Mapper
     */
    @Autowired
    private ValAssetBookParameter001Mapper mapper;
    
    @Autowired
    private ValAssetBookParameter002Mapper valAssetBookParameter002Mapper;

    /**
     * 获取查询结果
     * 
     * @param name 账套名称
     * @return List<ValAssetBookParameter002Dto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ValAssetBookParameter002Dto> getAllAssetbookPage(int page,int length, String name, String paramCode) throws Exception {
        return super.queryForPage(page,length,()-> mapper.getBookSetExt(name, paramCode));
    }
    
    /**
     * 列表查询
     * @param params
     * @return
     */
    public List<ValAssetBookParameter002Dto> queryAllList(String name, String paramCode) {
        return mapper.getBookSetExt(name, paramCode);
    }

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBooksetIdNew(int page,int length,Map<String, Object> param) {
        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return super.queryForPageForSelectReturn(page,length,()->mapper.getBooksetIdForSelect(param));
    }
    
	/**
     * 新建check
     * @return
     * @throws Exception
     */
	public List<ValAssetBookParameter002Dto> checkfornew(String booksetId) throws Exception {
		List<ValAssetBookParameter002Dto>  resultList = new ArrayList<>();
			resultList = valAssetBookParameter002Mapper.checkfornew(booksetId);
		return resultList;
    }

}