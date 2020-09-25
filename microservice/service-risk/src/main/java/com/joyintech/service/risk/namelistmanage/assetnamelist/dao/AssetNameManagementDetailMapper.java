package com.joyintech.service.risk.namelistmanage.assetnamelist.dao;

/**
 * 名单管理--资产名单明细
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagementDetailMapper
 * @since 1.0
 */
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Repository
public interface AssetNameManagementDetailMapper {

    /**
     * 查询未处理的名单明细
     * @param param
     * @return
     */
    List<RskAccessListDetailDto> getUnhandleRskAccessListDetailDtoList(Map<String, Object> param);

    /**
     * 删除明细
     * @param param
     */
    void deleteRskAccessListDetail(Map<String, Object> param);

    /**
     * 删除履历
     * @param param
     */
    void deleteRskAccessListLog(Map<String, Object> param);

    /**
     * 有效数据
     * @param param
     * @return
     */
    List<RskAccessListDetailDto> selectAssetListDetailValid(Map<String, Object> param);

    /**
     * 流程中数据
     * @param param
     * @return
     */
    List<RskAccessListDetailDto> selectAssetListDetailInworkflow(Map<String, Object> param);

    /**
     * 双击查看履历更新
     * @param param
     * @return
     */
    List<RskAccessListLogDto> selectAssetListDetailLog(Map<String, Object> param);

    /**
     * 双击查看履历明细
     * @param param
     * @return
     */
    List<RskAccessListLogDto> selectNameListLogDetail(Map<String, Object> param);

    /**
     * 当日历史履历
     * @param param
     * @return
     */
    List<RskAccessListLogDto> selectDayHisAssetLog(Map<String, Object> param);

    /**
     *  get名单总条数
     * @param param
     * @return
     */
    int getNameDetailCount(Map<String, Object> param);

    void updateDetailUpdateUser(Map<String, Object> param);
     
    /**
     * 非一个体系的名单明细
     * @param param
     * @return
     */
    List<RskAccessListDetailDto> getOtherRskAccessListDetailDtoList(Map<String, Object> param);
}
