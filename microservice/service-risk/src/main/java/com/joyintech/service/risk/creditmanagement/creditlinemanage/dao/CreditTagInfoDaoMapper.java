package com.joyintech.service.risk.creditmanagement.creditlinemanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.tams.common.base.entity.CreditLines;

@Repository
public interface CreditTagInfoDaoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditTagInfoDto record);

    int insertSelective(CreditTagInfoDto record);

    int insertBasicByBasicInfo(CreditTagInfoDto record);

    CreditTagInfoDto selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditTagInfoDto record);

    int updateByPrimaryKey(CreditTagInfoDto record);

    List<CreditTagInfoDto> selectForPage(Map<String, Object> param);

   	List<SelectOptionDto> getLimitLabel(Map<String, Object> map);
   	
    List<CreditLines> getCreditLabel(Map<String, Object> pageParam);

	List<SelectOptionDto> getCreditTagInfoList(Map<String, Object> param);
	
    int checkTag(Map<String, Object> param);

    int checkCreditType(Map<String, Object> param);

    // add by yushilong 20200630 增加额度标签管理导入功能 start
    /**
     * 保存，已存在则更新
     *
     * @param insertList 需要保存的数据
     * @see
     * @since 1.0
     */
    int mergeInfoList(@Param("newDataList")List<CreditTagInfoDto> insertList);

    int checkTagCanDelete(CreditTagInfoDto dto);
    // add by yushilong 20200630 增加额度标签管理导入功能 end

}