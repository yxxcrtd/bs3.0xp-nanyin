/*
 * 文件名：ModGradePdConfigMapper.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */
 
package com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dao;

import java.util.List;
import java.util.Map;

//import com.joyintech.service.model.impairment.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
//import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import org.springframework.stereotype.Repository;


/**
 * 评级PD表查询Mapper
 * 
 * @author yuzj
 * @version 1.0 2021年6月23日
 * @see ModGradePdConfigMapper
 * @since 1.0
 */
@Repository(value="serviceModGradePdConfigMapper")
public interface ModGradePdConfigMapper {

    List<ModGradePdConfigDto> selectAll(Map<String, Object> pageParam);
    List<ModGradePdConfigDto> selectSum(Map<String, Object> pageParam);

    ModGradePdConfigDto selectByPrimaryKey(ModGradePdConfigDto key);

    int deleteByPrimaryKey(String seqNo);

    int insert(ModGradePdConfigDto dto);

    int  updateByPrimaryKey(ModGradePdConfigDto dto);

    List<ModGradePdConfigDto> selectExportList(ModGradePdConfigDto dto);

    List<ModGradePdConfigDto> selectByMap(Map<String, Object> pageParam);

    int getRepeatCheckList(ModGradePdConfigDto dto);


    int updateRepProdIssue(RepProdIssue dto);


    List<ModGradePdConfigDto> selectRepetList(ModGradePdConfigDto dto);

    void updateRepetDto(ModGradePdConfigDto dto);

    int checkBySeqNoAndType(Map<String, Object> map);


}
