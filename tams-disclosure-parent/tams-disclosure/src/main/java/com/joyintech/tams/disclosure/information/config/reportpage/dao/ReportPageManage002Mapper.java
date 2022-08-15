/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.dao;

import com.joyintech.tams.disclosure.infoCompliance.dto.UploadFileDto;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportStatusDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see ReportPageManage002Mapper
 * @since 1.0
 */
@Repository
public interface ReportPageManage002Mapper {

    void saveReportStatus(ReportStatusDto dto);

    void updateUplodFile(UploadFileDto file);

    List<ReportPageManage001Dto> getReportInfo();

    void deleteReportStatus(Map<String,String> map);

    ReportStatusDto selectReportInfo(Map<String,String> map);

    void updateReportStatus(ReportStatusDto dto);

    int getInfoStatusAfterSysDate(ReportStatusDto dto);

    int getInfoStatusInFlow(ReportStatusDto dto);

    int getInfoStatusBeforeEffdate(ReportStatusDto dto);
}
