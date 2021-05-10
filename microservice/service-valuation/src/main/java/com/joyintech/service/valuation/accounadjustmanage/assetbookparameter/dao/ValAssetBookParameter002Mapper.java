/*
 * 文件名：ValAssetBookParameter002Mapper.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;

@Repository(value="serviceValAssetBookParameter002Mapper")
public interface ValAssetBookParameter002Mapper {

	
    /**
     * 通过参数名，获取参数值
     * @param pageParam
     * @return
     */
    ValAssetBookParameter002Dto getParamValue(Map<String, Object> pageParam);
	
    /**
     * 查询条件获取——参数
     * @param parameters
     * @return
     */
    List<SelectOptionDto> selectParam(Map<String, Object> parameters);
    
    /**
     * 新建时获取参数
     * @param parameters
     * @return
     */
    List<SelectOptionDto> selectParamNew(Map<String, Object> parameters);
    
    /**
     * 新建时check
     * @param booksetId
     * @return
     */
    List<ValAssetBookParameter002Dto> checkfornew(@Param("booksetId") String booksetId);
    
    /**
     * 修改参数
     * @param record
     * @return
     */
    int updateByBooksetId(ValAssetBookParameter002Dto record);
    
    /**
     * 新建参数
     * @param record
     * @return
     */
    int insertparam(ValAssetBookParameter002Dto record);
    
    /**
     * 获取账套模板代码
     * @param booksetId
     * @return
     */
    String getTmpl(@Param("booksetId") String booksetId);
    
    /**
     * 获取参数
     * @param record
     * @return
     */
    ValAssetBookParameter002Dto booksetparam(ValAssetBookParameter002Dto record);
    
    /**
     * 获取选中记录
     * @param params
     * @return
     */
    ValAssetBookParameter002Dto getByKey(Map<String, Object> params);

    /**
     * 检查是否存在相同的对象
     */
    List<ValAssetBookParameter002Dto> checkExistSameDto(ValAssetBookParameter002Dto dto);
}
