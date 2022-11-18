/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reveal.reportreveal.dao;

import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokFileInfoDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokFileTransfer003Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.BokNetValueCfmFileDto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportreview.dto.ReportReview001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 金融产品交易查询
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ReportRevealManageEX001Mapper
 * @since 1.0
 */
@Repository
public interface ReportRevealManageEX001Mapper {


    List<ReportConfirmManage001Dto> getRepRevealList(HashMap<String, Object> map);

    /**
     * 自动披露更新状态
     * @param infoDiscAutoRevealDto 更新内容
     * @return
     */
    int updateRevealStatusById(ReportConfirmManage001Dto infoDiscAutoRevealDto);

    int updateRevealStatusByIdEX(ReportConfirmManage001Dto dto);

    int backToTgReview(ReportReview001Dto dto);

    int backToReview(ReportReview001Dto dto);

    int getRevealListNum(HashMap<String, Object> map);
}