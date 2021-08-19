/*
 * 文件名：LgdSpecialConfigMapper.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */
 
package com.joyintech.service.valuation.lossmodel.lgdspecialconfig.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.lossmodel.lgdspecialconfig.dto.LgdSpecialConfigDto;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import org.springframework.stereotype.Repository;


/**
 * 特殊资产lgd维护Mapper
 * 
 * @author yuzj
 * @version 1.0 2021年6月23日
 * @see LgdSpecialConfigMapper
 * @since 1.0
 */
@Repository(value="serviceLgdSpecialConfigMapper")
public interface LgdSpecialConfigMapper {

    List<LgdSpecialConfigDto> selectAll(Map<String, Object> pageParam);
    List<LgdSpecialConfigDto> selectSum(Map<String, Object> pageParam);

    LgdSpecialConfigDto selectByPrimaryKey(LgdSpecialConfigDto key);

    int deleteByPrimaryKey(String seqNo);

    int insert(LgdSpecialConfigDto dto);

    int  updateByPrimaryKey(LgdSpecialConfigDto dto);

    List<LgdSpecialConfigDto> selectExportList(LgdSpecialConfigDto dto);

    List<LgdSpecialConfigDto> selectByMap(Map<String, Object> pageParam);

    int getRepeatCheckList(LgdSpecialConfigDto dto);

    int updateRepProdIssue(RepProdIssue dto);


    List<LgdSpecialConfigDto> selectRepetList(LgdSpecialConfigDto dto);

    void updateRepetDto(LgdSpecialConfigDto dto);

    /**
     * @Description:查询资产名称根据系统减值参数lossType=01
     * @auther: yuzj
     * @date: 2021/8/14
     * @param:
     * @return:
     */
    List<SelectOptionDto> getFinprodIdByLossTypeOne(Map<String, Object> pageParam);

    /**
     * @Description:查询资产名称根据系统减值参数lossType=02
     * @auther: yuzj
     * @date: 2021/8/14
     * @param:
     * @return:
     */
    List<SelectOptionDto> getFinprodIdByLossTypeTwo(Map<String, Object> pageParam);



}
