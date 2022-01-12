package com.joyintech.tams.svreport.interf.chb.saleinst.dao;

import com.joyintech.tams.common.base.entity.ChbSalerIdenInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbSaleIderInterfMapper {

    

    /**
     * 功能:批量插入
     * @since
     * @version
     * @author：
     * @param  新增数据 insertList
     */
    int mergeInfoList(@Param("newDataList")List<ChbSalerIdenInfo> insertList);

}
