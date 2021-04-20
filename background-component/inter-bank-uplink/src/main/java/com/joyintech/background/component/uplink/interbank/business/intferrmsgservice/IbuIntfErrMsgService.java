/*
 * 文件名：IbuIntfErrMsgService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.intferrmsgservice;

import java.util.List;

import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuInrfErrMsgSearchDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuIntfErrMsgDto;

/**
 * 银行间上行错误码接口
 * 
 * @author zhengjunping
 * @version 1.0 2020年5月18日
 * @see IbuIntfErrMsgService
 * @since 1.0
 */
public interface IbuIntfErrMsgService {

    /**
     * 根据错误码和错误信息分页查询<br />
     * 
     * @param page int
     * @param length int
     * @param ibuInrfErrMsgSearchDto IbuInrfErrMsgSearchDto
     * @return Page<IbuIntfErrMsgDto>
     */
    Page<IbuIntfErrMsgDto> selectForPage(int pageNum,int pageSize,IbuInrfErrMsgSearchDto ibuInrfErrMsgSearchDto);
    
    /**
     * 根据错误码和错误信息模糊查询<br />
     * 
     * @param ibuInrfErrMsgSearchDto IbuInrfErrMsgSearchDto
     * @return List<IbuIntfErrMsgDto>
     */
    List<IbuIntfErrMsgDto> selectIbuIntfErrMsgList(IbuInrfErrMsgSearchDto ibuInrfErrMsgSearchDto);
    
    /**
     * 根据错误码和错误信息查询<br />
     * 
     * @param errCode String 错误码
     * @return IbuIntfErrMsgDto
     */
    IbuIntfErrMsgDto selectByErrCode(String errCode);
}
