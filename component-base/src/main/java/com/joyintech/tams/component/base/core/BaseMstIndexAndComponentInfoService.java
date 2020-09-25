/*
 * 文件名：BaseMstIndexAndComponentInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.core;
import java.util.List;
import com.joyintech.tams.common.base.entity.MstIndexComponent;
import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.tams.component.base.core.dto.BaseMstIndexAndComponentInfoDto;
import com.joyintech.tams.component.base.core.dto.BaseMstIndexInfoDto;

/**
 * 基准信息组件接口类 
 * 功能详细描述〉 
 * 〈一句话功能简述〉 
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年8月31日
 * @see BaseMstIndexAndComponentInfoService
 * @since 1.0
 */
public interface BaseMstIndexAndComponentInfoService {

    /**
     * 新建基准信息 
     * <一句话功能简述>
     * <功能详细描述>
     * @param mstIndexInfos List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean newIndexInfo(List<MstIndexInfo> mstIndexInfos);

    /**
     * 新建基准信息(含基准成分) 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param dtos List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean newIndexInfoContainsElement(List<BaseMstIndexAndComponentInfoDto> dtos);

    /**
     * 查询基准信息 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param mstIndexInfo MstIndexInfo
     * @return List
     * @see
     * @since 1.0
     */
    List<MstIndexInfo> queryIndexInfo(MstIndexInfo mstIndexInfo);
    
    /**
     * 查询基准信息（含基准成分）
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseMstIndexInfoDto BaseMstIndexInfoDto
     * @return List
     * @see
     * @since 1.0
     */
    List<BaseMstIndexAndComponentInfoDto> queryIndexInfoContainsElement(BaseMstIndexInfoDto baseMstIndexInfoDto);

    /**
     * 删除基准信息 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param mstIndexInfo MstIndexInfo
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteIndexInfo(MstIndexInfo mstIndexInfo);
    
    /**
     * 删除基准信息（含基准成分） <一句话功能简述> <功能详细描述>
     * 
     * @param baseMstIndexInfoDto BaseMstIndexInfoDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteIndexInfoContainsElement(BaseMstIndexInfoDto baseMstIndexInfoDto);

    /**
     * 修改基准信息 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param mstIndexInfos List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean upadteIndexInfo(List<MstIndexInfo> mstIndexInfos);

    /**
     * 修改基准成分表 
     * <一句话功能简述> 
     * <功能详细描述>
     * @param mstIndexComponents List
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean upadteIndexComponent(List<MstIndexComponent> mstIndexComponents);
}
