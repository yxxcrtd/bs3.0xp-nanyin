/*
 * 文件名：RepTaskDetail.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dao.RepTaskDeatilMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.taskDetail.dto.RepTaskDeatil;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月20日
 * @see RepTaskDetailService
 * @since 1.0
 */
@Service
public class RepTaskDetailService {
    
    @Autowired
    private RepTaskDeatilMapper repTaskDeatilMapper;
    
    
    public int insert(RepTaskDeatil record) {
        return repTaskDeatilMapper.insert(record);
    }

    /**
     * 查询传入日期中是否有持有信息
     * @param generateDate
     * @return
     */
    public int  RepTaskDeatil(String flag) {
        return repTaskDeatilMapper.RepTaskDeatil(flag);
    }
    
    
    /**
     * 通知系统重启
     * <一句话功能简述>
     * <功能详细描述>
     * @param repTaskDeatil
     * @return
     * @see
     * @since 1.0
     */
    public int update(String flag,String upflag) {
        return repTaskDeatilMapper.updateRepTaskDeatil(flag , upflag);
    }
    
}
