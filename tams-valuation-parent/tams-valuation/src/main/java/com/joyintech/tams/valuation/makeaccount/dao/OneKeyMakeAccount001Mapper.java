/*
 * 文件名：OneKeyMakeAccount001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.makeaccount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.tams.valuation.makeaccount.dto.OneKeyMakeAccount001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2019年3月1日
 * @see OneKeyMakeAccount001Mapper
 * @since 1.0
 */
@Repository
public interface OneKeyMakeAccount001Mapper {

    /**
     * 检测主表中的数据是否能够执行一键做账
     * 
     * @return
     * @see
     * @since 1.0
     */
    int check();
    
    List<OneKeyMakeAccount001Dto> findloglogtotal(Map<String,Object> map);
    
    
    List<ValAccountHandle001Dto> findLogPage(Map<String,Object> map);

    List<OneKeyMakeAccount001Dto> checkAccountLog(Map<String,Object> map);
    
    List<String> findAllProdbyGroup(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);
    
}
