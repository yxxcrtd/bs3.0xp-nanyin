package com.joyintech.service.basicdata.mstfeevarieties.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;

/**
 * 
 * @author 王泽慧
 *
 */
@Repository
public interface MstFeeVarietiesMapper {

    /**
     * 画面查询
     * 
     * @param parameters 画面查询条件
     * @return 查询到的数据
     */
    List<MstFeeVarietiesDto> selectForPage(Map<String, Object> parameters);

    /**
     * 费用名称的模糊查询
     * 
     * @param param 画面输入的费用名称
     * @return 結果集
     */
    List<SuggestDto> getFeeNameOption(Map<String, Object> param);

    /**
     * 根据费用代码查询数据
     * 
     * @param map 费用代码
     * @return 实体类结果
     */
    MstFeeVarietiesDto selectByFeeId(Map<String, Object> map);

    /**
     * 通过费用代码判断数据在现金流信息表中是否存在
     * 
     * @param feeId 页面输入的费用ID
     * @return 查询到的数目
     */
    Integer isUseByFeeId(String feeId);

    /**
     * 通过费用名称查询数据，验证费用名称的唯一性
     * <功能详细描述>
     * @param feeName
     * @return
     * @see
     * @since 1.0
     */
    List<MstFeeVarietiesDto> getDtoByFeeName(String feeName);
    
    
    /**
     * 通过费用名称查询数据，验证费用名称的唯一性
     * <功能详细描述>
     * @param feeName
     * @return
     * @see
     * @since 1.0
     */
    List<MstFeeVarietiesDto> getDtoByFeeNameInstitution(Map<String,Object> para);

}
